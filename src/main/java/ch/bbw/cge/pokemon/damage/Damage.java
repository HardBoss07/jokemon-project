package ch.bbw.cge.pokemon.damage;

import ch.bbw.cge.pokemon.move.Move;

public abstract class Damage {
    int power;
    Move.Type type;

    public Damage(int power, Move.Type type) {
        this.power = power;
        this.type = type;
    }

    public int getPower() {
        return power;
    }
}
