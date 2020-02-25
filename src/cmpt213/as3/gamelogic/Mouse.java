package cmpt213.as3.gamelogic;

public class Mouse {
    private int x;
    private int y;

    private int numOfCheeseCollected;
    private int numOfCheeseToWin = 5;

    public Mouse(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveUp() {
        this.y = this.y - 1;
    }

    public void moveLeft() {
        this.x = this.x - 1;
    }

    public void moveRight() {
        this.x = this.x + 1;
    }

    public void moveDown() {
        this.y = this.y + 1;
    }

    public void increaseCheeseCollected() {
        this.numOfCheeseCollected++;
    }

    public void setNumOfCheeseToWin(int numOfCheeseToWin) {
        this.numOfCheeseToWin = numOfCheeseToWin;
    }

    public int cheeseToWin(){
        return this.numOfCheeseToWin;
    }

    public int cheeseCollected(){
        return this.numOfCheeseCollected;
    }
}
