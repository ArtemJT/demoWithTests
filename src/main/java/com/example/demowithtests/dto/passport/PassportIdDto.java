package com.example.demowithtests.dto.passport;

import lombok.Builder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Builder
public record PassportIdDto(
        @NotNull
        @Positive
        Integer id
) {
}
