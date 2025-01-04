package ch.bbw.cge.jokemon;

import ch.bbw.cge.jokemon.move.Move;

import java.util.List;

public class GrassJokemon extends Jokemon {
    public GrassJokemon(String name, int level, int evolution,
                        int baseHp, int baseAttack,
                        int baseDefense, int baseSpeed,
                        int baseSpecialAttack, int baseSpecialDefense, List<String> moves) {
        super(name, level, evolution, baseHp, baseAttack, baseDefense,
                baseSpeed, baseSpecialAttack, baseSpecialDefense, Move.Type.GRASS, moves);
    }

}
