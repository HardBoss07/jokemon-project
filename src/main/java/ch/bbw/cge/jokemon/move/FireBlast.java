package ch.bbw.cge.jokemon.move;

import ch.bbw.cge.jokemon.Jokemon;
import ch.bbw.cge.jokemon.damage.Damage;
import ch.bbw.cge.jokemon.damage.FireDamage;
import ch.bbw.cge.jokemon.effect.BurnEffect;
import ch.bbw.cge.jokemon.effect.MoveEffect;

public class FireBlast implements Move {
    private final String name = "Fire Blast";
    private final Category category = Category.Special;
    private final int power = 110;
    private final int powerPoints = 5;
    private final int accuracy = 80;
    private final int burnChancePercentage = 30;
    private final int minTurnDuration = 2;
    private final int maxTurnDuration = 4;
    private int actualDuration;
    private Damage damage = new FireDamage(power);

    //TODO task for the students: implement turnDuration in a way you like: min and max duration, specific duration, string that gives a range... or different
    MoveEffect burn = new BurnEffect(power, minTurnDuration, maxTurnDuration, burnChancePercentage);

    @Override
    public void executeOn(Jokemon jokemon) {
        System.out.println("Attacking with " + name);
        jokemon.takeDamage(damage);
        jokemon.appendMoveEffect(burn, MoveEffect.Type.NEGATIVE);
    }

    public Damage getDamage() {
        return damage;
    }
}
