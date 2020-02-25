package cmpt213.as3.ui;

import cmpt213.as3.gamelogic.Cat;
import cmpt213.as3.gamelogic.Cheese;
import cmpt213.as3.gamelogic.Mouse;

public class UserInterface {
    public void drawMaze(int[][] maze, Cat cat1, Cat cat2, Cat cat3, Mouse mouse, Cheese cheese) {
        for(int j = 0; j < 15; j ++){
            for(int i = 0; i < 20; i ++){
                if(maze[i][j] == 1){
                    System.out.print('#');
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
                else{
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
}
