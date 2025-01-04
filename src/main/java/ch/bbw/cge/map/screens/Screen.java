package ch.bbw.cge.map.screens;

import ch.bbw.cge.ANSI_CODES;
import ch.bbw.cge.jokemon.Trainer;
import ch.bbw.cge.building.Building;
import ch.bbw.cge.map.Player;
import ch.bbw.cge.map.objects.*;

import java.util.*;

public class Screen {
    // Full grid size (25x25)
    private static final int FULL_GRID_SIZE = 50;
    private static final int DISPLAY_GRID_SIZE = 10; // Display grid size (10x10 centered around player)
    private Region[][] regionMap = new Region[FULL_GRID_SIZE][FULL_GRID_SIZE];

    private char[][] grid = new char[FULL_GRID_SIZE][FULL_GRID_SIZE];

    private List<NPC> allNPCs = new ArrayList<>();
    private List<WildJokemon> allWildJokemons = new ArrayList<>();
    private List<Building> allBuildings = new ArrayList<>();

    private Trainer user;

    private static final char WILD_JOKEMON_CHAR = 'Ɉ';
    private static final char TREE_CHAR = 'T';
    private static final char NPC_CHAR = 'N';
    private static final char EMPTY_FIELD_CHAR = '░';
    private static final char DOOR_CHAR = '◫';
    private static final char WATER_CHAR = '▒';
    private static final char BRIDGE_CHAR = '█';

    private int[][] bridgePositions = {};
    private int[][] treePositions = {};
    private int[][] waterPositions = {};

    private static final ANSI_CODES ansi = new ANSI_CODES();

    public Screen(Trainer user) {
        this.user = user;

    }

    public enum Region {
        FOREST,
        MOUNTAIN,
        GRASSLAND,
        DESERT,
        WATER,
        INSIDE
    }

    // Initialize the grid
    public void initializeGrid(Player player, Trainer user) {
        for (int i = 0; i < FULL_GRID_SIZE; i++) {
            for (int j = 0; j < FULL_GRID_SIZE; j++) {
                grid[i][j] = EMPTY_FIELD_CHAR; // Empty space
            }
        }
        this.user = user;

        placeTrees();
        placeWaters();
        placeBuildings();
        placeBridges();

        for (NPC i : allNPCs) {
            NPC.placeNPC(grid, i.getX(), i.getY());
        }
        for (WildJokemon i : allWildJokemons) {
            WildJokemon.placeWildJokemon(grid, i.getX(), i.getY());
        }
        // Update the player's starting position
        grid[player.getX()][player.getY()] = player.getArrow();
    }

    // Get the grid size
    public int getGridSize() {
        return FULL_GRID_SIZE;
    }

