/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sections;



import Model.User;
import javax.swing.JFrame;

/**
 *
 * @author Eunar B. Dayangco
 */
public class StationSection {
    
    //private static User logUser;
    private static User logUser;
   

    public StationSection() {
    }

    public static User getLogUser() {
        return logUser;
    }

    public static void setLogUser(User logUser) {
        StationSection.logUser = logUser;
    }

     


    
    
}
