package com.example.demowithtests.web;

import com.example.demowithtests.dto.passport.PassportReadDto;
import com.example.demowithtests.util.PassportCancelingHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Artem Kovalov on 25.06.2023
 */
@RestController
@RequestMapping(value = "/api/history", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class HistoryController {

    @GetMapping("/passports")
    public Map<Integer, List<PassportReadDto>> getAllPassportHistory() {
        return PassportCancelingHistory.getPassportsCancelHistory();
    }

    @GetMapping("/passport")
    public List<PassportReadDto> getPassportHistoryByEmployee(@RequestParam Integer employeeId) {
        return PassportCancelingHistory.getPassportsCancelHistoryByEmployee(employeeId);
    }
}
