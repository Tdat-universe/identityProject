package com.example.identityproject.mapper;

import com.example.identityproject.dto.Request.UserCreationRequest;
import com.example.identityproject.dto.Request.UserUpdateRequest;
import com.example.identityproject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
