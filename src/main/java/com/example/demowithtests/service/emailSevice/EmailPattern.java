package com.example.demowithtests.service.emailSevice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Artem Kovalov on 23.06.2023
 */
@RequiredArgsConstructor
@Getter
public enum EmailPattern {

    UPDATE("Need to update your information",
            """
                    \s
                    \s
                    The expiration date of your information is coming up soon. \s
                    Please. Don't delay in updating it. \s
                    \s
                    Best regards,\s
                    Ukrainian Info Service.""");

    private final String subject;
    private final String body;
}
