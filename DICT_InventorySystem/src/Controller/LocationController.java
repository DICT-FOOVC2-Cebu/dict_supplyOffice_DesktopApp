/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.Database;
import Model.Location;
import Database.LocationDB;
import Sections.CreateSection;
import Views.Location.AddLocation;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Eunar B. Dayangco
 */
public class LocationController {
    
   private Database<Location> database;
   private String message;

    public LocationController() {
        this.database = new LocationDB();
    }
    
    public LocationController(Location location) {
         
         this.database = new LocationDB(location);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    

    public ArrayList<Location> getAllLocations(){
        
        ArrayList<Location> locations = new ArrayList<>();
        try {
            locations =  this.database.getAllDatas("");
        } catch (Exception ex) {
            CreateSection.displayErrorMessage("Get All Employees Error"
                    + " at line 46", ex.toString());
           // Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        return locations;
    }    
    public boolean validateInput(){
        
        String displayMessage = "";
        String locationName = this.database.getObject().getAddress();
        String locationContactNumber = this.database.getObject().getContactNumber();
        String locationContactPerson = this.database.getObject().getContactPerson();
        String locationDescription = this.database.getObject().getDescription();
        boolean isEmpty = locationName.isEmpty() || locationContactNumber.isEmpty()
                || locationContactPerson.isEmpty() || locationDescription.isEmpty();
        
        if(locationName.isEmpty()){displayMessage+="Name is empty.\n";}
        if(locationContactNumber.isEmpty()){displayMessage+="Contact Number is empty.\n";}
        if(locationContactPerson.isEmpty()){displayMessage+="Contact Person is empty.\n";}
        if(locationDescription.isEmpty()){displayMessage+="Description is empty.\n";}
        
        setMessage(displayMessage);
        
        return isEmpty;
                
    }
    public void insertProcess(AddLocation addLocation){
        
        if(!validateInput()){
            
            try {
                this.database.insertData();
                
                if(this.database.isSuccess()){
                    CreateSection.displayInfoMessage("Location Database Confirmation", 
                            "Successfully Insert New Location");
                    addLocation.clearAll();
                    addLocation.getAddInventory().displayLocations();
                }else{
                     CreateSection.displayInfoMessage("Location Database Confirmation", 
                            "Failed to Insert New Location");
                }
                
            } catch (Exception ex) {
                CreateSection.displayErrorMessage("Insert Location Error in line 86", ex.toString());
                //Logger.getLogger(LocationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
            CreateSection.displayInfoMessage("Field Validation", getMessage());
            
        }
    }
     
    public Location getLocation(int locationID){
        
        Location location = new Location();
        this.database = new LocationDB();
        
       
       try {
            ArrayList<Location> locations = this.database.getAllDatas(" WHERE locationID=?", locationID);
            
            if(!locations.isEmpty()){
                location = locations.get(0);
            }else{
                CreateSection.displayInfoMessage("Location Database Information", 
                        locationID+ " is not in the database");
            }
            
       } catch (Exception ex) {
           Logger.getLogger(LocationController.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    
       return location;
    }
   
    
    
}
