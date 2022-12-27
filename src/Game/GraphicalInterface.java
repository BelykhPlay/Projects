package Game;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GraphicalInterface extends JFrame {
    private JPanel rootPanel;

    private JTable playTable;

    private JButton upButton;
    private JButton downButton;
    private JButton rightButton;
    private JButton leftButton;

    private JButton restartButton;

    private JList list1;

    private JPanel panelWithJTable;


    private PlayingField playingField;
    private GameObject selectedGameObject;


    public GraphicalInterface() {
        // JFrame
        setTitle("Кугейм");

        setSize(400, 550);
        setLocationRelativeTo(null);
        setResizable(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setContentPane(rootPanel);

        //Listeners
        createActionListeners();
        createMouseListeners();

    }


    private void createActionListeners() {
        // Движение игровых элементов
        downButton.addActionListener(action -> {
            if(selectedGameObject != null) {
                String[][] modifiedLevel = Movement.downwardMovement(playingField.getLevel(), selectedGameObject);

                playingField.setLevel(modifiedLevel);
                playTable.setModel(playingField.getGraphicalLevelView());

                checkingOfCompletionOfGame();
            }
        });

        upButton.addActionListener(action -> {
            if(selectedGameObject != null) {
                String[][] modifiedLevel = Movement.upwardMovement(playingField.getLevel(), selectedGameObject);

                playingField.setLevel(modifiedLevel);
                playTable.setModel(playingField.getGraphicalLevelView());

                checkingOfCompletionOfGame();
            }
        });

        rightButton.addActionListener(action -> {
            if(selectedGameObject != null) {
                String[][] modifiedLevel = Movement.movementToTheRight(playingField.getLevel(), selectedGameObject);

                playingField.setLevel(modifiedLevel);
                playTable.setModel(playingField.getGraphicalLevelView());

                checkingOfCompletionOfGame();
            }
        });

        leftButton.addActionListener(action -> {
            if(selectedGameObject != null) {
                String[][] modifiedLevel = Movement.movementToTheLeft(playingField.getLevel(), selectedGameObject);

                playingField.setLevel(modifiedLevel);
                playTable.setModel(playingField.getGraphicalLevelView());

                checkingOfCompletionOfGame();
            }
        });

        // Рестарт
        restartButton.addActionListener(action -> {
            try {
                String nameCurrentLevel = playingField.getName();

                playingField = Logic.createPlayingField(nameCurrentLevel + ".txt");

                playTable.setModel(playingField.getGraphicalLevelView());

            } catch (IOException e) {
                throw new RuntimeException(e);

            }
        });
    }

    private void createMouseListeners() {
        // Построение уровня
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String nameLevel = list1.getSelectedValue().toString();

                    try {
                        playingField = Logic.createPlayingField(nameLevel + ".txt");

                        playTable.setModel(playingField.getGraphicalLevelView());

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        // Выбор игрового элемента для взаимодействия с ним
        playTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = playTable.rowAtPoint(e.getPoint());
                    int column = playTable.columnAtPoint(e.getPoint());

                    String name = String.valueOf(playTable.getValueAt(row, column));

                    selectedGameObject = getGameObjectByName(name);
                }
            }
        });
    }


    private void createUIComponents() {
        // Create playTable
        playTable = new JTable(new DefaultTableModel(9, 9)) {
            @Override // Создает таблицу с ячейками, который нельзя изменять
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        setCellsSizeOfTable(); // Установка размеров ячеек
        playTable.setShowGrid(false); // Отключение сетки
        playTable.setIntercellSpacing(new Dimension(0, 0)); // Установка размера между ячейками равному нулю
        playTable.setRowSelectionAllowed(false); // Отключение выделения по строкам

        playTable.setDefaultRenderer(Object.class, new LevelRender());

    }


    private void setCellsSizeOfTable() {
        playTable.setRowHeight(40);

        TableColumn columnModel;
        for (int j = 0; j < playTable.getColumnCount(); j++) {
            columnModel = playTable.getColumnModel().getColumn(j);
            columnModel.setWidth(40);

        }
    }

    private GameObject getGameObjectByName(String name) {
        try {
            for (GameObject e : playingField.getGameObjects()) {
                if (e.getName().equals(name)) {
                    return e;
                }
            }

        } catch (Exception e) {

        }

        return null;
    }

    private void checkingOfCompletionOfGame() {
        if(playingField.isEndGame()) {
            JOptionPane.showMessageDialog(panelWithJTable, "Congratulation, you have completed the level!!!");
        }
    }

}
