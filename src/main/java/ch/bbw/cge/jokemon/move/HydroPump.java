package ch.bbw.cge.jokemon.move;

import ch.bbw.cge.jokemon.Jokemon;
import ch.bbw.cge.jokemon.damage.Damage;
import ch.bbw.cge.jokemon.damage.WaterDamage;

public class HydroPump implements Move {
    private final String name = "Hydro Pump";
    private final Category category = Category.Special;
    private final int power = 110;
    private Damage damage = new WaterDamage(power);
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
