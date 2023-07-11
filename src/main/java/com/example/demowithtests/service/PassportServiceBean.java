package com.example.demowithtests.service;

import com.example.demowithtests.domain.Passport;
import com.example.demowithtests.domain.PassportPhoto;
import com.example.demowithtests.repository.PassportRepository;
import com.example.demowithtests.util.exception.PassportCanceledException;
import com.example.demowithtests.util.exception.PassportHandedException;
import com.example.demowithtests.util.exception.ResourceNotFoundException;
import com.example.demowithtests.util.mapper.PassportMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class PassportServiceBean implements PassportService {

    private final PassportRepository passportRepository;
    private final PassportMapper passportMapper;

    @Override
    public Passport create(Passport passport) {
        return passportRepository.save(passport);
    }

    @Override
    public List<Passport> getAll() {
        return passportRepository.findAll();
    }

    @Override
    public Passport getById(Integer id) {
        return passportRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<Passport> getAllHanded() {
        return passportRepository.findAllByIsHandedTrue().orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<Passport> getAllNotHanded() {
        return passportRepository.findAllByIsHandedFalse().orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Passport updatePhoto(Integer passportId, PassportPhoto photo) {
        Passport passport = getById(passportId);
        if (passport.getPreviousOwner() != null) {
            throw new PassportCanceledException();
        }
        if (!passport.getIsHanded()) {
            throw new RuntimeException("Passport didn't hand yet");
        }
        PassportPhoto passportPhoto = passport.getPhoto();
        passportPhoto.setPhotoLink(photo.getPhotoLink());
        return passportRepository.save(passport);
    }

    @Override
    public Passport handPassport(Integer passportId, PassportPhoto photo) throws PassportHandedException {
        Passport passport = getById(passportId);
        if (passport.getIsHanded()) {
            throw new PassportHandedException();
        }
        photo.setPassport(passport);
        passport.setPhoto(photo);
        passport.setIsHanded(Boolean.TRUE);
        return passportRepository.save(passport);
    }

    @Override
    public Passport cancelPassport(Passport passport, Integer employeeId) {
        if (passport.getPreviousOwner() != null) {
            throw new PassportCanceledException();
        }
        passport.setPreviousOwner(employeeId);
        return passportRepository.save(passport);
    }
}
