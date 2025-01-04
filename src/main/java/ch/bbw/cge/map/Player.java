package ch.bbw.cge.map;

import ch.bbw.cge.ANSI_CODES;
import ch.bbw.cge.building.Building;
import ch.bbw.cge.jokemon.*;
import ch.bbw.cge.map.objects.NPC;
import ch.bbw.cge.map.objects.WildJokemon;
import ch.bbw.cge.map.screens.InteriorScreen;
import ch.bbw.cge.map.screens.Screen;

import java.util.*;

public class Player {
    // Player's position
    private int x;
    private int y;

    // Player's direction (0: up, 1: right, 2: down, 3: left)
    private int direction = 0;
    private char[] arrows = {'↑', '→', '↓', '←'};
    private final Screen screen;
    private InteriorScreen interiorScreen;
    private final Trainer trainer;
    private boolean isInsideBuilding = false;
    public void setIsInsideBuilding(boolean insideBuilding) {
        isInsideBuilding = insideBuilding;
    }
    private int coins;
    private int treats;

    private Map<String, List<String>> movesMap;

    Scanner scanner = new Scanner(System.in);

    // Constructor that sets the initial position and screen
    public Player(Screen screen, Trainer trainer, Map<String, List<String>> movesMap) {
        this.screen = screen;
        this.trainer = trainer;
        this.movesMap = movesMap;
        this.x = screen.getGridSize() / 2;
        this.y = screen.getGridSize() / 2;
        screen.updatePlayerPosition(this.x, this.y, arrows[direction]);
        this.coins = 10;
        this.treats = 0;
    }
    // Turn right
    public void turnRight() {
        direction = (direction + 1) % 4;
        if (!isInsideBuilding) screen.updatePlayerPosition(x, y, arrows[direction]);
        else interiorScreen.updatePlayerPosition(x, y, arrows[direction]);
    }

    // Turn left
    public void turnLeft() {
        direction = (direction + 3) % 4; // Equivalent to -1 mod 4
        if (!isInsideBuilding) screen.updatePlayerPosition(x, y, arrows[direction]);
        else interiorScreen.updatePlayerPosition(x, y, arrows[direction]);
    }

    public void moveForward() {
        int newX = x;
        int newY = y;

        if (!isInsideBuilding) {
            switch (direction) {
                case 0: // Up
                    newX = x - 1;
                    break;
                case 1: // Right
                    newY = y + 1;
                    break;
                case 2: // Down
                    newX = x + 1;
                    break;
                case 3: // Left
                    newY = y - 1;
                    break;
            }
            if (screen.isValidMove(newX, newY)) {
                screen.clearPosition(x, y);
                x = newX;
                y = newY;
                screen.updatePlayerPosition(x, y, arrows[direction]);
            } else {
                System.out.println("Cannot move forward! There's a tree, Jokemon or NPC in the way.");
            }
        }
        else {
            switch (direction) {
                case 0: // Up
                    newY = y - 1;
                    break;
                case 1: // Right
                    newX = x + 1;
                    break;
                case 2: // Down
                    newY = y + 1;
                    break;
                case 3: // Left
                    newX = x - 1;
                    break;
            }
            if (interiorScreen.isValidMove(newX, newY)) {
                interiorScreen.clearPosition(x,y);
                x = newX;
                y = newY;
                interiorScreen.updatePlayerPosition(x, y, arrows[direction]);
            }
            else System.out.println("Cannot move here inside this building!");
        }
        randomEncounterBattle();
        randomEncounterJokemon();
    }


    // Interact with Jokemon
    public void interactWithJokemon() {
        WildJokemon jokemon = screen.findWildJokemonInList(getLocationOfSpaceInFront()[0], getLocationOfSpaceInFront()[1]);

        if (jokemon != null) {
            jokemon.interaction(trainer, treats);
        }
        else System.out.println("Not facing a Jokemon!");
    }

