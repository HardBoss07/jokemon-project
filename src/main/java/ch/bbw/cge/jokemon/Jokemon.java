package ch.bbw.cge.jokemon;

import ch.bbw.cge.jokemon.damage.Damage;
import ch.bbw.cge.jokemon.move.Move;
import ch.bbw.cge.jokemon.effect.MoveEffect;

import java.util.ArrayList;
import java.util.List;

public abstract class Jokemon {
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
    private Status status;
    private final Move.Type type;

    enum Status {
        ALIVE, KO
    }

    List<MoveEffect> positiveEffects = new ArrayList<>();
    List<MoveEffect> negativeEffects = new ArrayList<>();

    public Jokemon(String name, int level, int baseHp,
                   int baseAttack, int baseDefense, int baseSpeed,
                   int baseSpecialAttack, int baseSpecialDefense, Move.Type type) {
        this.name = name;
        this.level = level;
        this.baseHp = baseHp;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseSpeed = baseSpeed;
        this.baseSpecialAttack = baseSpecialAttack;
        this.baseSpecialDefense = baseSpecialDefense;
        this.type = type;
    }

    public void attack (Jokemon jokemon, Move move) {
        move.executeOn(jokemon);
        //jokemon.takeDamage(move.getDamage()); TODO maybe delete
    }
    public Move.Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void takeDamage(Damage damage) {
        int modifiedDamage = calculateDamage(damage);
        this.hp = calculateCurrentHp(modifiedDamage);
        updateStatus();
    }

    protected void updateStatus() {
        if (this.hp < 0) {
            // TODO implement the method emptyEffectList (positive and negative list).
            this.status = Status.KO;
        }
    }

    protected int calculateCurrentHp(int modifiedDamage) {
        return this.hp - modifiedDamage;
    }

    protected int calculateDamage(Damage damage) {
        var strongType = damage.getStrongAgainst().stream().filter(type -> type == this.getType()).findAny();
        var weakType = damage.getWeakAgainst().stream().filter(type -> type == this.getType()).findAny();
        int doubleDamage = 2;
        double halfDamage = 0.5;
        if(strongType.isPresent()) {
            System.out.println("Doppelter Schaden erhalten: " + damage.getPower() * doubleDamage);
            return damage.getPower() * doubleDamage;
        } else if (weakType.isPresent()) {
            System.out.println("Halber Schaden erhalten: " + (int) Math.round(damage.getPower() * halfDamage));
            return (int) Math.round(damage.getPower() * halfDamage);
        } else {
            System.out.println("Schaden erhalten: " + damage.getPower());
            return damage.getPower();
        }
    }

    public void appendMoveEffect(MoveEffect effect, MoveEffect.Type type) {
        if (isEffectAppended(effect)) {
            if (type == MoveEffect.Type.NEGATIVE) { // TODO check if the same effect is already in the list. If yes, replace it.
                negativeEffects.add(effect);
            }
            positiveEffects.add(effect);
        }
    }
    public boolean isEffectAppended(MoveEffect effect) {
        if((int) Math.round(Math.random()*100) > effect.getEffectChancePercentage()) {
            return false;
        }
        return true;
    }

    public boolean isEffectStillActive(){ //TODO build this method for the status check at the beginning of a turn. Use it in Battle class
        return false;
    }
}
