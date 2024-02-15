package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.services.abstracts.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PutMapping("/update/{userId}/{newRoleId}") // UPDATE_ADMIN sabitini doğrudan buraya entegre ediyoruz
    @ResponseStatus(HttpStatus.OK)
    public void updateUserRole(@PathVariable int userId, @PathVariable int newRoleId) {
        adminService.updateUserRole(userId, newRoleId);
    }
}
