/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;



import Controller.EmployeeController;
import Controller.LocationController;
import Controller.UserController;
import Model.Inventory;
import Sections.CreateSection;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.HashSet;


/**
 *
 * @author Eunar B. Dayangco
 */
public class InventoryDB extends Database<Inventory>{
    
 
    private final int startID = 1000;
    private final int endID = Integer.MAX_VALUE;
    

    public InventoryDB() {
        super();
        
        
    }
    
    public InventoryDB(Inventory inventory) {
        super();
       this.setObject(inventory);
    }
    
    public InventoryDB(ArrayList<Inventory> inventories){
        super();
        this.setObjects(inventories);
    }
   
    public void setInventoryID()throws Exception{
        
        int genID = 0;
        
        while(true){
            
            genID =  (int) (Math.random() * (endID - startID)) + startID;
            
         if(!isIDexist(genID)){
               break;
          }
        }
        
       getObject().setInventoryID(genID);
    
    }
    
     public boolean isIDexist(int id) throws Exception{
            
        String statement = "SELECT * FROM inventory WHERE inventoryID=?";

        this.setPs(this.getConn().prepareStatement(statement));
        this.getPs().setInt(1, id);
        this.setRs(this.getPs().executeQuery());

        int countfound = 0;

        while(this.getRs().next()){
            countfound++;

        }
        
        
       return countfound == 1;
             
    }
    
    
    
    
    
    
    public Inventory formInventory(ResultSet rs) throws Exception{
        
        
            Inventory invent = new Inventory();
            invent.setInventoryID(rs.getInt("InventoryID"));
            invent.setInventoryPAR_NO(rs.getString("InventoryParNo"));
            invent.setIssuedBy(new EmployeeController().getEmployee(rs.getInt("InventoryIssuedBy")));
            invent.setReceivedBy(new EmployeeController().getEmployee(rs.getInt("InventoryReceivedBy")));
            invent.setInventorySN(rs.getString("InventorySN"));
            invent.setInventoryName(rs.getString("InventoryName"));
            invent.setInventoryImage(CreateSection.getImage(rs.getBytes("InventoryImage")));
            invent.setInventoryAmount(rs.getDouble("InventoryAmount"));
            invent.setInventoryDescription(rs.getString("InventoryDescription"));
            invent.setInventoryLocation(new LocationController().getLocation(rs.getInt("InventoryLocation")));
            invent.setInventoryRemarks(rs.getString("InventoryRemarks"));
            invent.setInventoryStatus(rs.getString("InventoryStatus"));
            invent.setInventoryInsertDate(rs.getString("InventoryInsertDate"));
            invent.setInventoryUpdate(rs.getString("InventoryUpdate"));
            invent.setUpdateBy(new UserController().getUser(rs.getInt("InventoryUpdateBy")));
            invent.setInsertBy(new UserController().getUser(rs.getInt("InventoryInsertBy")));
          
           
            return invent;
    }

    

