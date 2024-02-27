package com.saara.getwayservice.controller;

import com.saara.getwayservice.dto.request.RequestExpenseDto;
import com.saara.getwayservice.dto.response.CommonResponse;
import com.saara.getwayservice.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/expense-gate")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }
    @PostMapping("/save")
    public ResponseEntity<CommonResponse> saveExpense(@RequestBody RequestExpenseDto requestExpenseDto) throws Exception {
        CommonResponse commonResponse = expenseService.saveExpense(requestExpenseDto);
        return ResponseEntity.ok(commonResponse);
    }
    @GetMapping("/getExpenseByUser/{userId}")
    public ResponseEntity<CommonResponse> getExpenseByUser(@PathVariable("userId") Long userId) throws Exception {
        CommonResponse commonResponse = expenseService.getExpenseByUser(userId);
        return ResponseEntity.ok(commonResponse);
    }
    @GetMapping("/getExpenseById/{id}")
    public ResponseEntity<CommonResponse> getExpenseById(@PathVariable("id") Long id) throws Exception {
        CommonResponse commonResponse = expenseService.getExpenseById(id);
        return ResponseEntity.ok(commonResponse);
    }
    @PutMapping("/update")
    public ResponseEntity<CommonResponse> updateExpense(@RequestBody RequestExpenseDto requestExpenseDto) throws Exception {
        CommonResponse commonResponse = expenseService.updateExpense(requestExpenseDto);
        return ResponseEntity.ok(commonResponse);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CommonResponse> deleteExpense(@PathVariable("id") Long id) throws Exception {
        CommonResponse commonResponse = expenseService.delete(id);
        return ResponseEntity.ok(commonResponse);
    }
    @GetMapping("/monthly/{userId}")
    public ResponseEntity<CommonResponse> monthlyExpense(@PathVariable("userId") Long userId) throws Exception {
        CommonResponse commonResponse = expenseService.monthlyExpense(userId);
        return ResponseEntity.ok(commonResponse);
    }
}
