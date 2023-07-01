package com.example.demowithtests.util.annotations.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class BlockedEmailDomainsValidator implements ConstraintValidator<BlockedEmailDomains, String> {

    private String[] domains;
    private String[] endings;

    @Override
    public void initialize(BlockedEmailDomains constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        domains = constraintAnnotation.contains();
        endings = constraintAnnotation.endings();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return Stream.of(email)
                .filter(Objects::nonNull)
                .map(e -> e.substring(email.lastIndexOf('@') +1))
                .filter(emailDomain -> Arrays.stream(endings).anyMatch(emailDomain::endsWith) ||
                        Arrays.stream(domains).anyMatch(emailDomain::contains))
                .findFirst().isEmpty();
    }

}
