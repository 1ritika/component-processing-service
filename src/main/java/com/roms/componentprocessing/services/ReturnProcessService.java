package com.roms.componentprocessing.services;

import com.roms.componentprocessing.entity.PaymentInformation;
import com.roms.componentprocessing.entity.ReturnRequest;
import com.roms.componentprocessing.clients.PackagingAndDeliveryFeignClient;

import com.roms.componentprocessing.payload.ReturnRequestPayload;
import com.roms.componentprocessing.payload.ReturnResponsePayload;
import com.roms.componentprocessing.repositories.PaymentReturnRepository;
import com.roms.componentprocessing.repositories.ReturnRequestRepository;
//import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanUtils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

@Service
//@Slf4j
public class ReturnProcessService {
    private final ReturnRequestRepository returnRequestRepository;
    private final PaymentReturnRepository paymentReturnRepository;
    
    private final PackagingAndDeliveryFeignClient packagingAndDeliveryFeignClient;

    @Autowired
    public ReturnProcessService(
            ReturnRequestRepository returnRequestRepository,
            PaymentReturnRepository paymentReturnRepository,
            
            PackagingAndDeliveryFeignClient packagingAndDeliveryFeignClient) {
        this.returnRequestRepository = returnRequestRepository;
        this.paymentReturnRepository = paymentReturnRepository;
        
        this.packagingAndDeliveryFeignClient = packagingAndDeliveryFeignClient;
    }

    public ReturnResponsePayload processReturnRequest(ReturnRequestPayload returnRequestPayload) {
        ReturnRequest returnRequest = new ReturnRequest();
        ReturnResponsePayload returnResponsePayload = new ReturnResponsePayload();

        BeanUtils.copyProperties(returnRequestPayload, returnRequest);

        int processingDays = 5;
        double processingCharge = returnRequestPayload.getComponentType().equalsIgnoreCase("integral") ? 500 : 300;

        if (returnRequestPayload.getComponentType().equalsIgnoreCase("integral") && returnRequestPayload.isPriorityRequest()) {
            processingDays = 2;
            processingCharge += 200;
        }
        LocalDate date = LocalDate.now().plusDays(processingDays);
        returnRequest.setProcessingCharge(processingCharge);
        returnRequest.setDateOfDelivery(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        returnRequest.setRequestId(UUID.randomUUID().toString().replace("-", ""));
//        System.out.print(packagingAndDeliveryFeignClient.getPackagingAndDeliveryCharge(
  //              returnRequestPayload.getComponentType(), returnRequestPayload.getQuantity()));

        returnRequest.setPackageAndDeliveryCharge(packagingAndDeliveryFeignClient.getPackagingAndDeliveryCharge(
                returnRequestPayload.getComponentType(),returnRequestPayload.getQuantity())
      // returnRequest.setPackageAndDeliveryCharge(packagingAndDeliveryFeignClient.getPackagingAndDeliveryCharge(
        //        returnRequestPayload.getComponentType(), returnRequestPayload.getQuantity())
        );
//
//       // returnRequestRepository.save(returnRequest);
//
        returnResponsePayload.setRequestId(returnRequest.getRequestId());
        returnResponsePayload.setProcessingCharge(returnRequest.getProcessingCharge());
        returnResponsePayload.setPackageAndDeliveryCharge(returnRequest.getPackageAndDeliveryCharge());
        returnResponsePayload.setDateOfDelivery(returnRequest.getDateOfDelivery());

        return returnResponsePayload;

    }

    public boolean makePayment(PaymentInformation paymentRequest) {

    	paymentReturnRepository.save(paymentRequest);
    	try {
        paymentReturnRepository.save(paymentRequest);
        return true;
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    public boolean saveRequest(ReturnRequest r) {

    	returnRequestRepository.save(r);
    	try {
        returnRequestRepository.save(r);

        return true;
    	}
    	catch(Exception e) {
    		return false;
    	}
    }
    
    
    public ReturnRequest getOrder(String requestId) {
    	return returnRequestRepository.findByRequestId(requestId);
    }
}
