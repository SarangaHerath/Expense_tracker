package com.saara.userservice.controller;

import com.saara.userservice.dto.request.LoginRequestDto;
import com.saara.userservice.dto.request.RegisterRequestDto;
import com.saara.userservice.dto.response.CommonResponse;
import com.saara.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("api/v1/user")
public class UserController {

    final private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<CommonResponse> register(@RequestBody RegisterRequestDto registerRequestDto) {
        CommonResponse commonResponse = userService.register(registerRequestDto);
        return ResponseEntity.ok(commonResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponse> login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        CommonResponse commonResponse = userService.login(loginRequestDto);
        return ResponseEntity.ok(commonResponse);
    }

    @GetMapping("/allUser")
    public ResponseEntity<CommonResponse> getAllUser() throws Exception {
        CommonResponse response = userService.getAllUser();
        return ResponseEntity.ok(response);
    }
    @GetMapping("getById/{id}")
    public ResponseEntity<CommonResponse> getUserById(@PathVariable("id") Long id) throws Exception {
        CommonResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<CommonResponse> deleteUser(@PathVariable("id") Long id) throws Exception {
        CommonResponse response = userService.deleteUser(id);
        return ResponseEntity.ok(response);
    }
}
