package ch.bbw.cge.jokemon;

import java.util.*;

public class Evolve {
    private Trainer trainer;
    private Jokemon jokemon;

    private Map<String, List<String>> movesMap;

    Map<String, Jokemon> evolutionMapInstancing = new HashMap<>();
    Map<String, String> evolutionMapString = new HashMap<>();

    Scanner scanner = new Scanner(System.in);

    public Evolve(Trainer trainer, Jokemon jokemon, Map<String, List<String>> movesMap) {
        this.trainer = trainer;
        this.jokemon = jokemon;
        this.movesMap = movesMap;
    }

    public void evolveJokemon() {

        evolutionMapString.put("Bulbasaur", "Ivysaur");
        evolutionMapString.put("Schaggi", "Wartortle");
        evolutionMapString.put("Squirtle", "Wartortle");
        evolutionMapString.put("Glomanda", "Charmeleon");
        evolutionMapString.put("Ivysaur", "Venusaur");
        evolutionMapString.put("Wartortle", "Blastoise");
        evolutionMapString.put("Charmeleon", "Charizard");

        evolutionMapInstancing.put("Bulbasaur", new GrassJokemon("Ivysaur", jokemon.getLevel() + 1, 2, 60, 62, 63, 60, 80, 80, movesMap.get("Ivysaur")));
        evolutionMapInstancing.put("Squirtle", new WaterJokemon("Wartortle", jokemon.getLevel() + 1, 2, 59, 63, 80, 58, 65, 80, movesMap.get("Wartortle")));
        evolutionMapInstancing.put("Schaggi", new WaterJokemon("Wartortle", jokemon.getLevel() + 1, 2, 59, 63, 80, 58, 65, 80, movesMap.get("Wartortle")));
        evolutionMapInstancing.put("Glomanda", new FireJokemon("Charmeleon", jokemon.getLevel() + 1, 2, 58, 64, 58, 80, 80, 65, movesMap.get("Charmeleon")));
        evolutionMapInstancing.put("Ivysaur", new GrassJokemon("Venusaur", jokemon.getLevel() + 1, 3, 80, 82, 83, 100, 100, 80, movesMap.get("Venusaur")));
        evolutionMapInstancing.put("Wartortle", new WaterJokemon("Blastoise", jokemon.getLevel() + 1, 3, 79, 83, 100, 85, 105, 78, movesMap.get("Blastoise")));
        evolutionMapInstancing.put("Charmeleon", new FireJokemon("Charizard", jokemon.getLevel() + 1, 3, 78, 84, 78, 109, 85, 100, movesMap.get("Charizard")));

        if (isEvolutionAvailabe()){
            System.out.println("Do you want to evolve your " + jokemon.getName() + " to a " + evolutionMapInstancing.get(jokemon.getName()).getName() + "? [y/n)");
            String decision = scanner.nextLine();
            if (decision.equals("y")) {
                System.out.println("Evolve " + jokemon.getName() + " to " + evolutionMapInstancing.get(jokemon.getName()).getName());
                List<Jokemon> allJokemons = new ArrayList<>();
                Jokemon evolution = evolutionMapInstancing.get(jokemon.getName());
                evolution.addXp(jokemon.getXp());
                allJokemons.addAll(trainer.getMyJokemons());
                allJokemons.add(evolution);
                allJokemons.remove(jokemon);
                trainer.setMyJokemons(allJokemons);
                System.out.println("Evolved Jokemon!");
                trainer.statusUpdateAllJokemons();
            }
        } else System.out.println("Error: Either too low level Jokemon or evolution doesnt exist");
    }

    boolean isEvolutionAvailabe() {
        if (evolutionMapInstancing.get(jokemon.getName()) != null && jokemon.isEvolutionAvailable()) {
            return true;
        }
        else return false;
    }
}
