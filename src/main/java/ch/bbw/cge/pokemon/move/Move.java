package ch.bbw.cge.pokemon.move;

import ch.bbw.cge.pokemon.Pokemon;
import ch.bbw.cge.pokemon.damage.Damage;

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
    abstract void executeOn(Pokemon pokemon);

    abstract Damage getDamage();
}
