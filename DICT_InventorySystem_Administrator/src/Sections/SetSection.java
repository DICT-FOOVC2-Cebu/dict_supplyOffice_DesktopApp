/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sections;


import static Sections.DesignForm.screenDimension;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
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
    }

    
    @Override
    public void setFrame(JFrame frame,int width,int height,String sizetype) {
        setIcon(frame);
       if(sizetype.equals("mini")){
        
        frame.setLayout(null);
        frame.setMinimumSize(new Dimension(width,height));
        frame.setLocation(screenDimension.width/2-frame.getSize().width/2,
                 (screenDimension.height/2-frame.getSize().height/2)-30);
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
          header.setFont(new Font("Dialog", Font.BOLD, 14));
          header.setBackground(new Color(255,204,0));
          header.setForeground(Color.BLACK);
          header.setPreferredSize(new Dimension(0,rowHeight));
          
          table.setRowHeight(rowHeight);
        
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                JLabel label = (JLabel)c;
                label.setVerticalAlignment(SwingConstants.CENTER);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setFont(new Font("Dialog", Font.PLAIN, fontsize));
                
                boolean activation = table.getValueAt(row, 3).toString().equals("YES")?true:false;
                if(isSelected){
                    label.setBackground(new Color(153,0,0));
                    label.setForeground(Color.YELLOW);
                }else{
                    if(activation){
                    
                       label.setBackground(Color.GREEN);
                       label.setForeground(Color.BLACK);

                    
                    }else{
                        label.setBackground(Color.RED);
                        label.setForeground(Color.WHITE);
                    }
                }
               

                return label;
            }
        
        };
        table.setDefaultRenderer(Object.class, renderer);
        table.setDefaultRenderer(Boolean.class, renderer);
    
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
      
      public void setDisplaytoForward(Component comp,int distanceWalk,int sleep){
          
          Point point = comp.getLocation();
          
    
           Thread th = new Thread(){
      
            @Override
            public void run() {
                   try {
                        
                    
                        for(int a = 1; a<6; a++){
                            
                            Thread.sleep(sleep);
                            int x = (int) (comp.getLocation().getX()+distanceWalk);
                            int y = (int) comp.getLocation().getY();
                            comp.setLocation(new Point(x,y));
                            
                        }
                   } catch (InterruptedException ex) {
                       Logger.getLogger(SetSection.class.getName()).log(Level.SEVERE, null, ex);
                   }
               
               
            }
         
        };
        th.start();
      
      }
      
    public void setDisplayFromDown(Component comp,int sleep){
          
          Point point = comp.getLocation();
         
          
          int screenHeight = (int) screenDimension.getHeight();
          int belowSpace = (int) (point.getY()- screenHeight);
          int addSpace = (int) (screenDimension.getHeight()/4);
          comp.setLocation((int) point.getX(),0-screenHeight);
          comp.setVisible(true);
         
          
           Thread th = new Thread(){
      
            @Override
            public void run() {
                   try {
                        
                        for(int a = 1; a<6; a++){
                            
                            Thread.sleep(sleep);
                            int x = (int) comp.getLocation().getX();
                            int y = (int) comp.getLocation().getY()+addSpace;
                            comp.setLocation(new Point(x,y));
                            
                        }
                   } catch (InterruptedException ex) {
                       Logger.getLogger(SetSection.class.getName()).log(Level.SEVERE, null, ex);
                   }
               
               
            }
         
        };
        th.start();
      
      }
    
    public void setDisplayFromAbove(Component comp,int sleep){
          
          Point point = comp.getLocation();
          
          int screenHeight = (int) screenDimension.getHeight();
         
          
           Thread th = new Thread(){
      
            @Override
            public void run() {
                   try {
                        
                        for(int a = 1; a<6; a++){
                            
                            Thread.sleep(sleep);
                            int x = (int) comp.getLocation().getX();
                            int y = (int) comp.getLocation().getY()-150;
                            comp.setLocation(new Point(x,y));
                            
                        }
                        comp.setVisible(false);
                   } catch (InterruptedException ex) {
                       Logger.getLogger(SetSection.class.getName()).log(Level.SEVERE, null, ex);
                   }
               
               
            }
         
        };
        th.start();
      
      }
    
     public void setDisplaytoBackward(Component comp,int distanceWalk,int sleep){
          
          Point point = comp.getLocation();
          
    
           Thread th = new Thread(){
      
            @Override
            public void run() {
                   try {
                        
                    
                        for(int a = 1; a<6; a++){
                            
                            Thread.sleep(sleep);
                            int x = (int) (comp.getLocation().getX()- distanceWalk);
                            int y = (int) comp.getLocation().getY();
                            comp.setLocation(new Point(x,y));
                            
                        }
                   } catch (InterruptedException ex) {
                       Logger.getLogger(SetSection.class.getName()).log(Level.SEVERE, null, ex);
                   }
               
               
            }
         
        };
        th.start();
      
      }
      
    
    
      
}
