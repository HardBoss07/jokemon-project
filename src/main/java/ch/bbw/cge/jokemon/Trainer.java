package ch.bbw.cge.jokemon;

import java.util.ArrayList;
import java.util.List;

public class Trainer {

    public Status status;
    private String name;
    private int level;
    private int xp;
    private List<Jokemon> myJokemons;

    public enum Status{
        USER, BOT
    }

    public Trainer(Status status, String name, int level, List<Jokemon> myJokemons) {
        this.status = status;
        this.name = name;
        this.level = level;
        this.myJokemons = myJokemons;
    }

    public Trainer(){

    }

    public int getXp() {
        return xp;
    }

    public void addXp(int amount) {
        xp += amount;
        updateLevel();
    }

    public boolean areEvolutionsAvailable() {
        boolean evolutionsAvailable = false;
        for (Jokemon i : myJokemons) {
            if (i.isEvolutionAvailable()) {
                System.out.println(i.getName() + " can evolve!");
                evolutionsAvailable = true;
            } else {
                System.out.println(i.getName() + " cannot evolve.");
            }
        }
        if (!evolutionsAvailable) {
            System.out.println("No Jokemon can evolve.");
        }
        return evolutionsAvailable;
    }

    public void displayAllLevel() {
        System.out.println(name + " has " + level + " level");
        for (Jokemon i : myJokemons) {
            System.out.println(i.getName() + " has " + i.getLevel() + " level");
        }
    }
    public void addXpToAllJokemons(int amount) {
        for (Jokemon i : myJokemons) {
            i.addXp(amount);
        }
    }
    private int calculateXpNeeded(int level) {
        int xpNeeded = 10;
        for (int i = 2; i <= level; i++) {
            xpNeeded += xpNeeded / 5;
        }
        return xpNeeded;
    }

    public void updateLevel() {
        int xpNeededForNextLevel = calculateXpNeeded(level);

        while (xp >= xpNeededForNextLevel) {
            level++;
            xp -= xpNeededForNextLevel;
            System.out.println(name + " has leveled up! Current level: " + level);
            xpNeededForNextLevel = calculateXpNeeded(level);
        }
    }

    public void displayMovesFromEachJokemon() {
        System.out.println("Moves from each Jokemon of " + name);
        for (Jokemon i : myJokemons) {
            System.out.println(i.getName() + " has these moves:");
            for (String j : i.getMoves()) {
                System.out.println(j);
            }
        }
    }
    public void statusUpdateAllJokemons() {
        System.out.println("Status of " + name + " Jokemons:");
        for (Jokemon i : myJokemons) {
            System.out.println(i.getName() + " has " + i.hp + " HP and is " + i.getStatus());
        }
    }
    public void regenerateAllJokemons() {
        for (Jokemon i : myJokemons) {
            i.regenerateHp();
        }
    }
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Jokemon> getMyJokemons() {
        return myJokemons;
    }

    public void addMyJokemons(Jokemon jokemon) {
        List<Jokemon> allJokemons = new ArrayList<>();
        allJokemons.addAll(myJokemons);
        allJokemons.add(jokemon);
        setMyJokemons(allJokemons);
    }

    public void setMyJokemons(List<Jokemon> myJokemons) {
        this.myJokemons = myJokemons;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "status=" + status +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", xp=" + xp +
                ", myJokemons=" + myJokemons +
                '}';
    }
}
