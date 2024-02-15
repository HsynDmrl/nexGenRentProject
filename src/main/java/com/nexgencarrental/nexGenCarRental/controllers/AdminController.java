package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApiPathConstants;
import com.nexgencarrental.nexGenCarRental.services.abstracts.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPathConstants.ADMIN_BASE_URL)
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PutMapping(ApiPathConstants.UPDATE_ADMIN)
    @ResponseStatus(HttpStatus.OK)
    public void updateUserRole(@PathVariable int userId, @PathVariable int newRoleId) {
        adminService.updateUserRole(userId, newRoleId);
    }
}
