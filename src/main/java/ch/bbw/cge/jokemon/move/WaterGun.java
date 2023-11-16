package ch.bbw.cge.jokemon.move;

import ch.bbw.cge.jokemon.Jokemon;
import ch.bbw.cge.jokemon.damage.Damage;

public class WaterGun implements Move {
    private final String name = "Water Gun";
    private final Category category = Category.Special;
    private final int power = 40;
    @Override
    public void executeOn(Jokemon jokemon) {
        System.out.println("Attacking with " + name);
    }

    @Override
    public Damage getDamage() {
        return null;
    }
}
