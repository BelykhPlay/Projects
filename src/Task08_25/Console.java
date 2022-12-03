package Task08_25;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {
    private final Logic logicConsole = new Logic();

    public void start() throws FileNotFoundException {
        String[] nameFiles = readFromConsole();

        boolean isChecked = logicConsole.checkOrderedSequence(readFile(nameFiles[0]));

        printToFile(nameFiles[1], isChecked);
    }

    private String[] readFromConsole() {
        Scanner scan = new Scanner(System.in);

        String[] nameFiles = new String[2];

        String[] inputData = scan.nextLine().split(" ");

        if (inputData[0].equals("-i") && inputData[2].equals("-o")) {
            nameFiles[0] = inputData[1];
            nameFiles[1] = inputData[3];

        } else {
            System.out.println("Invalid input");
            System.exit(0);

        }


        return nameFiles;
    }

    private int[][] readFile(String nameFile) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("/home/belykh/Документы/Проекты/Task's/" + nameFile));

        List<int[]> dataInList = new ArrayList<>();

        // Read file
        int rows = 0;
        int columns = 0;
        while (scan.hasNextLine()) {
            String[] dataRowString = scan.nextLine().split(" ");

            rows++;
            columns = dataRowString.length;

            int[] dataRowInt = new int[dataRowString.length];

            int count = 0;
            for (String elements : dataRowString) {
                dataRowInt[count++] = Integer.parseInt(elements);

            }

            dataInList.add(dataRowInt);
        }

        // Create int[][] for data in file
        int[][] data = new int[rows][columns];

        // List<int[]> in int[][]
        int count = 0;
        for (int[] elements : dataInList) {
            data[count++] = elements;
        }

        scan.close();
        return data;
    }

    private void printToFile(String nameFileOutput, boolean isChecked) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("/home/belykh/Документы/Проекты/Task's/" + nameFileOutput);

        pw.print(isChecked);

        pw.close();
    }

}