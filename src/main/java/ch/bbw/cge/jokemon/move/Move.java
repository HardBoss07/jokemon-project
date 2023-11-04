package ch.bbw.cge.jokemon.move;

import ch.bbw.cge.jokemon.Jokemon;
import ch.bbw.cge.jokemon.damage.Damage;

public interface Move {

    public enum Type {
        NORMAL, FIRE, WATER, GRASS, ELECTRIC,
        ICE, ROCK, STEEL, POISON, DARK, PSYCHIC,
        GROUND, FLYING, FIGHTING, BUG, DRAGON,
        GHOST, FAIRY
    }
    public enum Category {
        Physical, Special, Status
    }
    abstract void executeOn(Jokemon jokemon);

    abstract Damage getDamage();
}
