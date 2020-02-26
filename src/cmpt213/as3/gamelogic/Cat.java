package cmpt213.as3.gamelogic;

import java.util.Random;

public class Cat {
    private int x;
    private int y;
    private int lastMove = -1;

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
        boolean collisionFlag = true;
        Random randomNumGenerator = new Random();
        int blockedDirections = 0;
        int dice = -1;
        while (collisionFlag) {
            dice = randomNumGenerator.nextInt(4);
            if (dice == 0 && (2 != this.lastMove || blockedDirections == 3)) {
                if (!maze.objectsCollide(this.x, this.y+1)) {
                    this.y = this.y+1;
                    collisionFlag = false;
                }
                else if (blockedDirections < 3){
                    blockedDirections++;
                }
            }
            else if (dice == 1 && (3 != this.lastMove || blockedDirections == 3)) {
                if (!maze.objectsCollide(this.x+1, this.y)) {
                    this.x = this.x+1;
                    collisionFlag = false;
                }
                else if (blockedDirections < 3){
                    blockedDirections++;
                }
            }
            else if (dice == 2 && (0 != this.lastMove || blockedDirections == 3)) {
                if (!maze.objectsCollide(this.x, this.y-1)) {
                    this.y = this.y-1;
                    collisionFlag = false;
                }
                else if (blockedDirections < 3){
                    blockedDirections++;
                }
            }
            else if (dice == 3 && (1 != this.lastMove || blockedDirections == 3)){
                if (!maze.objectsCollide(this.x-1, this.y)) {
                    this.x = this.x-1;
                    collisionFlag = false;
                }
                else if (blockedDirections < 3){
                    blockedDirections++;
                }
            }
        }
        this.lastMove = dice;
    }



}
