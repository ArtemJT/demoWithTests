package com.example.demowithtests.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

/**
 * @author Artem Kovalov on 25.06.2023
 */
public interface MetricController {

    @GetMapping("/stat")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Returns statistics of all endpoints calling.",
            description = "Request to read statistics of all endpoints calling", tags = {"Metric"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. pam pam param."),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. No endpoints callings yet")})
    Map<String, Integer> getStatistic();
}
