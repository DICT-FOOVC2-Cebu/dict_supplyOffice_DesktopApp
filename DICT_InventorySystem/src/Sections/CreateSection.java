/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sections;

import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
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
    public static void displayComponents(JPanel cont,Container con){
        cont.removeAll();
        cont.add(con);
        cont.revalidate();
        cont.repaint();
    }
      
    public static String getDateToday(){
        
        SimpleDateFormat formatter = new SimpleDateFormat("MMM-dd-yyyy HH:mm:ss");  
        Date date = new Date();  

        return formatter.format(date);
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
    


}
