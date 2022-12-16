package Test;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        String text = "(a, second, (abc, y, (x, 7), uuu, (8, 9, (10, 1))), abcddcdba)";

        TreeNode rootNode = Logic.createRootNode(text);

        Tree.printTree(Logic.getInfoAboutEachLevels(rootNode));
    }

}