package com.example.demo.vendingmachine;

import com.example.demo.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendingMachineRepository extends JpaRepository<VendingMachine, Long> {
    List<VendingMachine> findByOwner(AppUser user);
}
