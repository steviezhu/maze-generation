package cmpt213.as2.gamelogic;

public class Mouse {
    private int x;
    private int y;

    private int numOfCheeseCollected;
    private int numOfCheeseToWin;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void moveUp() {}

    private void moveLeft() {}

    private void moveRight() {}

    private void moveDown() {}

    public void cheeseCollected() {
        this.numOfCheeseCollected++;
    }

    public void setNumOfCheeseToWin(int numOfCheeseToWin) {
        this.numOfCheeseToWin = numOfCheeseToWin;
    }
}
