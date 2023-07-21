package com.example.demowithtests.util.annotations.aspect;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.util.PassportHistory;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class HistoryPassportsAspect {

    @Pointcut("execution(public * com.example.demowithtests.service.PassportService.cancelPassport(..))")
    public void callAtPassportServiceCancelPassportMethod() {
    }

    @Pointcut("execution(public * com.example.demowithtests.service.EmployeeServiceBean.handPassportToEmployee(..))")
    public void callAtPassportServiceHandPassportMethod() {
    }

    @After("callAtPassportServiceCancelPassportMethod()")
    public void callAfterCancel(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        var passport = (Passport) args[0];
        var employeeId = (Integer) args[1];
        PassportHistory.addPassportToHistory(employeeId, passport);
    }

    @AfterReturning(value = "callAtPassportServiceHandPassportMethod()", returning = "retVal")
    public void callAfterHand(Object retVal) {
        var employee = (Employee) retVal;
        PassportHistory.addPassportToHistory(employee.getId(), employee.getPassport());
    }
}
