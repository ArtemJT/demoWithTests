package com.example.demowithtests.dto.employee;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record EmployeeIdDto(
        @NotNull
        @Positive
        Integer id) {
}
