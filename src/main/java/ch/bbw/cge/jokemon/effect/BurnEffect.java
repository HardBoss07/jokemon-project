package ch.bbw.cge.jokemon.effect;

import ch.bbw.cge.jokemon.Jokemon;

public class BurnEffect implements MoveEffect {

    private int effectPower;
    private int effectMinTurnDuration;
    private int effectMaxTurnDuration;
    private int effectChancePercentage;

    public BurnEffect(int effectPower, int effectMinTurnDuration,
                      int effectMaxTurnDuration, int effectChancePercentage) {
        this.effectPower = effectPower;
        this.effectMinTurnDuration = effectMinTurnDuration;
        this.effectMaxTurnDuration = effectMaxTurnDuration;
        this.effectChancePercentage = effectChancePercentage;
    }

    @Override
    public void effect(Jokemon jokemon) {

    }

    @Override
    public int getEffectPower() {
        return effectPower;
    }

    @Override
    public int getEffectMinTurnDuration() {
        return effectMinTurnDuration;
    }

    @Override
    public int getEffectMaxTurnDuration() {
        return effectMaxTurnDuration;
    }

    @Override
    public int getEffectChancePercentage() {
        return effectChancePercentage;
    }
}
