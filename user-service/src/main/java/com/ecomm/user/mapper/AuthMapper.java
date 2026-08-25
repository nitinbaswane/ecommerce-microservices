package com.ecomm.user.mapper;

import com.ecomm.user.dto.LoginResponseDto;
import com.ecomm.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public LoginResponseDto toLoginResponseDto(User user, String token){
        return new LoginResponseDto(token,"Bearer",Long.toString(user.getId()) ,user.getEmail(),user.getName());
    }
}
