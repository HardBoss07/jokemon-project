package ch.bbw.cge.pokemon.effect;

import ch.bbw.cge.pokemon.Pokemon;

public interface MoveEffect {
    void effect(Pokemon pokemon);

    public enum Type {
        POSITIVE, NEGATIVE
    }
}
