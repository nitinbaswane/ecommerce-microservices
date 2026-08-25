package com.ecomm.user.controller;

import com.ecomm.user.dto.LoginRequestDto;
import com.ecomm.user.dto.LoginResponseDto;
import com.ecomm.user.dto.UserCreatedRequestDto;
import com.ecomm.user.dto.UserResponseDto;
import com.ecomm.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserCreatedRequestDto requestDto) {
        UserResponseDto registered = userService.register(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registered);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto logined = userService.login(loginRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(logined);
    }

    @GetMapping("/id")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id , Authentication authentication) {
        UserResponseDto byId = userService.getById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(byId);
    }

}
