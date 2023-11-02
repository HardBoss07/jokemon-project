package ch.bbw.cge.pokemon;

import ch.bbw.cge.pokemon.damage.Damage;

public class WaterPokemon extends Pokemon {
    public WaterPokemon(String name, int level,
                       int baseHp, int baseAttack,
                       int baseDefense, int baseSpeed,
                       int baseSpecialAttack, int baseSpecialDefense) {
        super(name, level, baseHp, baseAttack, baseDefense,
                baseSpeed, baseSpecialAttack, baseSpecialDefense);
    }

    @Override
    public void attack(String move) {
        System.out.println(getName() + " uses Water Gun!");
    }

    @Override
    public void takeDamage(Damage damage) {
        System.out.println(getName() + " takes " + damage.getPower());
        this.hp = calculateCurrentHp(this, damage);
        // TODO updateStatus();
    }

    private int calculateCurrentHp(WaterPokemon waterPokemon, Damage damage) {
        int modifiedDamage = calculateDamage(damage);
        return waterPokemon.hp - modifiedDamage;
    }

    private int calculateDamage(Damage damage) {
        return damage.getPower(); // TODO Insert DMG class that has a type
    }


}
