package com.saara.userservice.service;

import com.saara.userservice.dto.request.LoginRequestDto;
import com.saara.userservice.dto.request.RegisterRequestDto;
import com.saara.userservice.dto.response.CommonResponse;

public interface UserService {

    CommonResponse register(RegisterRequestDto registerRequestDto);

    CommonResponse login(LoginRequestDto loginRequestDto) throws Exception;

    CommonResponse getAllUser() throws Exception;

    CommonResponse getUserById(Long id) throws Exception;

    CommonResponse deleteUser(Long id) throws Exception;

}
