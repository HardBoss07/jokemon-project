package ch.bbw.cge.saveSystem;

import java.io.Serializable;
import java.util.List;

public class GameState implements Serializable {
    private int seed;
    private int playerLevel;
    private int playerCoins;
    private int playerTreats;
    private int[] playerPosition;
    private int playerDirection;

    private List<JokemonData> playerJokemons;
    private List<JokemonData> jokemons;
    private List<WildJokemonData> wildJokemons;
    public GameState(int seed, int playerLevel, int playerCoins, int playerTreats, int[] playerPosition,
                     int playerDirection, List<JokemonData> jokemons, List<WildJokemonData> wildJokemons) {
        this.seed = seed;
        this.playerLevel = playerLevel;
        this.playerCoins = playerCoins;
        this.playerTreats = playerTreats;
        this.playerPosition = playerPosition;
        this.playerDirection = playerDirection;
        this.jokemons = jokemons;
        this.wildJokemons = wildJokemons;
    }

    // Getters and Setters for the fields
    public int getSeed() { return seed; }
    public int getPlayerLevel() { return playerLevel; }
    public int getPlayerCoins() { return playerCoins; }
    public int getPlayerTreats() { return playerTreats; }
    public int getPlayerDirection() { return playerDirection; }
    public int[] getPlayerPosition() { return playerPosition; }
    public List<JokemonData> getJokemons() { return jokemons; }

    public List<WildJokemonData> getWildJokemons() {
        return wildJokemons;
    }
}
