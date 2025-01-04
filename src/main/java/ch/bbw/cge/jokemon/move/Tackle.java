package ch.bbw.cge.jokemon.move;

import ch.bbw.cge.jokemon.Jokemon;
import ch.bbw.cge.jokemon.damage.Damage;
import ch.bbw.cge.jokemon.damage.GrassDamage;
import ch.bbw.cge.jokemon.damage.NormalDamage;

public class Tackle implements Move {
    private final String name = "Tackle";
    private final Category category = Category.Physical;
    private final int power = 40;
    private Damage damage = new NormalDamage(power);
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
