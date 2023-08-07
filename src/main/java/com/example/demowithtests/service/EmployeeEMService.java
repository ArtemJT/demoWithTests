package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;

import java.util.List;

public interface EmployeeEMService {

    Employee createEM(Employee employee);

    List<Employee> getAllEM();

    Employee updateByIdEM(Employee employee);

    List<String> getAllEmailsEM();
}
