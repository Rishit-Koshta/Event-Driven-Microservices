package com.rishit.order_service.ORDER_SERVICE.client;

import com.rishit.order_service.ORDER_SERVICE.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "user-service"
)
public interface UserClient {

    @GetMapping("/users/{id}")
    UserResponse getUser(
            @PathVariable Long id
    );
}