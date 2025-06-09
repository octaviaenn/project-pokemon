import java.lang.Character;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private int level;
    private int coin;
    private int potion;
    private Charmon currentChar;
    private List<Charmon> availChar;
    private int victoryCount = 0;

    public User(String name) {
        this.name = name;
        level = 1;
        coin = 1000;
        potion = 0;
        this.availChar = new ArrayList<>();
    }

    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getCoin() { return coin; }
    public int getPotion() { return potion; }
    public Charmon getCurrentChar() { return currentChar; }

    public void setCurrentChar(String name) {
        for (Charmon i : availChar) {
            if (i.getName().equalsIgnoreCase(name)) {
                currentChar = i;
                return;
            }
        }
    }
    public void changeChara(String name) { setCurrentChar(name); }
    public void addLevel() {
        level++; coin += 1000;
        for (Charmon c : availChar) { c.setHealth(c.getHealth() + 20); c.setAttack(c.getAttack() + 10);
            if(c!=null){
                c.setHealth((c.getHealth() + 20));
                c.setAttack((c.getAttack())+ 10);
            }
        } // Tingkatkan berdasarkan nilai asli
    }
    public void upgradeHealth() { for (Charmon c : availChar) { c.setHealth(c.getHealth() + 20); } }
    public void upgradeDamage() { for (Charmon c : availChar) { c.setAttack(c.getAttack() + 10); } }
    public void addPotion(int sum) { potion += sum; }
    public void win(Charmon enemy) { victoryCount++; }
    public int getVictoryCount() { return victoryCount; }
    public boolean expend(int amount) {
        if (coin >= amount) { coin -= amount; return true; }
        return false;
    }
}