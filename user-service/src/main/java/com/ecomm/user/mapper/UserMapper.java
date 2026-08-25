package com.ecomm.user.mapper;

import com.ecomm.user.dto.UserCreatedRequestDto;
import com.ecomm.user.dto.UserResponseDto;
import com.ecomm.user.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User toEntity(UserCreatedRequestDto requestDto){
        return User.builder()
                .email(requestDto.getEmail())
                .name(requestDto.getName())
                .phone(requestDto.getPhone())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .roles("ROLE_USER")
                .build();
    }

    public UserResponseDto toDto(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .roles(user.getRoles())
                .createdAt(user.getCreatedAt()).updatedAt(user.getUpdatedAt())
                .build();
    }
}
