package com.saara.getwayservice.service.Impl;

import com.saara.getwayservice.dto.request.LoginRequestDto;
import com.saara.getwayservice.dto.request.RegisterRequestDto;
import com.saara.getwayservice.dto.response.CommonResponse;
import com.saara.getwayservice.service.UserService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {
    private final RestTemplate restTemplate;
    private final String userUrl = "http://localhost:8081/api/v1/user";

    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<CommonResponse> registerUser(RegisterRequestDto registerRequestDto) throws Exception {
        try {

            // Use postForObject for making a POST request
            CommonResponse commonResponse = restTemplate.postForObject(userUrl + "/register", registerRequestDto, CommonResponse.class);

            // Assuming CommonResponse has appropriate constructor and getters
            return ResponseEntity.ok(commonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error occur during save user");
        }
    }

    @Override
    public ResponseEntity<CommonResponse> login(LoginRequestDto loginRequestDto) throws Exception {
        try {

            // Use postForObject for making a POST request
            CommonResponse commonResponse = restTemplate.postForObject(userUrl + "/login", loginRequestDto, CommonResponse.class);

            // Assuming CommonResponse has appropriate constructor and getters
            return ResponseEntity.ok(commonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error occur during login user");
        }
    }

    @Override
    public ResponseEntity<CommonResponse> getAll() throws Exception {
        try {

            // Use postForObject for making a POST request
            CommonResponse commonResponse = restTemplate.getForObject(userUrl + "/allUser", CommonResponse.class);

            // Assuming CommonResponse has appropriate constructor and getters
            return ResponseEntity.ok(commonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error occur during login user");
        }
    }

    @Override
    public CommonResponse getUserById(Long id) throws Exception {
        try {

            // Use postForObject for making a POST request
            CommonResponse commonResponse = restTemplate.getForObject(userUrl + "/getById/{id}", CommonResponse.class,id);

            // Assuming CommonResponse has appropriate constructor and getters
            return commonResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error occur during save user");
        }
    }

    @Override
    public ResponseEntity<CommonResponse> deleteUser(Long id) throws Exception {
        try {
            ResponseEntity<CommonResponse> responseEntity = restTemplate.exchange(
                    userUrl + "/deleteUser/{id}",
                    HttpMethod.DELETE,
                    null,
                    CommonResponse.class,
                    id
            );

            // Assuming CommonResponse has appropriate constructor and getters
            return responseEntity;
        } catch (HttpClientErrorException.NotFound notFoundException) {
            // Handle 404 Not Found exception
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (HttpClientErrorException.BadRequest badRequestException) {
            // Handle 400 Bad Request exception
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error occurred during delete user");
        }
    }
}
