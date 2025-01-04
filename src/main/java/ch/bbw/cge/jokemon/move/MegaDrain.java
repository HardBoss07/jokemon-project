package ch.bbw.cge.jokemon.move;

import ch.bbw.cge.jokemon.Jokemon;
import ch.bbw.cge.jokemon.damage.Damage;
import ch.bbw.cge.jokemon.damage.GrassDamage;

public class MegaDrain implements Move {
    private final String name = "Mega Drain";
    private final Category category = Category.Special;
    private final int power = 40;
    private Damage damage = new GrassDamage(power);
    @Override
    public void executeOn(Jokemon jokemon) {
        System.out.println("Attacking with " + name);
        jokemon.takeDamage(damage);
    }

    @Override
    public Damage getDamage() {
        return null;
    }
}
