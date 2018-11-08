/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Controller.UserController;
import Model.Employee;
import java.util.ArrayList;

/**
 *
 * @author Eunar B. Dayangco
 */
public class EmployeeDB extends Database<Employee>{
    
    
    private final int startID = 1000;
    private final int endID = Integer.MAX_VALUE;
    private final String TABLEFIELDS = "employeeID,addedDate,employeeName,employeePosition,employeeContactNumber,"
            + "employeeEmail,employeeActive,employeeInsertBy";

    public EmployeeDB() {
    }

    public EmployeeDB(Employee employee) {
        
        this.setObject(employee);
    }
    public void setID(Employee employee) throws Exception{
        
        int genID = 0;
        
        while(true){
            
            genID =  (int) (Math.random() * (endID - startID)) + startID;
            
            if(!isIDExist(genID)){
                break;
            }
        }
        
        employee.setEmployeeID(genID);
    
    }
    
    public boolean isIDExist(int id) throws Exception{
        
        String statement = "SELECT * FROM employee WHERE employeeiD=?";
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
        
        String statement = "INSERT INTO employee("+TABLEFIELDS+")VALUES("
                + "?,?,?,?,?,?,?,?)";
        
        setID(this.getObject());
        
        this.setPs(this.getConn().prepareCall(statement));
        this.getPs().setInt(1, this.getObject().getEmployeeID());
        this.getPs().setString(2, this.getObject().getAddedDate());
        this.getPs().setString(3, this.getObject().getFullname());
        this.getPs().setString(4, this.getObject().getPosition());
        this.getPs().setString(5, this.getObject().getContactNumber());
        this.getPs().setString(6, this.getObject().getEmail());
        this.getPs().setBoolean(7, this.getObject().isActive());
        this.getPs().setInt(8, this.getObject().getInsertBy().getUserID());
        this.setSuccess( this.getPs().executeUpdate() == 1);
       
    }

    @Override
    public void updateData() throws Exception {
       
        String statement = "UPDATE employee SET"
                + "employeeName=?,employeePosition=?,employeeContactNumber=?, employeeEmail=?,"
                + "employeeActive=?,employeeinsertBy=? WHERE employeeID=?";
        this.setPs(this.getConn().prepareStatement(statement));
       
        
        this.getPs().setString(1, this.getObject().getFullname());
        this.getPs().setString(2, this.getObject().getPosition());
        this.getPs().setString(3, this.getObject().getContactNumber());
        this.getPs().setString(4, this.getObject().getEmail());
        this.getPs().setBoolean(5, this.getObject().isActive());
        this.getPs().setInt(6, this.getObject().getInsertBy().getUserID());
        this.getPs().setInt(7, this.getObject().getEmployeeID());
        this.setSuccess( this.getPs().executeUpdate() == 1);
        
       
    }

    @Override
    public void deleteData() throws Exception {
        
        String statement = "DELETE FROM employee WHERE employeeID=?";
        this.setPs(this.getConn().prepareStatement(statement));
        this.getPs().setInt(1,  this.getObject().getEmployeeID());
        this.setSuccess( this.getPs().executeUpdate() == 1);
    }

    @Override
    public ArrayList<Employee> getAllDatas(String condition, Object... parameters) throws Exception {
        
        ArrayList<Employee> employees = new ArrayList<>();
        String statement = "SELECT * FROM employee "+condition;
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
            Employee em = new Employee();
             
            em.setEmployeeID(this.getRs().getInt("employeeiD"));
            em.setAddedDate(this.getRs().getString("addedDate"));
            em.setFullname(this.getRs().getString("employeeName"));
            em.setPosition(this.getRs().getString("employeePosition"));
            em.setContactNumber(this.getRs().getString("employeeContactNumber"));
            em.setEmail(this.getRs().getString("employeeEmail"));
            em.setActive(this.getRs().getBoolean("employeeActive"));
            em.setInsertBy(new UserController().getUser(this.getRs().getInt("employeeinsertBy")));
            employees.add(em);
        
        }
        
        this.getPs().close();
        this.getRs().close();
       
        
        return employees;
    }
    
  
    
   
    
}
