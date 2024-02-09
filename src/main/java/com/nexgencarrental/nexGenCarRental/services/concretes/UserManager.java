package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants;
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
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants.UNEXPECTED_ERROR_GETTING_USER_BY_EMAIL;
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
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ApplicationConstants.ERROR_ADDING_USER, ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ApplicationConstants.UNEXPECTED_ERROR_ADDING_USER, ex);
        }
    }

    @Override
    public void customUpdate(UpdateUserRequest updateUserRequest) {
        try {
            userBusinessRulesService.existsByNationalityId(updateUserRequest.getNationalityId()); // NationalityId kontrolü
            update(updateUserRequest, User.class);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "", ex);
        }
    }

    public GetUserResponse getByEmail(String email) {
        try {
            User user = userRepository.findByEmail(email).orElse(null);
            return modelMapperService.forResponse().map(user, GetUserResponse.class);
        } catch (DataAccessException ex) {
            throw new ErrorConstantException(ERROR_GETTING_USER_BY_EMAIL);
        } catch (Exception ex) {
            throw new RuntimeException(UNEXPECTED_ERROR_GETTING_USER_BY_EMAIL);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return userRepository.findByEmail(email);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ApplicationConstants.ERROR_FINDING_USER_BY_EMAIL, ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ApplicationConstants.UNEXPECTED_ERROR_FINDING_USER_BY_EMAIL, ex);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        try {
            return userRepository.existsByName(email);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ApplicationConstants.ERROR_CHECKING_USER_EXISTS_BY_EMAIL, ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ApplicationConstants.UNEXPECTED_ERROR_CHECKING_USER_EXISTS_BY_EMAIL, ex);
        }
    }

    @Override
    public Optional<Role> findRoleById(int roleId) {
        try {
            return roleRepository.findById(roleId);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ApplicationConstants.ERROR_FINDING_ROLE_BY_ID, ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ApplicationConstants.UNEXPECTED_ERROR_FINDING_ROLE_BY_ID, ex);
        }
    }

    @Override
    public Optional<User> findById(int userId) {
        try {
            return userRepository.findById(userId);
        } catch (DataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ApplicationConstants.ERROR_FINDING_USER_BY_ID, ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ApplicationConstants.UNEXPECTED_ERROR_FINDING_USER_BY_ID, ex);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(ApplicationConstants.NO_USER_FOUND));
        } catch (UsernameNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ApplicationConstants.ERROR_LOADING_USER_BY_USERNAME, ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ApplicationConstants.UNEXPECTED_ERROR_LOADING_USER_BY_USERNAME, ex);
        }
    }
}
