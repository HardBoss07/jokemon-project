package ch.bbw.cge.jokemon.damage;

import ch.bbw.cge.jokemon.move.Move;

import java.util.ArrayList;
import java.util.List;

public class WaterDamage extends Damage{
    public WaterDamage(int power) {
        super(power, Move.Type.WATER);
        this.strongAgainst = new ArrayList<>(List.of(Move.Type.FIRE, Move.Type.GROUND, Move.Type.ROCK));
        this.weakAgainst = new ArrayList<>(List.of(Move.Type.WATER, Move.Type.GRASS, Move.Type.DRAGON));
    }
}
