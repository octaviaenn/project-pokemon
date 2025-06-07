import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private int level;
    private int coin;
    private int potion;
    private Charmon currentChar;
    // private Charmon[] chara = new Charmon[15];
    private List<Charmon> availChar;
    // private int charaCount = 0;
    private int victoryCount = 0;
    // private int diffHealth = 0;

    public User(String name) {
        this.name = name;
        level = 1;
        coin = 1000;
        potion = 0;
        availChar = Character.getChara();
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getCoin() {
        return coin;
    }

    public int getPotion() {
        return potion;
    }

    public Charmon getCurrentChar() {
        return currentChar;
    }

    // public int getDiffHealth() {
    // return diffHealth;
    // }

    public void setCurrentChar(String name) {
        for (Charmon i : availChar) {
            if (i.getName().equalsIgnoreCase(name)) {
                currentChar = i;
                return;
            }
        }
    }

    public void changeChara(String name) {
        for (Charmon c : availChar) {
            if (c.getName().equalsIgnoreCase(name)) {
                currentChar = c;
                return;
            }
        }
    }

    public void addLevel() {
        level++;
        coin += 1000;
        for (Charmon c : availChar) {
            c.setHealth(20);
            c.setAttack(10);
        }
    }

    public void upgradeHealth() {
        // diffHealth += 20;
        for (Charmon c : availChar) {
            c.setHealth(20);
        }
    }

    public void upgradeDamage() {
        for (Charmon c : availChar) {
            c.setAttack(10);
        }
    }

    public void addPotion(int sum) {
        potion += sum;
    }

    public void win(Charmon enemy) {
        // addChara(enemy);
        victoryCount++;
    }

    public int getVictoryCount() {
        return victoryCount;
    }

    public boolean expend(int amount) {
        if (coin >= amount) {
            coin -= amount;
            return true;
        }
        return false;
    }
}