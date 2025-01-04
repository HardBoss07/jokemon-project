package ch.bbw.cge.map.screens;

import ch.bbw.cge.ANSI_CODES;
import ch.bbw.cge.building.Building;
import ch.bbw.cge.map.Player;

public class InteriorScreen {
    private static final int GRID_SIZE_X_AXIS = 15; // Columns (Width)
    private static final int GRID_SIZE_Y_AXIS = 7;  // Rows (Height)

    private char[][] grid = new char[GRID_SIZE_Y_AXIS][GRID_SIZE_X_AXIS]; // Fixed Y-axis and X-axis order
    private final Building building;

    private Player player;
    private int prevX;
    private int prevY;

    private static final char EMPTY_FIELD_CHAR = '█';
    private static final char DOOR_CHAR = '◫';
    private static final char VERTICAL_WALL_CHAR = '║';
    private static final char HORIZONTAL_WALL_CHAR = '═';
    private static final char UPPER_LEFT_CORNER_CHAR = '╔';
    private static final char UPPER_RIGHT_CORNER_CHAR = '╗';
    private static final char LOWER_LEFT_CORNER_CHAR = '╚';
    private static final char LOWER_RIGHT_CORNER_CHAR = '╝';
    private static final char DESK_LEFT_CORNER_CHAR = '└';
    private static final char DESK_RIGHT_CORNER_CHAR = '┘';
    private static final char HORIZONTAL_DESK_CHAR = '─';
    private static final char VERTICAL_DESK_CHAR = '│';
    private static final int[] entranceCords = {7, 5}; // Assuming starting point for entrance

    private final char[][] medicalMap = {
            {'╔','═','═','═','═','═','═','═','═','═','═','═','═','═','╗' },
            {'║','█','█','█','█','│','█','N','█','│','█','█','█','█','║' },
            {'║','█','█','█','█','└','─','─','─','┘','█','█','█','█','║' },
            {'║','█','█','█','█','█','█','█','█','█','█','█','█','█','║' },
            {'║','█','█','█','█','█','█','█','█','█','█','█','█','█','║' },
            {'║','█','█','█','█','█','█','█','█','█','█','█','█','█','║' },
            {'╚','═','═','═','═','═','═','◫','═','═','═','═','═','═','╝' }
    };

    private final char[][] shopMap = {
            {'╔','═','═','═','═','═','═','═','═','═','═','═','═','═','╗' },
            {'║','█','█','S','│','█','█','█','█','█','█','█','█','█','║' },
            {'║','─','─','─','┘','█','█','█','█','█','█','█','█','█','║' },
            {'║','█','█','█','█','█','█','█','█','█','█','█','█','█','║' },
            {'║','█','█','█','█','█','█','█','█','█','█','█','█','█','║' },
            {'║','█','█','█','█','█','█','█','█','█','█','█','█','█','║' },
            {'╚','═','═','═','═','═','═','◫','═','═','═','═','═','═','╝' }
    };

    private final char[][] houseMap = {
            {'╔','═','═','═','═','═','═','═','═','═','═','═','═','═','╗' },
            {'║','█','█','█','█','█','█','█','█','█','█','█','█','█','║' },
            {'║','█','█','█','█','█','█','$','█','█','█','█','█','█','║' },
            {'║','█','█','█','█','█','█','█','█','█','█','█','█','█','║' },
            {'║','█','█','█','█','█','█','█','█','█','█','█','█','█','║' },
            {'║','█','█','█','█','█','█','█','█','█','█','█','█','█','║' },
            {'╚','═','═','═','═','═','═','◫','═','═','═','═','═','═','╝' }
    };


    public InteriorScreen(Building building) {
        this.building = building;
    }

    public void initializeMap(Player player) {
        if (building.getBuildingType() == Building.BuildingType.MEDICAL) grid = medicalMap;
        if (building.getBuildingType() == Building.BuildingType.SHOP) grid = shopMap;
        if (building.getBuildingType() == Building.BuildingType.HOUSE) grid = houseMap;

        // Place Player
        this.player = player;
        prevX = this.player.getX();
        prevY = this.player.getY();
        System.out.println(prevX + prevY);
        System.out.println(this.player.getX() + this.player.getY());
        this.player.setX(entranceCords[0]);
        this.player.setY(entranceCords[1]);
        this.player.setIsInsideBuilding(true);
        grid[player.getY()][player.getX()] = player.getArrow();
    }

    public void printMap() {
        ANSI_CODES ansi = new ANSI_CODES();
        for (int i = 0; i < GRID_SIZE_Y_AXIS; i++) {
            for (int j = 0; j < GRID_SIZE_X_AXIS; j++) {
                System.out.print(ansi.colorChar(grid[i][j], building.getBuildingType(), Screen.Region.INSIDE));
            }
            System.out.println();
        }
    }

    public void updatePlayerPosition(int x, int y, char direction) {
        grid[y][x] = direction;
        System.out.println("updated inside pos to: " + x + y);
    }

    public void clearPosition(int x, int y) {
        grid[y][x] = EMPTY_FIELD_CHAR; // Updated to access correct grid indices
    }

    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < GRID_SIZE_X_AXIS && y >= 0 && y < GRID_SIZE_Y_AXIS &&
                grid[y][x] != VERTICAL_WALL_CHAR &&
                grid[y][x] != HORIZONTAL_WALL_CHAR &&
                grid[y][x] != UPPER_LEFT_CORNER_CHAR &&
                grid[y][x] != UPPER_RIGHT_CORNER_CHAR &&
                grid[y][x] != LOWER_LEFT_CORNER_CHAR &&
                grid[y][x] != LOWER_RIGHT_CORNER_CHAR &&
                grid[y][x] != VERTICAL_DESK_CHAR &&
                grid[y][x] != HORIZONTAL_DESK_CHAR &&
                grid[y][x] != DESK_LEFT_CORNER_CHAR &&
                grid[y][x] != DESK_RIGHT_CORNER_CHAR &&
                grid[y][x] != DOOR_CHAR &&
                grid[y][x] != '$';
    }

    public char getCellContent(int x, int y) {
        return grid[y][x];
    }

    public int[] getGridSize() {
        return new int[]{GRID_SIZE_X_AXIS, GRID_SIZE_Y_AXIS};
    }

    public int getPrevX() {
        return prevX;
    }

    public int getPrevY() {
        return prevY;
    }
}