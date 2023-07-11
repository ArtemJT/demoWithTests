package com.example.demowithtests.service;

import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.domain.PassportPhoto;

import java.util.List;

public interface PassportService {

    Passport create(Passport passport);

    List<Passport> getAll();

    Passport getById(Integer id);

    List<Passport> getAllHanded();

    List<Passport> getAllNotHanded();

    Passport updatePhoto(Integer passportId, PassportPhoto photo);

    Passport handPassport(Integer id, PassportPhoto photo);

    Passport cancelPassport(Passport passport, Integer employeeId);
}
