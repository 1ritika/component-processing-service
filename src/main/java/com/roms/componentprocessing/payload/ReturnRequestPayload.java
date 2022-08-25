package com.roms.componentprocessing.payload;

//import lombok.Data;


public class ReturnRequestPayload {
	private String requestId;
	private String userName;
    private long contactNumber;
    private boolean isPriorityRequest;
    private String componentName;
    private String componentType;

    private int quantity;
    
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
	public void setPriorityRequest(boolean isPriorityRequest) {
		this.isPriorityRequest = isPriorityRequest;
	}
	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	public String getComponentType() {
		// TODO Auto-generated method stub
		return this.componentType;
	}
	public boolean isPriorityRequest() {
		// TODO Auto-generated method stub
		return this.isPriorityRequest;
	}
	
	public boolean getIsPriorityRequest() {
		// TODO Auto-generated method stub
		return this.isPriorityRequest;
	
}
	public ReturnRequestPayload(String userName, long contactNumber,  boolean isPriorityRequest, String componentName, 
			String componentType, int quantity) {
		super();
		this.userName = userName;
		this.contactNumber = contactNumber;
		this.isPriorityRequest = isPriorityRequest;
		this.componentType = componentType;
		this.componentName = componentName;
		this.quantity = quantity;
	}
}
