package com.roms.componentprocessing.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class PaymentInformation {
	
	/*{
    "requestId": "e09d7c10265c4a2cbd53f4ef0f6138f1",
    "creditCardNumber":7890,
    "creditLimit":909,
    "processingCharge": 700.0
}*/

	@Id
	private String requestId;
	private String creditCardNumber;
	private float creditLimit;
	private float processingCharge;
	

	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public float getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(float creditLimit) {
		this.creditLimit = creditLimit;
	}
	public float getProcessingCharge() {
		return processingCharge;
	}
	public void setProcessingCharge(float processingCharge) {
		this.processingCharge = processingCharge;
	}
	
}
