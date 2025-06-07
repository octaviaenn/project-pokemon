import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.swing.JPanel;

public class Charmon {
    private String name;
    private Type type;
    private int health;
    private final int hp;
    private int attack;
    private int defense;
    private List<Move> moves;
    private String image;
    private JPanel text;
    private CardLayout cardText;

    public Charmon(String name, Type type, int health, int attack, int defense, String image) {
        this.name = name;
        this.type = type;
        this.health = health;
        hp = health;
        this.attack = attack;
        this.defense = defense;
        this.moves = new ArrayList<>();
        this.image = image;
    }

    // ***Getters and Setters (Crucial!)***
    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public int getHP() {
        return hp;
    }

    // public setText(JPanel text){
    // this.text =
    // }

    public void setHealth(int health) { // Setter for health (needed for healing)
        this.health += health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack += attack;
    }

    public int getDefense() {
        return defense;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void addMove(Move move) {
        moves.add(move);
    }

    public String getImage() {
        return image + "-f.gif";
    }

    public String getBackImage() {
        return image + "-b.gif";
    }

    public void attack(Charmon target, Move move) {
        int damage = Battle.calculateDamage(this, move, target);
        target.takeDamage(damage);
        text = BattleScreen.getText();
        cardText = BattleScreen.getCardText();
        JPanel textMessage = BattleScreen.textOption(this.name + " used " + move.getName() + " on " + target.getName());
        text.add(textMessage, "Message");
        cardText.show(text, "Message");
        System.out.println(
                this.name + " used " + move.getName() + " on " + target.getName() + " for " + damage + " damage!");
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            JPanel textMessage = BattleScreen.textOption(this.name + " fainted.");
            text.add(textMessage, "Message");
            cardText.show(text, "Message");
            System.out.println(this.name + " fainted!");
        }
    }

    public void heal(boolean flag) {
        // false buat opponent, true buat potion
        if (flag == false)
            health = hp;
        else
            health = (int) Math.max(hp, health * 1.5);
    }

    public boolean isFainted() {
        return health <= 0;
    }
}