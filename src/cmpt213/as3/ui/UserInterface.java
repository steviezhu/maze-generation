package cmpt213.as3.ui;

import cmpt213.as3.gamelogic.Cat;
import cmpt213.as3.gamelogic.Cheese;
import cmpt213.as3.gamelogic.Mouse;

public class UserInterface {
    public void drawMaze(int[][] maze, Cat cat1, Cat cat2, Cat cat3, Mouse mouse, Cheese cheese) {
        System.out.println("Maze:");
        for(int j = 0; j < 15; j ++){
            for(int i = 0; i < 20; i ++){
                if(i == mouse.getX() && j == mouse.getY()){
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
}
