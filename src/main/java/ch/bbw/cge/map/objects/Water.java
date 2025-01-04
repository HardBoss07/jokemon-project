package ch.bbw.cge.map.objects;

public class Water {
    public static void placeWater(char[][] grid, int x, int y) {
        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
            grid[x][y] = '▒';
        }
    }
}
