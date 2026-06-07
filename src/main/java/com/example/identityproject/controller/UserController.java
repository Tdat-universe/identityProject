package com.example.identityproject.controller;

import com.example.identityproject.dto.Request.UserCreationRequest;
import com.example.identityproject.dto.Request.UserUpdateRequest;
import com.example.identityproject.dto.Response.ApiResponse;
import com.example.identityproject.entity.User;
import com.example.identityproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;

    }

    @GetMapping
    List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/{userId}")
    User getUser(@PathVariable("userId") String userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    String updateInformation(@PathVariable("userId") String userId, @RequestBody UserUpdateRequest request) {
        userService.updateUser(userId, request);
        return "Update sucessfully";
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return "deleted";
    }
}