    // Interact with NPC
    public void interactWithNPC () {
        NPC npc = screen.findNPCinList(getLocationOfSpaceInFront()[0], getLocationOfSpaceInFront()[1]);

        if (getSpaceInFront() == 'N' && npc != null) {
            npc.interaction();
        }
        else System.out.println("Not facing a NPC!");
    }

    // Interact with Building
    public void interactWithBuilding() {
        Building building = screen.findBuildingInList(getLocationOfSpaceDoubleInFront()[0], getLocationOfSpaceInFront()[1]);
        interiorScreen = new InteriorScreen(building);
        interiorScreen.initializeMap(this);
        interiorScreen.printMap();

        while (true) {
            char command = scanner.next().charAt(0);
            System.out.println("Enter commands (r = turn right, l = turn left, m = move forward, e = interact:");
            switch (command) {
                case 'r':
                    turnRight();
                    break;
                case 'l':
                    turnLeft();
                    break;
                case 'm':
                    moveForward();
                    break;
                case 'e':
                    if (getSpaceDoubleInFront() == 'N') {
                        interactWithNurse();
                        break;
                    }
                    if (getSpaceDoubleInFront() == 'S') {
                        interactWithShopkeeper();
                        break;
                    }
                    if (getSpaceInFront() == '◫') {
                        exitBuilding();
                        return;
                    }
                    if (getSpaceInFront() == '$') {
                        interactWithSage();
                    }
                    else System.out.println("Cannot interact here!");
                    break;
                default:
                    System.out.println("Invalid command! Use 'r', 'l', 'm' or 'e'.");
            }
            interiorScreen.printMap();
        }
    }

    private void interactWithSage() {
        System.out.println("Hey " + trainer.getName() + "!");
        System.out.println("Did you have a great journey so far? [y/n]");
        String decision = null;
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        decision = scanner.nextLine();
        if (decision.equals("y")) {
            System.out.println(ANSI_CODES.ITALIC + "Yes I had, Sage." + ANSI_CODES.RESET);
            System.out.println("That's wonderful!");
            System.out.println("Well I dont want to hold you up for too long. Have a nice day!");
            System.out.println(ANSI_CODES.ITALIC + "Thank you, Sage! You have one too!");
        }
        if (decision.equals("n")) {
            System.out.println(ANSI_CODES.ITALIC + "Hey Sage. No, not really." + ANSI_CODES.RESET);
            System.out.println("Oh no! That's not good!");
            System.out.println("Hopefully you can have a better journey with these 10 coins!");
            coins += 10;
            System.out.println("Have a nice day!");
            System.out.println(ANSI_CODES.ITALIC + "Thank you for the coins, Sage! You have one too!");
        }
    }

    private void exitBuilding() {
        System.out.println("You're now leaving the Building!");
        isInsideBuilding = false;
        setX(interiorScreen.getPrevX());
        setY(interiorScreen.getPrevY());
        interiorScreen = null;
        screen.updatePlayerPosition(x, y, getArrow());
    }

    public void interactWithShopkeeper() {
        System.out.println("Interacted with Shopkeeper inside Shop");
        if (coins >= 1) {
            System.out.println("Hey, do you want to buy a treat for 1 coin? [y/n]");
            if(scanner.hasNextLine()) {
                scanner.nextLine();
            }
            String decision = scanner.nextLine();

            if (decision.equals("y")) {
                System.out.println("How many do you want? You have " + coins + " coins.");
                String amount = null;
                if (scanner.hasNextLine()) {
                    amount = scanner.nextLine();
                }
                if (Integer.parseInt(amount) > coins || Integer.parseInt(amount) < 0) {
                    System.out.println("You can't buy " + amount + " treats!");
                }
                else {
                    coins = coins - Integer.parseInt(amount);
                    treats = treats + Integer.parseInt(amount);
                    System.out.println("You bought " + amount + " treats! Have a nice day!");
                }
            }
        }
    }

    public void interactWithNurse() {
        System.out.println("Hello " + trainer.getName() + "!");
        System.out.println("Here in the medical center, you can heal your Jokemons!");
        System.out.println("Do you want to heal them? [y/n]");
        // Clear the buffer
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        String decision = scanner.nextLine();
        if (decision.equalsIgnoreCase("y")) {
            trainer.regenerateAllJokemons();
            System.out.println("Healed all your Jokemons! Have a nice day!");
        } else {
            System.out.println("Okay. Have a nice day!");
        }
    }

