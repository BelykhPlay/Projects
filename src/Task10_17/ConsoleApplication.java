package Task10_17;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ConsoleApplication {
    public void start() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);

        String[] nameFiles = readFromConsole(scan);

        double[][] coordinates = Logic.sortCoordinates(readFromFile(nameFiles[0]));

        Triangle triangle = new Triangle(coordinates);

        writeToFile(nameFiles[1], triangle.isItInAllQuarters());

    }

    private String[] readFromConsole(Scanner scan) {
        String[] inputData = scan.nextLine().split(" ");

        String[] nameFiles = new String[2];

        if (inputData[0].equals("-i") && inputData[2].equals("-o")) {
            nameFiles[0] = inputData[1];
            nameFiles[1] = inputData[3];

        } else {
            System.out.println("Invalid input");
            System.exit(0);
        }

        return nameFiles;
    }

    private double[] readFromFile(String nameFile) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("/home/belykh/Документы/Проекты/Task's/FilesForTask10/" + nameFile));

        String[] stringData = scan.nextLine().split(" ");

        double[] doubleData = new double[6];

        int count = 0;
        for(String element: stringData) {
            doubleData[count++] = Double.parseDouble(element);
        }

        return doubleData;
    }

    private void writeToFile(String nameInputFile, boolean isSatisfies) throws FileNotFoundException {
        String nameOutputFile = switch (nameInputFile) {
            case ("input01.txt")-> "output01.txt";
            case ("input02.txt")-> "output02.txt";
            case ("input03.txt")-> "output03.txt";
            case ("input04.txt")-> "output04.txt";
            case ("input05.txt")-> "output05.txt";

            default -> "output00.txt";
        };

        PrintWriter pw = new PrintWriter("/home/belykh/Документы/Проекты/Task's/FilesForTask10/" + nameOutputFile);

        pw.print(isSatisfies);
        pw.close();
    }

}
