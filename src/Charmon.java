import java.util.ArrayList;
import java.util.List;

public class Charmon {
    private String name;
    private Type type;
    private int health;
    private final int hp;
    private int attack;
    private int defense;
    private List<Move> moves;
    private String image;

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
        return image + "-front.gif";
    }

    public String getBackImage() {
        return image + "-back.gif";
    }

    public void attack(Charmon target, Move move) {
        int damage = Battle.calculateDamage(this, move, target);
        target.takeDamage(damage);
        System.out.println(
                this.name + " used " + move.getName() + " on " + target.getName() + " for " + damage + " damage!");
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
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