/**
 * Prints to terminal and displays user interface
 */

package cmpt213.as3.ui;

import cmpt213.as3.gamelogic.Cat;
import cmpt213.as3.gamelogic.Cheese;
import cmpt213.as3.gamelogic.Maze;
import cmpt213.as3.gamelogic.Mouse;

public class UserInterface {
    public void drawMaze(int[][] maze, Cat cat1, Cat cat2, Cat cat3, Mouse mouse, Cheese cheese) {
        System.out.println("Maze:");
        for(int j = 0; j < 15; j ++){
            for(int i = 0; i < 20; i ++){
                if(maze[i][j] == 'X'){
                    System.out.print('X');
                }
                else if(i == mouse.getX() && j == mouse.getY()){
                    System.out.print('@');
                }
                else if( (i == cat1.getX() && j == cat1.getY())
                        || ( i == cat2.getX() && j == cat2.getY() )
                        || ( i == cat3.getX() && j == cat3.getY() )
                ){
                    System.out.print('!');
                }
                else if( i == cheese.getX() && j == cheese.getY() ) {
                    System.out.print('$');
                }
                else if (maze[i][j] == 0 || maze[i][j] == 1){
                    System.out.print('.');
                }
                else if (maze[i][j] == 2){
                    System.out.print(' ');
                }
                else if (maze[i][j] == 3){
                    System.out.print('#');
                }
            }
            System.out.println();
        }
    }

    public void displayInstructions(){
        System.out.println("DIRECTIONS:\n" +
                "\tFind 5 cheese before a cat eats you!\n" +
                "LEGEND:\n" +
                "\t#: Wall\n" +
                "\t@: You (a mouse)\n" +
                "\t!: Cat\n" +
                "\t$: Cheese\n" +
                "\t.: Unexplored space\n" +
                "MOVES:\n" +
                "\tUse W (up), A (left), S (down) and D (right) to move.\n" +
                "\t(You must press enter after each move).");
    }

    public void gameWin(Maze maze, Cat cat1, Cat cat2, Cat cat3, Mouse mouse, Cheese cheese){
        System.out.println("Congratulations! You win!");
        maze.makeAllVisible();
        drawMaze(maze.getMaze(), cat1, cat2, cat3, mouse, cheese);
        System.out.println("Cheese collected: " + mouse.cheeseCollected() + " of " + mouse.cheeseToWin());
    }

    public void gameOver(Maze maze, Cat cat1, Cat cat2, Cat cat3, Mouse mouse, Cheese cheese){
        System.out.println("You have been eaten!");
        maze.makeAllVisible();
        (maze.getMaze())[mouse.getX()][mouse.getY()] = 'X';
        drawMaze(maze.getMaze(), cat1, cat2, cat3, mouse, cheese);
        System.out.println("Cheese collected: " + mouse.cheeseCollected() + " of " + mouse.cheeseToWin());
        System.out.println("GAME OVER");
    }

    public void invalidMoveWall(){
        System.out.println("Invalid move. You can't walk through walls.");
    }

    public void invalidMoveInput(){
        System.out.println("Invalid move. Please enter just A (left), S (down), D (right), or W (up).");
    }

    public void printCheeseCollected(Mouse mouse){
        System.out.println("Cheese collected: " + mouse.cheeseCollected() + " of " + mouse.cheeseToWin());
    }

    public void askMove(){
        System.out.print("Enter your move [WASD?]: ");
    }
}
