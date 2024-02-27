package com.saara.getwayservice.dto.request;

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