    // Check if a move is valid
    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < FULL_GRID_SIZE && y >= 0 && y < FULL_GRID_SIZE &&
                grid[x][y] != TREE_CHAR && grid[x][y] != NPC_CHAR &&
                grid[x][y] != WILD_JOKEMON_CHAR && !isBuildingBlocked(x, y)
                && grid[x][y] != WATER_CHAR && grid[x][y] != DOOR_CHAR;
    }

    // Check if a building is blocking the move
    private boolean isBuildingBlocked(int x, int y) {
        for (Building building : allBuildings) {
            int buildingX = building.getX();
            int buildingY = building.getY();

            // Check if the player's position overlaps with the building
            if (x >= buildingX - 1 && x <= buildingX + 1 &&
                    y >= buildingY - 1 && y <= buildingY + 1) {
                if (!(grid[x][y] == '◫' && (building.getBuildingType() == Building.BuildingType.MEDICAL ||
                        building.getBuildingType() == Building.BuildingType.SHOP ||
                        building.getBuildingType() == Building.BuildingType.HOUSE))) {
                    return true; // Block the move
                }
            }
        }
        return false; // Not blocked
    }

    // Update the grid with the player's new position and direction
    public void updatePlayerPosition(int x, int y, char direction) {
        grid[x][y] = direction;
        System.out.println("Updated player pos to: " + x + y);
    }

    // Clear the position on the grid
    public void clearPosition(int x, int y) {
        grid[x][y] = EMPTY_FIELD_CHAR;
        for (int[] bridgePosition : bridgePositions) {
            grid[bridgePosition[0]][bridgePosition[1]] = BRIDGE_CHAR;
        }
    }

    // New common method to print a grid section
    private void printGridSection(int startX, int endX, int startY, int endY) {
        for (int i = startX; i < endX; i++) {
            for (int j = startY; j < endY; j++) {
                char currentCell = grid[i][j];
                Region currentRegion = regionMap[i][j];
                System.out.print(ansi.colorChar(currentCell, Building.BuildingType.OUTSIDE, currentRegion));
            }
            System.out.println();
        }
    }

    // Print a 10x10 grid centered around the player's position with colors
    public void printGrid(int playerX, int playerY) {
        // Check if any WildJokemon has been caught before printing the grid
        removeCaughtJokemon();

        // Calculate the boundaries of the 10x10 grid centered around the player
        int startX = Math.max(0, playerX - DISPLAY_GRID_SIZE / 2);
        int startY = Math.max(0, playerY - DISPLAY_GRID_SIZE / 2);
        int endX = Math.min(FULL_GRID_SIZE, startX + DISPLAY_GRID_SIZE);
        int endY = Math.min(FULL_GRID_SIZE, startY + DISPLAY_GRID_SIZE);

        // Adjust the start positions if we're at the edges
        if (endX - startX < DISPLAY_GRID_SIZE) {
            startX = Math.max(0, endX - DISPLAY_GRID_SIZE);
        }
        if (endY - startY < DISPLAY_GRID_SIZE) {
            startY = Math.max(0, endY - DISPLAY_GRID_SIZE);
        }

        // Print the 10x10 grid
        printGridSection(startX, endX, startY, endY);

        // Print the player's coordinates
        System.out.println("\nPlayer Coordinates: (X: " + playerX + ", Y: " + playerY + ")");
    }

    public void printFullGrid() {
        System.out.println("*--------------------FULL MAP-------------------*");

        // Print the full grid
        printGridSection(0, FULL_GRID_SIZE, 0, FULL_GRID_SIZE);

        System.out.println("*--------------------FULL MAP-------------------*");
        System.out.println();
        System.out.println("*----PLAYER CENTERED MAP----*");
    }


    public char getCellContent(int x, int y) {
        return grid[x][y];
    }

    public NPC findNPCinList(int x, int y) {
        for (NPC npc : allNPCs) {
            if (npc.getX() == x && npc.getY() == y) {
                return npc;
            }
        }
        return null;
    }

    public WildJokemon findWildJokemonInList(int x, int y) {
        for (WildJokemon i : allWildJokemons) {
            if (i.getX() == x && i.getY() == y) {
                return i;
            }
        }
        return null;
    }

    public Building findBuildingInList(int x, int y) {
        for (Building i : allBuildings) {
            if (i.getX() == x && i.getY() == y) {
                return i;
            }
        }
        return null;
    }

    // Method to check and remove caught WildJokemons from the grid
    private void removeCaughtJokemon() {
        Iterator<WildJokemon> iterator = allWildJokemons.iterator();
        while (iterator.hasNext()) {
            WildJokemon jokemon = iterator.next();
            if (jokemon.getIsCaught()) {
                clearPosition(jokemon.getX(), jokemon.getY());
                iterator.remove();
            }
        }
    }

    // Method to place multiple trees across the map
    private void placeTrees() {
        for (int[] pos : treePositions) {
            int x = pos[0];
            int y = pos[1];
            if (x < FULL_GRID_SIZE && y < FULL_GRID_SIZE) {
                Tree.placeTree(grid, x, y);
            }
        }
    }

    // Method to place multiple waters across the map
    private void placeWaters() {
        for (int[] pos : waterPositions) {
            int x = pos[0];
            int y = pos[1];
            if (x < FULL_GRID_SIZE && y < FULL_GRID_SIZE) {
                Water.placeWater(grid, x, y);
            }
        }
    }

    private void placeBridges() {
        for (int[] pos : bridgePositions) {
            int x = pos[0];
            int y = pos[1];
            if (x < FULL_GRID_SIZE && y < FULL_GRID_SIZE) {
                Bridge.placeBridge(grid, x, y);
            }
        }
    }

    // Method to place multiple buildings across the map
    private void placeBuildings() {
        for (Building building : allBuildings) {
            placeBuilding(grid, building);
        }
    }
    public void placeBuilding(char[][] grid, Building building) {
        char[][] design = building.getBuildingDesign();
        int doorX = building.getX();
        int doorY = building.getY();

        int startX = doorX - 1;
        int startY = doorY - 1;

        // Place the 3x3 design on the grid
        for (int i = 0; i < design.length; i++) {
            for (int j = 0; j < design[i].length; j++) {
                int gridX = startX + i;
                int gridY = startY + j;
                if (gridX >= 0 && gridX < grid.length && gridY >= 0 && gridY < grid[0].length) {
                    grid[gridX][gridY] = design[i][j];
                }
            }
        }
    }

    // Experimental Seed loading
    public void loadSeed(List<Object> seedData){
        setAllNPCseed((List<NPC>) seedData.get(0));
        setAllWildJokemonsSeed((List<WildJokemon>) seedData.get(1));
        setAllBuildingsSeed((List<Building>) seedData.get(2));
        treePositions = (int[][]) seedData.get(3);
        bridgePositions = (int[][]) seedData.get(4);
        waterPositions = (int[][]) seedData.get(5);
        regionMap = (Region[][]) seedData.get(6);
    }

    public void setAllNPCseed(List<NPC> allNPCs) {
        this.allNPCs = allNPCs;
    }
    public void setAllWildJokemonsSeed(List<WildJokemon> allWildJokemons) {
        this.allWildJokemons = allWildJokemons;
    }
    public void setAllBuildingsSeed(List<Building> allBuildings) {
        this.allBuildings = allBuildings;
    }

    public Region getRegion(int x, int y) {
        return regionMap[x][y];
    }

    public List<WildJokemon> getAllWildJokemons() {
        return this.allWildJokemons;
    }
}