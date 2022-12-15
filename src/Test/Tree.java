package Test;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Tree {
    private TreeNode rootNode;

    public Tree(TreeNode rootNode) {
        this.rootNode = rootNode;
    }

    public String printSimpleTree(@NotNull TreeNode rootNode, String indent) {
        String name = null;
        System.out.println(indent + rootNode.getName());

        indent = indent + "  ";

        for (TreeNode e : rootNode.getChildren()) {
            if (e.getChildren().isEmpty()) {
                name = e.getName();
                System.out.println(indent + name);

            } else {
                name = printSimpleTree(e, indent);

            }
        }

        return name;
    }

    public static void printTree(List<List<TreeNode>> infoAboutLevels) {
        int positionPreviousNode;
        int position;
        for (int i = 0; i < infoAboutLevels.size(); i++) {
            positionPreviousNode = 0;
            for (TreeNode e : infoAboutLevels.get(i)) {
                position = e.getPosition() - positionPreviousNode;
                printingNameNode(position, e.getName());
                positionPreviousNode = e.getPosition() + e.getName().length();
            }
            System.out.print("\n\n");
        }
    }

    public static void printingNameNode(int position, String name) {
        for (int i = 0; i < position; i++) System.out.print(" ");

        System.out.print(name);
    }

}
