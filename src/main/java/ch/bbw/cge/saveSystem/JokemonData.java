package ch.bbw.cge.saveSystem;
import ch.bbw.cge.jokemon.move.Move;

import java.io.Serializable;
import java.util.List;

public class JokemonData implements Serializable {
    private final int evolution;
    private final int baseAttack;
    private final int baseDefense;
    private final int baseSpeed;
    private final int baseSpecialAttack;
    private final int baseSpecialDefense;
    private final Move.Type type;

    private String name;
    private int level;
    private int xp;
    private List<String> moves;
    private int baseHp;
    protected int hp;
    private int adjustedBaseHp;

    public JokemonData(String name, int level, int evolution, int baseHp,
                       int baseAttack, int baseDefense, int baseSpeed,
                       int baseSpecialAttack, int baseSpecialDefense, Move.Type type, List<String> moves,
                       int adjustedBaseHp, int hp, int xp) {
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
        this.adjustedBaseHp = adjustedBaseHp;
        this.hp = hp;
        this.xp = xp;
    }

    public String getName() { return name; }
    public int getLevel() { return level; }
    public int getXp() { return xp; }
    public List<String> getMoves() { return moves; }

    public int getEvolution() {
        return evolution;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public int getBaseSpecialAttack() {
        return baseSpecialAttack;
    }

    public int getBaseSpecialDefense() {
        return baseSpecialDefense;
    }

    public Move.Type getType() {
        return type;
    }

    public int getBaseHp() {
        return baseHp;
    }

    public int getHp() {
        return hp;
    }

    public int getAdjustedBaseHp() {
        return adjustedBaseHp;
    }
}

