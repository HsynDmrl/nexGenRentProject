package com.nexgencarrental.nexGenCarRental.services.abstracts;

import com.nexgencarrental.nexGenCarRental.entities.concretes.Role;
import com.nexgencarrental.nexGenCarRental.repositories.RoleRepository;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.role.AddRoleRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.role.UpdateRoleRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.role.GetRoleListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.role.GetRoleResponse;

public interface RoleService extends BaseService<Role, RoleRepository, GetRoleResponse,
        GetRoleListResponse, AddRoleRequest, UpdateRoleRequest> {
    void customDelete(int id);
}
