package ch.bbw.cge;

import ch.bbw.cge.jokemon.*;
import ch.bbw.cge.jokemon.move.Move;
import ch.bbw.cge.map.Player;
import ch.bbw.cge.map.screens.Screen;
import ch.bbw.cge.map.SeedLoading;
import ch.bbw.cge.map.objects.WildJokemon;
import ch.bbw.cge.saveSystem.GameState;
import ch.bbw.cge.saveSystem.JokemonData;
import ch.bbw.cge.saveSystem.SaveSystem;
import ch.bbw.cge.saveSystem.WildJokemonData;

import java.io.File;
import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char jokemonChar = 'Ɉ';
        char doorChar = '◫';

        Map<String, List<String>> movesMap = new HashMap<>();
        movesMap.put("Squirtle", List.of("Tackle", "Water Gun", "Bubble"));
        movesMap.put("Glomanda", List.of("Scratch", "Fire Spin", "Ember"));
        movesMap.put("Schaggi", List.of("Tackle", "Water Gun", "Waterfall"));
        movesMap.put("Bulbasaur", List.of("Tackle", "Absorb", "Mega Drain"));
        movesMap.put("Ivysaur", List.of("Tackle", "Absorb", "Mega Drain","Razor Leaf", "Petal Dance"));
        movesMap.put("Wartortle", List.of("Tackle", "Water Gun", "Waterfall","Crabhammer", "Bubble Beam"));
        movesMap.put("Charmeleon", List.of("Scratch", "Fire Spin", "Ember","Fire Punch", "Flamethrower"));
        movesMap.put("Charizard", List.of("Scratch", "Fire Spin", "Ember","Fire Punch", "Flamethrower","Fire Blast"));
        movesMap.put("Venusaur", List.of("Tackle", "Absorb", "Mega Drain","Razor Leaf", "Petal Dance","Solar Beam"));
        movesMap.put("Blastoise", List.of("Tackle", "Water Gun", "Waterfall","Crabhammer", "Bubble Beam","Hydro Pump"));

        Jokemon schaggi = new WaterJokemon("Schaggi", 25, 1, 44, 48, 65, 50, 64, 43, movesMap.get("Schaggi"));
        Jokemon bulbasaur = new GrassJokemon("Bulbasaur", 16, 1, 45, 49, 49, 45, 65, 65, movesMap.get("Bulbasaur"));
        Trainer user = new Trainer(Trainer.Status.USER, "Sage", 33    , List.of(bulbasaur, schaggi));

        //TODO add more items to the shop
        //TODO add more pokeballs with better catching percentages to catch

        Screen screen = new Screen(user);
        Player player = new Player(screen, user, movesMap);
        System.out.println("What seed do you want to play? '1', '2', '3', '4', '5'?");
        int seed = scanner.nextInt();

        SeedLoading test = new SeedLoading();
        test.loadSeed(seed, player, movesMap);
        screen.loadSeed(test.getSeedData());

        if (doesSaveExist(seed)) {
            System.out.println("Do you want to load your last save for seed " + seed + "? [y/n]");
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            String decision = scanner.nextLine();
            if (decision.equalsIgnoreCase("y")) {
                loadGameState(player, seed, screen);
            }
            else System.out.println("Not loaded save state.");
        }

        screen.initializeGrid(player, user);
        screen.printGrid(player.getX(), player.getY());

       /* OLD CODE BEFORE SEED LOADING
        screen.setAllNPC(List.of(bot1, bot2));
        screen.setAllWildJokemons(List.of(freshBulbasaur, freshGlomanda));
        screen.setAllBuildings(List.of(medicalCenter, groceryShop, playerHome));
        screen.initializeGrid(player, user);
        screen.printGrid(player.getX(), player.getY());*/

        int movesSinceLastSave = 0;

        System.out.println("Enter commands (r = turn right, l = turn left, m = move forward, e = interact with NPC / Jokemon / Building,\n" +
                " i = info of space in front of you, g = evolve Jokemon, u = full map view, s = inventory, q = quit):");
        while (true) {
            char command = scanner.next().charAt(0);
            movesSinceLastSave++;
            if (movesSinceLastSave % 10 == 0)  {
                System.out.println("Enter commands (r = turn right, l = turn left, m = move forward, e = interact with NPC / Jokemon / Building,\n" +
                        " i = info of space in front of you, g = evolve Jokemon, u = full map view, s = inventory, q = quit):");
            }
            else {
                System.out.println("Enter command: 'r', 'l', 'm', 'e', 'g', 'u', 's' or 'q'");
            }
            switch (command) {
                case 'r':
                    player.turnRight();
                    break;
                case 'l':
                    player.turnLeft();
                    break;
                case 'm':
                    player.moveForward();
                    break;
                case 'e':
                    if (player.getSpaceInFront() == 'N') player.interactWithNPC();
                    if (player.getSpaceInFront() == jokemonChar) player.interactWithJokemon();
                    if (player.getSpaceInFront() == doorChar && screen.findBuildingInList(player.getLocationOfSpaceDoubleInFront()[0], player.getLocationOfSpaceDoubleInFront()[1]).getCanEnter()) player.interactWithBuilding();
                    else System.out.println("Cannot interact here! Either there is an obstacle or the building can't be entered!");
                    break;
                case 'g':
                    player.evolveJokemon(movesMap);
                    break;
                case 'u':
                    screen.printFullGrid();
                    break;
                case 's':
                    player.stats();
                    break;
                case 'z':
                    movesSinceLastSave = 0;
                    saveGameState(player, seed, screen);
                    break;
                case 'q':
                    System.out.println("Last Save was " + movesSinceLastSave + " moves ago. Do you want to save? [y/n]");
                    char decision = scanner.next().charAt(0);
                    if (decision == 'y') saveGameState(player, seed, screen);
                    System.out.println("Game Over!");
                    return;
                default:
                    System.out.println("Invalid command! Use 'r', 'l', 'm', 'e', 'g', 'u', 's' or 'q'.");
            }
            screen.printGrid(player.getX(), player.getY());
            user.updateLevel();
        }
    }

    private static void saveGameState(Player player, int seed, Screen screen) {
        System.out.println("Saving gamestate...");
        List<JokemonData> jokemonData = new ArrayList<>();
        for (Jokemon jokemon : player.getTrainer().getMyJokemons()) {
            jokemonData.add(new JokemonData(jokemon.getName(), jokemon.getLevel(), jokemon.getEvolution(), jokemon.getBaseHp(), jokemon.getBaseAttack(),
                    jokemon.getBaseDefense(), jokemon.getBaseSpeed(), jokemon.getBaseSpecialAttack(), jokemon.getBaseSpecialDefense(), jokemon.getType(),
                    jokemon.getMoves(), jokemon.getAdjustedBaseHp(), jokemon.getHp(), jokemon.getXp()));
        }
        List<WildJokemonData> wildJokemonData = new ArrayList<>();
        if (screen.getAllWildJokemons() != null){
            for (WildJokemon wildJokemon : screen.getAllWildJokemons()) {
                Jokemon jokemon = wildJokemon.getJokemon();
                JokemonData jJokemonData = new JokemonData(jokemon.getName(), jokemon.getLevel(), jokemon.getEvolution(), jokemon.getBaseHp(), jokemon.getBaseAttack(),
                        jokemon.getBaseDefense(), jokemon.getBaseSpeed(), jokemon.getBaseSpecialAttack(), jokemon.getBaseSpecialDefense(), jokemon.getType(),
                        jokemon.getMoves(), jokemon.getBaseHp(), jokemon.getHp(), jokemon.getXp());
                wildJokemonData.add(new WildJokemonData(wildJokemon.getX(), wildJokemon.getY(), wildJokemon.getName(), jJokemonData, wildJokemon.getIsCaught()));
            }
        }

        GameState gameState = new GameState(seed, player.getTrainer().getLevel(), player.getCoins(), player.getTreats(),
                new int[]{player.getX(), player.getY()}, player.getDirection(), jokemonData, wildJokemonData);
        SaveSystem.saveGame(gameState);
    }

    private static void loadGameState(Player player, int seed, Screen screen) {
        GameState gameState = SaveSystem.loadGame(seed);
        List<Jokemon> jokemons = new ArrayList<>();
        for (JokemonData jokemonData : gameState.getJokemons()) {
            jokemons.add(createJokemonFromData(jokemonData));
        }
        List<WildJokemon> wildJokemons = new ArrayList<>();
        if (gameState.getWildJokemons() != null) {
            for (WildJokemonData wildJokemonData : gameState.getWildJokemons()) {
                Jokemon jokemon = createJokemonFromData(wildJokemonData.getJokemonData());
                wildJokemons.add(new WildJokemon(wildJokemonData.getX(), wildJokemonData.getY(), jokemon, wildJokemonData.isCaught()));
            }
        }

        player.setX(gameState.getPlayerPosition()[0]);
        player.setY(gameState.getPlayerPosition()[1]);
        player.setTreats(gameState.getPlayerTreats());
        player.setCoins(gameState.getPlayerCoins());
        player.setDirection(gameState.getPlayerDirection());
        player.getTrainer().setLevel(gameState.getPlayerLevel());
        player.getTrainer().setMyJokemons(jokemons);
        screen.setAllWildJokemonsSeed(wildJokemons);
    }

    private static Jokemon createJokemonFromData(JokemonData data) {
        Jokemon jokemon = null;
        if (data.getType() == Move.Type.GRASS) {
            jokemon = new GrassJokemon(data.getName(),data.getLevel(), data.getEvolution(), data.getBaseHp(), data.getBaseAttack(), data.getBaseDefense(), data.getBaseSpeed(), data.getBaseSpecialAttack(), data.getBaseDefense(), data.getMoves());
        }
        if (data.getType() == Move.Type.WATER) {
            jokemon = new WaterJokemon(data.getName(),data.getLevel(), data.getEvolution(), data.getBaseHp(), data.getBaseAttack(), data.getBaseDefense(), data.getBaseSpeed(), data.getBaseSpecialAttack(), data.getBaseDefense(), data.getMoves());
        }
        if (data.getType() == Move.Type.FIRE) {
            jokemon = new FireJokemon(data.getName(),data.getLevel(), data.getEvolution(), data.getBaseHp(), data.getBaseAttack(), data.getBaseDefense(), data.getBaseSpeed(), data.getBaseSpecialAttack(), data.getBaseDefense(), data.getMoves());
        }
        return jokemon;
    }

    private static boolean doesSaveExist(int seed) {
        String filename = "savedGames/save_seed_" + seed + ".dat";
        File file = new File(filename);
        return file.exists();
    }
}
