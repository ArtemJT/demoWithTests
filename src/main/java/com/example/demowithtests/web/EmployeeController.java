package com.example.demowithtests.web;

import com.example.demowithtests.dto.employee.EmployeeDto;
import com.example.demowithtests.dto.employee.EmployeeReadDto;
import com.example.demowithtests.dto.employee.EmployeeUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author Artem Kovalov on 30.06.2023
 */
public interface EmployeeController {

    //Операция сохранения юзера в базу данных
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "This is endpoint to add a new employee.",
            description = "Create request to add a new employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    EmployeeReadDto saveEmployee(@RequestBody @Valid EmployeeDto requestForSave);

    @PostMapping("/usersS")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "This is endpoint to add a new employee.",
            description = "Create request to add a new employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    void saveEmployee1(@RequestBody EmployeeDto employee);

    //Получение списка юзеров
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Returns all employees.",
            description = "Request to read all employees", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. pam pam param."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. No employees in data base")})
    List<EmployeeReadDto> getAllUsers();

    @GetMapping("/users/p")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Returns all employees and paginates.",
            description = "Request to read all employees and paginates", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. pam pam param."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. No employees in data base")})
    Page<EmployeeReadDto> getPage(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size);

    //Получения юзера по id
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint returned a employee by his id.",
            description = "Create request to read a employee by id", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. pam pam param."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    EmployeeReadDto getEmployeeById(@PathVariable Integer id);

    //Обновление юзера
    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Updates an employee's email by his id.",
            description = "Request to update an employee's email by id", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. pam pam param."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found.")})
    EmployeeDto refreshEmployee(@PathVariable("id") Integer id, @RequestBody @Valid EmployeeUpdateDto employee);

    //Удаление по id
    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes an employee by his id.",
            description = "Request to delete an employee by id", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "DELETED. pam pam param."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found.")})
    void removeEmployeeById(@PathVariable Integer id);

    //Удаление всех юзеров
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes all employees.",
            description = "Request to delete all employees", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "DELETED. pam pam param."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found.")})
    void removeAllUsers();


    @GetMapping("/users/country")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Returns all employees by country and paginates.",
            description = "Request to read all employees by country and paginates", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. pam pam param."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. No employees in data base")})
    Page<EmployeeReadDto> findByCountry(@RequestParam(required = false) String country,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "3") int size,
                                        @RequestParam(defaultValue = "") List<String> sortList,
                                        @RequestParam(defaultValue = "DESC") Sort.Direction sortOrder);

    @GetMapping("/users/c")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Returns all countries of all employees.",
            description = "Request to read all countries of all employees", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. pam pam param."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. No employees in data base")})
    List<String> getAllUsersC();

    @GetMapping("/users/s")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Returns a list of countries sorted by name.",
            description = "Request to read a list of countries sorted by name.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. pam pam param."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. No employees in data base")})
    List<String> getAllUsersSort();

    @GetMapping("/users/emails")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Returns a list of emails of all employees.",
            description = "Request to read all emails of all employees.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. pam pam param."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. No employees in data base")})
    Optional<String> getAllUsersSo();

    @GetMapping("/users/countryBy")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Returns all employees filtered by country.",
            description = "Request to read all employees filtered by country", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. pam pam param."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. No employees in data base")})
    List<EmployeeReadDto> getByCountry(@RequestParam(required = true) String country);


    // Получение юзеров у которых email равен null
    @GetMapping("/employee/emails")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Returns all employees whose email is null.",
            description = "Request to read all employees whose email is null", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. pam pam param."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. No employees in data base")})
    List<EmployeeReadDto> getNullEmails();

    // Получение юзеров у которых страна записана с маленькой буквы
    @GetMapping("/employee/countries")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Returns all employees whose country name starts with a lowercase letter.",
            description = "Request to read all employees whose country name starts with a lowercase letter",
            tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. pam pam param."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. No employees in data base")})
    List<EmployeeReadDto> getLowerCaseCountries();

    // Изменение всех записей стран с маленькой буквы на большую
    @PatchMapping("/employee/countries")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Updates country names of all employees to uppercase first letter.",
            description = "Request to update country names of all employees to uppercase first letter",
            tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. pam pam param."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. No employees in data base")})
    void updateLowerCaseCountriesToUpper();

}
