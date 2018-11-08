/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Slider;


import Sections.CreateSection;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;



/**
 *
 * @author Eunar B. Dayangco
 */
public class AnimationSection {
    
    public final Dimension screenDimension = Toolkit.getDefaultToolkit().
            getScreenSize();
    public final int textSize = 12;
    public AnimationSection() {
        
    }
    
    public void containersMovetoUp(Container cont,int sleep,int addDistance){
        
        int countdistance = cont.getHeight()/6;
       
         Thread th = new Thread(){
            @Override
            public void run() {
                 try {

                        for(int a = 1; a<6; a++){
                            
                            Thread.sleep(sleep);
                          
                                
                            int entrance_x =(int) cont.getLocation().getX();
                            int entrance_y = (int) (cont.getLocation().getY()-(countdistance+addDistance));
                            cont.setLocation(new Point(entrance_x,entrance_y));
                            
                        }
                   } catch (InterruptedException ex) {
                       //Logger.getLogger(SetSection.class.getName()).log(Level.SEVERE, null, ex);
                   }
            }
        };
         
         th.start();
    
    }

    public void containersMovetoDown(Container cont,int sleep,int addDistance){
        
        int countdistance = cont.getHeight()/6;
       
         Thread th = new Thread(){
            @Override
            public void run() {
                 try {
                        for(int a = 1; a<6; a++){
                            
                            Thread.sleep(sleep);
                            int x =(int) cont.getLocation().getX();
                            int y = (int) (cont.getLocation().getY()+(countdistance+addDistance));
                           
                            cont.setLocation(new Point(x,y));
                            
                        }
                   } catch (InterruptedException ex) {
                       //Logger.getLogger(SetSection.class.getName()).log(Level.SEVERE, null, ex);
                   }
            }
        };
         
         th.start();
    
    }
    
    public void containersMovetoLeft(Container cont,int sleep,int addDistance){
          
        int countdistance = cont.getWidth()/6;
       
         Thread th = new Thread(){
            @Override
            public void run() {
                 try {
                        for(int a = 1; a<6; a++){
                            
                            Thread.sleep(sleep);
                            int x =(int) (cont.getLocation().getX()-(countdistance+addDistance));
                            int y = (int) cont.getLocation().getY();
                           
                            cont.setLocation(new Point(x,y));
                            
                        }
                   } catch (InterruptedException ex) {
                       //Logger.getLogger(SetSection.class.getName()).log(Level.SEVERE, null, ex);
                   }
            }
        };
         
         th.start();
    
    
    }
    public void containersMovetoRight(Container cont,int sleep,int addDistance){
          
        int countdistance = cont.getWidth()/6;
       
         Thread th = new Thread(){
            @Override
            public void run() {
                 try {
                        for(int a = 1; a<6; a++){
                            
                            Thread.sleep(sleep);
                            int x =(int) (cont.getLocation().getX()+(countdistance+addDistance));
                            int y = (int) cont.getLocation().getY();
                           
                            cont.setLocation(new Point(x,y));
                            
                        }
                   } catch (InterruptedException ex) {
                       //Logger.getLogger(SetSection.class.getName()).log(Level.SEVERE, null, ex);
                   }
            }
        };
         
         th.start();
    
    
    }
    public void framesMovetoBottom(Component comp,int sleep,int deductTop){
          
        Point point = comp.getLocation();

        int screenHeight = (int) screenDimension.getHeight();
        int addSpace = (int) (screenDimension.getHeight()/4)-deductTop;
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
                       CreateSection.displayErrorMessage("Animation Error",
                               "Animation was Interrupted!Please run again!");
                       //Logger.getLogger(SetSection.class.getName()).log(Level.SEVERE, null, ex);
                   }
               
               
            }
         
        };
        th.start();
      
      }
     public void framesMoveBacktoTop(Component comp,int sleep,int deductTop){
          
   
        int addSpace = (int) (screenDimension.getHeight()/4)-deductTop;
      
           Thread th = new Thread(){
      
            @Override
            public void run() {
                   try {
                        for(int a = 1; a<6; a++){
                            
                            Thread.sleep(sleep);
                            int x = (int) comp.getLocation().getX();
                            int y = (int) comp.getLocation().getY()-addSpace;
                            comp.setLocation(new Point(x,y));
                            
                        }
                        comp.setVisible(false);
                   } catch (InterruptedException ex) {
                       CreateSection.displayErrorMessage("Animation Error",
                               "Animation was Interrupted!Please run again!");
                       //Logger.getLogger(SetSection.class.getName()).log(Level.SEVERE, null, ex);
                   }
               
               
            }
         
        };
        th.start();
      
      }
    public void framesMovetoLeft(Component comp,int distanceWalk,int sleep){
          
        Point point = comp.getLocation();
          comp.setLocation((int)point.getX()+comp.getWidth(),(int)point.getY());
          comp.setVisible(true);
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
                }catch (InterruptedException ex) {
                        CreateSection.displayErrorMessage("Animation Error",
                               "Animation was Interrupted!Please run again!");
                       //Logger.getLogger(SetSection.class.getName()).log(Level.SEVERE, null, ex);
                }
               
               
            }
         
        };
        th.start();
      
      }
    
    public void slidetoLeft(Component comp,int maxWidth,int sleep){
        
        
         Thread th = new Thread(){
      
            @Override
            public void run() {
                try {                      
                    for(int a = 1; a<6; a++){
                            
                        Thread.sleep(sleep);
                        
                        comp.setSize(comp.getWidth()+(maxWidth/6), comp.getHeight());
                        
                    }
                }catch (InterruptedException ex) {
                        CreateSection.displayErrorMessage("Animation Error",
                               "Animation was Interrupted!Please run again!");
                       //Logger.getLogger(SetSection.class.getName()).log(Level.SEVERE, null, ex);
                }
               
               
            }
         
        };
        th.start();
       
        
    }
    
     public void slidetoBackfromLeft(Component comp,int maxWidth,int sleep,JFrame frame){
        
        
         Thread th = new Thread(){
      
            @Override
            public void run() {
                try {                      
                    for(int a = 1; a<6; a++){
                            
                        Thread.sleep(sleep);
                        
                        comp.setSize(comp.getWidth()-(maxWidth/6), comp.getHeight());
                        
                    }
                    frame.dispose();
                }catch (InterruptedException ex) {
                        CreateSection.displayErrorMessage("Animation Error",
                               "Animation was Interrupted!Please run again!");
                       //Logger.getLogger(SetSection.class.getName()).log(Level.SEVERE, null, ex);
                }
               
               
            }
         
        };
        th.start();
       
        
    }

}
