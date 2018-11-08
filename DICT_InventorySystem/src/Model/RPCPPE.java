/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Eunar B. Dayangco
 */
public class RPCPPE {
    
    private Inventory inventoy;
    private String fundCluster;
    private String correctBy;
    private String approvedBy;
    private String verifiedBy;
    private String assumptionDate;
    private String article;
    private String measureUnit;
    private String valueUnit;
    private double propertyCardQuantity;
    private double physicalCountQuantity;
    private double shortage_overageQuantity;
    private double shortage_overageValue;

    public RPCPPE() {
    }

    public Inventory getInventoy() {
        return inventoy;
    }

    public void setInventoy(Inventory inventoy) {
        this.inventoy = inventoy;
    }

    public String getFundCluster() {
        return fundCluster;
    }

    public void setFundCluster(String fundCluster) {
        this.fundCluster = fundCluster;
    }

    public String getCorrectBy() {
        return correctBy;
    }

    public void setCorrectBy(String correctBy) {
        this.correctBy = correctBy;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public String getAssumptionDate() {
        return assumptionDate;
    }

    public void setAssumptionDate(String assumptionDate) {
        this.assumptionDate = assumptionDate;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public String getValueUnit() {
        return valueUnit;
    }

    public void setValueUnit(String valueUnit) {
        this.valueUnit = valueUnit;
    }

    public double getPropertyCardQuantity() {
        return propertyCardQuantity;
    }

    public void setPropertyCardQuantity(double propertyCardQuantity) {
        this.propertyCardQuantity = propertyCardQuantity;
    }

    public double getPhysicalCountQuantity() {
        return physicalCountQuantity;
    }

    public void setPhysicalCountQuantity(double physicalCountQuantity) {
        this.physicalCountQuantity = physicalCountQuantity;
    }

    public double getShortage_overageQuantity() {
        return shortage_overageQuantity;
    }

    public void setShortage_overageQuantity(double shortage_overageQuantity) {
        this.shortage_overageQuantity = shortage_overageQuantity;
    }

    public double getShortage_overageValue() {
        return shortage_overageValue;
    }

    public void setShortage_overageValue(double shortage_overageValue) {
        this.shortage_overageValue = shortage_overageValue;
    }
    
    
    
}
