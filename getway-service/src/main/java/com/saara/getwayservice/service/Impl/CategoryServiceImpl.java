package com.saara.getwayservice.service.Impl;

import com.saara.getwayservice.dto.request.RequestUpdateDto;
import com.saara.getwayservice.dto.response.CommonResponse;
import com.saara.getwayservice.service.CategoryService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final RestTemplate restTemplate;
    private final String categoryUrl = "http://localhost:8082/api/v1/category";

    public CategoryServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public ResponseEntity<CommonResponse> getAll() throws Exception {
        try {

            // Use postForObject for making a POST request
            CommonResponse commonResponse = restTemplate.getForObject(categoryUrl + "/getAll", CommonResponse.class);

            // Assuming CommonResponse has appropriate constructor and getters
            return ResponseEntity.ok(commonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error occur during login user");
        }
    }

    @Override
    public CommonResponse getCategoryById(Long id) throws Exception {
        try {

            // Use postForObject for making a POST request
            CommonResponse commonResponse = restTemplate.getForObject(categoryUrl + "/getCategoryById/{id}", CommonResponse.class, id);

            // Assuming CommonResponse has appropriate constructor and getters
            return commonResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error occur during get category by id");
        }
    }

    @Override
    public ResponseEntity<CommonResponse> deleteCategory(Long id) throws Exception {
        try {
            ResponseEntity<CommonResponse> responseEntity = restTemplate.exchange(
                    categoryUrl + "/delete/{id}",
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
            throw new Exception("Error occurred during delete category");
        }
    }

    @Override
    public ResponseEntity<CommonResponse> saveCategory(String name) throws Exception {
        try {
            // Create a map to hold the variable values
            Map<String, Object> uriVariables = new HashMap<>();
            uriVariables.put("name", name);

            // Use postForObject for making a POST request with variable values
            CommonResponse commonResponse = restTemplate.postForObject(categoryUrl + "/addCategory/{name}", null, CommonResponse.class, uriVariables);

            // Assuming CommonResponse has appropriate constructor and getters
            return ResponseEntity.ok(commonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error occur during save user");
        }
    }

    @Override
    public ResponseEntity<CommonResponse> updateCategory(RequestUpdateDto requestUpdateDto) throws Exception {
        try {

            // Use postForObject for making a POST request
            ResponseEntity<CommonResponse> responseEntity = restTemplate.exchange(
                    categoryUrl + "/update",
                    HttpMethod.PUT,
                    new HttpEntity<>(requestUpdateDto),
                    CommonResponse.class,
                    requestUpdateDto
            );

            // Assuming CommonResponse has appropriate constructor and getters
            return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error occur during login user");
        }
    }
}
