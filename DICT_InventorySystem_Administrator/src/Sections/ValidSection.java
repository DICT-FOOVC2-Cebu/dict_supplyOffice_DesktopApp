/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sections;

import javafx.beans.binding.Bindings;

/**
 *
 * @author Eunar B. Dayangco
 */
public class ValidSection {
    
    public String message;
    public char[] alphabets = {'a','b','c','d','e','f','g','h'};

    public ValidSection() {
      message = "";
    }
    
    
    
    public boolean isACellNumber(String value){
        message = "Please input a valid cellphone number";
        
        if(value.length()>11 || value.length()<11){
          return false;
            
        }else if(value.toCharArray()[0] != 0 || value.toCharArray()[1] == 9){
            return false;
        }
        
        else{
           
            try{
                Integer.parseInt(value);
                message = "";
                
                return true;
            }catch(Exception ex){
                return false;
            }
        }
   
    
    }
    
    
}
