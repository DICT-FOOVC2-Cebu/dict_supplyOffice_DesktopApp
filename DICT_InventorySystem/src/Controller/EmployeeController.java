/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Database.Database;
import Database.EmployeeDB;
import Model.Employee;
import Sections.CreateSection;
import Sections.SetSection;
import Views.Employee.AddEmployee;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author Eunar B. Dayangco
 */
public class EmployeeController {
    
    private Database<Employee> database;
    private String message;
    
   

    public EmployeeController() {
        this.database = new EmployeeDB();
    }
    public EmployeeController(Employee employee) {
         this.database = new EmployeeDB(employee);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public ArrayList<Employee> getAllEmployees(){
        
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            employees =  this.database.getAllDatas("");
        } catch (Exception ex) {
            CreateSection.displayErrorMessage("Get All Employees Error"
                    + " at line 46", ex.toString());
           // Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        return employees;
    } 
    
    public void insertProcess(AddEmployee addEmployee){
        
        if(!validateInput()){
            try {
                this.database.insertData();
                
                if(this.database.isSuccess()){
                    
                    CreateSection.displayInfoMessage("Employee Database Confirmation", 
                            "Successfully added "+ this.database.getObject().getFullname());
                    addEmployee.clearAll();
                    
                     addEmployee.getAddInventory().displayEmployees();
                    //new InventoryController().displayEmployeeList(addEmployee.getAddInventory().getTxtIssuedBy());
                    //new InventoryController().displayEmployeeList(addEmployee.getAddInventory().getTxtReceivedBy());
                    
                }else{
                    CreateSection.displayInfoMessage("Employee Database Confirmation", 
                            "Cannot Insert New Employee.");
                }
            } catch (Exception ex) {
                CreateSection.displayErrorMessage("Insert Employees Error at 64", 
                        ex.toString());
                //Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            CreateSection.displayInfoMessage("Field Validation", 
                            getMessage());
        }
    }
    
    public boolean validateInput(){
        
        
        String displaymessage = "";
        String fullname = this.database.getObject().getFullname();
        String position = this.database.getObject().getPosition();
        String email = this.database.getObject().getEmail();
        String contactNumber = this.database.getObject().getContactNumber();
        
        if(fullname.isEmpty()){displaymessage+="Fullname is empty.\n";}
        if(position.isEmpty()){displaymessage+="Position is empty.\n";}
        if(email.isEmpty()){displaymessage+="Email is empty.\n";}
        if(contactNumber.isEmpty()){displaymessage+="ContactNumber is empty.\n";}
        setMessage(displaymessage);
        
        return fullname.isEmpty() || position.isEmpty() || email.isEmpty() ||
                contactNumber.isEmpty();

    }
    
    public void displayOnEditEmployeesTable(JTable table){
        
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        Object[] row = new Object[3];
        
        for(Employee employee:getAllEmployees()){
            row[0] = employee.getEmployeeID();
            row[1] = employee.getFullname();
            row[2] = employee.getPosition();
            model.addRow(row);
        }
        
        SetSection.hideColumn(0, table);
        
    
    }
    
    public Employee getEmployee(int id){
       
        this.database = new EmployeeDB();
        Employee employee = new Employee();
        
        try {
            ArrayList<Employee> employees = this.database.getAllDatas(" WHERE employeeID=?", id);
            
            if(!employees.isEmpty()){
                employee = employees.get(0);
            }else{
                CreateSection.displayInfoMessage("Get Employee by ID Information", 
                        id+ " is not found!");
            }
            
            
            
        } catch (Exception ex) {
            CreateSection.displayErrorMessage("Get Employee By ID Error at Employee Controller", ex.toString());
            Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                this.database.getConn().close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        
        return employee;
    }
    
   

    
   
     
    
}
