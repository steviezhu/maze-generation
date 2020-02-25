package cmpt213.as3.gamelogic;

import cmpt213.as3.ui.UserInterface;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Cat cat1 = new Cat(18, 1);
        Cat cat2 = new Cat(1, 13);
        Cat cat3 = new Cat(18, 13);
        Mouse mouse = new Mouse(1, 1);
        Maze maze = new Maze();
        Cheese cheese = new Cheese(maze, mouse);

        UserInterface ui = new UserInterface();
        ui.displayInstructions();

        maze.changeVisibilityBasedOnMouse(mouse.getX() ,mouse.getY());
        ui.drawMaze(maze.getMaze(), cat1, cat2, cat3, mouse, cheese);

        Scanner scanner = new Scanner(System.in);
        char movement;

        //WHILE GAME IS RUNNING
        while(mouse.cheeseCollected() != mouse.cheeseToWin()) {
            System.out.println("Cheese collected: " + mouse.cheeseCollected() + " of " + mouse.cheeseToWin());
            System.out.print("Enter your move [WASD?]: ");
            movement = scanner.next().charAt(0);

            //HANDLE NON-MOVEMENT INPUTS
            while("wWaAsSdD".indexOf(movement) == -1){
                if (movement == '?'){
                    ui.displayInstructions();
                }
                else if (movement == 'c' || movement == 'C'){
                    mouse.setNumOfCheeseToWin(1);
                }
                else if (movement == 'm' || movement == 'M'){
                    maze.makeAllVisible();
                    ui.drawMaze(maze.getMaze(), cat1, cat2, cat3, mouse, cheese);
                }
                else{
                    System.out.println("Invalid move. Please enter just A (left), S (down), D (right), or W (up).");
                }
                System.out.print("Enter your move [WASD?]: ");
                movement = scanner.next().charAt(0);
            }

            //MOVEMENT INPUTS
            if (movement == 'w' || movement == 'W') {
                mouse.moveUp();
            }
            else if (movement == 's' || movement == 'S') {
                mouse.moveDown();
            }
            else if (movement == 'a' || movement == 'A') {
                mouse.moveLeft();
            }
            else if (movement == 'd' || movement == 'D') {
                mouse.moveRight();
            }

            maze.changeVisibilityBasedOnMouse(mouse.getX() ,mouse.getY());
            ui.drawMaze(maze.getMaze(), cat1, cat2, cat3, mouse, cheese);
        }
    }


}
