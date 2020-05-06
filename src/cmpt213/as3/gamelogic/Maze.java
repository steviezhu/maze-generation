/**
 * Generates the maze and handles visibility and collisions within the maze
 * The maze generation algorithm used is the Recursive Back-tracker
 */

package cmpt213.as3.gamelogic;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Maze {
    private int[][] maze = new int[19][15];

    final int NOTVISIBLESPACE = 0;
    final int NOTVISIBLEWALL = 1;
    final int VISIBLESPACE = 2;
    final int VISIBLEWALL = 3;

    public Maze() {
        System.out.println("MAZE GENERATION ALGORITHM: Recursive Backtracker");
        this.generateMazePrim(7, 7);
        this.hideInnerMaze();
    }

    public int[][] getMaze() {
        return maze;
    }

    //VISIBILITY BY NUMBER: 0 = NOT VISIBLE SPACE, 1 = NOT VISIBLE WALL, 2 = VISIBLE SPACE, 3 = VISIBLE WALL
    //MAZE IS INITIATED TO AN OUTER WALL OF 1'S THEN 0'S BOXED BY 1'S
    public void generateMazeRecursive(int x, int y, boolean firstIteration){
        if(firstIteration) {
            for (int i = 0; i < 19; i++) {
                for (int j = 0; j < 15; j++) {
                    if ((i % 2 == 1) && (j % 2 == 1)) {
                        maze[i][j] = NOTVISIBLESPACE;
                    } else {
                        maze[i][j] = NOTVISIBLEWALL;
                    }
                }
            }

            for (int i = 0; i < 15; i++) {
                maze[18][i] = NOTVISIBLEWALL;
            }
        }

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
            if((newX >= 0 && newX < 19) && (newY >= 0 && newY < 15) && (maze[newX][newY] == NOTVISIBLESPACE)){
                maze[x][y] = VISIBLESPACE;
                maze[carveX][carveY] = VISIBLESPACE;
                generateMazeRecursive(newX, newY, false);
            }
        }
    }

    public void generateMazePrim(int x, int y){
        for(int i = 0; i < 19; i++){
            for(int j = 0; j < 15; j++){
                maze[i][j] = NOTVISIBLEWALL;
            }
        }

        LinkedList<int[]> frontiers = new LinkedList<>();
        Random random = new Random();

        frontiers.add(new int[]{x,y,x,y});

        while(!frontiers.isEmpty()){
            int[] removed = frontiers.remove(random.nextInt(frontiers.size()));
            x = removed[2];
            y = removed[3];
            if(maze[x][y] == NOTVISIBLEWALL || maze[x][y] == VISIBLEWALL){
                maze[removed[0]][removed[1]] = VISIBLESPACE;
                maze[x][y] = VISIBLESPACE;

                if ( x > 1 && (maze[x-2][y] == NOTVISIBLEWALL)) {
                    frontiers.add(new int[]{x - 1, y, x - 2, y});
                }
                if ( y > 1 && (maze[x][y-2] == NOTVISIBLEWALL)) {
                    frontiers.add(new int[]{x, y - 1, x, y - 2});
                }
                if ( x < 17 && (maze[x+2][y] == NOTVISIBLEWALL)) {
                    frontiers.add(new int[]{x + 1, y, x + 2, y});
                }
                if ( y < 13 && (maze[x][y+2] == NOTVISIBLEWALL)) {
                    frontiers.add(new int[]{x, y + 1, x, y + 2});
                }
            }
        }
    }

    public void hideInnerMaze(){
        //SET ALL TO NOT VISIBLE
        for(int i = 0; i < 19; i ++){
            for(int j = 0; j < 15; j ++){
                if(maze[i][j] == VISIBLESPACE){
                    maze[i][j] = NOTVISIBLESPACE;
                }
            }
        }

        //SET OUTER WALL TO VISIBLE
        for(int i = 0; i < 19; i ++){
            maze[i][0] = VISIBLEWALL;
            maze[i][14] = VISIBLEWALL;
        }
        for(int i = 0; i < 15; i ++){
            maze[0][i] = VISIBLEWALL;
            maze[18][i] = VISIBLEWALL;
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
        for(int i = 0; i < 19; i ++){
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
