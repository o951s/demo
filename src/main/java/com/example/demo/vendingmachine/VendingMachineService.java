package com.example.demo.vendingmachine;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendingMachineService {

    private final VendingMachineRepository vendingMachineRepository;
    private final AppUserRepository appUserRepository;

    @Autowired
    public VendingMachineService(VendingMachineRepository vendingMachineRepository, AppUserRepository appUserRepository) {
        this.vendingMachineRepository = vendingMachineRepository;
        this.appUserRepository = appUserRepository;
    }

    public VendingMachine createVendingMachine(VendingMachineRequest request) {
        AppUser owner = appUserRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.setName(request.getMachineName());
        vendingMachine.setLocation(request.getLocation());
        vendingMachine.setCapacity(request.getCapacity());
        vendingMachine.setOwner(owner);

        return vendingMachineRepository.save(vendingMachine);
    }

    public VendingMachine createVendingMachineForUser(AppUser user, VendingMachineRequest request) {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.setName(request.getMachineName());
        vendingMachine.setLocation(request.getLocation());
        vendingMachine.setCapacity(request.getCapacity());
        vendingMachine.setOwner(user);

        return vendingMachineRepository.save(vendingMachine);
    }

    public List<VendingMachine> getVendingMachinesByUser(AppUser user) {
        return vendingMachineRepository.findByOwner(user);
    }
}
