package ch.bbw.cge.pokemon.move;

import ch.bbw.cge.pokemon.Pokemon;

public interface MoveEffect {
    void effect(Pokemon pokemon);

    public enum Type {
        POSITIVE, NEGATIVE
    }
}
