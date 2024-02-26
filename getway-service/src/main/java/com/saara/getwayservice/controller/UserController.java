package com.saara.getwayservice.controller;

import com.saara.getwayservice.dto.request.LoginRequestDto;
import com.saara.getwayservice.dto.request.RegisterRequestDto;
import com.saara.getwayservice.dto.response.CommonResponse;
import com.saara.getwayservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("api/v1/user-gate")
@RestController
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity<CommonResponse> registerUser(@RequestBody RegisterRequestDto registerRequestDto) throws Exception {
              log.info("hit user-gate register method, dto: {} ",registerRequestDto);
              ResponseEntity<CommonResponse> commonResponse = userService.registerUser(registerRequestDto);
              return commonResponse;
    }
    @PostMapping("login")
    public ResponseEntity<CommonResponse> login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        log.info("hit user-gate login method, dto: {} ",loginRequestDto);
        ResponseEntity<CommonResponse> commonResponse = userService.login(loginRequestDto);
        return commonResponse;
    }
    @GetMapping("getAll")
    public ResponseEntity<CommonResponse> getAllUser() throws Exception {
        log.info("hit user-gate get all user method");
        ResponseEntity<CommonResponse> commonResponse = userService.getAll();
        return commonResponse;
    }
    @GetMapping("getById/{id}")
    public ResponseEntity<CommonResponse> getUserById(@PathVariable("id") Long id) throws Exception {
        CommonResponse response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<CommonResponse> deleteUser(@PathVariable("id") Long id) throws Exception {
        ResponseEntity<CommonResponse> response = userService.deleteUser(id);
        return ResponseEntity.ok(response.getBody());
    }
}
