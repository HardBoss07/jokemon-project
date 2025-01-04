package ch.bbw.cge.saveSystem;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class SaveSystem {

    public static void saveGame(GameState gameState) {
        String filename = "savedGames/save_seed_" + gameState.getSeed() + ".dat";

        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            out.writeObject(gameState);
            System.out.println("Game has been saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving the game: " + e.getMessage());
        }
    }

    public static GameState loadGame(int seed) {
        String filename = "savedGames/save_seed_" + seed + ".dat";

        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            GameState gameState = (GameState) in.readObject();
            System.out.println("Game has been loaded successfully!");
            return gameState;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading the game: " + e.getMessage());
            return null; // Return null if loading fails
        }
    }
}
