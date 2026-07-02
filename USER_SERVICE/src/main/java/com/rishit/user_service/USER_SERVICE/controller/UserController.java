package com.rishit.user_service.USER_SERVICE.controller;

import com.rishit.user_service.USER_SERVICE.dto.CreateUserRequest;
import com.rishit.user_service.USER_SERVICE.dto.UserResponse;
import com.rishit.user_service.USER_SERVICE.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public UserResponse create(
            @RequestBody @Valid CreateUserRequest request){

        return service.create(request);
    }

    @GetMapping("/{id}")
    public UserResponse get(
            @PathVariable Long id){

        return service.getById(id);
    }
}