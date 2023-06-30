package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.employee.EmployeeDto;
import com.example.demowithtests.dto.employee.EmployeeReadDto;
import com.example.demowithtests.dto.employee.EmployeeUpdateDto;
import com.example.demowithtests.service.EmployeeService;
import com.example.demowithtests.util.mapper.EmployeeMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Employee", description = "Employee API")
public class EmployeeControllerBean implements EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    //Операция сохранения юзера в базу данных
    @Override
    public EmployeeReadDto saveEmployee(@RequestBody @Valid EmployeeDto requestForSave) {

        var employee = employeeMapper.toEntity(requestForSave);
        return employeeMapper.toReadDto(employeeService.create(employee));
    }

    @Override
    public void saveEmployee1(@RequestBody EmployeeDto employee) {

        employeeService.create(employeeMapper.toEntity(employee));

    }

    //Получение списка юзеров
    @Override
    public List<EmployeeReadDto> getAllUsers() {
        return employeeMapper.listToReadDto(employeeService.getAll());
    }

    @Override
    public Page<EmployeeReadDto> getPage(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "5") int size
    ) {
        Pageable paging = PageRequest.of(page, size);
        return employeeService.getAllWithPagination(paging).map(employeeMapper::toReadDto);
    }

    //Получения юзера по id
    @Override
    public EmployeeReadDto getEmployeeById(@PathVariable Integer id) {
        log.debug("getEmployeeById() EmployeeController - start: id = {}", id);
        var employee = employeeService.getById(id);
        log.debug("getById() EmployeeController - to dto start: id = {}", id);
        var dto = employeeMapper.toReadDto(employee);
        log.debug("getEmployeeById() EmployeeController - end: name = {}", dto.name());
        return dto;
    }

    //Обновление юзера
    @Override
    public EmployeeDto refreshEmployee(@PathVariable("id") Integer id, @RequestBody @Valid EmployeeUpdateDto employee) {
        Employee updateDtoToEntity = employeeMapper.updateDtoToEntity(employee);
        return employeeMapper.toDto(employeeService.updateById(id, updateDtoToEntity));
    }

    //Удаление по id
    @Override
    public void removeEmployeeById(@PathVariable Integer id) {
        employeeService.removeById(id);
    }

    //Удаление всех юзеров
    @Override
    public void removeAllUsers() {
        employeeService.removeAll();
    }

    @Override
    public Page<EmployeeReadDto> findByCountry(@RequestParam(required = false) String country,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "3") int size,
                                               @RequestParam(defaultValue = "") List<String> sortList,
                                               @RequestParam(defaultValue = "DESC") Sort.Direction sortOrder) {
        //Pageable paging = PageRequest.of(page, size);
        //Pageable paging = PageRequest.of(page, size, Sort.by("name").ascending());
        return employeeService.findByCountryContaining(country, page, size, sortList, sortOrder.toString())
                .map(employeeMapper::toReadDto);
    }

    @Override
    public List<String> getAllUsersC() {
        return employeeService.getAllEmployeeCountry();
    }

    @Override
    public List<String> getAllUsersSort() {
        return employeeService.getSortCountry();
    }

    @Override
    public Optional<String> getAllUsersSo() {
        return employeeService.findEmails();
    }

    @Override
    public List<EmployeeReadDto> getByCountry(@RequestParam(required = true) String country) {
        return employeeMapper.listToReadDto(employeeService.filterByCountry(country));
    }

    // Получение юзеров у которых имейл равен null
    @Override
    public List<EmployeeReadDto> getNullEmails() {
        return employeeMapper.listToReadDto(employeeService.filterNullEmails());
    }

    // Получение юзеров у которых страна записана с маленькой буквы
    @Override
    public List<EmployeeReadDto> getLowerCaseCountries() {
        return employeeMapper.listToReadDto(employeeService.filterLowerCaseCountries());
    }

    // Изменение всех записей стран с маленькой буквы на большую
    @Override
    public void updateLowerCaseCountriesToUpper() {
        employeeService.updateLowerCaseCountriesToUpperCase();
    }
}