    // Evolve Jokemon
    public void evolveJokemon(Map<String, List<String>> movesMap) {
        boolean evolutionsAvailable = trainer.areEvolutionsAvailable();
        if (!evolutionsAvailable) {
            System.out.println("Cannot evolve any Jokemon!");
            return;
        }

        System.out.println("Input the name of the Jokemon you want to evolve (or type 'none' to skip):");
        String name = scanner.nextLine();

        if (name == null || name.equalsIgnoreCase("none")) {
            System.out.println("No Jokemon was selected for evolution.");
            return;
        }
        Jokemon jokemon = null;
        while (jokemon == null) {
            for (Jokemon currentJokemon : trainer.getMyJokemons()) {
                if (currentJokemon.getName().equalsIgnoreCase(name)) {
                    jokemon = currentJokemon;
                    break;
                }
            }

            if (jokemon == null) {
                System.out.println("No Jokemon with the name '" + name + "' found. Please enter the correct name or 'none' to skip:");
                name = scanner.nextLine();
                if (name.equalsIgnoreCase("none")) {
                    System.out.println("No Jokemon was selected for evolution.");
                    return;
                }
            }
        }

        // Proceed with evolving the found Jokemon
        Evolve evolve = new Evolve(trainer, jokemon, movesMap);
        evolve.evolveJokemon();
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

    public char getSpaceInFront() {
        int[] newPosition = calculateNewPosition(1);
        int newX = newPosition[0];
        int newY = newPosition[1];

        if (!isInsideBuilding) {
            if (newX >= 0 && newX < screen.getGridSize() && newY >= 0 && newY < screen.getGridSize()) {
                return screen.getCellContent(newX, newY);
            } else {
                return ' ';
            }
        }
        else {
            if (newX >= 0 && newX < interiorScreen.getGridSize()[0] && newY >= 0 && newY < interiorScreen.getGridSize()[1]) {
                return interiorScreen.getCellContent(newX, newY);
            } else {
                return ' ';
            }
        }
    }

    public char getSpaceDoubleInFront() {
        int[] newPosition = calculateNewPosition(2);
        int newX = newPosition[0];
        int newY = newPosition[1];

        if (!isInsideBuilding) {
            if (newX >= 0 && newX < screen.getGridSize() && newY >= 0 && newY < screen.getGridSize()) {
                return screen.getCellContent(newX, newY);
            } else {
                return ' ';
            }
        }
        else {
            if (newX >= 0 && newX < interiorScreen.getGridSize()[0] && newY >= 0 && newY < interiorScreen.getGridSize()[1]) {
                return interiorScreen.getCellContent(newX, newY);
            } else {
                return ' ';
            }
        }
    }

    public int[] getLocationOfSpaceInFront() {
        int[] location = calculateNewPosition(1);
        return location;
    }

    public int[] getLocationOfSpaceDoubleInFront() {
        int[] location = calculateNewPosition(2);
        return location;
    }

    private int[] calculateNewPosition(int stepDistance) {
        int newX = x;
        int newY = y;

        switch (direction) {
            case 0: // Up
                newX = isInsideBuilding ? x : x - stepDistance;
                newY = isInsideBuilding ? y - stepDistance : y;
                break;
            case 1: // Right
                newX = isInsideBuilding ? x + stepDistance : x;
                newY = isInsideBuilding ? y : y + stepDistance;
                break;
            case 2: // Down
                newX = isInsideBuilding ? x : x + stepDistance;
                newY = isInsideBuilding ? y + stepDistance : y;
                break;
            case 3: // Left
                newX = isInsideBuilding ? x - stepDistance : x;
                newY = isInsideBuilding ? y : y - stepDistance;
                break;
        }

        return new int[]{newX, newY};
    }

    public char getArrow() {
        return arrows[direction];
    }

    public Trainer getTrainer() {
        return trainer;
    }

    private void randomEncounterBattle() {
        Screen.Region currentRegion = screen.getRegion(x,y);
        Random random = new Random();
        if (aliveJokemon()) {
            if (random.nextInt(255) == 0 && currentRegion != null && !isInsideBuilding) {
                Battle randomBattle = new Battle(randomTrainer(currentRegion), trainer);
                randomBattle.fight();
            }
        }
    }

    private void randomEncounterJokemon() {
        Screen.Region currentRegion = screen.getRegion(x,y);
        Random random = new Random();
        if (random.nextInt(255) == 0 && currentRegion != null && !isInsideBuilding) {
            Evolve randomEvolve = new Evolve(trainer, randomJokemon(currentRegion), movesMap);
            randomEvolve.evolveJokemon();
        }
    }

    private Jokemon randomJokemon(Screen.Region region) {
        Jokemon randomJokemon = null;

        switch (region) {
            case DESERT:
                randomJokemon = new FireJokemon("Glomanda", 13, 1, 39, 52, 43, 60,50, 65, movesMap.get("Glomanda"));
                break;
            case MOUNTAIN:
                randomJokemon = new WaterJokemon("Squirtle", 8, 1, 44, 48, 65, 43, 50, 64, movesMap.get("Squirtle"));
                break;
            case GRASSLAND:
                Random rand = new Random();
                boolean i = rand.nextBoolean();
                if (i) randomJokemon = new WaterJokemon("Squirtle", 8, 1, 44, 48, 65, 43, 50, 64, movesMap.get("Squirtle"));
                else randomJokemon = new GrassJokemon("Bulbasaur", 11, 1, 45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur"));
            case FOREST:
                randomJokemon = new GrassJokemon("Bulbasaur", 11, 1, 45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur"));
                break;
        }
        return randomJokemon;
    }

    private Trainer randomTrainer(Screen.Region region) {
        String name = "Grass Fighter";
        Random random = new Random();
        int lvl = random.nextInt(10, 50);
        switch (region) {
            case DESERT:
                name = "Desert Fighter";
                break;
            case MOUNTAIN:
                name = "Mountain Fighter";
                break;
            case GRASSLAND:
                name = "Grass Fighter";
                break;
            case FOREST:
                name = "Forest Fighter";
                break;
        }
        return new Trainer(Trainer.Status.BOT, name, lvl, jokemonForRandomTrainer(lvl, region));
    }

    private List<Jokemon> jokemonForRandomTrainer(int lvl, Screen.Region region) {
        List<Jokemon> jokemons = new ArrayList<>();
        switch (region) {
            case DESERT:
                jokemons.add(new FireJokemon("Glomanda", 13, 1, 39, 52, 43, 60,50, 65, movesMap.get("Glomanda")));
                jokemons.add(new GrassJokemon("Bulbasaur", 16, 1, 45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur")));
                jokemons.add(new FireJokemon("Charmeleon", 27, 2, 58, 64, 58, 80, 80, 65, movesMap.get("Charmeleon")));
                jokemons.add(new GrassJokemon("Ivysaur", 23, 2, 60, 62, 63, 60, 80, 80, movesMap.get("Ivysaur")));
                jokemons.add(new FireJokemon("Charizard", 44, 3, 78, 84, 78, 109, 85, 100, movesMap.get("Charizard")));
                break;
            case MOUNTAIN:
                jokemons.add(new WaterJokemon("Squirtle", 8, 1, 44, 48, 65, 43, 50, 64, movesMap.get("Squirtle")));
                jokemons.add(new GrassJokemon("Bulbasaur", 12, 1, 45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur")));
                jokemons.add(new WaterJokemon("Wartortle", 25, 2, 59, 63, 80, 58, 65, 80, movesMap.get("Wartortle")));
                jokemons.add(new GrassJokemon("Ivysaur", 23, 2, 60, 62, 63, 60, 80, 80, movesMap.get("Ivysaur")));
                jokemons.add(new WaterJokemon("Blastoise", 42, 3, 79, 83, 100, 85, 105, 78, movesMap.get("Blastoise")));
                break;
            case GRASSLAND:
                jokemons.add(new FireJokemon("Glomanda", 14, 1, 39, 52, 43, 60,50, 65, movesMap.get("Glomanda")));
                jokemons.add(new WaterJokemon("Squirtle", 15, 1, 44, 48, 65, 43, 50, 64, movesMap.get("Squirtle")));
                jokemons.add(new GrassJokemon("Ivysaur", 23, 2, 60, 62, 63, 60, 80, 80, movesMap.get("Ivysaur")));
                jokemons.add(new WaterJokemon("Wartortle", 25, 2, 59, 63, 80, 58, 65, 80, movesMap.get("Wartortle")));
                jokemons.add(new FireJokemon("Charizard", 44, 3, 78, 84, 78, 109, 85, 100, movesMap.get("Charizard")));
                break;
            case FOREST:
                jokemons.add(new GrassJokemon("Bulbasaur", 11, 1, 45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur")));
                jokemons.add(new WaterJokemon("Squirtle", 16, 1, 44, 48, 65, 43, 50, 64, movesMap.get("Squirtle")));
                jokemons.add(new GrassJokemon("Ivysaur", 23, 2, 60, 62, 63, 60, 80, 80, movesMap.get("Ivysaur")));
                jokemons.add(new WaterJokemon("Wartortle", 24, 2, 59, 63, 80, 58, 65, 80, movesMap.get("Wartortle")));
                jokemons.add(new GrassJokemon("Venusaur", 40, 3, 80, 82, 83, 100, 100, 80, movesMap.get("Venusaur")));
                break;
        }
        if (lvl <= 15) {
            jokemons.remove(jokemons.size()-3);
        }
        if (lvl <= 30) {
            jokemons.remove(jokemons.size()-2);
        }
        if (lvl <= 40) {
            jokemons.remove(jokemons.size()-1);
        }
        Collections.shuffle(jokemons);
        return jokemons;
    }

    private boolean aliveJokemon() {
        boolean alive = false;
        for (Jokemon i : trainer.getMyJokemons()) {
            if (i.getStatus() == Jokemon.Status.ALIVE) {
                alive = true;
                break;
            }
        }
        return alive;
    }

    public void stats() {
        System.out.println("All stats for " + trainer.getName() + ":");
        System.out.println("Level: " + trainer.getLevel());
        System.out.println("Coins: " + coins);
        System.out.println("Treats: " + treats);
        System.out.println(ANSI_CODES.BOLD + "Jokemons:" + ANSI_CODES.RESET);
        for (Jokemon i : trainer.getMyJokemons()) {
            System.out.println(i.getName());
            System.out.print("Level: " + i.getLevel());
            if (i.isEvolutionAvailable()) System.out.println(ANSI_CODES.ITALIC + " (can evolve)" + ANSI_CODES.RESET);
            else System.out.println(ANSI_CODES.ITALIC + " (cannot evolve)" + ANSI_CODES.RESET);
            System.out.println("Moves (" + i.getMoves().size() + "): ");
            for (String j : i.getMoves()) {
                System.out.print(ANSI_CODES.UNDERLINE + j + ", ");
            }
            System.out.println(ANSI_CODES.RESET);
        }
    }

    public int getDirection() {
        return direction;
    }

    public char[] getArrows() {
        return arrows;
    }

    public Screen getScreen() {
        return screen;
    }

    public InteriorScreen getInteriorScreen() {
        return interiorScreen;
    }

    public boolean isInsideBuilding() {
        return isInsideBuilding;
    }

    public int getCoins() {
        return coins;
    }

    public int getTreats() {
        return treats;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setArrows(char[] arrows) {
        this.arrows = arrows;
    }

    public void setInteriorScreen(InteriorScreen interiorScreen) {
        this.interiorScreen = interiorScreen;
    }

    public void setInsideBuilding(boolean insideBuilding) {
        isInsideBuilding = insideBuilding;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setTreats(int treats) {
        this.treats = treats;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}