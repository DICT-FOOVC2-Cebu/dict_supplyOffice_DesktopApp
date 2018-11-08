/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Database.UserDB;
import Sections.CreateSection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eunar B. Dayangco
 */
public class UserController {
    
    private UserDB userDB;
    private boolean success;

    public UserController() {
        this.userDB = new UserDB();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public UserController(User user) {
        
        this.userDB = new UserDB(user);
        this.success = false;
        
    }
    
    public void insertNewUser(){
    
        try {
            userDB.insertData();
            setSuccess(userDB.isSuccess());
        } catch (Exception ex) {
            CreateSection.displayErrorMessage("Insert User Error", ex.getMessage());
            //Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteUser(){
        try {
            userDB.deleteData();
            setSuccess(userDB.isSuccess());
            
        } catch (Exception ex) {
             CreateSection.displayErrorMessage("Delete User Error", ex.getMessage());
           // Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void updateUser(){
        
        try {
            userDB.updateData();
            setSuccess(userDB.isSuccess());
            
        } catch (Exception ex) {
             CreateSection.displayErrorMessage("Update User Error", ex.getMessage());
            //Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public ArrayList<User> getAllUsers(){
          
        try {
            return userDB.getUsers();
        } catch (Exception ex) {
            //Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            CreateSection.displayErrorMessage("Getting All User Error", 
                    ex.getMessage());
            return null;
        }
    }
    
    public User getUser(int id){
        
        try {
           return userDB.getUser(id);
        } catch (Exception ex) {
            CreateSection.displayErrorMessage("Get User Error", ex.getMessage());
           // Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    
    }
    
    public void changeActiveStatus(boolean active){
           
        try {
            this.userDB.changeUserActiveStatus(active);
             setSuccess(userDB.isSuccess());
        } catch (Exception ex) {
            CreateSection.displayErrorMessage("User Edit Active Status Error", 
                    ex.toString());
            //Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
