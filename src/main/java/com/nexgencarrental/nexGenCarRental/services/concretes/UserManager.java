package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.ConflictException;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.ErrorConstantException;
import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Role;
import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.repositories.RoleRepository;
import com.nexgencarrental.nexGenCarRental.repositories.UserRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.UserService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.AddUserRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.UpdateUserRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.GetUserListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.GetUserResponse;
import com.nexgencarrental.nexGenCarRental.services.rules.user.UserBusinessRulesService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants.UNEXPECTED_ERROR_GETTING_USER_BY_EMAIL;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictEnum.DATA_CONFLICT;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.USER_NOT_FOUND;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ErrorEnum.ERROR_GETTING_USER_BY_EMAIL;

@Service
public class UserManager extends BaseManager<User, UserRepository, GetUserResponse, GetUserListResponse,
        AddUserRequest, UpdateUserRequest> implements UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserBusinessRulesService userBusinessRulesService;

    public UserManager(UserRepository repository, ModelMapperService modelMapperService, UserRepository userRepository, RoleRepository roleRepository, UserBusinessRulesService userBusinessRulesService) {
        super(repository, modelMapperService, GetUserResponse.class, GetUserListResponse.class, User.class,
                AddUserRequest.class, UpdateUserRequest.class);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userBusinessRulesService = userBusinessRulesService;
    }

    @Override
    public void add(User user) {
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new ConflictException(DATA_CONFLICT);}
    }

    @Override
    public void customUpdate(UpdateUserRequest updateUserRequest) {

        userBusinessRulesService.existsByNationalityId(updateUserRequest.getNationalityId());
        User userToUpdate = userRepository.findById(updateUserRequest.getId()).orElse(null);

        if (userToUpdate == null) {
            throw new DataNotFoundException(USER_NOT_FOUND);
        } else {
            userToUpdate.setName(updateUserRequest.getName());
            userToUpdate.setSurname(updateUserRequest.getSurname());
            userToUpdate.setEmail(updateUserRequest.getEmail());
            userToUpdate.setGsm(userToUpdate.getGsm());
            userToUpdate.setRole(userToUpdate.getRole());
            userToUpdate.setNationalityId(updateUserRequest.getNationalityId());
            userRepository.save(userToUpdate);
        }
    }

    public GetUserResponse getByEmail(String email) {
            User user = userRepository.findByEmail(email).orElse(null);
            return modelMapperService.forResponse().map(user, GetUserResponse.class);
    }

    @Override
    public Optional<User> findByEmail(String email) {
            return userRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
            return userRepository.existsByName(email);
    }

    @Override
    public Optional<Role> findRoleById(int roleId) {
            return roleRepository.findById(roleId);
    }

    @Override
    public Optional<User> findById(int userId) {
            return userRepository.findById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(ApplicationConstants.NO_USER_FOUND));
    }
}
