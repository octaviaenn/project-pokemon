public class Battle {
    private Charmon player;
    private Charmon enemy;

    public Battle(Charmon player) {
        this.player = player;
        enemy = Character.findRandom(player);
    }

    public void start() {
        System.out.println("A battle begins between " + player.getName() + " and " + enemy.getName() + "!");

        while (!player.isFainted() && !enemy.isFainted()) {
            turn(player, enemy, 0); // Charmon 1's turn
            if (enemy.isFainted()) break; // Check if Charmon 2 fainted
            turn(enemy, player, 0); // Charmon 2's turn
            if (player.isFainted()) break; // Check if Charmon 1 fainted
        }
    // kalo udah selese, nanti dipenuhin lagi healthnya
    enemy.heal(false);
        player.heal(false);

        if (player.isFainted()) {
            System.out.println("You lose");
        } else {
            System.out.println(player.getName() + " wins!");
        }
    }

    private void turn(Charmon attacker, Charmon defender, int indexAttack) {
        // (Simplified) Choose a move (you might want to let the user choose)
        Move chosenMove = attacker.getMoves().get(indexAttack);

        attacker.attack(defender, chosenMove);
    }

    public static int calculateDamage(Charmon attacker, Move move, Charmon defender) {
        double effectiveness = getTypeEffectiveness(move.getType(), defender.getType()); // Type effectiveness
        int damage = (int) ((move.getPower() * attacker.getAttack() / defender.getDefense()) * effectiveness);
        return Math.max(0, damage); // Ensure damage is not negative
    }

    private static double[][] typeChart = {
            //                      FIRE   WATER  GRASS  NORMAL ELECTRIC ... (Add more types)
            /* FIRE */         {  1.0,   0.5,   2.0,   1.0,   1.0,  /* ... */ },
            /* WATER */        {  2.0,   1.0,   0.5,   1.0,   1.0,  /* ... */ },
            /* GRASS */        {  0.5,   2.0,   1.0,   1.0,   1.0,  /* ... */ },
            /* NORMAL */       {  1.0,   1.0,   1.0,   1.0,   1.0,  /* ... */ },
            /* ELECTRIC */     {  1.0,   1.0,   1.0,   1.0,   1.0,  /* ... */ },
            // ... Add more rows for other types
    };

    private static double getTypeEffectiveness(Type attackType, Type defendType) {
        int attackIndex = attackType.ordinal(); // Get the index of the attack type in the enum
        int defendIndex = defendType.ordinal(); // Get the index of the defense type in the enum

        // Make sure your typeChart is large enough
        if (attackIndex < typeChart.length && defendIndex < typeChart[attackIndex].length) {
            return typeChart[attackIndex][defendIndex];
        } else {
            System.out.println("Type effectiveness data not found for " + attackType + " attacking " + defendType + ". Returning 1.0 (normal effectiveness).");
            return 1.0; // Default to normal effectiveness if data is missing
        }

    }
}