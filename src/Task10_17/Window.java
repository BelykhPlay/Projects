package Task10_17;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Window extends JFrame {
    private JTextField inputFieldForCoordinates;

    private JButton buttonStartProgram;
    private JButton buttonCustomCoordinates;

    private JLabel infoAboutOpenFile;
    private JLabel infoAboutResult;

    private JList listFile;

    private JScrollPane scrollForInputFile;
    private JScrollPane scrollForInputFieldForCustomCoordinates;
    private JScrollPane scrollForOutputFieldForResult;

    private JFileChooser explorer;

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
        add(createInputFieldForCoordinates());

        // JButton
        add(createButtonStartProgram());
        add(createButtonCustomCoordinates());

        // JLabel
        add(createInfoAboutOpenFile());
        add(createInfoAboutResult());

        // JList
        add(createListFile());

        //JScrollPanel
        add(createScrollForListFile());
    }


    private JTextField createInputFieldForCoordinates() {
        inputFieldForCoordinates = new JTextField(0);

        inputFieldForCoordinates.setBounds(10, 10, 381, 50);
        inputFieldForCoordinates.setEditable(false);
        inputFieldForCoordinates.setVisible(true);

        return inputFieldForCoordinates;
    }


    private JButton createButtonStartProgram() {
        buttonStartProgram = new JButton("Start");
        buttonStartProgram.setBounds(95, 65, 295, 40);
        buttonStartProgram.setVisible(true);

        buttonStartProgram.addActionListener(action -> {
            double[][] coordinates = Logic.sortCoordinates(readFromInputFieldForList());

            Triangle triangle = new Triangle(coordinates);

            boolean isSatisfies = triangle.isItInAllQuarters();

            if(isSatisfies) {
                infoAboutResult.setBackground(Color.green);

            } else {
                infoAboutResult.setBackground(Color.red);
            }

            try {
                writeToFile(infoAboutOpenFile.getText(), isSatisfies);

            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        });

        return buttonStartProgram;
    }

    private JButton createButtonCustomCoordinates() {
        buttonCustomCoordinates = new JButton("Custom");

        buttonCustomCoordinates.setBounds(10, 65, 80, 40);
        buttonCustomCoordinates.setVisible(true);

        buttonCustomCoordinates.addActionListener(action -> {
            buttonCustomCoordinates.setBackground(Color.green);

            inputFieldForCoordinates.setEditable(true);
            inputFieldForCoordinates.setText(null);

            infoAboutResult.setBackground(Color.white);

            infoAboutOpenFile.setText("BebraCompany");
        });

        return buttonCustomCoordinates;
    }


    private JLabel createInfoAboutOpenFile() {
        infoAboutOpenFile = new JLabel("BebraCompany", SwingConstants.CENTER);

        infoAboutOpenFile.setBounds(395, 135, 95, 18);

        infoAboutOpenFile.setOpaque(true);
        infoAboutOpenFile.setBackground(Color.black);
        infoAboutOpenFile.setForeground(Color.white);

        return infoAboutOpenFile;
    }

    private JLabel createInfoAboutResult() {
        infoAboutResult = new JLabel("", SwingConstants.CENTER);

        infoAboutResult.setBounds(10, 110, 380, 50);

        infoAboutResult.setOpaque(true);
        infoAboutResult.setBackground(Color.white);

        return infoAboutResult;
    }


    private JList createListFile() {
        String[] data = {"input01.txt", "input02.txt", "input03.txt", "input04.txt", "input05.txt", "BEBRA_FILE"};

        listFile = new JList(data);

        listFile.setVisible(true);

        listFile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    // Zeroing out
                    buttonCustomCoordinates.setBackground(null);
                    inputFieldForCoordinates.setEditable(false);
                    infoAboutResult.setBackground(Color.white);

                    // Getting name open file
                    String nameFile = (String) listFile.getSelectedValue();
                    // Set text in inputFieldForCoordinates
                    try{
                        if(nameFile == "BEBRA_FILE") {
                            explorer = new JFileChooser("/home/belykh/Документы/Проекты/Task's/FilesForTask10/");

                            if(explorer.showOpenDialog(null) == explorer.APPROVE_OPTION) {
                                File file = explorer.getSelectedFile();

                                inputFieldForCoordinates.setText(doubleArrayToString(readFromFile(file.getName())));

                                infoAboutOpenFile.setText(file.getName());
                            }
                        } else {
                            inputFieldForCoordinates.setText(doubleArrayToString(readFromFile(nameFile)));
                            infoAboutOpenFile.setText(nameFile);
                        }

                    } catch (FileNotFoundException ex) {
                        System.out.println("File not found");

                    }

                }
            }
        });

        return listFile;
    }


    private JScrollPane createScrollForListFile() {
        scrollForInputFile = new JScrollPane(listFile);

        scrollForInputFile.setBounds(395, 10, 95, 120);

        return scrollForInputFile;
    }


    // Working with file
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

    private String doubleArrayToString(double[] data) {
        StringBuilder stringData = new StringBuilder();

        for(double element: data) {
            stringData.append(element).append(" ");
        }

        return stringData.toString();
    }

    // Read from inputFieldForArr
    private double[] readFromInputFieldForList() {
        String[] stringData = inputFieldForCoordinates.getText().split(" ");

        double[] doubleData = new double[6];

        int count = 0;
        for(String element: stringData) {
            doubleData[count++] = Double.parseDouble(element);
        }

        return doubleData;
    }

}