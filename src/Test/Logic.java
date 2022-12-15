package Test;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Logic {
    private static int maxLevel;
    private static int positionNextChild;

    private static List<List<TreeNode>> infoAboutEachLevels = new ArrayList<>();


    // Creating object of TreeNode.class
    public static TreeNode createRootNode(String text) {
        // Creating rootNode and the definition of all class variables
        TreeNode rootNode = childNode(text);

        setPositionForNode(rootNode);
        setLevelForNode(rootNode, 0);

        return rootNode;
    }

    private static @NotNull TreeNode childNode(String textTree) {
        textTree = textTree.substring(1, textTree.length() - 1);
        List<String> treeChildren = buildTreeChildren(textTree);

        TreeNode currentTreeNode = new TreeNode(treeChildren.get(0));

        // Есть ли у вершины ребенок?
        boolean isChild = false;
        for (String e : treeChildren) {

            if (e.startsWith("(") && isChild) {
                currentTreeNode.addChildren(childNode(e));


            } else if (isChild) {
                currentTreeNode.addChildren(new TreeNode(e));

            }

            isChild = true;
        }

        return currentTreeNode;

    }

    private static List<String> buildTreeChildren(String textTree) {
        String[] treeElements = textTree.split(", ");

        List<String> treeLevel = new ArrayList<>();

        StringBuilder textTreeInTextTree = new StringBuilder();

        boolean isChildFound = false;

        // Counts
        int countLeftBrackets = 0;
        int countRightBrackets = 0;

        for (String e : treeElements) {
            if (e.startsWith("(")) {
                countLeftBrackets++;

                isChildFound = true;
            }

            if (isChildFound) {
                countRightBrackets += findNumberRightBrackets(e);

                if (countRightBrackets == countLeftBrackets) {
                    textTreeInTextTree.append(e);
                    treeLevel.add(textTreeInTextTree.toString());
                    textTreeInTextTree.setLength(0);

                    isChildFound = false;

                } else {
                    textTreeInTextTree.append(e).append(", ");
                }


            } else {
                treeLevel.add(e);

            }
        }

        return treeLevel;
    }

    private static int findNumberRightBrackets(String element) {
        int count = 0;
        for (char e : element.toCharArray()) {
            if (String.valueOf(e).equals(")")) {
                count++;
            }
        }

        return count;
    }


    // Determining position for each node relative to the left edge
    private static void setPositionForNode(TreeNode rootNode) {
        for (TreeNode e : rootNode.getChildren()) {
            if (e.getChildren().isEmpty()) {
                e.setPosition(positionNextChild);
                positionNextChild += e.getName().length() + 1;

            } else {
                setPositionForNode(e);

            }
        }

        int position = solvePositionParent(rootNode);
        rootNode.setPosition(position);
    }

    private static int solvePositionParent(TreeNode parentNode) {
        int positionParent = parentNode.getChildren().size() - 1;

        boolean isFirstChild = true;
        for (TreeNode child : parentNode.getChildren()) {
            if (isFirstChild) {
                positionParent += child.getPosition();
                isFirstChild = false;

            }

            positionParent += child.getName().length();
        }

        positionParent = positionParent / 2;

        return positionParent;
    }


    // Determining the level for each node
    private static void setLevelForNode(TreeNode rootNode, int level) {
        rootNode.setLevel(level);
        for (TreeNode e : rootNode.getChildren()) {
            if (e.getChildren().isEmpty()) {
                maxLevel = Math.max(level+1, maxLevel);
                e.setLevel(level + 1);

            } else {
                setLevelForNode(e, level + 1);
            }
        }
    }


    // Creating list<> with elements at each levels
    private static void createListWithInfoAboutLevels() {
        for (int i = 0; i <= maxLevel; i++) {
            List<TreeNode> empty = new ArrayList<>();
            infoAboutEachLevels.add(empty);
        }


    }
    private static void fillingListWithElements(TreeNode rootNode, boolean isFirst) {
        List<TreeNode> storage;

        if(isFirst) {
            storage = infoAboutEachLevels.get(rootNode.getLevel());
            storage.add(rootNode);
        }

        for (TreeNode e: rootNode.getChildren()) {
            if(e.getChildren().isEmpty()) {
                storage = infoAboutEachLevels.get(e.getLevel());
                storage.add(e);

            } else {
                storage = infoAboutEachLevels.get(e.getLevel());
                storage.add(e);

                fillingListWithElements(e, false);
            }
        }
    }


    // Getters and Setters
    public static List<List<TreeNode>> getInfoAboutEachLevels(TreeNode rootNode) {
        createListWithInfoAboutLevels();
        fillingListWithElements(rootNode, true);

        return infoAboutEachLevels;
    }

}