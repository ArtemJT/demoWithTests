package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.employee.EmployeeDto;
import com.example.demowithtests.dto.employee.EmployeeReadDto;
import com.example.demowithtests.dto.employee.EmployeeUpdateDto;
import com.example.demowithtests.service.EmployeeEMService;
import com.example.demowithtests.util.mapper.EmployeeMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "EmployeeEM", description = "Employee API")
public class EmployeeEMController {

    private final EmployeeEMService employeeEMService;
    private final EmployeeMapper employeeMapper;

    //Операция сохранения юзера в базу данных
    @PostMapping("/usersEM")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "This is endpoint to add a new employee.", description = "Create request to add a new employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public EmployeeReadDto saveEmployeeEM(@RequestBody @Valid EmployeeDto requestForSave) {

        var employee = employeeMapper.toEntity(requestForSave);
        return employeeMapper.toReadDto(employeeEMService.createEM(employee));
    }

    //Получение списка юзеров
    @GetMapping("/usersEM")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeReadDto> getAllUsersEM() {
        return employeeMapper.listToReadDto(employeeEMService.getAllEM());
    }

    //Обновление юзера
    @PutMapping("/usersEM")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto refreshEmployeeEM(@RequestBody @Valid EmployeeUpdateDto employee) {
        Employee updateDtoToEntity = employeeMapper.updateDtoToEntity(employee);
        return employeeMapper.toDto(employeeEMService.updateByIdEM(updateDtoToEntity));
    }

    @GetMapping("/usersEM/emails")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllUsersEmailsEM() {
        return employeeEMService.getAllEmailsEM();
    }

}
