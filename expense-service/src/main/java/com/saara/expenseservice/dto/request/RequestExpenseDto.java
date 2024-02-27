package com.saara.expenseservice.dto.request;

import com.saara.expenseservice.entity.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RequestExpenseDto {
    private Long id;
    private Double amount;
    private String description;
    private LocalDate date;
    private Long categoryId;
    private Long userId;
}
