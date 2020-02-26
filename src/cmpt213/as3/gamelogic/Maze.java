/**
 * Generates the maze and handles visibility and collisions within the maze
 */

package cmpt213.as3.gamelogic;

import java.util.Arrays;
import java.util.Collections;

public class Maze {
    private int[][] maze = new int[20][15];

    public Maze() {
        this.initializeMaze();
        this.generateMaze(7, 7);
        this.handleRightWall();
        this.hideInnerMaze();
    }

    public int[][] getMaze() {
        return maze;
    }

    //VISIBILITY BY NUMBER: 0 = NOT VISIBLE SPACE, 1 = NOT VISIBLE WALL, 2 = VISIBLE SPACE, 3 = VISIBLE WALL
    //MAZE IS INITIATED TO AN OUTER WALL OF 1'S THEN 0'S BOXED BY 1'S
    public void initializeMaze(){
        for(int i = 0; i < 20; i ++){
            for(int j = 0; j < 15; j ++){
                if((i % 2 == 1) && (j % 2 == 1)){
                    maze[i][j] = 0;
                }
                else{
                    maze[i][j] = 1;
                }
            }
        }

        for(int i = 0; i < 15; i ++){
            maze[19][i] = 1;
        }
    }

    public void generateMaze(int x, int y){
        Integer[] directions = {0,1,2,3};

        //SHUFFLE DIRECTIONS
        Collections.shuffle(Arrays.asList(directions));

        for(Integer i : directions){
            //NEW COORDINATES FOR RECURSIVE CALL
            int newX = 0;
            int newY = 0;

            //COORDINATES FOR CONNECTING PASSAGE
            int carveX = 0;
            int carveY = 0;

            //UP
            if(i == 0){
                newX = x;
                carveX = x;
                newY = y - 2;
                carveY = y - 1;
            }
            //DOWN
            if(i == 1){
                newX = x;
                carveX = x;
                newY = y + 2;
                carveY = y + 1;
            }
            //LEFT
            if(i == 2){
                newX = x - 2;
                carveX = x - 1;
                newY = y;
                carveY = y;
            }
            //RIGHT
            if (i == 3){
                newX = x + 2;
                carveX = x + 1;
                newY = y;
                carveY = y;
            }

            //CHECK WITHIN BOUNDS OF MAZE AND NEW COORDINATES ARE UNVISITED
            if((newX >= 0 && newX < 20) && (newY >= 0 && newY < 15) && (maze[newX][newY] == 0)){
                maze[x][y] = 2;
                maze[carveX][carveY] = 2;
                generateMaze(newX, newY);
            }
        }
    }

    //REMOVE 2X2 WALL CHUNKS FROM RIGHT WALL
    public void handleRightWall(){
        for(int i = 0; i < 15; i ++){
            if(i % 2 == 1 ){
                maze[18][i] = 2;
            }
        }
    }

    public void hideInnerMaze(){
        //SET ALL TO NOT VISIBLE
        for(int i = 0; i < 20; i ++){
            for(int j = 0; j < 15; j ++){
                if(maze[i][j] == 2){
                    maze[i][j] = 0;
                }
            }
        }

        //SET OUTER WALL TO VISIBLE
        for(int i = 0; i < 20; i ++){
            maze[i][0] = 3;
            maze[i][14] = 3;
        }
        for(int i = 0; i < 15; i ++){
            maze[0][i] = 3;
            maze[19][i] = 3;
        }
    }

    public void changeVisibilityBasedOnMouse(int mouseX, int mouseY){
        for(int i = -1; i <= 1; i ++){
            for(int j = -1; j <= 1; j ++){
                if(maze[mouseX + i][mouseY + j] == 0 || maze[mouseX + i][mouseY + j] == 1){
                    maze[mouseX + i][mouseY + j] = maze[mouseX + i][mouseY + j] + 2;
                }
            }
        }
    }

    public void makeAllVisible(){
        for(int i = 0; i < 20; i ++){
            for(int j = 0; j < 15; j ++){
                if(maze[i][j] == 0 || maze[i][j] == 1){
                    maze[i][j] = maze[i][j] + 2;
                }
            }
        }
    }

    public boolean objectsCollide(int x, int y) {
        if(maze[x][y] == 1 || maze[x][y] == 3) {
            return true;
        }
        else {
            return false;
        }
    }
}
