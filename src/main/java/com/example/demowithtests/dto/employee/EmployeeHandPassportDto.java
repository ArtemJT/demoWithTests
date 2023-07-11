package com.example.demowithtests.dto.employee;

import com.example.demowithtests.domain.PassportPhoto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record EmployeeHandPassportDto(
        @NotNull
        @Positive
        Integer employeeId,

        @NotNull
        @Positive
        Integer passportId,

        @NotNull
        PassportPhoto photo) {
}
