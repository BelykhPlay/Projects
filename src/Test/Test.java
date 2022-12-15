package Test;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        String text = "(a, (b, (c, j, k)), d)";

        TreeNode rootNode = Logic.createRootNode(text);

        Tree.printTree(Logic.getInfoAboutEachLevels(rootNode));
    }

    public static void tree(TreeNode rootNode, boolean isRootNode) {
        String name = rootNode.getName();
        int position = rootNode.getPosition();
        List<TreeNode> children = rootNode.getChildren();

        // Вывод родителя
        if(isRootNode) {
        printingNameNode(position, name);
            System.out.println();
            System.out.println();
            System.out.println();
        }
//        printingBranches(children, rootNode.getPosition());

        int positionPreviousChildren = 0;
        boolean isLevelPrinted = false;
        for (int i = 0; i <= children.size(); i++) {
            if (i == children.size()) {
                isLevelPrinted = !isLevelPrinted;

                System.out.println();

            }

            if (!isLevelPrinted) {
                name = children.get(i).getName();
                position = children.get(i).getPosition() - positionPreviousChildren;

                printingNameNode(position, name);

                positionPreviousChildren = children.get(i).getPosition() + name.length();

            } else {

            }

        }
    }

    public static void printingNameNode(int position, String name) {
        for (int i = 0; i < position; i++) {
            System.out.print(" ");
        }

        System.out.print(name);
    }

    public static void printingBranches(List<TreeNode> children, int positionParent) {
        // Для родителя
        for (int i = 0; i < positionParent; i++) {
            System.out.print(" ");
        }
        System.out.println("|");

        // Для детей

        // Переменные
        TreeNode lastChildren = children.get(children.size() - 1);

        int numberOfMinus = lastChildren.getPosition() + lastChildren.getName().length();
        int positionFirstChild = children.get(0).getPosition();
        int lengthNamePreviousChild = 0;
        int stopper;

        // Отступ для линии
        for (int i = 0; i < positionFirstChild; i++) {
            System.out.print(" ");
        }

        //Todo отступ по позиции предедыщего элемента

        // Сама линия
        for (int i = 0; i < numberOfMinus; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Черточки для каждого из детей
        for (TreeNode e : children) {
            stopper = lengthNamePreviousChild;
            for (int i = 0; i < stopper; i++) {
                System.out.print(" ");
            }
            System.out.print("|");

            lengthNamePreviousChild = e.getName().length();
        }
        System.out.println();

    }

}