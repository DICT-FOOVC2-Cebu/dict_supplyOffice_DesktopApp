/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sections;

import Database.DatabaseTemplates;
import Database.UserDB;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Eunar B. Dayangco
 */
public class CreateSection {
    
    public static void displayErrorMessage(String title, String message){

        JOptionPane.showMessageDialog(null,message, title,JOptionPane.ERROR_MESSAGE);
    }
    public static void displayInfoMessage(String title, String message){

        JOptionPane.showMessageDialog(null,message, title,JOptionPane.INFORMATION_MESSAGE);
    }
    public static void displayComponents(Container cont,Container con){
        cont.removeAll();
        cont.add(con);
        cont.revalidate();
        cont.repaint();
    }
      
    public static String getDateToday(){
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String dateOnString = dateFormat.format(date);
        return dateFormat.format(date);
      
    }
    
    public static ByteArrayInputStream  makeImage(Image image){
        
        
        BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bi.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        ByteArrayOutputStream baos = null;
      
            baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bi, "png", baos);
        } catch (IOException ex) {
            Logger.getLogger(CreateSection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        return bais;
     }
    
    public static Image getImage(byte[] imagebytes){
    
        Image image = null;
        InputStream in = new ByteArrayInputStream(imagebytes);
        try {
            BufferedImage bImageFromConvert = ImageIO.read(in);
            
            image = bImageFromConvert.getScaledInstance(bImageFromConvert.getWidth(), bImageFromConvert.getHeight(), Image.SCALE_SMOOTH);
        } catch (IOException ex) {
            Logger.getLogger(CreateSection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return image;
    }
    
    public static void makeFadeIn(JPanel container,Container to,int sleep){
        
        Dimension componentSize = container.getSize();
        
        Thread thread = new Thread(){
            
          
            Point p = container.getLocation();
            int currentWidth=(int) componentSize.getWidth();
            int currentHeight = (int) componentSize.getHeight();
            int subtractWidth = (int) (componentSize.getWidth()/5);
            int addWidth = (int) (componentSize.getWidth()/5);
            
            @Override
            public void run() {
                try {
                  for(int a = 1; a<6; a++){
                       Thread.sleep(sleep);
                       currentWidth -=subtractWidth;
                      
                     
                      container.setSize(new Dimension(currentWidth,currentHeight));
                     
                  }
                  
                  CreateSection.displayComponents(container, to);
                  
                  for(int a = 1; a<6; a++){
                       Thread.sleep(sleep);
                      
                       currentWidth +=addWidth;
                      
                     
                      container.setSize(new Dimension(currentWidth,currentHeight));
                     
                  }
                  
                } catch (InterruptedException ex) {
                       Logger.getLogger(SetSection.class.getName()).log(Level.SEVERE, null, ex);
                }

                
            }
        
        
    
    };
       thread.start();
      
        
    }
    
    public static int generateID(int from,int to,String fieldbase){
        
        int genID = 0;
        
        while(true){
            
            genID =  (int) (Math.random() * (to - from)) + from;
            
            if(!new UserDB().isDataExist(fieldbase, genID)){
                break;
            }
        }
        
        return genID;
    
    }
    
    
    

}
