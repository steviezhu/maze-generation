/**
 * Holds coordinates of Cat and handles random movement of the object
 */

package cmpt213.as3.gamelogic;

import java.util.Random;

public class Cat {
    private int x;
    private int y;
    private int lastMove = -1;

    private int UP = 0;
    private int DOWN = 2;
    private int RIGHT = 1;
    private int LEFT = 3;

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
        boolean leftBlocked = false;
        boolean rightBlocked = false;
        boolean upBlocked = false;
        boolean downBlocked = false;
        int dice = -1;
        while (collisionFlag) {
            dice = randomNumGenerator.nextInt(4);
            if (dice == UP && (DOWN != this.lastMove || blockedDirections == 3)) {
                if (!maze.objectsCollide(this.x, this.y+1)) {
                    this.y = this.y+1;
                    collisionFlag = false;
                }
                else if (!upBlocked){
                    blockedDirections++;
                    upBlocked = true;
                }
            }
            else if (dice == RIGHT && (LEFT != this.lastMove || blockedDirections == 3)) {
                if (!maze.objectsCollide(this.x+1, this.y)) {
                    this.x = this.x+1;
                    collisionFlag = false;
                }
                else if (!rightBlocked){
                    blockedDirections++;
                    rightBlocked = true;
                }
            }
            else if (dice == DOWN && (UP != this.lastMove || blockedDirections == 3)) {
                if (!maze.objectsCollide(this.x, this.y-1)) {
                    this.y = this.y-1;
                    collisionFlag = false;
                }
                else if (!downBlocked){
                    blockedDirections++;
                    downBlocked = true;
                }
            }
            else if (dice == LEFT && (RIGHT != this.lastMove || blockedDirections == 3)){
                if (!maze.objectsCollide(this.x-1, this.y)) {
                    this.x = this.x-1;
                    collisionFlag = false;
                }
                else if (!leftBlocked){
                    blockedDirections++;
                    leftBlocked = true;
                }
            }
        }
        this.lastMove = dice;
    }



}
