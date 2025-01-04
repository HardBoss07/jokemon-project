package ch.bbw.cge.jokemon.damage;

import ch.bbw.cge.jokemon.move.Move;

import java.util.ArrayList;
import java.util.List;

public class NormalDamage extends Damage {
    public NormalDamage(int power) {
        super(power, Move.Type.NORMAL);
        this.strongAgainst = new ArrayList<>();
        this.weakAgainst = new ArrayList<>();
    }
}
