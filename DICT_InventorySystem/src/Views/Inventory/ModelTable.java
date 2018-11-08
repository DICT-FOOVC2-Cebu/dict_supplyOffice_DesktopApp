/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Inventory;

import java.util.Vector;
import javax.swing.Icon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ModelTable B. Dayangco
 */
public class ModelTable extends DefaultTableModel{
    

  

    public ModelTable(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
   
    }
    
 
    
    @Override
    public Class getColumnClass(int column){
        
        if(column == 2){
            return Icon.class;
        }else{
            return getValueAt(0, column).getClass();
        }
    }
    
    
    
    
}
