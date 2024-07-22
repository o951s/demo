package com.example.demo.vendingmachine;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vending-machines")
public class VendingMachineController {

    private final VendingMachineService vendingMachineService;

    @Autowired
    public VendingMachineController(VendingMachineService vendingMachineService) {
        this.vendingMachineService = vendingMachineService;
    }

    @PostMapping
    public ResponseEntity<VendingMachine> createVendingMachine(@RequestBody VendingMachineRequest request) {
        VendingMachine vendingMachine = vendingMachineService.createVendingMachine(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(vendingMachine);
    }

    @GetMapping("/user")
    public ResponseEntity<List<VendingMachine>> getUserVendingMachines(@AuthenticationPrincipal AppUser user) {
        List<VendingMachine> vendingMachines = vendingMachineService.getVendingMachinesByUser(user);
        return ResponseEntity.ok(vendingMachines);
    }

    @GetMapping("/dashboard")
    public ModelAndView showUserVendingMachinesPage(@AuthenticationPrincipal AppUser user) {
        ModelAndView modelAndView = new ModelAndView("user_vending_machines");
        List<VendingMachine> vendingMachines = vendingMachineService.getVendingMachinesByUser(user);
        modelAndView.addObject("vendingMachines", vendingMachines);
        return modelAndView;
    }

}
