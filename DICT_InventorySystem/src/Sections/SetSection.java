/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sections;

import Model.Employee;
import Model.Location;

import static Sections.DesignForm.screenDimension;
import com.github.sarxos.webcam.Webcam;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Eunar B. Dayangco
 */
public class SetSection implements DesignForm{

    private boolean status;
    static int xMouseLocation,yMouseLocation;

    public SetSection() {
        
        status = false;
        xMouseLocation = 0;
        yMouseLocation = 0;
      
    }
    
    
    
    public void setIcon(JFrame frame){
        
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/pictures/DICT_LOGO.png")));
    
    }
    
   
    
    public static void setCameralist(JComboBox c,List<Webcam> webcams){
        
        c.removeAllItems();
        for(Webcam w:webcams){
            c.addItem(w.getDevice().getName());
        }
    
    }
    
    
     public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
    public void setFormDesign(JFrame form){
        
        
       
    }

    @Override
    public void designForm(JFrame frame,JPanel panel,String sizetype) {
     
        setFrame(frame,panel.getWidth(),panel.getHeight(),sizetype);
        setPanel(panel,sizetype);
        setMovableUndecorated(frame, panel);
        setIcon(frame);
    }

    
    @Override
    public void setFrame(JFrame frame,int width,int height,String sizetype) {
       
       if(sizetype.equals("mini")){
        
        frame.setLayout(null);
        frame.setMinimumSize(new Dimension(width,height));
        frame.setLocation(screenDimension.width/2-frame.getSize().width/2,
                 (screenDimension.height/2-frame.getSize().height/2));
        frame.setBackground(new Color(0,0,0,0));
       
       }
       else if(sizetype.equals("full")){
                  
       
        frame.setSize(screenDimension);
        
        //center the form
        
        frame.setLocationRelativeTo(null);
        frame.setExtendedState( JFrame.MAXIMIZED_BOTH );
        
       }
       
    }

    @Override
    public void setPanel(JPanel panel,String sizetype) {
       
        if (sizetype.equals("full")){
            panel.setSize(screenDimension);
             
        }
    }

    @Override
    public void setMovableUndecorated(JFrame frame, JPanel panel) {
         panel.addMouseMotionListener(new MouseMotionListener() {
          @Override
          public void mouseDragged(MouseEvent e) {
              
            int xMouseDragged = e.getXOnScreen();
            int yMouseDragged = e.getYOnScreen();
        
            frame.setLocation(xMouseDragged-xMouseLocation, yMouseDragged-yMouseLocation);
          }

          @Override
          public void mouseMoved(MouseEvent e) {
              
          }
      });
      
      panel.addMouseListener(new MouseListener() {
          @Override
          public void mouseClicked(MouseEvent e) {
              
          }

          @Override
          public void mousePressed(MouseEvent e) {
               xMouseLocation = e.getX();
               yMouseLocation = e.getY();
          }

          @Override
          public void mouseReleased(MouseEvent e) {
             
          }

          @Override
          public void mouseEntered(MouseEvent e) {
              
          }

          @Override
          public void mouseExited(MouseEvent e) {
             
          }
      });
    }

    public void extendPanelHeight(JPanel panel){
      Dimension dim = new Dimension(panel.getWidth(), (int) screenDimension.getHeight());
      panel.setSize(dim);
    } 
    
    public void setTableRender(JTable table,int fontsize,int rowHeight,
            Color odd,Color even,Color oddfont,Color evenfont){
        
          JTableHeader header = table.getTableHeader();
          header.setFont(new Font("Dialog", Font.BOLD, fontsize));
          
          table.setRowHeight(rowHeight);
        
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                JLabel label = (JLabel)c;
                label.setVerticalAlignment(SwingConstants.CENTER);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                if(row % 2 == 0){
                    
                   label.setBackground(even);
                   label.setForeground(evenfont);
                   
                    
                }else{

                    label.setBackground(odd);
                    label.setForeground(oddfont);
                }
                
    
                return label;
            }
        
        };
        table.setDefaultRenderer(Object.class, renderer);
    
    }
    public void setTableRender(JTable table){
          
          JTableHeader header = table.getTableHeader();
          header.setFont(new Font("Dialog", Font.BOLD, 12));
        
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                JLabel label = (JLabel)c;
                label.setVerticalAlignment(SwingConstants.CENTER);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                if(row % 2 == 0){
                    
                   label.setBackground(new Color(204,204,204));
                   label.setForeground(Color.black);
                   
                    
                }else{
                   
                    label.setBackground(new Color(255,255,255));
                    label.setForeground(Color.black);
                }
                
    
                return label;
            }
        
        };
        table.setDefaultRenderer(Object.class, renderer);
      
       
    }
    
    public static void hideColumn(int column,JTable table){
        
        table.getColumnModel().getColumn(column).setMinWidth(0);
        table.getColumnModel().getColumn(column).setMaxWidth(0);
        table.getColumnModel().getColumn(column).setWidth(0);
    }
    
    public static void displayImage(JLabel label,Image image){
        
        try{
        
            label.setIcon(null);
           
            Image resizedImage = image.getScaledInstance(label.getWidth(), label.getHeight(),Image.SCALE_SMOOTH);
            ImageIcon newImage = new ImageIcon(resizedImage);
            label.setIcon(newImage);
            
        
        }catch(NullPointerException ex){
            CreateSection.displayInfoMessage("Display Image on JLabel error.", ex.toString());
        }

    }
    

    
}
