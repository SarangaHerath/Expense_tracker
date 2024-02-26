package com.saara.userservice.service.Impl;

import com.saara.userservice.dto.request.LoginRequestDto;
import com.saara.userservice.dto.request.RegisterRequestDto;
import com.saara.userservice.dto.response.CommonResponse;
import com.saara.userservice.entity.Roles;
import com.saara.userservice.entity.User;
import com.saara.userservice.exception.InternalServerException;
import com.saara.userservice.exception.UsernameAlreadyExistsException;
import com.saara.userservice.repository.UserRepo;
import com.saara.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public CommonResponse register(RegisterRequestDto registerRequestDto) {
        try {
            boolean usernameExists = userRepo.existsByUsername(registerRequestDto.getUsername());
            if (usernameExists) {
                throw new UsernameAlreadyExistsException("Username already exists");
            }
            var user = User.builder()
                    .fistName(registerRequestDto.getFirstName())
                    .lastName(registerRequestDto.getLastName())
                    .username(registerRequestDto.getUsername())
                    .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                    .roles(Roles.USER)
                    .build();

            userRepo.save(user);


            return CommonResponse.builder()
                    .message("User Create Successful")
                    .data(user)
                    .responseCode(HttpStatus.CREATED)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerException("Error occur during register");
        }

    }

    @Override
    public CommonResponse login(LoginRequestDto loginRequestDto) throws Exception {
        try {
            var user = userRepo.findByUsername(loginRequestDto.getUsername());
            if (user != null && passwordEncoder.matches(loginRequestDto.getPassword(), user.get().getPassword())) {

                return CommonResponse.builder()
                        .data(user)
                        .responseCode(HttpStatus.OK)
                        .message("Login Success")
                        .build();
            } else {
                return CommonResponse.builder()
                        .responseCode(HttpStatus.UNAUTHORIZED)
                        .message("Invalid username or password")
                        .build();
            }
        } catch (Exception e) {
            throw new Exception("Error occur during login");
        }

    }

    @Override
    public CommonResponse getAllUser() throws Exception {
        try {
            List<User> userList = userRepo.findAll();
            return CommonResponse.builder()
                    .message("Successfully get all users")
                    .responseCode(HttpStatus.OK)
                    .data(userList)
                    .build();
        }catch (Exception e){
            throw new Exception("Error occur during get all user");
        }
    }

    @Override
    public CommonResponse getUserById(Long id) throws Exception {
        try {
            Optional<User> user = userRepo.findById(id);
            if(user.isPresent()){
                return CommonResponse.builder()
                        .data(user)
                        .responseCode(HttpStatus.OK)
                        .message("Get user by id success")
                        .build();
            }
            else {
                return CommonResponse.builder()
                        .message("User not found")
                        .responseCode(HttpStatus.NOT_FOUND)
                        .build();
            }
        }catch (Exception e){
            throw new Exception("Error occur during get user by id");
        }
    }

    @Override
    public CommonResponse deleteUser(Long id) throws Exception {
        try {
            Optional<User> user = userRepo.findById(id);
            if (user.isPresent()){
                userRepo.deleteById(id);
                return CommonResponse.builder()
                        .responseCode(HttpStatus.OK)
                        .message("User delete success")
                        .build();
            }else {
                return CommonResponse.builder()
                        .message("User not found")
                        .responseCode(HttpStatus.NOT_FOUND)
                        .build();
            }
        }catch (Exception e){
            throw new Exception("Error occur during delete user ");
        }
    }
}

