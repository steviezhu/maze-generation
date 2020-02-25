package cmpt213.as3.gamelogic;

import java.util.Random;

public class Cheese {
    private int x;
    private int y;

    public Cheese(Maze maze, Mouse mouse) {
        int tempMaze[][] = maze.getMaze();

        Random randomNumGenerator = new Random();
        int x = randomNumGenerator.nextInt(19);
        int y = randomNumGenerator.nextInt(14);
        while (tempMaze[x][y] == 1 || ( x == mouse.getX() && y == mouse.getY() ) ) {
            x = randomNumGenerator.nextInt(19);
            y = randomNumGenerator.nextInt(14);
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
