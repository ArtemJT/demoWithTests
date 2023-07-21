package com.example.demowithtests.util.annotations.aspect;

import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.util.PassportCancelingHistory;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class HistoryPassportsCancelingAspect {

    @Pointcut("execution(public * com.example.demowithtests.service.PassportService.cancelPassport(..))")
    public void callAtPassportServiceCancelPassportMethod() {
    }

    @After("callAtPassportServiceCancelPassportMethod()")
    public void callAfter(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        var passport = (Passport) args[0];
        var employeeId = (Integer) args[1];
        PassportCancelingHistory.addCanceledPassport(employeeId, passport);
    }
}
