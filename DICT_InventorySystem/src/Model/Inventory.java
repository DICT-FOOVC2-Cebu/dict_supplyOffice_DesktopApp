/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Image;

/**
 *
 * @author Eunar B. Dayangco
 */
public class Inventory {
    
    private int inventoryID;
    private String inventoryPAR_NO;
    private String inventorySN,inventoryName;
    private Image inventoryImage;
    private double inventoryAmount;
    private String inventoryDescription,inventoryRemarks;
    private String inventoryStatus,inventoryInsertDate,inventoryUpdate;
    private Employee issuedBy,receivedBy;
    private User insertBy,updateBy;
    private Location inventoryLocation;

    public Inventory() {
    }

    public int getInventoryID() {
        return inventoryID;
    }

    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
    }

    public String getInventoryPAR_NO() {
        return inventoryPAR_NO;
    }

    public void setInventoryPAR_NO(String inventoryPAR_NO) {
        this.inventoryPAR_NO = inventoryPAR_NO;
    }


    public String getInventorySN() {
        return inventorySN;
    }

    public void setInventorySN(String inventorySN) {
        this.inventorySN = inventorySN;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public Image getInventoryImage() {
        return inventoryImage;
    }

    public void setInventoryImage(Image inventoryImage) {
        this.inventoryImage = inventoryImage;
    }

    public double getInventoryAmount() {
        return inventoryAmount;
    }

    public void setInventoryAmount(double inventoryAmount) {
        this.inventoryAmount = inventoryAmount;
    }

    public String getInventoryDescription() {
        return inventoryDescription;
    }

    public void setInventoryDescription(String inventoryDescription) {
        this.inventoryDescription = inventoryDescription;
    }

    public Location getInventoryLocation() {
        return inventoryLocation;
    }

    public void setInventoryLocation(Location inventoryLocation) {
        this.inventoryLocation = inventoryLocation;
    }

  

    public String getInventoryRemarks() {
        return inventoryRemarks;
    }

    public void setInventoryRemarks(String inventoryRemarks) {
        this.inventoryRemarks = inventoryRemarks;
    }

    public String getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(String inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public String getInventoryInsertDate() {
        return inventoryInsertDate;
    }

    public void setInventoryInsertDate(String inventoryInsertDate) {
        this.inventoryInsertDate = inventoryInsertDate;
    }

    public String getInventoryUpdate() {
        return inventoryUpdate;
    }

    public void setInventoryUpdate(String inventoryUpdate) {
        this.inventoryUpdate = inventoryUpdate;
    }

    public Employee getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(Employee issuedBy) {
        this.issuedBy = issuedBy;
    }

    public Employee getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(Employee receivedBy) {
        this.receivedBy = receivedBy;
    }

    public User getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(User updateBy) {
        this.updateBy = updateBy;
    }

    public User getInsertBy() {
        return insertBy;
    }

    public void setInsertBy(User insertBy) {
        this.insertBy = insertBy;
    }

     
    
    
    
}
