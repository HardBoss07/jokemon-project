package ch.bbw.cge.pokemon;

import ch.bbw.cge.pokemon.damage.Damage;

public class FirePokemon extends Pokemon {
    public FirePokemon(String name, int level,
                       int baseHp, int baseAttack,
                       int baseDefense, int baseSpeed,
                       int baseSpecialAttack, int baseSpecialDefense) {
        super(name, level, baseHp, baseAttack, baseDefense,
                baseSpeed, baseSpecialAttack, baseSpecialDefense);
    }

    @Override
    public void attack(String move) {
        System.out.println(getName() + " uses Ember!");
    }

    @Override
    public void takeDamage(Damage damage) {
        System.out.println(getName() + " takes " + damage + " damage from water attack.");
    }
}
