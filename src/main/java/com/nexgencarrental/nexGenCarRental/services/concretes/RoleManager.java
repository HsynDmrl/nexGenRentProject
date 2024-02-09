package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Role;
import com.nexgencarrental.nexGenCarRental.repositories.RoleRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.RoleService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.role.AddRoleRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.role.UpdateRoleRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.role.GetRoleListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.role.GetRoleResponse;
import org.springframework.stereotype.Service;

@Service
public class RoleManager extends BaseManager<Role, RoleRepository, GetRoleResponse, GetRoleListResponse,
        AddRoleRequest, UpdateRoleRequest> implements RoleService {
    public RoleManager(RoleRepository repository, ModelMapperService modelMapperService) {
        super(repository, modelMapperService, GetRoleResponse.class, GetRoleListResponse.class, Role.class,
                AddRoleRequest.class, UpdateRoleRequest.class);
    }
}