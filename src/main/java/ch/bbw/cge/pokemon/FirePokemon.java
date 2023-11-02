package ch.bbw.cge.pokemon;

import ch.bbw.cge.pokemon.damage.Damage;
import ch.bbw.cge.pokemon.move.Move;

public class FirePokemon extends Pokemon {
    public FirePokemon(String name, int level,
                       int baseHp, int baseAttack,
                       int baseDefense, int baseSpeed,
                       int baseSpecialAttack, int baseSpecialDefense) {
        super(name, level, baseHp, baseAttack, baseDefense,
                baseSpeed, baseSpecialAttack, baseSpecialDefense, Move.Type.FIRE);
    }

}
