/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Sections.CreateSection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Eunar B. Dayangco
 */
public abstract class Database<Type> {

    private String dbDriver,dbUrl,dbUsername,dbPassword,dbName,dbHost;
    private String message;
    private Connection conn;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;
    private boolean success;
    private Type object;
    private ArrayList<Type> objects;

    public Database() {

        init();
        connectDB();
    }

    public Type getObject() {
        return object;
    }

    public void setObject(Type object) {
        this.object = object;
    }

    public ArrayList<Type> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<Type> objects) {
        this.objects = objects;
    }
   
    
    

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getSt() {
        return st;
    }

    public void setSt(Statement st) {
        this.st = st;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }
    
        public void init(){
        
         this.dbDriver = "com.mysql.jdbc.Driver";
         this.dbUrl = "jdbc:mysql://localhost:3306/dict_inventory_system?zeroDateTimeBehavior=convertToNull";
         this.dbName = "dict_inventory_system";
         this.dbUsername = "dictUser";
         this.dbPassword = "zJXnJx5um7RHCk9u";
         this.dbHost = "localhost";
         this.conn = null;
         this.st = null;
         this.rs = null;
      
    }
    
    public void connectDB(){
       
        try {
            
            Class.forName(this.dbDriver);
            
            this.conn = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
            this.st =  conn.createStatement();
            
        }catch(Exception ex){
            
            CreateSection.displayErrorMessage("Error connectDB found", ex.getMessage());
           
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
  
    
  
    public abstract void insertData()throws Exception;
    public abstract void updateData()throws Exception;
    public abstract void deleteData()throws Exception;
    public abstract ArrayList<Type> getAllDatas(String condition, Object... parameters) throws Exception;
     
   
    
}
