package Game;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PlayingField {
    private String name;
    private String[][] level;

    private List<GameObject> gameObjects;

    private boolean isEndGame;

    public PlayingField(String name, String[][] level, List<GameObject> gameObjects) {
        this.name = name;

        this.level = level;

        this.gameObjects = gameObjects;

        this.isEndGame = false;
    }

    public DefaultTableModel getGraphicalLevelView() {
        DefaultTableModel tableModel = new DefaultTableModel(9, 9);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                tableModel.setValueAt(level[i][j], i, j);
            }
        }

        return tableModel;
    }

    public List<GameObject> getGameObjects() {
        for (int i = 0; i < gameObjects.size(); i++) {
            if(gameObjects.get(i).isFinishedGame()) {
                gameObjects.remove(i);
                i--;
            }
        }

        return gameObjects;
    }

    public void setLevel(String[][] level) {
        this.level = level;
    }

    public boolean isEndGame() {
        for (GameObject e : gameObjects) {
            if(!e.isFinishedGame()) {
                return isEndGame;
            }
        }

        return !isEndGame;
    }

    public String[][] getLevel() {
        return level.clone();
    }

    public String getName() {
        return name;
    }
}