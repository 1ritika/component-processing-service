package com.roms.componentprocessing.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

@FeignClient(name = "AUTH-SERVICE", url="http://54.160.184.159:2000")

public interface AuthFeignClient {

    @GetMapping("/api/auth-service/validate")
    boolean validateAdmin(@RequestHeader("Authorization")String token);

}
 