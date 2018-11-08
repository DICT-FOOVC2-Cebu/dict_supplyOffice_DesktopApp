/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sections;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;


/**
 *
 * @author Eunar B. Dayangco
 */
public class OpenPDF {

    public OpenPDF() {
    }
    
  
    public void open(JScrollPane scrollpane,String file){
        try {
           SwingController control=new SwingController();
            SwingViewBuilder factry=new SwingViewBuilder(control);
            JPanel veiwerCompntpnl=factry.buildViewerPanel();
            ComponentKeyBinding.install(control, veiwerCompntpnl);
           
            control.getDocumentViewController().setAnnotationCallback(
                    new org.icepdf.ri.common.MyAnnotationCallback(
                    control.getDocumentViewController()));
                   control.openDocument(file);
        scrollpane.setViewportView(veiwerCompntpnl);  
        } catch (Exception ex) {
        }
    
    }
    
}
