package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Role;
import com.nexgencarrental.nexGenCarRental.services.abstracts.RoleService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.role.AddRoleRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.role.UpdateRoleRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.role.GetRoleListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.role.GetRoleResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@AllArgsConstructor
public class RolesController {

    private final RoleService roleService;
    @GetMapping("/getAll")
    public List<GetRoleListResponse> getAll(){
        return roleService.getAll();
    }
    @GetMapping("/{id}")
    public GetRoleResponse getById(int id){
        return roleService.getById(id);
    }
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddRoleRequest addRoleRequest) {
        this.roleService.add(addRoleRequest, Role.class);
    }

    @PutMapping("/update")
    public void update(@RequestBody @Valid UpdateRoleRequest updateRoleRequest){
        roleService.update(updateRoleRequest, Role.class);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        roleService.delete(id);
    }
}
