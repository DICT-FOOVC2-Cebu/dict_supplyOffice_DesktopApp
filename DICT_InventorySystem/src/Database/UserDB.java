/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Model.User;
import java.util.ArrayList;


/**
 *
 * @author Eunar B. Dayangco
 */
public class UserDB extends Database<User>{
   

    public UserDB() {
        super();
    }
    
    public UserDB(User user) {
        super();
        this.setObject(user);
    }
    


    @Override
    public void insertData() throws Exception {
       
    }

    @Override
    public void updateData() throws Exception {
        
    }

    @Override
    public void deleteData() throws Exception {
        
    }

    @Override
    public ArrayList<User> getAllDatas(String condition, Object... parameters) throws Exception {
        
        ArrayList<User> users = new ArrayList<>();
        String statement = "SELECT * FROM user "+condition;
        this.setPs(this.getConn().prepareStatement(statement));
        int x = 1;
        for(Object parameter:parameters){
            
            if(parameter instanceof String){
                this.getPs().setString(x, parameter.toString()); 
            }else if(parameter instanceof Integer){
                this.getPs().setInt(x, Integer.parseInt(parameter.toString()));
            }else if(parameter instanceof Double){
                this.getPs().setDouble(x, Double.parseDouble(parameter.toString()));
            }else if(parameter instanceof Boolean){
                this.getPs().setBoolean(x, Boolean.parseBoolean(parameter.toString()));
            }
            x++;
        }
        
        
        this.setRs(this.getPs().executeQuery());
        while(this.getRs().next()){
                           User foundUser = new User();
            foundUser.setUserID(this.getRs().getInt("userID"));
            foundUser.setCreateDate(this.getRs().getString("userCreateDate"));
            foundUser.setFullname(this.getRs().getString("userFullname"));
            foundUser.setContactNumber(this.getRs().getString("userContactNumber"));
            foundUser.setEmail(this.getRs().getString("userEmail"));
            foundUser.setPosition(this.getRs().getString("userPosition"));
            foundUser.setUsername(this.getRs().getString("userUsername"));
            foundUser.setPassword(this.getRs().getString("userPassword"));
            foundUser.setActive(this.getRs().getBoolean("userActive"));
            foundUser.setLogged(this.getRs().getBoolean("userLogged"));
            users.add(foundUser);
        
        }
        
        this.getPs().close();
        this.getRs().close();
       
        
        return users;
    }
    
    
}
