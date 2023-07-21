package com.example.demowithtests.web;

import com.example.demowithtests.dto.passport.PassportReadDto;
import com.example.demowithtests.util.PassportHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
        return PassportHistory.getPassportsCancelHistory();
    }

    @GetMapping("/passport")
    public List<PassportReadDto> getPassportHistoryByEmployee(@ModelAttribute("empId") Integer empId) {
        return PassportHistory.getPassportsCancelHistoryByEmployee(empId);
    }
}
