package com.saara.getwayservice.service.Impl;

import com.saara.getwayservice.dto.request.RequestExpenseDto;
import com.saara.getwayservice.dto.response.CommonResponse;
import com.saara.getwayservice.service.ExpenseService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final RestTemplate restTemplate;
    private final String expenseUrl = "http://localhost:8083/api/v1/expense";

    public ExpenseServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public CommonResponse saveExpense(RequestExpenseDto requestExpenseDto) throws Exception {
        try {
            // Use postForObject for making a POST request with the request body
            CommonResponse commonResponse = restTemplate.postForObject(
                    expenseUrl + "/save",
                    requestExpenseDto,
                    CommonResponse.class
            );

            // Assuming CommonResponse has appropriate constructor and getters
            return commonResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error occur during save expense");
        }
    }


    @Override
    public CommonResponse getExpenseByUser(Long userId) throws Exception {
        try {

            // Use postForObject for making a POST request
            CommonResponse commonResponse = restTemplate.getForObject(expenseUrl + "/getExpenseByUser/{userId}", CommonResponse.class, userId);

            // Assuming CommonResponse has appropriate constructor and getters
            return commonResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error occur during get expense by user id");
        }
    }

    @Override
    public CommonResponse getExpenseById(Long id) throws Exception {
        try {

            // Use postForObject for making a POST request
            CommonResponse commonResponse = restTemplate.getForObject(expenseUrl + "/getExpenseById/{id}", CommonResponse.class, id);

            // Assuming CommonResponse has appropriate constructor and getters
            return commonResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error occur during get expense by id");
        }
    }

    @Override
    public CommonResponse updateExpense(RequestExpenseDto requestExpenseDto) throws Exception {
        try {
            // Use postForObject for making a POST request
            CommonResponse commonResponse = restTemplate.exchange(
                    expenseUrl + "/update",
                    HttpMethod.PUT,
                    new HttpEntity<>(requestExpenseDto),
                    CommonResponse.class,
                    requestExpenseDto
            ).getBody();  // Extracting the CommonResponse from the ResponseEntity

            return commonResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error occur during updating expense");
        }
    }

    @Override
    public CommonResponse delete(Long id) throws Exception {
        try {
            restTemplate.exchange(
                    expenseUrl + "/delete/{id}",
                    HttpMethod.DELETE,
                    null,
                    CommonResponse.class,
                    id
            );

            // Assuming CommonResponse has appropriate constructor and getters
            return new CommonResponse("Success", "Expense deleted successfully", null);
        } catch (HttpClientErrorException.NotFound notFoundException) {
            // Handle 404 Not Found exception
            return new CommonResponse("Error", "Expense not found", null);
        } catch (HttpClientErrorException.BadRequest badRequestException) {
            // Handle 400 Bad Request exception
            return new CommonResponse("Error", "Bad request", null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error occurred during delete expense");
        }
    }

    @Override
    public CommonResponse monthlyExpense(Long userId) throws Exception {
        try {

            // Use postForObject for making a POST request
            CommonResponse commonResponse = restTemplate.getForObject(expenseUrl + "/monthly/{userId}", CommonResponse.class, userId);

            // Assuming CommonResponse has appropriate constructor and getters
            return commonResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error occur during get monthly expense by user id");
        }
    }


}
