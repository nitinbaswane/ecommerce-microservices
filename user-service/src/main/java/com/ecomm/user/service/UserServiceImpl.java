package com.ecomm.user.service;

import com.ecomm.user.dto.LoginRequestDto;
import com.ecomm.user.dto.LoginResponseDto;
import com.ecomm.user.dto.UserCreatedRequestDto;
import com.ecomm.user.dto.UserResponseDto;
import com.ecomm.user.entity.User;
import com.ecomm.user.exception.ResourceAlreadyExistException;
import com.ecomm.user.exception.ResourceNotFoundException;
import com.ecomm.user.mapper.AuthMapper;
import com.ecomm.user.mapper.UserMapper;
import com.ecomm.user.repository.UserRepository;
import com.ecomm.user.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthMapper authMapper;


    @Override
    public UserResponseDto register(UserCreatedRequestDto dto) {
      if(userRepository.existsByEmail(dto.getEmail())){
          throw new ResourceAlreadyExistException("User already exist");
      }
        User user = userMapper.toEntity(dto);
        User save = userRepository.save(user);

        return userMapper.toDto(save);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto dto) {
        User user = userRepository.findUserByEmail(dto.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User not exist"));
        boolean matches = passwordEncoder.matches(dto.getPassword(), user.getPassword());
        if(!matches){
            throw new ResourceNotFoundException("invalid credential");
        }
        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRoles());
        return authMapper.toLoginResponseDto(user,token);
    }

    @Override
    public UserResponseDto getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found: " + id));
        return userMapper.toDto(user);
    }



}
