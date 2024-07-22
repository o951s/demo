package com.example.demo.userdetails;

import com.example.demo.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findByAppUser(AppUser appUser);
}
