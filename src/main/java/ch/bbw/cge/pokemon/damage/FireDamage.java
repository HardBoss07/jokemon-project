package ch.bbw.cge.pokemon.damage;

import ch.bbw.cge.pokemon.move.Move;

import java.util.ArrayList;
import java.util.List;

public class FireDamage extends Damage {
    public FireDamage(int power) {
        super(power, Move.Type.FIRE);
        this.strongAgainst = new ArrayList<>(List.of(Move.Type.GRASS, Move.Type.ICE, Move.Type.BUG, Move.Type.STEEL));
        this.weakAgainst = new ArrayList<>(List.of(Move.Type.FIRE, Move.Type.WATER, Move.Type.ROCK, Move.Type.DRAGON));
    }
}
