import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LifeSimulation {
    public static void main(String[] args) throws InterruptedException {
        double chance = initialChances();
        List<int[][]> lookedAt = new ArrayList<>();
        
        for(int i = 0; i < 1000; i++) {
            Landscape scape = new Landscape(100, 100, chance);
            float living = livingCells(scape);
            LandscapeDisplay display = new LandscapeDisplay(scape, 6);
            float livingAverage = living / 1000;

            
            System.out.println("------------------------------------------------------------------------");
            System.out.println("| Living Cell Counts / Simulation: " + livingAverage + " | Chance: " + chance + "|");
            System.out.println("-------------------------------------------------------------------------");
            
            
            if (i > 0 && lookingTowardsFuture(scape.getGrid(), lookedAt)) {
                System.out.println("Infinite loop detected.");
                break;
            }
            else {
                System.out.println("No infinite loop detected");
            }
            
            while (true) {
                Thread.sleep(250);
                scape.advance();
                display.repaint();
            }
        }
    }

    
    private static double initialChances() {
        Random rand = new Random();
        return rand.nextDouble(); 
    }

    
    private static int livingCells(Landscape landscape) { 
        int livingCells = 0;
        int rows = landscape.getRows();
        int cols = landscape.getCols();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (landscape.getCell(i, j).getAlive()) {
                    livingCells++;
                }
            }
        }
        return livingCells;
    }

    
    public static boolean lookingTowardsFuture(int[][] grid, List<int[][]> lookedAt) {
        for(int[][] elem : lookedAt) {
            if(equalGrids(elem, grid)) {
                return true;
            }
        }
        lookedAt.add(gridCopy(grid));
        return false;
    }

    
    private static boolean equalGrids(int[][] tempGrid1, int[][] tempGrid2) {
        if (tempGrid1.length != tempGrid2.length || tempGrid1[0].length != tempGrid2[0].length) {
            return false;
        }
        for (int i = 0; i < tempGrid1.length; i++) {
            for (int j = 0; j < tempGrid1[0].length; j++) {
                if (tempGrid1[i][j] != tempGrid2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    
    private static int[][] gridCopy(int[][] grid) {
        int[][] copy = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, grid[i].length);
        }
        return copy;
    }
}