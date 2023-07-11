package com.example.demowithtests.dto.passport;

import lombok.Builder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Builder
public record PassportPhotoUpdateDto(@NotNull
                                     @Positive
                                     Integer passportId,
                                     @NotNull PassportPhotoDto photo) {
}
