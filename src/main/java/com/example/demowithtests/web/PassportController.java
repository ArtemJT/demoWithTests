package com.example.demowithtests.web;

import com.example.demowithtests.dto.passport.*;
import com.example.demowithtests.service.PassportService;
import com.example.demowithtests.util.mapper.PassportMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Passport", description = "Passport API")
public class PassportController {

    private final PassportService passportService;
    private final PassportMapper passportMapper;

    @PostMapping("/users/passport")
    @ResponseStatus(HttpStatus.CREATED)
    public PassportDto createPassport(@RequestBody PassportDto passportDto) {
        var passport = passportMapper.toEntity(passportDto);
        return passportMapper.toDto(passportService.create(passport));
    }

    @GetMapping("/users/passports")
    @ResponseStatus(HttpStatus.OK)
    public List<PassportReadDto> getAllPassports() {
        return passportMapper.listToReadDto(passportService.getAll());
    }

    @GetMapping("/users/passport")
    @ResponseStatus(HttpStatus.OK)
    public PassportReadDto getPassport(@ModelAttribute("passId") Integer passId) {
        return passportMapper.toReadDto(passportService.getById(passId));
    }

    @PatchMapping("/users/passport")
    @ResponseStatus(HttpStatus.OK)
    public PassportDto updatePhoto(@ModelAttribute("passId") Integer passId,
                                   @ModelAttribute("photoDto") PassportPhotoDto photoDto) {
        var photo = passportMapper.toEntity(photoDto);
        return passportMapper.toDto(passportService.updatePhoto(passId, photo));
    }
}
