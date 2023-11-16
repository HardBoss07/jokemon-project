package ch.bbw.cge.jokemon.effect;

import ch.bbw.cge.jokemon.Jokemon;

public interface MoveEffect {
    void effect(Jokemon jokemon);

    public enum Type {
        POSITIVE, NEGATIVE
    }
    int getEffectPower();
    int getEffectMinTurnDuration();
    int getEffectMaxTurnDuration();
    int getEffectChancePercentage();


}
