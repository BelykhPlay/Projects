package Task11_25;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class WindowApplication extends JFrame {
    private JPanel rootPanel;

    private JButton button1;
    private JButton button2;
    private JButton button3;

    private JTextArea textArea;

    private JLabel label1;
    private JLabel label2;
    private JLabel label3;

    private JTable table;

    private JFileChooser fileChooser;

    private JOptionPane warning;

    private DefaultTableModel tableModel;

    public WindowApplication() {
        // JFrame
        setTitle("Email");

        setSize(400, 600);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(rootPanel);

        // Other
        createListeners();

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        // JTable
        tableModel = new DefaultTableModel(0, 1);
        table = new JTable(tableModel);

        table.setTableHeader(null);


        //JTextArea
        textArea = new JTextArea();

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

    }

    private void createListeners() {
        // For button1
        button1.addActionListener(actionEvent -> {
            fileChooser = new JFileChooser("/home/belykh/Документы/Projects/FilesForTask11");

            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
            fileChooser.setFileFilter(filter);

            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = fileChooser.getSelectedFile();
                    textArea.setText(readFile(file));
                    label3.setText(file.getName());

                } catch (IOException e) {
                    System.out.println("Что-то пошло не так");

                }
            }
        });

        // For button2
        button2.addActionListener(actionEvent -> {
                textArea.setText(null);

                tableModel.setRowCount(1);
                tableModel.setValueAt(null, 0, 0);

                label3.setText("Никакой из файлов не был открыт");
        });

        // For button3
        button3.addActionListener(actionEvent -> {
            if (textArea.getText().length() != 0) {
                List<String> emailsList = Logic.getEmail(textArea.getText());

                tableModel.setRowCount(emailsList.size());

                for(int i = 0; i < emailsList.size(); i++) {
                    tableModel.setValueAt(emailsList.get(i), i, 0);
                }

                try {
                    writeToFile(label3.getText(), emailsList);

                } catch (FileNotFoundException e) {
                    System.out.println("Файл не найден");

                }

            } else {
                JOptionPane.showMessageDialog(rootPanel, "Может быть ты уже заполнишь поле ввода?");

            }
        });
    }


    private String readFile(File file) throws IOException {
        Scanner reader = new Scanner(file);

        StringBuilder text = new StringBuilder();

        while (reader.hasNextLine()) {
            text.append(reader.nextLine());
        }

        reader.close();

        return text.toString();
    }

    private void writeToFile(String nameFile, List<String> emails) throws FileNotFoundException {
        String nameOutputFile = "/home/belykh/Документы/Projects/FilesForTask11/" + switch(nameFile) {
            case("input01.txt") -> "output01.txt";
            case("input02.txt") -> "output02.txt";
            case("input03.txt") -> "output03.txt";
            case("input04.txt") -> "output04.txt";
            case("input05.txt") -> "output05.txt";

            default -> "output00.txt";
        };

        PrintStream stream = new PrintStream(nameOutputFile);

        for(String email: emails) {
            stream.println(email);
        }

        stream.close();
    }

}