package ch.bbw.cge.map.objects;

public class Tree {
    // Place a tree at a specific location
    public static void placeTree(char[][] grid, int x, int y) {
        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
            grid[x][y] = 'T';
        }
    }
}
