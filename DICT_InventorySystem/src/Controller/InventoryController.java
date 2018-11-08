/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Database.Database;
import Database.InventoryDB;
import Model.Employee;
import Model.Inventory;
import Model.Location;
import Model.User;
import Views.Inventory.AddInventory;
import Sections.CreateSection;
import Views.Inventory.InventoryPage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author Eunar B. Dayangco
 */
public class InventoryController {
    
    Database<Inventory> database;
    ArrayList<Object> listValues;
   

    public InventoryController() {
      
    }
    
 
    
    public void insertMultipleProcess(AddInventory inventory) {
        
        InventoryDB databases = new InventoryDB(inventory.getInventories());
        try {
            databases.multipleInsertData(inventory.getInventories());
            if(databases.isSuccess()){
                    CreateSection.displayInfoMessage("Database Confirmation", 
                            "SuccessFully added new Inventories!");
                    
           }else{
                CreateSection.displayErrorMessage("Insert Inventory Error", "Failed to Insert Inventory/ies!");
            }
        } catch (Exception ex) {
            CreateSection.displayErrorMessage("Insert Multiple Inventories Error at InventoryController", 
                    ex.toString());
            Logger.getLogger(InventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                databases.getConn().close();
            } catch (SQLException ex) {
                Logger.getLogger(InventoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
          
    }
    
    public void displayList(JList list,String columnName){
      
        InventoryDB db = new InventoryDB();
        
        try {
           
           listValues = db.listOfColumnvalue(columnName);
               DefaultListModel listModel = new DefaultListModel();
                 listModel.removeAllElements();
            if(!listValues.isEmpty()){
               
                 for(Object obj:listValues){
                     
                     if(columnName.equals("InventoryLocation")){
                         
                        Location location = new LocationController().getLocation(Integer.parseInt(obj.toString()));
                         
                         listModel.addElement(location.getAddress());
                     }else if(columnName.equals("InventoryIssuedBy") || 
                             columnName.equals("InventoryReceivedBy") ){
                         
                        Employee employee = new EmployeeController().getEmployee(Integer.parseInt(obj.toString()));
                         
                         listModel.addElement(employee.getFullname());
                     }else if(columnName.equals("InventoryInsertBy") || 
                             columnName.equals("InventoryUpdateBy") ){
                         
                        User user = new UserController().getUser(Integer.parseInt(obj.toString()));
                         
                         listModel.addElement(user.getFullname());
                     }
                     
                     else{
                         listModel.addElement(obj);
                     }
                     
                 }
                 
            }
            list.setModel(listModel);
            
        } catch (Exception ex) {
            CreateSection.displayErrorMessage("Select column in inventory error", ex.toString());
            Logger.getLogger(InventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                db.getConn().close();
            } catch (SQLException ex) {
                Logger.getLogger(InventoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
    }
    
    public void displayChooseResult(InventoryPage page){
        
        String field = page.getSelectedColumn();
        int value = page.getListSelectedIndex();
                
        this.database = new InventoryDB();
        
        try {
            
            if(value !=-1){
                ArrayList<Inventory> found = this.database.getAllDatas(" WHERE "+field+"=?", listValues.get(value));

                if(found.isEmpty()){

                     CreateSection.displayInfoMessage("Empty", "found is Empty!");
                    page.displayOnTable(new ArrayList<>());
                }else{
                   page.displayOnTable(found);
                }
            }
            
        } catch (Exception ex) {
            CreateSection.displayInfoMessage("Find Inventory by List Selected Error", ex.toString());
            //Logger.getLogger(InventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                this.database.getConn().close();
            } catch (SQLException ex) {
                Logger.getLogger(InventoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
    public Inventory getInventory(int id){
        this.database = new InventoryDB();
        Inventory inventory = new Inventory();
        
        try {
            ArrayList<Inventory> inventories = this.database.getAllDatas(" WHERE InventoryID=?", id);
            
            if(inventories.isEmpty()){
                CreateSection.displayInfoMessage("Get Inventory Info by ID", id+" is not found in Database");
            }else{
                inventory = inventories.get(0);
            }
            
        } catch (Exception ex) {
            CreateSection.displayErrorMessage("Get Inventory By ID Error", ex.toString());
            //Logger.getLogger(InventoryController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            
            try {
                this.database.getConn().close();
            } catch (SQLException ex) {
                Logger.getLogger(InventoryController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
       return inventory; 
    }
    
  
    
}
