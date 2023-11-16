package ch.bbw.cge;

import ch.bbw.cge.jokemon.FireJokemon;
import ch.bbw.cge.jokemon.Jokemon;
import ch.bbw.cge.jokemon.WaterJokemon;
import ch.bbw.cge.jokemon.move.FireBlast;

public class App {
    public static void main(String[] args) {
        // TODO Implement logic to ask for user input. Also give instructions, what the players option are. Validate the user input
        // TODO Implement random encounter. But consider the levels of the encountered jokemons and trainers.

        Jokemon glomanda = new FireJokemon("Glomanda", 23, 39, 52, 43, 60,50, 65);
        Jokemon schaggi = new WaterJokemon("Schaggi", 25, 44, 48, 65, 50, 64, 43);

        FireBlast fireBlast = new FireBlast();
        glomanda.attack(schaggi, fireBlast);

        // TODO insert some System.out.println() to the code for more Output in the console during run time.
        // TODO encounter trainer
        // TODO encounter pokémon
        // TODO catchPokémon
        // TODO amountOfPokémon of the trainer
        // TODO evolve
    }
}
