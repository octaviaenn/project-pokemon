import java.util.ArrayList;
import java.util.List;

public class Character {

    private static List<Charmon> chara = new ArrayList<>();

    public static void define() {
        Charmon pikachu = new Charmon("Pikachu", Type.ELECTRIC, 35, 55, 40, "assets\\charmon\\pikachu");
        pikachu.addMove(new Move("Quick Attack", Type.NORMAL, 40));
        pikachu.addMove(new Move("Flame Wheel", Type.FIRE, 60));
        pikachu.addMove(new Move("Thunderbolt", Type.ELECTRIC, 90));
        chara.add(pikachu);

        Charmon bulbasaur = new Charmon("Bulbasaur", Type.GRASS, 45, 49, 49, "assets\\charmon\\bulbasaur");
        bulbasaur.addMove(new Move("Tackle", Type.NORMAL, 40));
        bulbasaur.addMove(new Move("Water Gun", Type.WATER, 40));
        bulbasaur.addMove(new Move("Seed Bomb", Type.GRASS, 80));
        chara.add(bulbasaur);

        Charmon squirtle = new Charmon("Squirtle", Type.WATER, 44, 48, 65, "assets\\charmon\\squirtle");
        squirtle.addMove(new Move("Tackle", Type.NORMAL, 40));
        squirtle.addMove(new Move("Thunder Shock", Type.ELECTRIC, 40));
        squirtle.addMove(new Move("Bubble Beam", Type.WATER, 65));
        chara.add(squirtle);

        Charmon charmander = new Charmon("Charmander", Type.FIRE, 39, 52, 43, "assets\\charmon\\charmander");
        charmander.addMove(new Move("Scratch", Type.NORMAL, 40));
        charmander.addMove(new Move("Water Gun", Type.WATER, 40));
        charmander.addMove(new Move("Flame Burst", Type.FIRE, 70));
        chara.add(charmander);

        Charmon hoothoot = new Charmon("Hoothoot", Type.NORMAL, 60, 30, 30, "assets\\charmon\\hoothoot");
        hoothoot.addMove(new Move("Tackle", Type.NORMAL, 40));
        hoothoot.addMove(new Move("Thunder Shock", Type.ELECTRIC, 40));
        hoothoot.addMove(new Move("Echoed Voice", Type.NORMAL, 70));
        chara.add(hoothoot);

        Charmon pidgey = new Charmon("Pidgey", Type.NORMAL, 40, 45, 40, "assets\\charmon\\pidgey");
        pidgey.addMove(new Move("Tackle", Type.NORMAL, 40));
        pidgey.addMove(new Move("Flame Wheel", Type.FIRE, 60));
        pidgey.addMove(new Move("Quick Attack", Type.NORMAL, 40));
        chara.add(pidgey);

        Charmon jigglypuff = new Charmon("Jigglypuff", Type.NORMAL, 115, 45, 20, "assets\\charmon\\jigglypuff");
        jigglypuff.addMove(new Move("Pound", Type.NORMAL, 40));
        jigglypuff.addMove(new Move("Seed Bomb", Type.GRASS, 80));
        jigglypuff.addMove(new Move("Body Slam", Type.NORMAL, 70));
        chara.add(jigglypuff);

        Charmon meowth = new Charmon("Meowth", Type.NORMAL, 40, 45, 35, "assets\\charmon\\meowth");
        meowth.addMove(new Move("Scratch", Type.NORMAL, 40));
        meowth.addMove(new Move("Thunderbolt", Type.ELECTRIC, 90));
        meowth.addMove(new Move("Slash", Type.NORMAL, 70));
        chara.add(meowth);

        Charmon growlithe = new Charmon("Growlithe", Type.FIRE, 55, 70, 45, "assets\\charmon\\growlithe");
        growlithe.addMove(new Move("Bite", Type.NORMAL, 60));
        growlithe.addMove(new Move("Vine Whip", Type.GRASS, 45));
        growlithe.addMove(new Move("Flame Wheel", Type.FIRE, 60));
        chara.add(growlithe);

        Charmon eevee = new Charmon("Eevee", Type.NORMAL, 55, 55, 50, "assets\\charmon\\eevee");
        eevee.addMove(new Move("Quick Attack", Type.NORMAL, 40));
        eevee.addMove(new Move("Water Gun", Type.WATER, 40));
        eevee.addMove(new Move("Take Down", Type.NORMAL, 90));
        chara.add(eevee);

        Charmon flareon = new Charmon("Flareon", Type.FIRE, 65, 130, 60, "assets\\charmon\\flareon");
        flareon.addMove(new Move("Tackle", Type.NORMAL, 40));
        flareon.addMove(new Move("Thunderbolt", Type.ELECTRIC, 90));
        flareon.addMove(new Move("Flamethrower", Type.FIRE, 90));
        chara.add(flareon);

        Charmon nidoran = new Charmon("Nidoran", Type.NORMAL, 55, 47, 52, "assets\\charmon\\nidoran");
        nidoran.addMove(new Move("Scratch", Type.NORMAL, 40));
        nidoran.addMove(new Move("Vine Whip", Type.GRASS, 45));
        nidoran.addMove(new Move("Body Slam", Type.NORMAL, 70));
        chara.add(nidoran);

        Charmon clefairy = new Charmon("Clefairy", Type.NORMAL, 70, 45, 48, "assets\\charmon\\clefairy");
        clefairy.addMove(new Move("Pound", Type.NORMAL, 40));
        clefairy.addMove(new Move("Water Gun", Type.WATER, 40));
        clefairy.addMove(new Move("Headbutt", Type.NORMAL, 70));
        chara.add(clefairy);

        Charmon abra = new Charmon("Abra", Type.NORMAL, 25, 20, 15, "assets\\charmon\\abra");
        abra.addMove(new Move("Tackle", Type.NORMAL, 40));
        abra.addMove(new Move("Thunder Shock", Type.ELECTRIC, 40));
        abra.addMove(new Move("Headbutt", Type.NORMAL, 70));
        chara.add(abra);

        Charmon sandshrew = new Charmon("Sandshrew", Type.NORMAL, 50, 75, 85, "assets\\charmon\\sandshrew");
        sandshrew.addMove(new Move("Scratch", Type.NORMAL, 40));
        sandshrew.addMove(new Move("Flame Burst", Type.FIRE, 70));
        sandshrew.addMove(new Move("Body Slam", Type.NORMAL, 70));
        chara.add(sandshrew);

    }

    public static Charmon findRandom(Charmon player) {
        Charmon enemy;
        do {
            int index = (int) Math.round(Math.random() * 15);
            if (index == 15)
                index--;
            enemy = chara.get(index);
        } while (enemy == player);
        return enemy;
    }

    public static List<Charmon> getChara() {
        return chara;
    }

}
