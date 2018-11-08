/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sections;

import static Sections.DesignForm.screenDimension;
import java.awt.Component;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Eunar B. Dayangco
 */
public class TransitionSection {

    public TransitionSection() {
    }
    
    public void fromLeft_toRight(Component comp,int distanceWalk,int sleep){
          
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
                       CreateSection.displayErrorMessage("Animation Error",
                               "Animation was Interrupted!Please run again!");
                       //Logger.getLogger(SetSection.class.getName()).log(Level.SEVERE, null, ex);
                   }
            }

        };
        th.start();
      
      }
    public void fromRight_toLeft(Component comp,int distanceWalk,int sleep){
          
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
                }catch (InterruptedException ex) {
                        CreateSection.displayErrorMessage("Animation Error",
                               "Animation was Interrupted!Please run again!");
                       //Logger.getLogger(SetSection.class.getName()).log(Level.SEVERE, null, ex);
                }
               
               
            }
         
        };
        th.start();
      
      }
    public void fromTop_toBottom(Component comp,int sleep,int deductTop){
          
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
    public void fromBottom_toTop(Component comp,int sleep){
          
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
                       CreateSection.displayErrorMessage("Animation Error",
                               "Animation was Interrupted!Please run again!");
                       //Logger.getLogger(SetSection.class.getName()).log(Level.SEVERE, null, ex);
                    }
               
            }
         
        };
        th.start();
      
    }
    
}
