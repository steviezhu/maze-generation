/**
 * Main class used to create game objects and run the game
 */

package cmpt213.as3.gamelogic;

import cmpt213.as3.ui.UserInterface;

import java.util.Scanner;

public class Main {
    final static int CAT1STARTINGX = 17;
    final static int CAT1STARTINGY = 1;
    final static int CAT2STARTINGX = 1;
    final static int CAT2STARTINGY = 13;
    final static int CAT3STARTINGX = 17;
    final static int CAT3STARTINGY = 13;

    public static void main(String[] args) {
        Cat cat1 = new Cat(CAT1STARTINGX, CAT1STARTINGY);
        Cat cat2 = new Cat(CAT2STARTINGX, CAT2STARTINGY);
        Cat cat3 = new Cat(CAT3STARTINGX, CAT3STARTINGY);
        Mouse mouse = new Mouse(1, 1);
        Maze maze = new Maze();
        Cheese cheese = new Cheese(maze, mouse);

        UserInterface ui = new UserInterface();
        ui.displayInstructions();

        maze.changeVisibilityBasedOnMouse(mouse.getX() ,mouse.getY());
        ui.drawMaze(maze.getMaze(), cat1, cat2, cat3, mouse, cheese);
        ui.printCheeseCollected(mouse);

        Scanner scanner = new Scanner(System.in);
        char movement;

        //WHILE GAME IS RUNNING
        while(true) {
            ui.askMove();
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
                    ui.invalidMoveInput();
                }
                ui.askMove();
                movement = scanner.next().charAt(0);
            }

            //MOVEMENT INPUTS, CHECKS FOR COLLISION WITH WALLS
            if (movement == 'w' || movement == 'W') {
                if(maze.objectsCollide(mouse.getX(), mouse.getY() - 1)){
                    ui.invalidMoveWall();
                    continue;
                }
                else {
                    mouse.moveUp();
                }
            }
            else if (movement == 's' || movement == 'S') {
                if (maze.objectsCollide(mouse.getX(), mouse.getY() + 1)) {
                    ui.invalidMoveWall();
                    continue;
                }
                else {
                    mouse.moveDown();
                }
            }
            else if (movement == 'a' || movement == 'A') {
                if (maze.objectsCollide(mouse.getX() - 1, mouse.getY())) {
                    ui.invalidMoveWall();
                    continue;
                }
                else {
                    mouse.moveLeft();
                }
            }
            else if (movement == 'd' || movement == 'D') {
                if (maze.objectsCollide(mouse.getX() + 1, mouse.getY())) {
                    ui.invalidMoveWall();
                    continue;
                }
                else {
                    mouse.moveRight();
                }
            }

            //CHEESE COLLECTED
            if((mouse.getX() == cheese.getX()) && (mouse.getY() == cheese.getY())){
                mouse.increaseCheeseCollected();
                cheese = new Cheese(maze, mouse);
            }

            //GAME OVER - MOVING INTO CELL CURRENTLY WITH CAT
            if((mouse.getX() == cat1.getX()) && (mouse.getY() == cat1.getY())){
                ui.gameOver(maze, cat1, cat2, cat3, mouse, cheese);
                break;
            }
            if((mouse.getX() == cat2.getX()) && (mouse.getY() == cat2.getY())){
                ui.gameOver(maze, cat1, cat2, cat3, mouse, cheese);
                break;
            }
            if((mouse.getX() == cat3.getX()) && (mouse.getY() == cat3.getY())){
                ui.gameOver(maze, cat1, cat2, cat3, mouse, cheese);
                break;
            }

            //GAME OVER - MOVING INTO A CELL SIMULTANEOUSLY WITH CAT
            cat1.moveRandomly(maze);
            if((mouse.getX() == cat1.getX()) && (mouse.getY() == cat1.getY())){
                ui.gameOver(maze, cat1, cat2, cat3, mouse, cheese);
                break;
            }
            cat2.moveRandomly(maze);
            if((mouse.getX() == cat2.getX()) && (mouse.getY() == cat2.getY())){
                ui.gameOver(maze, cat1, cat2, cat3, mouse, cheese);
                break;
            }
            cat3.moveRandomly(maze);
            if((mouse.getX() == cat3.getX()) && (mouse.getY() == cat3.getY())){
                ui.gameOver(maze, cat1, cat2, cat3, mouse, cheese);
                break;
            }

            //GAME WIN, CHECKED AFTER GAME OVERS
            if(mouse.cheeseCollected() == mouse.cheeseToWin()){
                ui.gameWin(maze, cat1, cat2, cat3, mouse, cheese);
                break;
            }

            //UPDATE VISIBILITY BASED ON MOUSE, THEN REDRAW MAZE AND CHEESE
            maze.changeVisibilityBasedOnMouse(mouse.getX() ,mouse.getY());
            ui.drawMaze(maze.getMaze(), cat1, cat2, cat3, mouse, cheese);
            ui.printCheeseCollected(mouse);
        }
    }
}
