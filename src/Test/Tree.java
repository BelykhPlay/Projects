package Test;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    public static void printTree(@NotNull List<List<TreeNode>> infoAboutLevels) {
        int positionPreviousNode;
        int position;

        // Вывод | для тех элементов, которые имеют детей
        List<Integer> positionNodeWithChildrenAtLevel = new ArrayList<>();

        // Вывод всех ---
        List<int[]> coordinatesFirstAndLastChild = new ArrayList<>();

        // Вывод | для всех элементов на следующем уровне
        List<Integer> positionAllNodeAtNextLevel = new ArrayList<>();

        for (int i = 0; i < infoAboutLevels.size(); i++) {
            positionPreviousNode = 0;

            positionNodeWithChildrenAtLevel.clear();
            coordinatesFirstAndLastChild.clear();
            positionAllNodeAtNextLevel.clear();

            for (TreeNode e : infoAboutLevels.get(i)) {
                position = e.getPosition() - positionPreviousNode;
                positionPreviousNode = e.getPosition() + e.getName().length();

                printingNameNode(position, e.getName());

                if (!e.getChildren().isEmpty()) {
                    coordinatesFirstAndLastChild.add(getCoordinatesFirstAndLastChild(e));
                    positionNodeWithChildrenAtLevel.add(e.getPosition());

                }
            }

            if (i != infoAboutLevels.size() - 1) {
                positionAllNodeAtNextLevel = getPositionAllNodeAtNextLevel(infoAboutLevels.get(i + 1));

            }

            printingBranches(positionNodeWithChildrenAtLevel, coordinatesFirstAndLastChild, positionAllNodeAtNextLevel);

        }
    }

    // Вспомогательные функции
    private static int[] getCoordinatesFirstAndLastChild(TreeNode parentNode) {
        int[] coordinates = new int[2];

        List<TreeNode> children = parentNode.getChildren();
        int numberLastChild = parentNode.getChildren().size() - 1;

        coordinates[0] = children.get(0).getPosition();
        coordinates[1] = children.get(numberLastChild).getPosition();

        return coordinates;
    }

    private static List<Integer> getPositionAllNodeAtNextLevel(List<TreeNode> infoAboutNextLevel) {
        List<Integer> positionAllNodeAtNextLevel = new ArrayList<>();

        for (TreeNode e : infoAboutNextLevel) {
            positionAllNodeAtNextLevel.add(e.getPosition());
        }

        return positionAllNodeAtNextLevel;
    }

    // Вывод всех элементов дерева на экран
    private static void printingNameNode(int position, String name) {
        printingSigns(position, " ");

        System.out.print(name);
    }

    private static void printingBranches(List<Integer> positionNodeWithChildrenAtLevel,
                                         List<int[]> coordinatesFirstAndLastChild,
                                         List<Integer> positionAllNodeAtNextLevel) {

        int positionPreviousElement = 0;

        System.out.println();


        // Вывод | для тех элементов, которые имеют детей
        int numberOfSpaces;

        for (int position : positionNodeWithChildrenAtLevel) {
            numberOfSpaces = position - positionPreviousElement;

            printingSigns(numberOfSpaces, " ");
            System.out.print("|");

            positionPreviousElement = position + 1;
        }
        System.out.println();


        // Вывод всех ---
        int difference;
        boolean isFirst = true;

        positionPreviousElement = 0;
        for (int[] arr : coordinatesFirstAndLastChild) {
            difference = arr[1] - arr[0] + 1;

            if (isFirst) {
                printingSigns(arr[0], " ");
                printingSigns(difference, "-");

                isFirst = false;

            } else {
                printingSigns(arr[0] - positionPreviousElement, " ");
                printingSigns(difference, "-");
            }

            positionPreviousElement = arr[1] + 1;
        }
        System.out.println();


        // Вывод | для всех элементов на следующем уровне, если выводится не последний уровень
        positionPreviousElement = 0;
        for (int position : positionAllNodeAtNextLevel) {
            numberOfSpaces = position - positionPreviousElement;

            printingSigns(numberOfSpaces, " ");
            System.out.print("|");

            positionPreviousElement = position + 1;
        }
        System.out.println();
    }

    private static void printingSigns(int numberOfSigns, String sign) {
        for (int i = 0; i < numberOfSigns; i++) {
            System.out.print(sign);
        }
    }

}
