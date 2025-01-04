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
    private int adjustedBaseHp;
    private int baseAttack;
    protected int attack;
    private int baseDefense;
    protected int defense;
    private int baseSpecialAttack;
    protected int specialAttack;
    private int baseSpecialDefense;
    private int specialDefense;
    private int baseSpeed;
    protected int speed;
    private Status status;
    private final Move.Type type;
    private List<String> moves = new ArrayList<>();
    private int xp = 0;
    private final int evolution;
    private String removedMove;

    public enum Status {
        ALIVE, KO
    }

    List<MoveEffect> positiveEffects = new ArrayList<>();
    List<MoveEffect> negativeEffects = new ArrayList<>();

    public Jokemon(String name, int level,int evolution, int baseHp,
                   int baseAttack, int baseDefense, int baseSpeed,
                   int baseSpecialAttack, int baseSpecialDefense, Move.Type type, List<String> moves) {
        this.name = name;
        this.level = level;
        this.evolution = evolution;
        this.baseHp = baseHp;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseSpeed = baseSpeed;
        this.baseSpecialAttack = baseSpecialAttack;
        this.baseSpecialDefense = baseSpecialDefense;
        this.type = type;
        this.moves = moves;

        capLevel();
        adjustMovesBasedOnLevel();
        startHpCalculation();
    }

    void capLevel() {
        if (evolution == 1 && level >= 16) {
            level = 16;
            System.out.println("Capped level to first evolution");
        }
        if (evolution == 2 && level >= 32) {
            level = 32;
            System.out.println("Capped level to second evolution");
        }
        if (evolution == 3) {
            System.out.println("Cannot cap level! Jokemon is at max evolution");
        }
    }
    public boolean isEvolutionAvailable() {
        if (level == 16 || level == 32) return true;
        else return false;
    }
    public int getXp() {
        return xp;
    }

    public void addXp(int amount) {
        xp += amount;
        updateLevel();
    }

    private int calculateXpNeeded(int level) {
        int xpNeeded = 10;
        for (int i = 2; i <= level; i++) {
            xpNeeded += xpNeeded / 5;
        }
        return xpNeeded;
    }

    private void updateLevel() {
        int xpNeededForNextLevel = calculateXpNeeded(level);

        while (xp >= xpNeededForNextLevel) {
            level++;
            capLevel();
            xp -= xpNeededForNextLevel;
            System.out.println(name + " has leveled up! Current level: " + level);
            xpNeededForNextLevel = calculateXpNeeded(level);
        }
        adjustMovesBasedOnLevel();
    }

    public int getAdjustedBaseHp() {
        return adjustedBaseHp;
    }
    public void adjustMovesBasedOnLevel() {
        moves = new ArrayList<>(moves);

        if (level <= 10 && moves.size() > 2) {
            removedMove = moves.get(moves.size()-1);
            moves.remove(moves.size() - 1);
        }
        if (level > 10 && removedMove != null) {
            moves.add(removedMove);
            removedMove = null;
        }
    }
    void startHpCalculation() {
        adjustedBaseHp = calculateAdjustedBaseHp();
        hp = adjustedBaseHp;
        updateStatus();
    }

    public void regenerateHp(){
        hp = adjustedBaseHp;
        updateStatus();
    }
    protected int calculateAdjustedBaseHp(){
        return (int) (level * 0.7 + baseHp);
    }
    public void attack (Jokemon jokemon, Move move) {
        move.executeOn(jokemon);
        //jokemon.takeDamage(move.getDamage()); TODO maybe delete
    }

    public List<String> getMoves() {
        return moves;
    }

    public void setMoves(List<String> moves) {
        this.moves = moves;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void takeDamage(Damage damage) {
        System.out.println(name + " took damage"); //FIXME
        int modifiedDamage = calculateDamage(damage);
        this.hp = calculateCurrentHp(modifiedDamage);
        updateStatus();
    }

    protected void updateStatus() {
        if (this.hp <= 0) {
            // TODO implement the method emptyEffectList (positive and negative list).
            this.status = Status.KO;
        } else this.status = Status.ALIVE;
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

    public int getBaseHp() {
        return baseHp;
    }

    public int getHp() {
        return hp;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public int getAttack() {
        return attack;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public int getDefense() {
        return defense;
    }

    public int getBaseSpecialAttack() {
        return baseSpecialAttack;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getBaseSpecialDefense() {
        return baseSpecialDefense;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public int getSpeed() {
        return speed;
    }

    public int getEvolution() {
        return evolution;
    }

    public String getRemovedMove() {
        return removedMove;
    }

    public List<MoveEffect> getPositiveEffects() {
        return positiveEffects;
    }

    public List<MoveEffect> getNegativeEffects() {
        return negativeEffects;
    }
}
