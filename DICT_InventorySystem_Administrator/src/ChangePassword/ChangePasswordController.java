/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChangePassword;

import Account.Admin;
import Database.AdminDB;
import Sections.CreateSection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eunar B. Dayangco
 */
public class ChangePasswordController {
    
    private AdminDB adminDB;
    private boolean success;

    public ChangePasswordController() {
        this.success = false;
    }
    
     public ChangePasswordController(Admin admin) {
         
         this.adminDB = new AdminDB(admin);
         this.success = false;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
     
     
     
    public void changePassword(){
       
        try {
            adminDB.updateData();
            setSuccess(adminDB.isSuccess());
        } catch (Exception ex) {
            //Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
            
            CreateSection.displayErrorMessage("Update Password Error", ex.getMessage());
            setSuccess(false);
        }
    
    } 
    
    
    
}
