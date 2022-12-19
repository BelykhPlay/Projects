package Game;

public class Movement {
    private Movement() {
        throw new IllegalStateException("Utility class");
    }

    public static String[][] upwardMovement(String[][] level, GameObject object) {
        int[] position = object.getPosition();

        int carriage = position[0];

        String objectName = object.getName();
        String destinationName = objectName.toUpperCase();

        while (level[carriage - 1][position[1]].equals("f")) {
            level[carriage - 1][position[1]] = objectName;
            level[carriage][position[1]] = "f";

            carriage--;
        }

        if(level[carriage - 1][position[1]].equals(destinationName)) {
            object.setFinishedGame(true);

            level[carriage - 1][position[1]] = "w";
            level[carriage][position[1]] = "f";
        }

        object.setPosition(new int[] {carriage, position[1]});

        return level;
    }

    public static String[][] downwardMovement(String[][] level, GameObject object) {
        int[] position = object.getPosition();

        int carriage = position[0];

        String objectName = object.getName();
        String destinationName = objectName.toUpperCase();

        while (level[carriage + 1][position[1]].equals("f")) {
            level[carriage + 1][position[1]] = objectName;
            level[carriage][position[1]] = "f";

            carriage++;
        }

        if(level[carriage + 1][position[1]].equals(destinationName)) {
            object.setFinishedGame(true);

            level[carriage + 1][position[1]] = "w";
            level[carriage][position[1]] = "f";
        }

        object.setPosition(new int[] {carriage, position[1]});

        return level;
    }

    public static String[][] movementToTheRight(String[][] level, GameObject object) {
        int[] position = object.getPosition();

        int carriage = position[1];

        String objectName = object.getName();
        String destinationName = objectName.toUpperCase();

        while (level[position[0]][carriage + 1].equals("f")) {
            level[position[0]][carriage + 1] = objectName;
            level[position[0]][carriage] = "f";

            carriage++;
        }

        if (level[position[0]][carriage + 1].equals(destinationName)) {
            object.setFinishedGame(true);

            level[position[0]][carriage + 1] = "w";
            level[position[0]][carriage] = "f";
        }

        object.setPosition(new int[] {position[0], carriage});

        return level;
    }

    public static String[][] movementToTheLeft(String[][] level, GameObject object) {
        int[] position = object.getPosition();

        int carriage = position[1];

        String objectName = object.getName();
        String destinationName = objectName.toUpperCase();

        while (level[position[0]][carriage - 1].equals("f")) {
            level[position[0]][carriage - 1] = objectName;
            level[position[0]][carriage] = "f";

            carriage--;
        }

        if (level[position[0]][carriage - 1].equals(destinationName)) {
            object.setFinishedGame(true);

            level[position[0]][carriage - 1] = "w";
            level[position[0]][carriage] = "f";
        }

        object.setPosition(new int[] {position[0], carriage});

        return level;
    }
}