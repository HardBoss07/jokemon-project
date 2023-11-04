package ch.bbw.cge;

import ch.bbw.cge.jokemon.FireJokemon;
import ch.bbw.cge.jokemon.Jokemon;
import ch.bbw.cge.jokemon.WaterJokemon;
import ch.bbw.cge.jokemon.move.FireBlast;

public class App {
    public static void main(String[] args) {
        Jokemon glomanda = new FireJokemon("Glomanda", 23, 39, 52, 43, 60,50, 65);
        Jokemon schaggi = new WaterJokemon("Schaggi", 25, 44, 48, 65, 50, 64, 43);

        FireBlast fireBlast = new FireBlast();
        glomanda.attack(schaggi, fireBlast);

        //    encounter trainer
        //    encounter pokémon
        //    catchPokémon
        //    amountOfPokémon of the trainer
        //    evolve
    }
}
