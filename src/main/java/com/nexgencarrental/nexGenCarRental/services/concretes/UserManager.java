package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.ConflictException;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.DataNotFoundException;
import com.nexgencarrental.nexGenCarRental.core.utilities.exceptions.UnauthorizedException;
import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Role;
import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.repositories.RoleRepository;
import com.nexgencarrental.nexGenCarRental.repositories.UserRepository;
import com.nexgencarrental.nexGenCarRental.services.abstracts.UserService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.auth.UpdatePasswordRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.AddUserRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.user.UpdateUserRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.GetUserEmailResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.GetUserListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.user.GetUserResponse;
import com.nexgencarrental.nexGenCarRental.services.rules.user.UserBusinessRulesService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.ConflictEnum.DATA_CONFLICT;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.DataNotFoundEnum.USER_NOT_FOUND;
import static com.nexgencarrental.nexGenCarRental.core.utilities.constants.UnauthorizedEnum.INVALID_CREDENTIALS;

@Service
public class UserManager extends BaseManager<User, UserRepository, GetUserResponse, GetUserListResponse,
        AddUserRequest, UpdateUserRequest> implements UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserBusinessRulesService userBusinessRulesService;
    private final PasswordEncoder passwordEncoder;

    public UserManager(ModelMapperService modelMapperService, UserRepository userRepository, RoleRepository roleRepository, UserBusinessRulesService userBusinessRulesService, PasswordEncoder passwordEncoder) {
        super(userRepository, modelMapperService, GetUserResponse.class, GetUserListResponse.class, User.class,
                AddUserRequest.class, UpdateUserRequest.class);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userBusinessRulesService = userBusinessRulesService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void add(User user) {
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new ConflictException(DATA_CONFLICT);
        }
    }

    @Override
    public void customUpdate(UpdateUserRequest updateUserRequest) {

        userBusinessRulesService.checkUpdateUserRules(updateUserRequest);

        User userToUpdate = userRepository.findById(updateUserRequest.getId()).orElse(null);
        if (userToUpdate == null) {
            throw new DataNotFoundException(USER_NOT_FOUND);
        } else {
            userToUpdate.setName(updateUserRequest.getName());
            userToUpdate.setSurname(updateUserRequest.getSurname());
            userToUpdate.setGsm(updateUserRequest.getGsm());
            userToUpdate.setNationalityId(updateUserRequest.getNationalityId());
            userRepository.save(userToUpdate);
        }
    }

    @Override
    public void customDelete(int id) {
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(USER_NOT_FOUND));

        userBusinessRulesService.checkDeleteUserRules(userToDelete);

        userRepository.delete(userToDelete);
    }

    public GetUserEmailResponse getByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        return modelMapperService.forResponse().map(user, GetUserEmailResponse.class);
    }

    @Override
    public void updateUserPassword(UpdatePasswordRequest updatePasswordRequest) {
        User user = userRepository.findByEmail(updatePasswordRequest.getEmail())
                .orElseThrow(() -> new DataNotFoundException(USER_NOT_FOUND));

        if (!passwordEncoder.matches(updatePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new UnauthorizedException(INVALID_CREDENTIALS);
        }

        user.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
        userRepository.save(user);
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
