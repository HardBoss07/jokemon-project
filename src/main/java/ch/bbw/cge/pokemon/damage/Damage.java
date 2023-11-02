package ch.bbw.cge.pokemon.damage;

import ch.bbw.cge.pokemon.move.Move;

import java.util.ArrayList;
import java.util.List;

public abstract class Damage {
    int power;
    Move.Type type;

    public List<Move.Type> strongAgainst;
    public List<Move.Type> weakAgainst;

    public Damage(int power, Move.Type type) {
        this.power = power;
        this.type = type;
    }

    public int getPower() {
        return power;
    }

    public List<Move.Type> getStrongAgainst() {
        return strongAgainst;
    }

    public void setStrongAgainst(List<Move.Type> strongAgainst) {
        this.strongAgainst = strongAgainst;
    }

    public List<Move.Type> getWeakAgainst() {
        return weakAgainst;
    }

    public void setWeakAgainst(List<Move.Type> weakAgainst) {
        this.weakAgainst = weakAgainst;
    }
}
