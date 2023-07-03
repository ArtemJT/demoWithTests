package com.example.demowithtests.web;

import com.example.demowithtests.util.EndpointCallingCounter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Artem Kovalov on 25.06.2023
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Metric", description = "Metric API")
public class MetricControllerBean implements MetricController {

    @Override
    public Map<String, Integer> getStatistic() {
        return EndpointCallingCounter.getEndpointsStat();
    }
}
