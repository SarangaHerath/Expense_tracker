package com.saara.expenseservice.service;

import com.saara.expenseservice.dto.request.RequestExpenseDto;
import com.saara.expenseservice.dto.response.CommonResponse;

public interface ExpenseService {
    CommonResponse saveExpense(RequestExpenseDto requestExpenseDto) throws Exception;

    CommonResponse getExpenseByUser(Long userId) throws Exception;

    CommonResponse getExpenseById(Long id) throws Exception;

    CommonResponse updateExpense(RequestExpenseDto requestExpenseDto) throws Exception;

    CommonResponse delete(Long id) throws Exception;

    CommonResponse monthlyExpense(Long userId) throws Exception;
}
