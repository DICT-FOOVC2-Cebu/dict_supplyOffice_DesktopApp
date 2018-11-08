/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Account;

import Database.AdminDB;
import Sections.CreateSection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eunar B. Dayangco
 */
public class AccountController {
    
    private AdminDB adminDB;
    private boolean success;

    public AccountController(Admin admin) {
        
        adminDB = new AdminDB(admin);
        this.success = false;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    
    
    public void editAccount(){
       
        try {
            adminDB.updateData();
            setSuccess(adminDB.isSuccess());
            
            
        } catch (Exception ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
            CreateSection.displayErrorMessage("Edit Admin Account Error", ex.getMessage());
        }
    
    }
    
    
    
}
