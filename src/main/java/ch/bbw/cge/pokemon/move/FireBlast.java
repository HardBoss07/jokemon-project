package ch.bbw.cge.pokemon.move;

import ch.bbw.cge.pokemon.Pokemon;
import ch.bbw.cge.pokemon.damage.Damage;
import ch.bbw.cge.pokemon.damage.FireDamage;

public class FireBlast implements FireMove, EffectBehaviour {
    private final String name = "Fire Blast";
    private final Category category = Category.Special;
    private final int power = 110;
    private final int powerPoints = 5;
    private final int accuracy = 80;

    MoveEffect burn = new BurnEffect(power);

    @Override
    public void executeOn(Pokemon pokemon) {
        System.out.println("Attacking with " + name);
        pokemon.takeDamage(new FireDamage(power));
        pokemon.appendMoveEffect(burn, MoveEffect.Type.NEGATIVE);
    }

    @Override
    public void inflictsEffectOn(Pokemon pokemon) {

    }


}
