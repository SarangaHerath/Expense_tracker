package com.saara.expenseservice.repository;

import com.saara.expenseservice.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense,Long> {
    List<Expense> findByUser_UserId(Long userId);

    List<Expense> findByUser_UserIdAndDateBetween(Long userId, LocalDate firstDayOfMonth, LocalDate lastDayOfMonth);

}
