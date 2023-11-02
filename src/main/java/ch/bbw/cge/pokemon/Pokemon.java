package ch.bbw.cge.pokemon;

import ch.bbw.cge.pokemon.damage.Damage;
import ch.bbw.cge.pokemon.move.MoveEffect;

import java.util.ArrayList;
import java.util.List;

public abstract class Pokemon {

//    encounter trainer
//    encounter pokémon
//    catchPokémon
//    amountOfPokémon of the trainer
//    evolve
    private String name;
    private int level;
    private int baseHp;
    protected int hp;
    private int baseAttack;
    protected int attack;
    private int baseDefense;
    protected int defense;
    private int baseSpecialAttack;
    protected int specialAttack;
    private int baseSpecialDefense;
    protected int specialDefense;
    private int baseSpeed;
    protected int speed;

    List<MoveEffect> positiveEffects = new ArrayList<>();
    List<MoveEffect> negativeEffects = new ArrayList<>();

    public Pokemon(String name, int level, int baseHp,
                   int baseAttack, int baseDefense, int baseSpeed,
                   int baseSpecialAttack, int baseSpecialDefense) {
        this.name = name;
        this.level = level;
        this.baseHp = baseHp;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseSpeed = baseSpeed;
        this.baseSpecialAttack = baseSpecialAttack;
        this.baseSpecialDefense = baseSpecialDefense;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public abstract void attack(String move);

    public abstract void takeDamage(Damage damage);

    public void appendMoveEffect(MoveEffect effect, MoveEffect.Type type) {
        if(type == MoveEffect.Type.NEGATIVE) {
            negativeEffects.add(effect);
        }
        positiveEffects.add(effect);
    }
}
