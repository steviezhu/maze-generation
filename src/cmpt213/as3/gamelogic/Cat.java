package cmpt213.as3.gamelogic;

import java.util.Random;

public class Cat {
    private int x;
    private int y;

    public Cat(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }

    public void moveRandomly(Maze maze) {
        int tempMaze[][] = maze.getMaze();
        boolean collisionFlag = true;
        Random randomNumGenerator = new Random();
        while (collisionFlag) {
            int dice = randomNumGenerator.nextInt(4);
            if(dice == 0) {
                if(!maze.objectsCollide(this.x, this.y+1)) {
                    this.y = this.y+1;
                    collisionFlag = false;
                }
            }
            else if (dice == 1) {
                if(!maze.objectsCollide(this.x+1, this.y)) {
                    this.x = this.x+1;
                    collisionFlag = false;
                }
            }
            else if (dice == 2) {
                if(!maze.objectsCollide(this.x, this.y-1)) {
                    this.y = this.y-1;
                    collisionFlag = false;
                }
            }
            else {
                if(!maze.objectsCollide(this.x-1, this.y)) {
                    this.x = this.x-1;
                    collisionFlag = false;
                }
            }
        }
    }



}
