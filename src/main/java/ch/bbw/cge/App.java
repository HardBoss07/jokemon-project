package ch.bbw.cge;

import ch.bbw.cge.pokemon.FirePokemon;
import ch.bbw.cge.pokemon.Pokemon;
import ch.bbw.cge.pokemon.WaterPokemon;
import ch.bbw.cge.pokemon.move.FireBlast;

public class App {
    public static void main(String[] args) {
        Pokemon glumanda = new FirePokemon("Glumanda", 23, 39, 52, 43, 60,50, 65);
        Pokemon schiggi = new WaterPokemon("Schiggi", 25, 44, 48, 65, 50, 64, 43);

        FireBlast fireBlast = new FireBlast();
        glumanda.attack(schiggi, fireBlast);

        //    encounter trainer
        //    encounter pokémon
        //    catchPokémon
        //    amountOfPokémon of the trainer
        //    evolve
    }
}
