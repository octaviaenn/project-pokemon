import javax.swing.*; // Needed for SwingUtilities.invokeLater
import java.util.ArrayList; // Assuming Charmon and Move are in the same package or imported
// Import Charmon.Type and Charmon.Move if they are inner classes as shown above
// import static Charmon.Type.*; // Static import for easier type access

public class Battle {
    private Charmon player;
    private Charmon enemy;

    // Callbacks to update GUI elements
    private Runnable onPlayerHpChange;
    private Runnable onEnemyHpChange;
    private Runnable onTurnEnd; // To signal BattleScreen to move to next turn/check status
    private Runnable onBattleEnd; // To signal BattleScreen battle is over

    private boolean isPlayerTurn = true; // To manage whose turn it is

    public Battle(Charmon player, Runnable onPlayerHpChange, Runnable onEnemyHpChange, Runnable onTurnEnd,
            Runnable onBattleEnd) {
        this.player = player;
        this.enemy = Character.findRandom(player); // Assuming Character.findRandom exists

        this.onPlayerHpChange = onPlayerHpChange;
        this.onEnemyHpChange = onEnemyHpChange;
        this.onTurnEnd = onTurnEnd;
        this.onBattleEnd = onBattleEnd;

        System.out.println("A battle begins between " + player.getName() + " and " + enemy.getName() + "!");
    }

    // Method to initiate a player's turn from the GUI
    public void playerTurn(int moveIndex) {
        if (!isPlayerTurn || player.isFainted() || enemy.isFainted()) {
            return; // Not player's turn or battle is over
        }

        turn(player, enemy, moveIndex);
        onEnemyHpChange.run(); // Notify GUI to update enemy HP bar

        if (enemy.isFainted()) {
            System.out.println(enemy.getName() + " fainted!");
            endBattle();
        } else {
            isPlayerTurn = false; // Switch to enemy's turn
            onTurnEnd.run(); // Signal GUI to handle next turn (e.g., enemy AI)
        }
    }

    // Method to initiate an enemy's turn (called by GUI after player's turn)
    public void enemyTurn() {
        if (isPlayerTurn || player.isFainted() || enemy.isFainted()) {
            return; // Not enemy's turn or battle is over
        }

        // Enemy AI: for simplicity, always use the first move
        turn(enemy, player, 0);
        onPlayerHpChange.run(); // Notify GUI to update player HP bar

        if (player.isFainted()) {
            System.out.println(player.getName() + " fainted!");
            endBattle();
        } else {
            isPlayerTurn = true; // Switch back to player's turn
            onTurnEnd.run(); // Signal GUI to re-enable player actions
        }
    }

    private void turn(Charmon attacker, Charmon defender, int indexAttack) {
        // (Simplified) Choose a move
        Move chosenMove = attacker.getMoves().get(indexAttack);
        attacker.attack(defender, chosenMove); // Charmon.attack now calls defender.takeDamage
    }

    private void endBattle() {
        if (player.isFainted()) {
            System.out.println("You lose!");
        } else {
            System.out.println(player.getName() + " wins!");
        }
        // Heal both Charmons after battle for next engagement
        player.heal(false); // Heal player to full
        enemy.heal(false); // Heal enemy to full

        // Notify GUI that battle is over
        onBattleEnd.run();
    }

    // Make calculateDamage public static if it's not already
    public static int calculateDamage(Charmon attacker, Move move, Charmon defender) {
        double effectiveness = getTypeEffectiveness(move.getType(), defender.getType());
        int damage = (int) ((move.getPower() * attacker.getAttack() / defender.getDefense()) * effectiveness);
        return Math.max(1, damage); // Ensure min 1 damage
    }

    // Type chart and effectiveness calculation (same as yours)
    private static double[][] typeChart = {
            // F   W   G   N  E
            /* F */ { 1.0, 0.5, 2.0, 1.0, 1.0 },
            /* W */ { 2.0, 1.0, 0.5, 1.0, 1.0 },
            /* G */ { 0.5, 2.0, 1.0, 1.0, 1.0 },
            /* N */ { 1.0, 1.0, 1.0, 1.0, 1.0 },
            /* E */ { 1.0, 1.0, 1.0, 1.0, 1.0 },
    };

    private static double getTypeEffectiveness(Type attackType, Type defendType) {
        int attackIndex = attackType.ordinal();
        int defendIndex = defendType.ordinal();

        if (attackIndex < typeChart.length && defendIndex < typeChart[attackIndex].length) {
            return typeChart[attackIndex][defendIndex];
        } else {
            System.out.println("Type effectiveness data not found for " + attackType + " attacking " + defendType
                    + ". Returning 1.0.");
            return 1.0;
        }
    }

    // Getters for Charmons
    public Charmon getPlayerCharmon() {
        return player;
    }

    public Charmon getEnemyCharmon() {
        return enemy;
    }
}