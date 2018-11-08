/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import User.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eunar B. Dayangco
 */
public class UserDB extends DatabaseTemplates{

    private User user;
    

    public UserDB() {
        super();
    }

    public UserDB(User user) {
        super();
        this.user = user;
    }
    
    
    

    @Override
    public boolean isDataExist(String fieldName,Object value) {
       
        try {
            String statement = "SELECT * FROM user WHERE "+fieldName+"=?";
            
            this.setPs(this.getConn().prepareStatement(statement));
            
            if(value instanceof Integer){
                this.getPs().setInt(1,Integer.parseInt(value.toString()));
            
            }
           
             this.setRs(this.getPs().executeQuery());
             int countfound = 0;
             while( this.getRs().next()){
                 countfound++;
             }
            return countfound==1;

        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            
            return false;
        }
    }

    @Override
    public void insertData() throws Exception {
        
        String fields = "userID,userCreateDate,userFullname,"
                + "userContactNumber,userEmail,userPosition,userUsername,"
                + "userPassword,userActive,userLogged";
        String values = "?,?,?,?,?,?,?,?,?,?";
        String statement = "INSERT INTO user("+fields+")VALUES("+values+")";
        
        this.setPs(this.getConn().prepareStatement(statement));
        this.getPs().setInt(1,  user.getUserID());
        this.getPs().setString(2, user.getUserCreateDate());
        this.getPs().setString(3, user.getUserFullname()); 
        this.getPs().setString(4, user.getUserContactNumber());   
        this.getPs().setString(5, user.getUserEmail());   
        this.getPs().setString(6, user.getUserPosition());   
        this.getPs().setString(7, user.getUserUsername());   
        this.getPs().setString(8, user.getUserPassword());
        this.getPs().setBoolean(9, user.isActive());
        this.getPs().setBoolean(10, user.isLogged());
        this.setSuccess( this.getPs().executeUpdate() == 1);

    }

    @Override
    public void updateData() throws Exception {
        
         String statement = "UPDATE user set userFullname=?,userContactNumber=?,userEmail=?"
                + ",userPosition=?,userUsername=?,userPassword=?,userActive=?,userLogged=? "
                 + "WHERE userID=?";
             
         this.setPs(this.getConn().prepareStatement(statement));
             this.getPs().setString(1, user.getUserFullname());
             this.getPs().setString(2, user.getUserContactNumber());
             this.getPs().setString(3, user.getUserEmail());
             this.getPs().setString(4, user.getUserPosition());
             this.getPs().setString(5, user.getUserUsername());
             this.getPs().setString(6, user.getUserPassword());
             this.getPs().setBoolean(7, user.isActive());
             this.getPs().setBoolean(8, user.isLogged());
             this.getPs().setInt(9, user.getUserID());
             this.setSuccess(this.getPs().executeUpdate() == 1); 
        
    }

    @Override
    public void deleteData() throws Exception {
        
        String statement = "DELETE FROM user WHERE userID=?";
        this.setPs(this.getConn().prepareStatement(statement));
        this.getPs().setInt(1,  user.getUserID());
        this.setSuccess( this.getPs().executeUpdate() == 1);
        
    }
    
    public ArrayList<User>getUsers()throws Exception{
        
        ArrayList<User> users = new ArrayList<>();
        String statement = "SELECT * FROM user";
        
        
            this.setPs(this.getConn().prepareStatement(statement));
            this.setRs(this.getPs().executeQuery());
          
           while(this.getRs().next()){
                
                User user= new User();
                
                user.setUserID(this.getRs().getInt("userID"));
                user.setUserCreateDate(this.getRs().getString("userCreateDate"));
                user.setUserFullname(this.getRs().getString("userFullname"));
                user.setUserContactNumber(this.getRs().getString("userContactNumber"));
                user.setUserEmail(this.getRs().getString("userEmail"));
                user.setUserPosition(this.getRs().getString("userPosition"));
                user.setUserUsername(this.getRs().getString("userUsername"));
                user.setUserPassword(this.getRs().getString("userPassword"));
                user.setActive(this.getRs().getBoolean("userActive"));
                user.setLogged(this.getRs().getBoolean("userLogged"));
                users.add(user);
            
            }
           
        return users;
        
    }
    
    public User getUser(int id)throws Exception{
        
        User lookUser = new User();
         String statement = "SELECT * FROM user where userID=?";
      
            this.setPs(this.getConn().prepareStatement(statement));
            this.getPs().setInt(1, id);
            this.setRs(this.getPs().executeQuery());
             while(this.getRs().next()){
                
                lookUser.setUserID(this.getRs().getInt("userID"));
                lookUser.setUserCreateDate(this.getRs().getString("userCreateDate"));
                lookUser.setUserFullname(this.getRs().getString("userFullname"));
                lookUser.setUserContactNumber(this.getRs().getString("userContactNumber"));
                lookUser.setUserEmail(this.getRs().getString("userEmail"));
                lookUser.setUserPosition(this.getRs().getString("userPosition"));
                lookUser.setUserUsername(this.getRs().getString("userUsername"));
                lookUser.setUserPassword(this.getRs().getString("userPassword"));
                lookUser.setActive(this.getRs().getBoolean("userActive"));
                lookUser.setLogged(this.getRs().getBoolean("userLogged"));
              
             }
        
        return lookUser;
    
    }
    
    public void changeUserActiveStatus(boolean active)throws Exception{
      String statement = "UPDATE user set userActive=? WHERE userID=?";
       
       this.setPs(this.getConn().prepareStatement(statement));
       this.getPs().setBoolean(1, active);
       this.getPs().setInt(2, user.getUserID());
       this.setSuccess(this.getPs().executeUpdate() == 1); 
      
      
    }
    
    
}
