package ch.bbw.cge.jokemon.move;

import ch.bbw.cge.jokemon.Jokemon;
import ch.bbw.cge.jokemon.damage.Damage;
import ch.bbw.cge.jokemon.damage.FireDamage;
import ch.bbw.cge.jokemon.effect.BurnEffect;
import ch.bbw.cge.jokemon.effect.EffectBehaviour;
import ch.bbw.cge.jokemon.effect.MoveEffect;

public class FireBlast implements FireMove, EffectBehaviour {
    private final String name = "Fire Blast";
    private final Category category = Category.Special;
    private final int power = 110;
    private final int powerPoints = 5;
    private final int accuracy = 80;
    Damage damage = new FireDamage(power);

    MoveEffect burn = new BurnEffect(power);

    @Override
    public void executeOn(Jokemon jokemon) {
        System.out.println("Attacking with " + name);
        jokemon.takeDamage(new FireDamage(power));
        jokemon.appendMoveEffect(burn, MoveEffect.Type.NEGATIVE);
    }

    @Override
    public void inflictsEffectOn(Jokemon jokemon) {

    }

    public Damage getDamage() {
        return damage;
    }
}
