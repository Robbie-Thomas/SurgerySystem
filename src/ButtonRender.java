import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRender extends JButton implements TableCellRenderer {
    {
//SET BUTTON PROPERTIES
        setOpaque(true);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object obj,
                                                   boolean selected, boolean focused, int row, int col) {
//SET PASSED OBJECT AS BUTTON TEXT
        setText((obj==null) ? "":obj.toString());
        return this;
    }
}