    @Override
    public void insertData() throws Exception {
        
        String fields = "InventoryID,InventoryParNo,InventoryIssuedBy,"
                + "InventoryReceivedBy,InventorySN,InventoryName,InventoryImage,"
                + "InventoryAmount,InventoryDescription,InventoryLocation,"
                + "InventoryRemarks,InventoryStatus,InventoryInsertDate,"
                + "InventoryUpdate,InventoryUpdateBy,InventoryInsertBy";
        String values = "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"; 
        setInventoryID();
        String statement = "INSERT INTO inventory("+fields+")VALUES("+values+")";
        this.setPs(this.getConn().prepareStatement(statement));
        
        this.getPs().setInt(1,  this.getObject().getInventoryID());
        this.getPs().setString(2,this.getObject().getInventoryPAR_NO());
        this.getPs().setString(3,this.getObject().getIssuedBy().getEmployeeID()+"");
        this.getPs().setString(4,this.getObject().getReceivedBy().getEmployeeID()+"");
        this.getPs().setString(5,this.getObject().getInventorySN());
        this.getPs().setString(6, this.getObject().getInventoryName());
        this.getPs().setBlob(7, CreateSection.makeImage(this.getObject().getInventoryImage()));
        this.getPs().setDouble(8, this.getObject().getInventoryAmount());
        this.getPs().setString(9, this.getObject().getInventoryDescription());
      //  this.getPs().setString(10, this.getObject().getInventoryLocation());
        this.getPs().setString(11, this.getObject().getInventoryRemarks());
        this.getPs().setString(12, this.getObject().getInventoryStatus());
        this.getPs().setString(13, this.getObject().getInventoryInsertDate());
        this.getPs().setString(14, this.getObject().getInventoryUpdate());
        this.getPs().setString(15, this.getObject().getUpdateBy().getUserID()+"");
        this.getPs().setString(16, this.getObject().getInsertBy().getUserID()+"");
        this.setSuccess( this.getPs().executeUpdate() == 1);
        
    }
    
    
    public void multipleInsertData(ArrayList<Inventory> inventories)throws Exception{
        
         
        String fields = "InventoryID,InventoryParNo,InventoryIssuedBy,"
                + "InventoryReceivedBy,InventorySN,InventoryName,InventoryImage,"
                + "InventoryAmount,InventoryDescription,InventoryLocation,"
                + "InventoryRemarks,InventoryStatus,InventoryInsertDate,"
                + "InventoryUpdate,InventoryUpdateBy,InventoryInsertBy";
        String values = "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"; 

        String statement = "INSERT INTO inventory("+fields+")VALUES("+values+")";
        
        for(int y = 0; y<inventories.size(); y++){
            
            setObject(inventories.get(y));
            setInventoryID();
           
            inventories.get(y).setInventoryID(this.getObject().getInventoryID());
            //this.getConn().setAutoCommit(false);
            this.setPs(this.getConn().prepareStatement(statement));
            this.getPs().setInt(1,  this.getObject().getInventoryID());
            this.getPs().setString(2,this.getObject().getInventoryPAR_NO());
            this.getPs().setInt(3,this.getObject().getIssuedBy().getEmployeeID());
            this.getPs().setInt(4,this.getObject().getReceivedBy().getEmployeeID());
            this.getPs().setString(5,this.getObject().getInventorySN());
            this.getPs().setString(6, this.getObject().getInventoryName());
            this.getPs().setBlob(7, CreateSection.makeImage(this.getObject().getInventoryImage()));
            this.getPs().setDouble(8, this.getObject().getInventoryAmount());
            this.getPs().setString(9, this.getObject().getInventoryDescription());
            this.getPs().setInt(10, this.getObject().getInventoryLocation().getLocationID());
            this.getPs().setString(11, this.getObject().getInventoryRemarks());
            this.getPs().setString(12, this.getObject().getInventoryStatus());
            this.getPs().setString(13, this.getObject().getInventoryInsertDate());
            this.getPs().setString(14, this.getObject().getInventoryUpdate());
            this.getPs().setInt(15, this.getObject().getUpdateBy().getUserID());
            this.getPs().setInt(16, this.getObject().getInsertBy().getUserID());
            this.setSuccess(this.getPs().executeUpdate()>0);
            
            //this.getConn().commit();
 
        }
        
    
    }
    

    @Override
    public void updateData() throws Exception {
        
        String cond = "InventoryParNo=?,InventoryIssuedBy=?,InventoryReceivedBy=?,"
                + "InventorySN=?,InventoryName=?,InventoryImage=?,InventoryAmount=?,"
                + "InventoryDescription=?,InventoryLocation=?,InventoryRemarks=?,"
                + "InventoryStatus=?,InventoryInsertDate=?,InventoryUpdate=?,InventoryUpdateBy=?,"
                + "InventoryInsertBy=?";
        
        String statement = "UPDATE Inventory SET "+cond+" WHERE InventoryID=?";
        this.setPs(this.getConn().prepareStatement(statement));
        
        this.setSuccess(this.getPs().executeUpdate() == 1);
        
        
    }
    
   

    @Override
    public void deleteData() throws Exception {
        
    }
    
 
    public ArrayList<Object> listOfColumnvalue(String columName)throws Exception{
    
        ArrayList<Object> objects = new ArrayList<>();
        
        String statement = "SELECT  DISTINCT "+columName + " FROM inventory";
        this.setPs(this.getConn().prepareStatement(statement));
        this.setRs(this.getPs().executeQuery());
        while(this.getRs().next()){
            
            Object columnValues = this.getRs().getObject(columName);
            objects.add(columnValues);
        }
        
        
        return objects;
    }
    
    

    
     @Override
    public ArrayList<Inventory> getAllDatas(String condition, Object... parameters) throws Exception {
        
        ArrayList<Inventory> inventories = new ArrayList<>();
        String statement = "SELECT * FROM inventory "+ condition;
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
             inventories.add(formInventory(this.getRs()));
        
        }
        
        this.getPs().close();
        this.getRs().close();

        return inventories;
    }
    
    
    
    
}
