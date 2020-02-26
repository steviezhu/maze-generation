/**
 * Generates the maze and handles visibility and collisions within the maze
 * The maze generation algorithm used is the Recursive Back-tracker
 */

package cmpt213.as3.gamelogic;

import java.util.Arrays;
import java.util.Collections;

public class Maze {
    private int[][] maze = new int[20][15];

    final int NOTVISIBLESPACE = 0;
    final int NOTVISIBLEWALL = 1;
    final int VISIBLESPACE = 2;
    final int VISIBLEWALL = 3;

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
                    maze[i][j] = NOTVISIBLESPACE;
                }
                else{
                    maze[i][j] = NOTVISIBLEWALL;
                }
            }
        }

        for(int i = 0; i < 15; i ++){
            maze[19][i] = NOTVISIBLEWALL;
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
            if((newX >= 0 && newX < 20) && (newY >= 0 && newY < 15) && (maze[newX][newY] == NOTVISIBLESPACE)){
                maze[x][y] = VISIBLESPACE;
                maze[carveX][carveY] = VISIBLESPACE;
                generateMaze(newX, newY);
            }
        }
    }

    //REMOVE 2X2 WALL CHUNKS FROM RIGHT WALL
    public void handleRightWall(){
        for(int i = 0; i < 15; i ++){
            if(i % 2 == 1 ){
                maze[18][i] = VISIBLESPACE;
            }
        }
    }

    public void hideInnerMaze(){
        //SET ALL TO NOT VISIBLE
        for(int i = 0; i < 20; i ++){
            for(int j = 0; j < 15; j ++){
                if(maze[i][j] == VISIBLESPACE){
                    maze[i][j] = NOTVISIBLESPACE;
                }
            }
        }

        //SET OUTER WALL TO VISIBLE
        for(int i = 0; i < 20; i ++){
            maze[i][0] = VISIBLEWALL;
            maze[i][14] = VISIBLEWALL;
        }
        for(int i = 0; i < 15; i ++){
            maze[0][i] = VISIBLEWALL;
            maze[19][i] = VISIBLEWALL;
        }
    }

    public void changeVisibilityBasedOnMouse(int mouseX, int mouseY){
        for(int i = -1; i <= 1; i ++){
            for(int j = -1; j <= 1; j ++){
                if(maze[mouseX + i][mouseY + j] == NOTVISIBLESPACE || maze[mouseX + i][mouseY + j] == NOTVISIBLEWALL){
                    maze[mouseX + i][mouseY + j] = maze[mouseX + i][mouseY + j] + 2;
                }
            }
        }
    }

    public void makeAllVisible(){
        for(int i = 0; i < 20; i ++){
            for(int j = 0; j < 15; j ++){
                if(maze[i][j] == NOTVISIBLESPACE || maze[i][j] == NOTVISIBLEWALL){
                    maze[i][j] = maze[i][j] + 2;
                }
            }
        }
    }

    public boolean objectsCollide(int x, int y) {
        if(maze[x][y] == NOTVISIBLEWALL || maze[x][y] == VISIBLEWALL) {
            return true;
        }
        else {
            return false;
        }
    }
}
