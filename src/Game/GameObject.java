package Game;

public class GameObject {
    private String name;

    private int[] position;

    private boolean isFinishedGame;

    public GameObject(String name, int[] position) {
        this.name = name;
        this.position = position;
        this.isFinishedGame = false;
    }

    public int[] getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public boolean isFinishedGame() {
        return isFinishedGame;
    }

    public void setFinishedGame(boolean finishedGame) {
        isFinishedGame = finishedGame;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }


}