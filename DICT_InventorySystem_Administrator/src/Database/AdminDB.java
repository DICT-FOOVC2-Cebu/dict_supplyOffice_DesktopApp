/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Account.Admin;
import Sections.StationSection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eunar B. Dayangco
 */
public class AdminDB extends DatabaseTemplates{
    
    private Admin admin;

    public AdminDB() {
        super();
    }

    public AdminDB(Admin admin) {
        super();
        this.admin = admin;
    }
    
    public void findAdmin() throws Exception{
        
       String statement = "SELECT * FROM admin WHERE adminUsername=? AND adminPassword=?";
        
       
            
            this.setPs(this.getConn().prepareStatement(statement));
            this.getPs().setString(1,admin.getAdminUsername());
            this.getPs().setString(2, admin.getAdminPassword());
            
            this.setRs(this.getPs().executeQuery());
            if(this.getRs().next()){
                
                admin.setAdminID(this.getRs().getInt("adminID"));
                admin.setAdminDate(this.getRs().getDate("adminDate"));
                admin.setAdminFullname(this.getRs().getString("adminFullname"));
                admin.setAdminAddress(this.getRs().getString("adminAddress"));
                admin.setAdminContactNumber(this.getRs().getString("adminContactNumber"));
                admin.setAdminEmail(this.getRs().getString("adminEmail"));
                admin.setAdminUsername(this.getRs().getString("adminUsername"));
                admin.setAdminPassword(this.getRs().getString("adminPassword"));
                StationSection.setLoggedAdmin(admin);
                
                this.setFound(true);
            }else{
                this.setFound(false);
            }
            
    }
  

    @Override
    public boolean isDataExist(String fieldName,Object value) {
        return false;
    }

    @Override
    public void insertData() throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateData() throws Exception {
        
            String statement = "UPDATE admin set adminFullname=?,adminAddress=?,adminContactNumber=?"
                + ",adminUsername=?,adminPassword=?,adminEmail=?";
             this.setPs(this.getConn().prepareStatement(statement));
             this.getPs().setString(1, admin.getAdminFullname());
             this.getPs().setString(2, admin.getAdminAddress());
             this.getPs().setString(3, admin.getAdminContactNumber());
             this.getPs().setString(4, admin.getAdminUsername());
             this.getPs().setString(5, admin.getAdminPassword());
             this.getPs().setString(6, admin.getAdminEmail());
            
             this.setSuccess(this.getPs().executeUpdate() == 1); 
    }

    @Override
    public void deleteData() throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
