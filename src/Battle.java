import javax.swing.*; // Needed for SwingUtilities.invokeLater
import java.awt.*;
//import javafx.scene.text.Font;

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
    private User user;
    private boolean isPlayerTurn = true; // To manage whose turn it is

    public Battle(User user, Charmon player, Runnable onPlayerHpChange, Runnable onEnemyHpChange, Runnable onTurnEnd,
            Runnable onBattleEnd) {
        this.user = user;
        this.player = player;
        this.enemy = Character.findRandom(player); // Assuming Character.findRandom exists

        this.onPlayerHpChange = onPlayerHpChange;
        this.onEnemyHpChange = onEnemyHpChange;
        this.onTurnEnd = onTurnEnd;
        this.onBattleEnd = onBattleEnd;

        System.out.println("A battle begins between " + player.getName() + " and " + enemy.getName() + "!");
    }

    public void changeEnemy() {
        enemy = Character.findRandom(player);
        onEnemyHpChange.run();
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

        int random = (int) Math.round(Math.random() * 3);
        if (random == 3)
            random--;
        turn(enemy, player, random);
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
        switch (chosenMove.getType()) {
            case FIRE:
                Sound.play("assets\\sound\\fire.wav");
                break;
            case WATER:
                Sound.play("assets\\sound\\water.wav");
                break;
            case GRASS:
                Sound.play("assets\\sound\\grass.wav");
                break;
            case NORMAL:
                Sound.play("assets\\sound\\normal.wav");
                break;
            case ELECTRIC:
                Sound.play("assets\\sound\\electric.wav");
                break;
        }
        attacker.attack(defender, chosenMove); // Charmon.attack now calls defender.takeDamage
    }

    private void endBattle() {
        JPanel home = Onboard.getHome();
        CardLayout mainCard = Onboard.getCard();
        CustomDialog dialogAttack = BattleScreen.getDialogAttack();
        dialogAttack.setVisible(false);
        if (player.isFainted()) {
            System.out.println("You lose!");
            CustomDialog dialogLose = new CustomDialog("assets\\popup-result.png", 400, 60, 0);
            JLabel textLose = new JLabel("YOU LOSE..");
            // textLose.setFont("Verdana");
            textLose.setBounds(100, 150, 200, 100);
            dialogLose.addLabel(textLose);
            Sound.play("assets\\sound\\lose.wav");
            dialogLose.setVisible(true);
            mainCard.show(home, "Onboard");
            // nanti tambahin bgm lose, popup-result, terus data kereset/keapus aja,
            // langsung quit
        } else {
            System.out.println(player.getName() + " wins!");
            CustomDialog dialogWin = new CustomDialog("assets\\popup-result.png", 400, 60, 0);
            JLabel textWin = new JLabel("YOU WIN!!");
            // textWin.setFont("Verdana");
            textWin.setBounds(100, 150, 200, 100);
            dialogWin.addLabel(textWin);
            Sound.play("assets\\sound\\win.wav");
            dialogWin.setVisible(true);
            // tambahin bgm win, popup-result win
            user.win(enemy);
            // kalo jumlahnya sesuai itu yaudah popup-result naik level suara dll
            String reach = "You have reached level ";
            String levelText = "";
            switch (user.getVictoryCount()) {
                case 1:
                    levelText = reach + "2";
                    break;
                case 3:
                    levelText = reach + "3";
                    break;
                case 6:
                    levelText = reach + "4";
                    break;
                case 10:
                    levelText = reach + "5";
                    break;
                case 15:
                    levelText = reach + "6";
                    break;
                case 21:
                    levelText = reach + "7";
                    break;
                case 28:
                    levelText = reach + "8";
                    break;
                case 36:
                    levelText = reach + "9";
                    break;
                case 45:
                    levelText = "You have reached end level!";
                    break;
            }
            user.addLevel();
            user.upgradeDamage();
            user.upgradeHealth();
            JLabel levelUp = new JLabel(levelText);
            CustomDialog dialogLevel = new CustomDialog("assets\\popup-result.png", 400, 60, 0);
            // textLevel.setFont("Verdana");
            levelUp.setBounds(100, 150, 200, 100);
            dialogLevel.addLabel(levelUp);
            Sound.play("assets\\sound\\level-up.wav");
            dialogLevel.setVisible(true);
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
        int damage = (int) ((move.getPower() * attacker.getAttack() / defender.getDefense()) * effectiveness / 7);
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

    public Runnable getPlayerHpChange() {
        return onPlayerHpChange;
    }

    // Getters for Charmons
    public Charmon getPlayer() {
        return player;
    }

    public Charmon getEnemy() {
        return enemy;
    }

    public boolean getIsPlayer() {
        return isPlayerTurn;
    }
}