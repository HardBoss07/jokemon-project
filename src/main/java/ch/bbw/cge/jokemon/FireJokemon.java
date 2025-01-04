package ch.bbw.cge.jokemon;

import ch.bbw.cge.jokemon.move.Move;

import java.util.List;

public class FireJokemon extends Jokemon {
    public FireJokemon(String name, int level, int evolution,
                       int baseHp, int baseAttack,
                       int baseDefense, int baseSpeed,
                       int baseSpecialAttack, int baseSpecialDefense, List<String> moves) {
        super(name, level, evolution, baseHp, baseAttack, baseDefense,
                baseSpeed, baseSpecialAttack, baseSpecialDefense, Move.Type.FIRE, moves);
    }

}
