package ch.bbw.cge.jokemon.move;

import ch.bbw.cge.jokemon.Jokemon;
import ch.bbw.cge.jokemon.damage.Damage;

public interface Move {
    // variables in interfaces are explicitly public static final
    // enums in interfaces are explicitly public static
    // methods in interfaces are explicitly public abstract
    enum Type {
        NORMAL, FIRE, WATER, GRASS, ELECTRIC,
        ICE, ROCK, STEEL, POISON, DARK, PSYCHIC,
        GROUND, FLYING, FIGHTING, BUG, DRAGON,
        GHOST, FAIRY
    }
    enum Category { // TODO implement the move category logic into damage calculation
        Physical, Special, Status
    }
    void executeOn(Jokemon jokemon);

    Damage getDamage();
}
