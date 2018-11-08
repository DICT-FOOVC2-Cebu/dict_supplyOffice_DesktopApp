/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import Account.Admin;
import Database.AdminDB;
import Sections.CreateSection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eunar B. Dayangco
 */
public class LoginController {
    
    private AdminDB adminDB;
    private String message;
    private boolean found;

    public LoginController() {
         this.found = false;
    }
    
    public LoginController(Admin admin){
        
    
        this.adminDB = new AdminDB(admin);
        this.found = false;
    
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }
    
    

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    public void verifyUser(){
        
        try {
            
            adminDB.findAdmin();
            
            if(adminDB.isFound()){
                //setMessage("You are the admin!");
                this.setFound(true);
                
            }else{
                this.setFound(false);
                setMessage("Invalid Account");
            }
            
        } catch (Exception ex) {
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            CreateSection.displayErrorMessage("Find Admin Account error", ex.getMessage());
        }
        
        
            
    }
    
}
