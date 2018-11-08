/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Controller.UserController;
import Database.Database;
import Model.Location;


import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Eunar B. Dayangco
 */
public class LocationDB extends Database<Location>{
    
  
    private final int startID = 1000000;
    private final int endID = 20000000;
    private final String TABLEFIELDS = "locationID,addedDate,locationAddress,locationContactNumber,"
                  + "locationContactPerson,locationDescription,locationInsertBy";

    public LocationDB() {
        super();
       
    }
    public LocationDB(Location location) {
        super();
       this.setObject(location);
    }
    
    public void setID(Location location)throws Exception{
        
        int genID = 0;
        
        while(true){
            
            genID =  (int) (Math.random() * (endID - startID)) + startID;
            
            if(!isIDExist(genID)){
                break;
            }
        }
        
       location.setLocationID(genID);
    
    }
    public boolean isIDExist(int id) throws Exception{
        
        String statement = "SELECT * FROM location WHERE locationID=?";
        this.setPs(this.getConn().prepareCall(statement));
        this.getPs().setInt(1, id);
        this.setRs(this.getPs().executeQuery());
        int found = 0;
        while(this.getRs().next()){
            found++;
        }
        
        return found == 1;
    
    }
    
    
    @Override
    public void insertData() throws Exception {
       
        String values = "?,?,?,?,?,?,?"; 
        String statement = "INSERT INTO location("+TABLEFIELDS+")VALUES("+values+")";
        setID(this.getObject());
        
        this.setPs(this.getConn().prepareStatement(statement));
        this.getPs().setInt(1,  this.getObject().getLocationID());
        this.getPs().setString(2, this.getObject().getAddedDate());
        this.getPs().setString(3, this.getObject().getAddress());
        this.getPs().setString(4, this.getObject().getContactNumber());
        this.getPs().setString(5, this.getObject().getContactPerson());
        this.getPs().setString(6, this.getObject().getDescription());
        this.getPs().setInt(7, this.getObject().getInsertBy().getUserID());
        this.setSuccess( this.getPs().executeUpdate() == 1);
    }

    @Override
    public void updateData() throws Exception {
        String statement = "UPDATE location SET"
                + "locationAddress=?,locationContactNumber=?,"
                + "locationContactPerson=?, locationDescription=?"
                +" WHERE locationID=?";
        
        this.setPs(this.getConn().prepareStatement(statement));
        this.getPs().setString(1, this.getObject().getAddress());
        this.getPs().setString(2, this.getObject().getContactNumber());
        this.getPs().setString(3, this.getObject().getContactPerson());
        this.getPs().setString(4, this.getObject().getDescription());
        this.getPs().setInt(5,  this.getObject().getLocationID());
        this.setSuccess( this.getPs().executeUpdate() == 1);
    }

    @Override
    public void deleteData() throws Exception {
        
        String statement = "DELETE FROM location WHERE locationID=?";
        this.setPs(this.getConn().prepareStatement(statement));
        this.getPs().setInt(1,  this.getObject().getLocationID());
        this.setSuccess( this.getPs().executeUpdate() == 1);
    }

    @Override
    public ArrayList<Location> getAllDatas(String condition, Object... parameters) throws Exception {
     
        
        ArrayList<Location> locations = new ArrayList<>();
        String statement = "SELECT * FROM location "+condition;
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
             
            Location location = new Location();

            location.setLocationID(this.getRs().getInt("locationID"));
            location.setAddedDate(this.getRs().getString("addedDate"));
            location.setAddress(this.getRs().getString("locationAddress"));
            location.setContactNumber(this.getRs().getString("locationContactNumber"));
            location.setContactPerson(this.getRs().getString("locationContactPerson"));
            location.setDescription(this.getRs().getString("locationDescription"));
            location.setInsertBy(new UserController().getUser(this.getRs().getInt("locationInsertBy")));

            locations.add(location);

         }  
        
        return locations;
    }
    
    


    
}
