package Task08_25;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleGUI extends JFrame {
    private final Logic logic = new Logic();

    private JTable table;

    private JTextField inputRows;
    private JTextField inputColumns;

    private JButton buttonBuildTable;
    private JButton buttonCheckTable;

    private JLabel dataAboutResult;
    private JLabel dataAboutInputFile;

    private JList listFile;

    public SimpleGUI() {
        // Frame
        super("Task08_25");

        setSize(700, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Other components
        createComponents();
    }

    private void createComponents() {
        add(createTableArray(0, 0));
        add(createInputRows());
        add(createInputColumns());

        add(createButtonBuildTable());
        add(createButtonCheckTable());

        add(createListFile());

        add(createDataAboutInputFile());

        add(createDataAboutResult());
    }


    private JTable createTableArray(int rows, int columns) {
        table = new JTable(rows, columns);

        table.setBounds(10, 10, 680, 500);

        return table;
    }


    private JTextField createInputRows() {
        inputRows = new JTextField("0");

        inputRows.setBounds(10, 520, 140, 30);
        inputRows.setVisible(true);

        return inputRows;
    }

    private JTextField createInputColumns() {
        inputColumns = new JTextField("0");

        inputColumns.setBounds(160, 520, 140, 30);
        inputColumns.setVisible(true);

        return inputColumns;
    }

    private JButton createButtonBuildTable() {
        buttonBuildTable = new JButton("Build");

        buttonBuildTable.setBounds(10, 555, 290, 40);
        buttonBuildTable.setVisible(true);

        // Clear and build new JTable
        buttonBuildTable.addActionListener(action -> {
            int rows = Integer.parseInt(inputRows.getText());
            int columns = Integer.parseInt(inputColumns.getText());

            // Rebuild JTable
            remove(table);
            add(createTableArray(rows, columns));

            // Reset data about checking
            dataAboutResult.setBackground(Color.white);

            //Set text in JLabel dataAboutInputFile
            dataAboutInputFile.setText("Custom JTable");
        });

        return buttonBuildTable;
    }

    private JButton createButtonCheckTable() {
        buttonCheckTable = new JButton("Check");

        buttonCheckTable.setBounds(10, 605, 290, 50);
        buttonCheckTable.setVisible(true);

        buttonCheckTable.addActionListener(action -> {

            boolean isChecked = logic.checkOrderedSequence(getArrayJTable(table.getRowCount(), table.getColumnCount()));

            // Set background in JLabel dataAboutResult
            if(isChecked) {
                dataAboutResult.setBackground(Color.green);

            } else {
                dataAboutResult.setBackground(Color.red);

            }

            // Output in file
            try {
                outputFile(dataAboutInputFile.getText(), isChecked);

            } catch (FileNotFoundException e) {
                System.out.println("File not found");

            }

        });

        return buttonCheckTable;
    }


    private JList createListFile() {
        String[] data = {"input01.txt", "input02.txt", "input03.txt", "input04.txt", "input05.txt",};
        listFile = new JList<>(data);

        listFile.setBounds(320, 520, 150, 135);
        listFile.setVisible(true);

        listFile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    String nameFile = (String) listFile.getSelectedValue();

                    // Data about which file is open
                    dataAboutInputFile.setText(nameFile);

                    // Reset data about checking
                    dataAboutResult.setBackground(Color.white);

                    // Creating a table with data from a file
                    try {
                        String[][] dataInFile = readFile(nameFile);

                        int rows = dataInFile.length;
                        int columns = dataInFile[0].length;

                        // Creating a table for array data
                        remove(table);
                        add(createTableArray(rows, columns));

                        // Assigning values to table cells
                        for (int i = 0; i < rows; i++) {
                            for(int j = 0; j < columns; j++) {
                                table.setValueAt(dataInFile[i][j], i, j);
                            }
                        }

                    } catch (FileNotFoundException e) {
                        System.out.println("File not found.");

                    }

                }

            }
        });

        return listFile;
    }

    private JLabel createDataAboutInputFile() {
        dataAboutInputFile = new JLabel("There is no open file");

        dataAboutInputFile.setBounds(480, 520, 210, 40);
        dataAboutInputFile.setOpaque(true);
        dataAboutInputFile.setBackground(Color.white);
        dataAboutInputFile.setVisible(true);

        return dataAboutInputFile;
    }

    private JLabel createDataAboutResult() {
        dataAboutResult = new JLabel();

        dataAboutResult.setBounds(480, 550, 210, 105);
        dataAboutResult.setOpaque(true);
        dataAboutResult.setBackground(Color.white);
        dataAboutResult.setVisible(true);

        return dataAboutResult;
    }

    // Read file
    private String[][] readFile(String nameFile) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("/home/belykh/Документы/Проекты/Task's/FilesForTask08/" + nameFile));

        List<String[]> dataInList = new ArrayList<>();

        // Read file
        int rows = 0;
        int columns = 0;
        while (scan.hasNextLine()) {

            String[] dataRowString = scan.nextLine().split(" ");

            rows++;
            columns = dataRowString.length;

            dataInList.add(dataRowString);
        }

        // Create String[][] for data in file
        String[][] data = new String[rows][columns];

        // List<String[]> in String[][]
        int count = 0;
        for (String[] elements : dataInList) {
            data[count++] = elements;
        }

        scan.close();
        return data;
    }

    // Output file
    private void outputFile(String nameFileInput, boolean isChecked) throws FileNotFoundException {
        String nameFileOutput = switch ("/home/belykh/Документы/Проекты/Task's/FilesForTask08/" + nameFileInput) {
            case ("input01.txt") -> "output01.txt";

            case ("input02.txt") -> "output02.txt";

            case ("input03.txt") -> "output03.txt";

            case ("input04.txt") -> "output04.txt";

            case ("input05.txt") -> "output05.txt";

            default -> "outputCustom.txt";
        };

        PrintWriter pw = new PrintWriter(nameFileOutput);

        pw.print(isChecked);

        pw.close();
    }

    // Building a table array
    private int[][] getArrayJTable(int a, int b) {
        int[][] arr = new int[a][b];

        for (int row = 0; row < a; row++) {
            for (int column = 0; column < b; column++) {
                arr[row][column] = Integer.parseInt((String) table.getValueAt(row, column));
            }
        }

        return arr;
    }

}