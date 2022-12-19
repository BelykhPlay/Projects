package Game;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Logic {

    private Logic() {
        throw new IllegalStateException("Utility class");
    }
    private static String[][] readFile(String nameLevelFile) throws IOException {
        String[][] levelFile = new String[11][9];
        FileInputStream fileInputStream = new FileInputStream(nameLevelFile);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream))) {
            int count = 0;

            while (reader.ready()) levelFile[count++] = reader.readLine().split(" ");

        }

        return levelFile;
    }

    public static PlayingField createPlayingField(String nameLevelFile) throws IOException {
        String[][] levelFile = readFile("/home/belykh/Документы/Projects/src/levels/" + nameLevelFile);

        // Create name of PlayingField
        String name = nameLevelFile.substring(0, 8);

        // Create levelMarkup
        String[][] levelMarkup = new String[9][9];

        System.arraycopy(levelFile, 2, levelMarkup, 0, 9);

        // Create List<GameObjects>
        String[] names = levelFile[0];
        String[] positions = levelFile[1];

        List<GameObject> gameObjects = createListWithGameObjects(names, positions);

        return new PlayingField(name, levelMarkup, gameObjects);
    }


    private static List<GameObject> createListWithGameObjects(String[] names, String[] positions) {
        List<GameObject> gameObjects = new ArrayList<>();

        String name;
        int[] position = new int[positions.length / 2];

        int delta = 1;
        for (int i = 1; i < positions.length; i += 2) {
            name = names[i - delta];

            position[0] = Integer.parseInt(positions[i - 1]);
            position[1] = Integer.parseInt(positions[i]);

            delta++;

            gameObjects.add(new GameObject(name, Arrays.copyOf(position, 2)));
        }

        return gameObjects;
    }

}
