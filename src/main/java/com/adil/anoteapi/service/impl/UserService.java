package com.adil.anoteapi.service.impl;


import com.adil.anoteapi.dto.user.UserChangePasswordDto;
import com.adil.anoteapi.dto.user.UserCreateDto;
import com.adil.anoteapi.dto.user.UserDetailDto;
import com.adil.anoteapi.dto.user.UserUpdateDto;
import com.adil.anoteapi.entity.User;
import com.adil.anoteapi.exception.ResourceAlreadyExistsException;
import com.adil.anoteapi.exception.ResourceNotFoundException;
import com.adil.anoteapi.mapper.UserMapper;
import com.adil.anoteapi.repository.UserRepository;
import com.adil.anoteapi.service.interf.IUserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetailDto getUserDetails() {
        String username = retrieveCurrentUsername();
        User user = userRepository.findByUsernameOrEmail(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return userMapper.toDetailDto(user);
    }


    @Override
    public UserDetailDto updateUser(UserUpdateDto dto) {
        User user = userRepository.findByUsernameOrEmail(retrieveCurrentUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (dto.getUsername() != null && !dto.getUsername().equals(user.getUsername())) {
            if (userRepository.existsByUsername(dto.getUsername())) {
                throw new ResourceAlreadyExistsException("Username is already taken");
            }
            user.setUsername(dto.getUsername());
        }

        if (dto.getEmail() != null && !dto.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(dto.getEmail())) {
                throw new ResourceAlreadyExistsException("Email is already taken");
            }
            user.setEmail(dto.getEmail());
        }

        userMapper.updateUserFromDto(dto, user);

        User updatedUser = userRepository.save(user);
        return userMapper.toDetailDto(updatedUser);
    }


    @Override
    public void changePassword(UserChangePasswordDto dto) {
        User user = userRepository.findByUsernameOrEmail(retrieveCurrentUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Old password is not correct");
        }
        if (passwordEncoder.matches(dto.getNewPassword(), user.getPassword())) {
            throw new IllegalArgumentException("New password cannot be the same as old password");
        }
//        TODO: For production
//        if (passwordEncoder.matches(dto.getNewPassword(), user.getUsername())) {
//            throw new IllegalArgumentException("New password cannot be the same as username");
//        }
//        if (passwordEncoder.matches(dto.getNewPassword(), user.getEmail())) {
//            throw new IllegalArgumentException("New password cannot be the same as email");
//        }
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteUser() {
        User user = userRepository.findByUsernameOrEmail(retrieveCurrentUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
    }

    private static String retrieveCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
