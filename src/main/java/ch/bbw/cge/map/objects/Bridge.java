package ch.bbw.cge.map.objects;

public class Bridge {
    public static void placeBridge(char[][] grid, int x, int y) {
        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
            grid[x][y] = '█';
        }
    }
}
