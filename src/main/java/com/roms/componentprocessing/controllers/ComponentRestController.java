package com.roms.componentprocessing.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.roms.componentprocessing.clients.AuthFeignClient;
import com.roms.componentprocessing.entity.PaymentInformation;
import com.roms.componentprocessing.entity.ReturnRequest;

import com.roms.componentprocessing.payload.ReturnRequestPayload;
import com.roms.componentprocessing.payload.ReturnResponsePayload;
import com.roms.componentprocessing.services.ReturnProcessService;

//import lombok.extern.slf4j.Slf4j;

@RestController
//@Slf4j
@CrossOrigin(origins="http://localhost:4200")
public class ComponentRestController {
	@Autowired
    private final AuthFeignClient authFeignClient;

    private final ReturnProcessService returnProcessService;
    
    private static List<ReturnRequestPayload> processRequestsList = new ArrayList<ReturnRequestPayload>();
    private static List<ReturnResponsePayload> processResponseList = new ArrayList<ReturnResponsePayload>();

    @Autowired
    public ComponentRestController(AuthFeignClient authFeignClient, ReturnProcessService returnProcessService) {
        this.authFeignClient = authFeignClient;
        this.returnProcessService = returnProcessService;
    }

    @GetMapping("/check")
    public String work() {
    	return "working";
    }
    @GetMapping("/hello")
    public boolean hello(@RequestHeader("Authorization") String token)
    {
    //	log.info(token);

    	return authFeignClient.validateAdmin(token);
//		@SuppressWarnings({ "rawtypes", "unchecked" })
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("Authorization",token);
//		HttpEntity req = new HttpEntity(headers);
//		
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<Boolean> response = restTemplate.exchange("http://localhost:2000/validate",HttpMethod.GET,req,Boolean.class);
//		
//		if(response.getStatusCode()==HttpStatus.OK) {
//			return response.getBody();}
//    	return false;
//    
    }
	@GetMapping(path="/ProcessDetail")
	public ResponseEntity<ReturnResponsePayload> getComponentCharges(@RequestHeader("Authorization") String token,@RequestParam String name,@RequestParam long contactNumber,@RequestParam String componentName,@RequestParam String componentType,@RequestParam int quantity) {
		//for(Map.Entry<String,String> 
		//key: allParams.entrySet()) 
		authFeignClient.validateAdmin(token);
		ReturnRequestPayload returnRequestPayload = new ReturnRequestPayload(name,contactNumber,true,componentName,componentType,quantity);
		
		
		
		//String uri = "http://localhost:9000/GetPackagingDeliveryCharge/Integral/2";
		//sending request with headers to PackagingDelivery service to get charges 
		ReturnResponsePayload response = returnProcessService.processReturnRequest(returnRequestPayload);
		returnRequestPayload.setRequestId(response.getRequestId());
		processRequestsList.add(returnRequestPayload);

		processResponseList.add(response);
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}

    
@PostMapping(path="/CompleteProcessing")
	public String paymentForReturnRequest(@RequestHeader("Authorization") String token, @RequestBody PaymentInformation paymentInformation) {
	authFeignClient.validateAdmin(token);
	ReturnRequest returnRequest = new ReturnRequest();
		for(ReturnRequestPayload req:processRequestsList) {
			if(req.getRequestId().equals(paymentInformation.getRequestId()))
			{
				returnRequest.setRequestId(req.getRequestId());
				returnRequest.setUserName(req.getUserName());
				returnRequest.setContactNumber(req.getContactNumber());
				returnRequest.setComponentName(req.getComponentName());
				returnRequest.setComponentType(req.getComponentType());
				returnRequest.setQuantity(req.getQuantity());
			}
		}
		
		returnRequest.setPriorityRequest(true);
		for(ReturnResponsePayload req:processResponseList) {
			if(req.getRequestId().equals(paymentInformation.getRequestId()))
			{
				returnRequest.setDateOfDelivery(req.getDateOfDelivery());
				returnRequest.setPackageAndDeliveryCharge(req.getPackageAndDeliveryCharge());
				returnRequest.setProcessingCharge(req.getProcessingCharge());

			}
		}
		
		
		
		// PaymentInformation newpaym = returnOrderRequest.saveOrderDetails(paymentInformation);
		if(returnProcessService.makePayment(paymentInformation) && returnProcessService.saveRequest(returnRequest))
		 return "Your request has been processed successfully!!";
		
		else return "Cannot process payment";
	}

	@GetMapping(path="returnorders/{requestId}")
	public ReturnRequest getReturnOrderDetail(@RequestHeader("Authorization") String token,@PathVariable String requestId) {
		authFeignClient.validateAdmin(token);
	return returnProcessService.getOrder(requestId);
}




}
