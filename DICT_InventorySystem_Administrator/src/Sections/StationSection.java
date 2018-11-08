/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sections;

import Account.Admin;

/**
 *
 * @author Eunar B. Dayangco
 */
public class StationSection {
    
    private static Admin loggedAdmin;

    public StationSection() {
    }

    public static Admin getLoggedAdmin() {
        return loggedAdmin;
    }

    public static void setLoggedAdmin(Admin loggedAdmin) {
        StationSection.loggedAdmin = loggedAdmin;
    }
    
    
    
}
