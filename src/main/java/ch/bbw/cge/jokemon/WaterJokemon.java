package ch.bbw.cge.jokemon;

import ch.bbw.cge.jokemon.move.Move;

public class WaterJokemon extends Jokemon {
    public WaterJokemon(String name, int level,
                        int baseHp, int baseAttack,
                        int baseDefense, int baseSpeed,
                        int baseSpecialAttack, int baseSpecialDefense) {
        super(name, level, baseHp, baseAttack, baseDefense,
                baseSpeed, baseSpecialAttack, baseSpecialDefense, Move.Type.WATER);
    }

}
