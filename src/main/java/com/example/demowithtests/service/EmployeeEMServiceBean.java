package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.util.exception.EmployeeNotFoundException;
import com.example.demowithtests.util.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class EmployeeEMServiceBean implements EmployeeEMService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Employee createEM(Employee employee) {
        entityManager.persist(employee);
        entityManager.flush();
        return entityManager.find(Employee.class, employee.getId());
    }

    @Override
    public List<Employee> getAllEM() {
        return entityManager.createQuery("select e from Employee e where e.isDeleted = false", Employee.class)
                .getResultList();
    }

    @Override
    public Employee updateByIdEM(Employee employee) {
        Employee toMerge = Optional.ofNullable(entityManager.find(Employee.class, employee.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("id=" + employee.getId()));
        toMerge.setName(employee.getName());
        toMerge.setEmail(employee.getEmail());
        return entityManager.merge(toMerge);
    }

    @Override
    public List<String> getAllEmailsEM() {
        return entityManager.createQuery("select email from Employee e where e.isDeleted = false", String.class)
                .getResultList();
    }
}
