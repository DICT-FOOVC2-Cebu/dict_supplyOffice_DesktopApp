/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Sections.CreateSection;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Eunar B. Dayangco
 */
public class PanelRender implements TableCellRenderer{
    
    private JPanel panel;

    public PanelRender(JPanel panel) {
        this.panel = panel;
    }

    public PanelRender() {
    }
    
    

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
     //   CreateSection.displayInfoMessage("row and col","Row:"+row+" Col:"+column);
        return (Component)this.panel;
    }
    
}
