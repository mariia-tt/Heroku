package ucu.edu.apps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ucu.edu.apps.model.AppUser;
import ucu.edu.apps.repository.AppUserRepository;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public List<AppUser> getAppUsers() {
        return appUserRepository.findAll();
    }

    public AppUser createAppUser(AppUser appUser) {
        Optional<AppUser> userByEmail = Optional.ofNullable(appUserRepository.findUserByEmail(appUser.getEmail()));
        if (userByEmail.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }
        return appUserRepository.save(appUser);
    }

    public AppUser getUserByEmail(String email) {
        return appUserRepository.findUserByEmail(email);
    }
}