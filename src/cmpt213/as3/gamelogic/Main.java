package cmpt213.as3.gamelogic;

import cmpt213.as3.ui.UserInterface;

public class Main {

    public static void main(String[] args) {

        Cat cat1 = new Cat(18, 1);
        Cat cat2 = new Cat(1, 13);
        Cat cat3 = new Cat(18, 13);
        Mouse mouse = new Mouse(1, 1);
        Maze maze = new Maze();
        Cheese cheese = new Cheese(maze, mouse);

        UserInterface ui = new UserInterface();
        ui.drawMaze(maze.getMaze(), cat1, cat2, cat3, mouse, cheese);

    }


}
