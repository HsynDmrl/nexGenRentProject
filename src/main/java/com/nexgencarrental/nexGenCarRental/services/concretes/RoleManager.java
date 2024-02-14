package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Role;
import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.repositories.RoleRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.RoleService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.role.AddRoleRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.role.DeleteRoleRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.role.UpdateRoleRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.role.GetRoleListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.role.GetRoleResponse;
import org.springframework.stereotype.Service;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.ROLE_NOT_FOUND;

@Service
public class RoleManager extends BaseManager<Role, RoleRepository, GetRoleResponse, GetRoleListResponse,
        AddRoleRequest, UpdateRoleRequest> implements RoleService {
    public RoleManager(RoleRepository repository, ModelMapperService modelMapperService) {
        super(repository, modelMapperService, GetRoleResponse.class, GetRoleListResponse.class, Role.class,
                AddRoleRequest.class, UpdateRoleRequest.class);
    }

    @Override
    public void customDelete(DeleteRoleRequest deleteRoleRequest) {
        int roleId = deleteRoleRequest.getId();
        Role roleToDelete = repository.findById(roleId)
                .orElseThrow(() -> new DataNotFoundException(ROLE_NOT_FOUND));

        // Kullanıcıların rol alanlarını null olarak ayarla
        for (User user : roleToDelete.getUsers()) {
            user.setRole(null);
        }

        // Rolü sil
        repository.delete(roleToDelete);
    }
}