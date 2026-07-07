package com.rishit.order_service.ORDER_SERVICE.service;

import com.rishit.order_service.ORDER_SERVICE.client.UserClient;
import com.rishit.order_service.ORDER_SERVICE.dto.UserResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserClientService {

    private final UserClient userClient;


    @CircuitBreaker(
            name = "user-service",
            fallbackMethod = "fallbackUser"
    )
    public UserResponse getUser(Long id){

        System.out.println("CALLING REAL USER SERVICE");

        return userClient.getUser(id);
    }


    public UserResponse fallbackUser(
            Long id,
            Throwable throwable
    ){

        System.out.println(
                "========== FALLBACK EXECUTED =========="
        );

        System.out.println(
                throwable.getMessage()
        );


        return UserResponse.builder()
                .id(id)
                .email("fallback@gmail.com")
                .build();
    }
}