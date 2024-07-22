package com.example.demo.admin;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.vendingmachine.VendingMachineRequest;
import com.example.demo.vendingmachine.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private VendingMachineService vendingMachineService;

    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping("/dashboard")
    public ModelAndView showAdminDashboard() {
        ModelAndView mav = new ModelAndView("admin_dashboard");
        List<AppUser> users = appUserRepository.findAll();
        mav.addObject("users", users);
        return mav;
    }

    @PostMapping("/create-vending-machine")
    public ResponseEntity<?> createVendingMachine(@RequestParam Long userId, @RequestBody VendingMachineRequest request) {
        AppUser user = appUserRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User not found"));
        vendingMachineService.createVendingMachineForUser(user, request);
        return ResponseEntity.ok().build();
    }
}
