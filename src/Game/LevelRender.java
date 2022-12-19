package Game;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class LevelRender extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        Color color = fromValueToColor(String.valueOf(value));

        cell.setForeground(color);
        cell.setBackground(color);

        return cell;
    }

    private Color fromValueToColor(String value) {
        return switch (value) {
            case "w" -> new Color(97, 97, 97);
            case "f" -> new Color(255, 242, 242, 141);

            case "G" -> new Color(155, 255, 125, 102);
            case "B" -> new Color(155, 125, 255, 102);
            case "Y" -> new Color(251, 251, 56, 98);

            case "g" -> new Color(155, 255, 125);
            case "b" -> new Color(155, 125, 255);
            case "y" -> new Color(255, 255, 125);
            default -> Color.white;
        };

    }

}