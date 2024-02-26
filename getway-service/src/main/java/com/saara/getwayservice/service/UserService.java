package com.saara.getwayservice.service;

import com.saara.getwayservice.dto.request.LoginRequestDto;
import com.saara.getwayservice.dto.request.RegisterRequestDto;
import com.saara.getwayservice.dto.response.CommonResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<CommonResponse> registerUser(RegisterRequestDto registerRequestDto) throws Exception;

    ResponseEntity<CommonResponse> login(LoginRequestDto loginRequestDto) throws Exception;

    ResponseEntity<CommonResponse> getAll() throws Exception;

    CommonResponse getUserById(Long id) throws Exception;

    ResponseEntity<CommonResponse> deleteUser(Long id) throws Exception;

//    CommonResponse deleteUser(Long id);
}
