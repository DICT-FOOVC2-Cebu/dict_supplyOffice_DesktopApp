/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sections;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Eunar B. Dayangco
 */
public interface DesignForm {
    
    public final Dimension screenDimension = Toolkit.getDefaultToolkit().
            getScreenSize();
    public final int textSize = 12;
    
    public void designForm(JFrame frame,JPanel panel,String sizetype);
    public void setFrame(JFrame frame,int width,int height,String sizetype);
    public void setPanel(JPanel panel,String sizetype);
    public void setMovableUndecorated(JFrame frame,JPanel panel);
}
