package ch.bbw.cge.pokemon.move;

import ch.bbw.cge.pokemon.Pokemon;
import ch.bbw.cge.pokemon.damage.Damage;

public class WaterGun implements WaterMove {
    private final String name = "Water Gun";
    private final Category category = Category.Special;
    private final int power = 40;
    @Override
    public void executeOn(Pokemon pokemon) {
        System.out.println("Attacking with " + name);
    }

    @Override
    public Damage getDamage() {
        return null;
    }
}
