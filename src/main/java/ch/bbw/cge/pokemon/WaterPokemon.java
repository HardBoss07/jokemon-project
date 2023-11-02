package ch.bbw.cge.pokemon;

import ch.bbw.cge.pokemon.damage.Damage;
import ch.bbw.cge.pokemon.move.Move;

public class WaterPokemon extends Pokemon {
    public WaterPokemon(String name, int level,
                       int baseHp, int baseAttack,
                       int baseDefense, int baseSpeed,
                       int baseSpecialAttack, int baseSpecialDefense) {
        super(name, level, baseHp, baseAttack, baseDefense,
                baseSpeed, baseSpecialAttack, baseSpecialDefense, Move.Type.WATER);
    }

}
