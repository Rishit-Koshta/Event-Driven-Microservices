package com.rishit.user_service.USER_SERVICE.service;

import com.rishit.user_service.USER_SERVICE.dto.CreateUserRequest;
import com.rishit.user_service.USER_SERVICE.dto.UserResponse;
import com.rishit.user_service.USER_SERVICE.entity.User;
import com.rishit.user_service.USER_SERVICE.mapper.UserMapper;
import com.rishit.user_service.USER_SERVICE.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final UserMapper mapper;

    public UserResponse create(CreateUserRequest request){

        if(repository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        User saved = repository.save(
                mapper.toEntity(request)
        );

        return mapper.toResponse(saved);
    }

    public UserResponse getById(Long id){

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return mapper.toResponse(user);
    }
}
