package com.ecomm.user.service;

import com.ecomm.user.dto.LoginRequestDto;
import com.ecomm.user.dto.LoginResponseDto;
import com.ecomm.user.dto.UserCreatedRequestDto;
import com.ecomm.user.dto.UserResponseDto;

public interface UserService {
    UserResponseDto register(UserCreatedRequestDto dto);
    LoginResponseDto login(LoginRequestDto dto);
    UserResponseDto getById(Long id);
}
