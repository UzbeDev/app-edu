package it.city.edu.service;

import it.city.edu.entity.User;
import it.city.edu.entity.enums.RoleName;
import it.city.edu.payload.ApiResponse;
import it.city.edu.payload.ReqRegister;
import it.city.edu.repository.AuthRepository;
import it.city.edu.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {

    final
    AuthRepository authRepository;
    final
    ContactService contactService;
    final
    PasswordEncoder passwordEncoder;
    final
    RoleRepository roleRepository;

    public AuthService(AuthRepository authRepository, ContactService contactService, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.authRepository = authRepository;
        this.contactService = contactService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public ApiResponse register(ReqRegister reqRegister) {
        boolean existsUser = authRepository.existsByPassSerialEqualsIgnoreCaseAndPassNumber(reqRegister.getPassSerial(), reqRegister.getPassNumber());
        if (isAgeValid(reqRegister.getBirthDate())) {
            if (!existsUser) {
                if (reqRegister.getPassword().equals(reqRegister.getPrePassword())) {
                    User user = new User();
                    user.setFirstName(reqRegister.getFirstName());
                    user.setLastName(reqRegister.getLastName());
                    user.setMiddleName(reqRegister.getMiddleName());
                    user.setBirthDate(reqRegister.getBirthDate());
                    user.setContact(contactService.saveContact(reqRegister.getReqContact()));
                    user.setPassSerial(reqRegister.getPassSerial());
                    user.setPassNumber(reqRegister.getPassNumber());
                    user.setPhoneNumber(reqRegister.getPhoneNumber());
                    user.setPassword(passwordEncoder.encode(reqRegister.getPassword()));
                    user.setRoles(Collections.singleton(roleRepository.findByRoleName(RoleName.ROLE_USER)));
                    authRepository.save(user);
                    return new ApiResponse("Successfully registration", true);
                }
                return new ApiResponse("Password and PrePassword must be equals", false);
            }
            return new ApiResponse("Already exists user", false);
        }
        return new ApiResponse("Yoshiz kichik ekan", false);

    }

    public boolean isAgeValid(Date birthDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        Integer now = Integer.valueOf(simpleDateFormat.format(new Date()));
        Integer client = Integer.valueOf(simpleDateFormat.format(birthDate));
        return now - client > 5;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] split = username.split("@@");
        User user = authRepository.findByPassSerialAndPassNumber(split[0], split[1]).orElseThrow(() -> new UsernameNotFoundException("GetUser"));
        return user;
    }

    public User getUserByToken(UUID userId){
       return authRepository.findById(userId).orElseThrow(() -> new ResourceAccessException("GetUser"));
    }
}
