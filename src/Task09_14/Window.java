package Task09_14;

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

public class Window extends JFrame {
    private Logic logic = new Logic();

    private JTextField inputFieldForList;
    private JTextField outputFieldForResult;

    private JButton buttonStartProgram;
    private JButton buttonCustomList;

    private JLabel infoAboutOpenFile;

    private JList listFile;

    private JScrollPane scrollForListFile;
    private JScrollPane scrollForInputFieldForList;
    private JScrollPane scrollForOutputFieldForResult;

    public Window() {
        // Frame
        super("Task09_14");

        setSize(500, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        //Other components
        createComponents();
    }

    private void createComponents() {
        // JTextField
        add(createInputFieldForList());
        add(createOutputFieldForResult());

        // JButton
        add(createButtonStartProgram());
        add(createButtonCustomList());

        // JLabel
        add(createInfoAboutOpenFile());

        // JList
        add(createListFile());

        //JScrollPanel
        add(createScrollForListFile());
        add(createScrollForInputFieldForList());
        add(createScrollForOutputFieldForResult());
    }


    private JTextField createInputFieldForList() {
        inputFieldForList = new JTextField(0);

        inputFieldForList.setVisible(true);

        return inputFieldForList;
    }

    private JTextField createOutputFieldForResult() {
        outputFieldForResult = new JTextField();

        outputFieldForResult.setVisible(true);

        outputFieldForResult.setEditable(false);

        return outputFieldForResult;
    }


    private JButton createButtonStartProgram() {
        buttonStartProgram = new JButton("Start");

        buttonStartProgram.setBounds(95, 65, 295, 40);
        buttonStartProgram.setVisible(true);

        buttonStartProgram.addActionListener(action -> {
            List<Integer> result = logic.solve(readFromInputFieldForList());

            // Writing result to outputFieldForResult
            writeTextToOutputFieldForResult(result);

            // Writing result to file
            try {
                writeToFile(result, infoAboutOpenFile.getText());
            } catch (FileNotFoundException e) {
                System.out.println("Output file not found");
            }

        });

        return buttonStartProgram;
    }

    private JButton createButtonCustomList() {
        buttonCustomList = new JButton("Custom");

        buttonCustomList.setBounds(10, 65, 80, 40);
        buttonCustomList.setVisible(true);

        buttonCustomList.addActionListener(action -> {
            buttonCustomList.setBackground(Color.green);

            inputFieldForList.setEditable(true);

            inputFieldForList.setText(null);
            outputFieldForResult.setText(null);

            infoAboutOpenFile.setText("BebraCompany");
        });

        return buttonCustomList;
    }


    private JLabel createInfoAboutOpenFile() {
        infoAboutOpenFile = new JLabel("BebraCompany", SwingConstants.CENTER);

        infoAboutOpenFile.setBounds(395, 135, 95, 18);

        infoAboutOpenFile.setOpaque(true);
        infoAboutOpenFile.setBackground(Color.black);
        infoAboutOpenFile.setForeground(Color.white);

        return infoAboutOpenFile;
    }


    private JList createListFile() {
        String[] data = {"input01.txt", "input02.txt", "input03.txt", "input04.txt", "input05.txt",
                            "input06.txt", "input07.txt", "input08.txt", "input09.txt", "input10.txt"};

        listFile = new JList(data);

        listFile.setVisible(true);

        listFile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // Zeroing out
                    buttonCustomList.setBackground(null);

                    inputFieldForList.setEditable(false);

                    outputFieldForResult.setText(null);

                    // Getting name open file
                    String nameFile = (String) listFile.getSelectedValue();

                    // Set text in inputFieldForList
                    try {
                        writeTextToInputFieldForList(readFromFile(nameFile));
                    } catch (FileNotFoundException ex) {
                        System.out.println("Input file not found");
                    }

                    // Set text in BebraCompany
                    infoAboutOpenFile.setText(nameFile);
                }
            }
        });

        return listFile;
    }


    private JScrollPane createScrollForListFile() {
        scrollForListFile = new JScrollPane(listFile);

        scrollForListFile.setBounds(395, 10, 95, 120);

        return scrollForListFile;
    }

    private JScrollPane createScrollForInputFieldForList() {
        scrollForInputFieldForList = new JScrollPane(inputFieldForList);

        scrollForInputFieldForList.setBounds(10, 10, 381, 50);
        scrollForInputFieldForList.setVisible(true);

        return scrollForInputFieldForList;
    }

    private JScrollPane createScrollForOutputFieldForResult() {
        scrollForOutputFieldForResult = new JScrollPane(outputFieldForResult);

        scrollForOutputFieldForResult.setBounds(10, 110, 381, 50);
        scrollForOutputFieldForResult.setVisible(true);

        return scrollForOutputFieldForResult;
    }


    // Working with file
    private List<Integer> readFromFile(String nameFile) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("/home/belykh/Документы/Проекты/Task's/FilesForTask09/" + nameFile));

        List<Integer> integerData = new ArrayList<>();

        String[] stringData = scan.nextLine().split(" ");

        for(String element: stringData) {
            integerData.add(Integer.parseInt(element));
        }

        scan.close();

        return integerData;
    }

    private void writeToFile(List<Integer> resultInList, String nameInputFile) throws FileNotFoundException {
        String nameOutputFile = switch (nameInputFile) {
            case ("input01.txt")-> "output01.txt";
            case ("input02.txt")-> "output02.txt";
            case ("input03.txt")-> "output03.txt";
            case ("input04.txt")-> "output04.txt";
            case ("input05.txt")-> "output05.txt";
            case ("input06.txt")-> "output06.txt";
            case ("input07.txt")-> "output07.txt";
            case ("input08.txt")-> "output08.txt";
            case ("input09.txt")-> "output09.txt";
            case ("input10.txt")-> "output10.txt";

            default -> "output00.txt";
        };

        PrintWriter pw = new PrintWriter("/home/belykh/Документы/Проекты/Task's/FilesForTask09/" + nameOutputFile);

        StringBuilder stringResult = new StringBuilder();

        for(int element: resultInList){
            stringResult.append(element + " ");
        }

        pw.print(stringResult);

        pw.close();
    }


    // Read from inputFieldForList
    private List<Integer> readFromInputFieldForList() {
        String[] stringData = inputFieldForList.getText().split(" ");
        List<Integer> integerData = new ArrayList<>();

        for(String element: stringData) {
            integerData.add(Integer.parseInt(element));
        }

        return integerData;
    }

    // Write to TextField
    private void writeTextToInputFieldForList(List<Integer> dataFile) {
        StringBuilder stringResult = new StringBuilder();

        for(int element: dataFile) {
            stringResult.append(element + " ");
        }

        inputFieldForList.setText(String.valueOf(stringResult));
    }

    private void writeTextToOutputFieldForResult(List<Integer> result) {
        StringBuilder stringResult = new StringBuilder();

        for(int element: result) {
            stringResult.append(element + " ");
        }

        outputFieldForResult.setText(String.valueOf(stringResult));
    }

}