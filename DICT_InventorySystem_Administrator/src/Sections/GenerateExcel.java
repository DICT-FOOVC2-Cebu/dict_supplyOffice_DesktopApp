/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sections;

import User.User;
import User.UserController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Eunar B. Dayangco
 */
public class GenerateExcel {
    
    private ArrayList<User> users;
    private final String[] userTitles=new String[]{"userID","userCreateDate","userFullname"
    ,"userContactNumber","userEmail","userPosition","userUsername","userPassword"
    ,"userActive","userLogged"};
    private UserController controller;
    
    public GenerateExcel() {
        
        this.controller = new UserController();
        this.users = controller.getAllUsers();
      
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
    
    
    public void generateWorkBook(){
        
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("user");
        HSSFRow row = sheet.createRow(0);
        //title
        for(int x = 0; x<userTitles.length; x++){
            HSSFCell cell = row.createCell(x);
            cell.setCellValue(userTitles[x]);
            
        }
        
        
        for(int y = 0; y<users.size(); y++){
            
             User user = users.get(y);
            
             HSSFRow eachrow = sheet.createRow(y+1);
             eachrow.createCell(0).setCellValue(user.getUserID());
             eachrow.createCell(1).setCellValue(user.getUserCreateDate());
             eachrow.createCell(2).setCellValue(user.getUserFullname());
             eachrow.createCell(3).setCellValue(user.getUserContactNumber());
             eachrow.createCell(4).setCellValue(user.getUserEmail());
             eachrow.createCell(5).setCellValue(user.getUserPosition());
             eachrow.createCell(6).setCellValue(user.getUserUsername());
             eachrow.createCell(7).setCellValue(user.getUserPassword());
             eachrow.createCell(8).setCellValue(user.isActive());
             eachrow.createCell(9).setCellValue(user.isLogged());
            
        }
        
     
        
        try {
            
            workbook.write(new FileOutputStream(getALocation()));
            workbook.close();
            
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(GenerateExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
           // Logger.getLogger(GenerateExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public String getALocation(){
        String path = "";
         JFileChooser chooser = new JFileChooser("Generate.xls");
         int result = chooser.showSaveDialog(null);
         
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            path = file.getAbsolutePath();
        }
        
        
        return path;
    
    }
    
}
