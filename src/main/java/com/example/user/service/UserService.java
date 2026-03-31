package com.example.user.service;

import com.example.user.dto.UserRequest;
import com.example.user.dto.UserResponse;
import com.example.user.entity.UserEntity;
import com.example.user.exception.ResourceAlreadyExistsException;
import com.example.user.exception.ResourceNotFoundException;
import com.example.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserResponse createUser(UserRequest request) {
        repository.findByEmail(request.getEmail()).ifPresent(u -> {
            throw new ResourceAlreadyExistsException("User with email already exists");
        });

        UserEntity entity = new UserEntity(request.getName(), request.getEmail(), request.getPassword());
        UserEntity saved = repository.save(entity);

        return toResponse(saved);
    }

    public UserResponse getUser(Long id) {
        UserEntity user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return toResponse(user);
    }

    private UserResponse toResponse(UserEntity user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}
