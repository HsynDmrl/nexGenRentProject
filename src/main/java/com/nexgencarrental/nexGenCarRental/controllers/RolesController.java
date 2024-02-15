package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApiPathConstants;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Role;
import com.nexgencarrental.nexGenCarRental.services.abstracts.RoleService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.role.AddRoleRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.role.DeleteRoleRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.role.UpdateRoleRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.role.GetRoleListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.role.GetRoleResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPathConstants.ROLES_BASE_URL)
@AllArgsConstructor
public class RolesController {

    private final RoleService roleService;

    @GetMapping(ApiPathConstants.GET_ALL_ROLES)
    @ResponseStatus(HttpStatus.OK)
    public List<GetRoleListResponse> getAll() {
        return roleService.getAll();
    }

    @GetMapping(ApiPathConstants.GET_ROLE_BY_ID)
    @ResponseStatus(HttpStatus.OK)
    public GetRoleResponse getById(int id) {
        return roleService.getById(id);
    }

    @PostMapping(ApiPathConstants.ADD_ROLE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddRoleRequest addRoleRequest) {
        this.roleService.add(addRoleRequest, Role.class);
    }

    @PutMapping(ApiPathConstants.UPDATE_ROLE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Valid UpdateRoleRequest updateRoleRequest) {
        roleService.update(updateRoleRequest, Role.class);
    }

    @DeleteMapping(ApiPathConstants.DELETE_ROLE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody @Valid DeleteRoleRequest deleteRoleRequest) {
        roleService.customDelete(deleteRoleRequest);
    }
}
