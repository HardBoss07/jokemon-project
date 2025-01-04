package ch.bbw.cge.map.objects;

import ch.bbw.cge.jokemon.Battle;
import ch.bbw.cge.jokemon.CatchJokemon;
import ch.bbw.cge.jokemon.Jokemon;
import ch.bbw.cge.jokemon.Trainer;

import java.util.Scanner;

public class WildJokemon {

    private int x;
    private int y;
    private String name;
    private Jokemon jokemon;
    private boolean isCaught;

    private Scanner scanner = new Scanner(System.in);
    public WildJokemon(int x, int y, Jokemon jokemon, boolean isCaught) {
        this.name = jokemon.getName();
        this.x = x;
        this.y = y;
        this.jokemon = jokemon;
        this.isCaught = isCaught;
    }

    // Place an NPC at a specific location
    public static void placeWildJokemon(char[][] grid, int x, int y) {
        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
            grid[x][y] = 'Ɉ';
        }
    }

    public void interaction(Trainer trainer, int treats) {
        System.out.println("You have met a wild level " + jokemon.getLevel() + " " + jokemon.getName() + "!");

        String decision = null;
        // Offer a treat to the Jokemon
        if (treats > 0) {
            System.out.println("Do you want to give a treat to it for 1XP? [y/n]");
            decision = scanner.nextLine();
            if (decision.equalsIgnoreCase("y")) {
                System.out.println("You gave the " + jokemon.getName() + " a treat!");
                trainer.addXp(1);
            }
        }

        // Attempt to catch the Jokemon, with multiple tries allowed
        boolean continueTrying = true;
        while (continueTrying) {
            System.out.println("Do you want to try catching the wild level " + jokemon.getLevel() + " " + jokemon.getName() + "? [y/n]");
            decision = scanner.nextLine();

            if (decision.equalsIgnoreCase("y")) {
                CatchJokemon catchJokemon = new CatchJokemon(trainer, this);
                catchJokemon.encounter();

                if (catchJokemon.getHasCaught()) {
                    System.out.println("Congratulations! You caught the " + jokemon.getName() + "!");
                    break; // Exit the loop once the Jokemon is caught
                } else {
                    System.out.println("The " + jokemon.getName() + " broke free! Do you want to try again? [y/n]");
                    decision = scanner.nextLine();

                    if (!decision.equalsIgnoreCase("y")) {
                        System.out.println("You decided to stop trying to catch the " + jokemon.getName() + ".");
                        continueTrying = false;
                    }
                }
            } else {
                System.out.println("You chose not to attempt catching the " + jokemon.getName() + ".");
                break;
            }
        }
    }



    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setIsCaught(boolean isCaught) {
        this.isCaught = isCaught;
    }

    public boolean getIsCaught() {
        return isCaught;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Jokemon getJokemon() {
        return jokemon;
    }

    public void setJokemon(Jokemon jokemon) {
        this.jokemon = jokemon;
    }
}
