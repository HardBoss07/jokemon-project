package ch.bbw.cge.jokemon;

import ch.bbw.cge.map.objects.WildJokemon;

import java.util.*;

public class CatchJokemon {
    private Trainer trainer;
    private Jokemon jokemon;
    private boolean hasCaught = false;
    private WildJokemon wildJokemon;

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    public CatchJokemon(Trainer trainer, WildJokemon wildJokemon) {
        this.trainer = trainer;
        this.jokemon = wildJokemon.getJokemon();
        this.wildJokemon = wildJokemon;
    }

    public void encounter() {
        System.out.println(trainer.getName() + " has encountered a wild level " + jokemon.getLevel() + " " + jokemon.getName() + "!");

        // Ask the player once if they want to start trying to catch the Jokemon
        System.out.println("Do you want to try to catch the " + jokemon.getName() + "? [y/n]");
        String decision = scanner.nextLine();

        if (decision.equalsIgnoreCase("y")) {
            System.out.println(trainer.getName() + " tries to catch the " + jokemon.getName());

            // Automatically continue trying until either caught or player stops
            while (!hasCaught) {
                catching();

                if (!hasCaught) {
                    // If not caught, ask if the player wants to keep trying
                    System.out.println("The " + jokemon.getName() + " broke free! Do you want to try again? [y/n]");
                    decision = scanner.nextLine();

                    if (!decision.equalsIgnoreCase("y")) {
                        System.out.println(trainer.getName() + " has decided to stop trying to catch the " + jokemon.getName());
                        break;
                    }
                }
            }
        } else {
            System.out.println(trainer.getName() + " has decided not to attempt catching the " + jokemon.getName());
        }
    }

    private void catching() {
        int difference = trainer.getLevel() - jokemon.getLevel();

        int chance = calculateChance(difference);

        if (chance == 0) {
            didThisTryWork(true);
        } else {
            didThisTryWork(false);
        }
    }

    private int calculateChance(int difference) {
        // Determine the range for the random chance based on level difference
        if (difference <= 10) {
            return random.nextInt(8);
        } else if (difference > 10 && difference <= 20) {
            return random.nextInt(5);
        } else if (difference > 20 && difference <= 30) {
            return random.nextInt(3);
        } else if (difference > 30 && difference <= 40) {
            return random.nextInt(2);
        } else {
            return 1; // Provide almost certain chance when difference is above 40
        }
    }

    private void didThisTryWork(boolean hasWorked) {
        if (hasWorked) {
            System.out.println(trainer.getName() + " has caught the wild level " + jokemon.getLevel() + " " + jokemon.getName());
            trainer.addMyJokemons(jokemon); // Assuming a method exists in Trainer to add Jokemon
            hasCaught = true;
            wildJokemon.setIsCaught(true);
        } else {
            System.out.println(trainer.getName() + " failed to catch the " + jokemon.getName() + "! Try again.");
            hasCaught = false; // Ensure `hasCaught` remains false on failure
        }
    }

    public boolean getHasCaught() {
        return hasCaught;
    }
}
