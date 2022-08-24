package com.roms.componentprocessing.entity;
//
//import lombok.Data;

import javax.persistence.*;
import java.util.Date;

//@Data
@Entity
@Table(name="ReturnRequests")
public class ReturnRequest {
    @Id
    private String requestId;
    private String userName;
    private long contactNumber;
    private String componentType;
    private String componentName;
    private int quantity;

	private boolean isPriorityRequest;
    private double processingCharge;
    private double packageAndDeliveryCharge;
    private Date dateOfDelivery;
    
    public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public boolean isPriorityRequest() {
		return isPriorityRequest;
	}
	public void setPriorityRequest(boolean isPriorityRequest) {
		this.isPriorityRequest = isPriorityRequest;
	}
	public String getComponentType() {
		return componentType;
	}
	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPackageAndDeliveryCharge() {
		return packageAndDeliveryCharge;
	}
	public void setPackageAndDeliveryCharge(double packageAndDeliveryCharge) {
		this.packageAndDeliveryCharge = packageAndDeliveryCharge;
	}
	public Date getDateOfDelivery() {
		return dateOfDelivery;
	}
	public void setDateOfDelivery(Date dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}
	public double getProcessingCharge() {
		return processingCharge;
	}



	public void setProcessingCharge(double processingCharge) {
		// TODO Auto-generated method stub
		this.processingCharge=processingCharge;
	}

}
