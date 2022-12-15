package Test;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private List<TreeNode> children = new ArrayList<>();

    private String name;
    private int level;
    private int position;

    public TreeNode(String name) {
        this.name = name;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getPosition() {
        return position;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void addChildren(TreeNode partOfTree) {
        this.children.add(partOfTree);
    }

}
