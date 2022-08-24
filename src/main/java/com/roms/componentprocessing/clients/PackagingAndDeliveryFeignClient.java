package com.roms.componentprocessing.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "packaging-and-delivery-service-777", url="http://localhost:5000")
public interface PackagingAndDeliveryFeignClient {
    @GetMapping("/api/packagingAndDelivery/getCharge/{componentType}/{count}")
    double getPackagingAndDeliveryCharge(@PathVariable String componentType, @PathVariable int count);
}
