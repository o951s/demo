package com.example.demo.admin;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.appuser.AppUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AdminUserInitializer(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        String adminEmail = "vacnelan70@gmail.com";
        if (appUserRepository.findByEmail(adminEmail).isEmpty()) {
            AppUser admin = new AppUser();
            admin.setFirstName("Yasin");
            admin.setLastName("Bayrampınar");
            admin.setEmail(adminEmail);
            admin.setPassword(bCryptPasswordEncoder.encode("12345"));
            admin.setAppUserRole(AppUserRole.ADMIN);
            admin.setEnabled(true);
            admin.setLocked(false);
            appUserRepository.save(admin);
        }
    }
}
