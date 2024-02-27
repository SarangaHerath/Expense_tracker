package com.saara.expenseservice.service.impl;

import com.saara.expenseservice.dto.request.RequestExpenseDto;
import com.saara.expenseservice.dto.response.CommonResponse;
import com.saara.expenseservice.entity.Category;
import com.saara.expenseservice.entity.Expense;
import com.saara.expenseservice.entity.User;
import com.saara.expenseservice.repository.CategoryRepo;
import com.saara.expenseservice.repository.ExpenseRepo;
import com.saara.expenseservice.repository.UserRepo;
import com.saara.expenseservice.service.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepo expenseRepo;
    private final CategoryRepo categoryRepo;
    private final UserRepo userRepo;

    public ExpenseServiceImpl(ExpenseRepo expenseRepo, CategoryRepo categoryRepo, UserRepo userRepo) {
        this.expenseRepo = expenseRepo;
        this.categoryRepo = categoryRepo;
        this.userRepo = userRepo;
    }

    @Override
    public CommonResponse saveExpense(RequestExpenseDto requestExpenseDto) throws Exception {
        try {

            Category category = categoryRepo.findById(requestExpenseDto.getCategoryId()).get();
            User user = userRepo.findById(requestExpenseDto.getUserId()).get();
            Expense expense = Expense.builder()
                    .amount(requestExpenseDto.getAmount())
                    .category(category)
                    .date(requestExpenseDto.getDate())
                    .description(requestExpenseDto.getDescription())
                    .user(user)
                    .build();
            expenseRepo.save(expense);
            return CommonResponse.builder()
                    .data(expense)
                    .responseCode(HttpStatus.CREATED)
                    .message("Expense save success")
                    .build();

        } catch (Exception e) {
            throw new Exception("Error occur during save expense");
        }
    }

    @Override
    public CommonResponse getExpenseByUser(Long userId) throws Exception {
        try {
            List<Expense> list = expenseRepo.findByUser_UserId(userId);
            if (list.isEmpty()) {
                return CommonResponse.builder()
                        .message("This user have not expense")
                        .responseCode(HttpStatus.NOT_FOUND)
                        .build();
            } else {
                return CommonResponse.builder()
                        .responseCode(HttpStatus.OK)
                        .message("Expense get by user success")
                        .data(list)
                        .build();
            }
        } catch (Exception e) {
            throw new Exception("Error occur during get expense by user");
        }
    }

    @Override
    public CommonResponse getExpenseById(Long id) throws Exception {
        try {
            Optional<Expense> optionalExpense = expenseRepo.findById(id);
            if (optionalExpense.isPresent()) {
                Expense expense = optionalExpense.get();
                return CommonResponse.builder()
                        .data(expense)
                        .responseCode(HttpStatus.OK)
                        .message("Get expense by id success")
                        .build();
            } else {
                return CommonResponse.builder()
                        .message("Expense not found")
                        .responseCode(HttpStatus.NOT_FOUND)
                        .build();
            }
        } catch (Exception e) {
            throw new Exception("Error occur during get expense by id");
        }
    }

    @Override
    public CommonResponse updateExpense(RequestExpenseDto requestExpenseDto) throws Exception {
        try {
            Optional<Expense> optionalExpense = expenseRepo.findById(requestExpenseDto.getId());

            User user = userRepo.findById(requestExpenseDto.getUserId()).get();
            Category category = categoryRepo.findById(requestExpenseDto.getCategoryId()).get();

            if (optionalExpense.isPresent()) {
                Expense expense = optionalExpense.get();
                expense.setAmount(requestExpenseDto.getAmount());
                expense.setDate(requestExpenseDto.getDate());
                expense.setDescription(requestExpenseDto.getDescription());
                expense.setUser(user);
                expense.setCategory(category);
                expenseRepo.save(expense);
                return CommonResponse.builder()
                        .message("Expense update success")
                        .responseCode(HttpStatus.OK)
                        .data(expense)
                        .build();
            } else {
                return CommonResponse.builder()
                        .responseCode(HttpStatus.NOT_FOUND)
                        .message("Expense not found")
                        .build();
            }
        } catch (Exception e) {
            throw new Exception("Error occur during expense update");
        }
    }

    @Override
    public CommonResponse delete(Long id) throws Exception {
        try {
            Optional<Expense> optionalExpense = expenseRepo.findById(id);
            if (optionalExpense.isPresent()) {
                expenseRepo.deleteById(id);
                return CommonResponse.builder()
                        .responseCode(HttpStatus.OK)
                        .message("Expense delete success")
                        .build();
            } else {
                return CommonResponse.builder()
                        .message("Expense not found")
                        .responseCode(HttpStatus.NOT_FOUND)
                        .build();
            }
        } catch (Exception e) {
            throw new Exception("Error occur during delete expense");
        }

    }

    @Override
    public CommonResponse monthlyExpense(Long userId) throws Exception {
        try {
            YearMonth currentMonthYear = YearMonth.now();


            LocalDate firstDayOfMonth = currentMonthYear.atDay(1);
            LocalDate lastDayOfMonth = currentMonthYear.atEndOfMonth();

            List<Expense> list = expenseRepo.findByUser_UserIdAndDateBetween(userId,firstDayOfMonth,lastDayOfMonth);
//            List<Expense> list = expenseRepo.findByUser_UserIdAndDateBetween(userId, firstDayOfMonth, lastDayOfMonth);
            log.info("User ID: {}, Start Date: {}, End Date: {}, Expenses: {}", userId, firstDayOfMonth, lastDayOfMonth, list);
            if(list.isEmpty()){
                return CommonResponse.builder()
                        .message("Not found any expense this month")
                        .responseCode(HttpStatus.NOT_FOUND)
                        .build();
            }else {
                return CommonResponse.builder()
                        .message("Successfully get expense this month")
                        .responseCode(HttpStatus.OK)
                        .data(list)
                        .build();
            }
        } catch (Exception e) {
              throw new Exception("Error occur during get expense this month");
        }
    }
}
