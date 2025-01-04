package ch.bbw.cge.jokemon.damage;

import ch.bbw.cge.jokemon.move.Move;

import java.util.ArrayList;
import java.util.List;

public class GrassDamage extends Damage {
    public GrassDamage(int power) {
        super(power, Move.Type.GRASS);
        this.strongAgainst = new ArrayList<>(List.of(Move.Type.WATER, Move.Type.GROUND, Move.Type.ROCK));
        this.weakAgainst = new ArrayList<>(List.of(Move.Type.GRASS, Move.Type.FIRE, Move.Type.BUG, Move.Type.FLYING, Move.Type.POISON, Move.Type.ICE));
    }
}
