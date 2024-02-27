package com.saara.getwayservice.service;

import com.saara.getwayservice.dto.request.RequestExpenseDto;
import com.saara.getwayservice.dto.response.CommonResponse;

public interface ExpenseService {
    CommonResponse saveExpense(RequestExpenseDto requestExpenseDto) throws Exception;

    CommonResponse getExpenseByUser(Long userId) throws Exception;

    CommonResponse getExpenseById(Long id) throws Exception;

    CommonResponse updateExpense(RequestExpenseDto requestExpenseDto) throws Exception;

    CommonResponse delete(Long id) throws Exception;

    CommonResponse monthlyExpense(Long userId) throws Exception;
}
