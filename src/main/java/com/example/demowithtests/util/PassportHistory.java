package com.example.demowithtests.util;

import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.dto.passport.PassportReadDto;
import com.example.demowithtests.util.mapper.PassportMapper;

import java.util.*;


/**
 * @author Artem Kovalov on 25.06.2023
 */
public record PassportHistory() {

    private static final PassportMapper passportMapper = PassportMapper.INSTANCE;
    private static final Map<Integer, List<PassportReadDto>> passports = new HashMap<>();

    public static void addPassportToHistory(Integer employeeId, Passport passport) {
        List<PassportReadDto> passportList = Optional.ofNullable(passports.get(employeeId))
                .orElse(new ArrayList<>());
        PassportReadDto readDto = passportMapper.toReadDto(passport);
        passportList.removeIf(pass -> readDto.id().equals(pass.id()));
        passportList.add(readDto);
        passports.put(employeeId, passportList);
    }

    public static Map<Integer, List<PassportReadDto>> getPassportsCancelHistory() {
        return passports;
    }

    public static List<PassportReadDto> getPassportsCancelHistoryByEmployee(Integer employeeId) {
        return passports.get(employeeId);
    }

}
