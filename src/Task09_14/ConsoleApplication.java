package Task09_14;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {
    private Logic logicConsole = new Logic();

    public void start() throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);

        String[] nameFiles = readFromConsole(scan);

        List<Integer> result = logicConsole.solve(readFromFile(nameFiles[0]));

        writeToFile(result, nameFiles[1]);
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

    private List<Integer> readFromFile(String nameFile) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("/home/belykh/Документы/Проекты/Task's/FilesForTask09/" + nameFile));

        List<Integer> integerData = new ArrayList<>();

        String[] stringData = scan.nextLine().split(" ");

        for (String element : stringData) {
            integerData.add(Integer.parseInt(element));
        }

        scan.close();

        return integerData;
    }

    private void writeToFile(List<Integer> resultInList, String nameFile) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("/home/belykh/Документы/Проекты/Task's/FilesForTask09/" + nameFile);

        StringBuilder stringResult = new StringBuilder();

        for (int element : resultInList) {
            stringResult.append(element + " ");
        }

        pw.print(stringResult);

        pw.close();
    }
}
