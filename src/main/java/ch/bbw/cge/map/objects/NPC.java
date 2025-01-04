package ch.bbw.cge.map.objects;

import ch.bbw.cge.jokemon.Battle;
import ch.bbw.cge.jokemon.Trainer;

import java.util.Scanner;

public class NPC {

    private int x;
    private int y;
    private String name;
    private Trainer trainer;
    private Trainer player;

    private Scanner scanner = new Scanner(System.in);
    public NPC(int x, int y, Trainer trainer, Trainer player) {
        this.name = trainer.getName();
        this.trainer = trainer;
        this.player = player;
        this.x = x;
        this.y = y;
    }

    // Place an NPC at a specific location
    public static void placeNPC(char[][] grid, int x, int y) {
        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
            grid[x][y] = 'N';
        }
    }

    public void interaction() {
        System.out.println("Hello there! I am " + name + " and I challenge you to a battle!");
        System.out.println("Accept? [y/n]");
        String decision = scanner.nextLine();
        if (decision.equals("y")) {
            Battle battle = new Battle(player, trainer);
            battle.fight();
        }
        else System.out.println("Okay, until we meet again!");
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Trainer getTrainer() {
        return this.trainer;
    }

    public Trainer getPlayer() {
        return this.player;
    }
}
