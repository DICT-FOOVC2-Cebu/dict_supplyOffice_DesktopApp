/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.Database;
import Model.User;
import Database.UserDB;
import Sections.CreateSection;
import Sections.StationSection;
import Views.Login.LoginUI;
import Views.Main.DICTISMain;
import java.awt.Cursor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Eunar B. Dayangco
 */
public class UserController {
    
    private Database<User> database;
    
    
    public UserController() {
        
    }
    
    public UserController(User user) {
        this.database = new UserDB(user);
     
    }

    
    public void loginProcess(LoginUI login){
        String displayMessage = "";
    
        if(login.validateInput()){
            CreateSection.displayInfoMessage("Fields Validation",login.getMessage());
        }else{
            login.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));              
            
            User user = login.getUserEntry();
            this.database = new UserDB();
           
            
            
            try {
                ArrayList<User> usersfound = this.database.getAllDatas
        (" WHERE userUsername=? AND userPassword=?", user.getUsername(),user.getPassword());
                
                if(usersfound.isEmpty()){
                     displayMessage+="Invalid Account.\n";
                }else{
                    StationSection.setLogUser(usersfound.get(0));
                    displayMessage = "";
                    new DICTISMain().setVisible(true);
                    login.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    login.dispose();
                }
            
            } catch (Exception ex) {
                
                displayMessage+=ex.toString()+"\n";
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    this.database.getConn().close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            

        }
        
        if(!displayMessage.isEmpty()){
            CreateSection.displayInfoMessage("User Login Information", displayMessage);
            login.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); 
        }

 
    }
    
   
    
    public User getUser(int id){
        
        User foundUser = new User();
        
        this.database = new UserDB();
        
        try {
            
            ArrayList<User> usersfound = this.database.getAllDatas(" WHERE userID=?", id);
            
            if(!usersfound.isEmpty()){
                foundUser = usersfound.get(0);
            }
            
        } catch (Exception ex) {
            
            CreateSection.displayErrorMessage("Getting User by ID error", ex.toString());
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                this.database.getConn().close();
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        return foundUser;
        
    }
    
   
    
    
    
}
