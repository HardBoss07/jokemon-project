package ch.bbw.cge.pokemon.damage;

import ch.bbw.cge.pokemon.move.Move;

public class FireDamage extends Damage {
    public FireDamage(int power) {
        super(power, Move.Type.FIRE);
    }
}
