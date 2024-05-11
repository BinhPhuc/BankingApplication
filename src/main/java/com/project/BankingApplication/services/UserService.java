package com.project.BankingApplication.services;

import com.project.BankingApplication.config.JwtTokenService;
import com.project.BankingApplication.dtos.LoginDTO;
import com.project.BankingApplication.dtos.RegisterDTO;
import com.project.BankingApplication.entities.Role;
import com.project.BankingApplication.entities.User;
import com.project.BankingApplication.exceptions.InvalidException;
import com.project.BankingApplication.exceptions.NotFoundException;
import com.project.BankingApplication.repositories.RoleRepositoy;
import com.project.BankingApplication.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepositoy roleRepositoy;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public com.project.BankingApplication.entities.User register(RegisterDTO registerDTO) throws NotFoundException {
        com.project.BankingApplication.entities.User user = com.project.BankingApplication.entities.User
                .builder()
                .email(registerDTO.getEmail())
                .fullName(registerDTO.getFullName())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .build();
        Role role = roleRepositoy.findById(registerDTO.getRoleId()).orElseThrow(
                () -> new NotFoundException("Cannot find role")
        );
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public String login(LoginDTO loginDTO) throws NotFoundException, InvalidException {
        Optional<com.project.BankingApplication.entities.User> existingUser = userRepository.findUserByEmail(loginDTO.getEmail());
        if(existingUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        com.project.BankingApplication.entities.User user = existingUser.get();
        if(!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new InvalidException("Password are not matching");
        }
        UsernamePasswordAuthenticationToken uPassToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail(), loginDTO.getPassword()
        );
        authenticationManager.authenticate(uPassToken);
        return jwtService.generateToken(user);
    }

    @Override
    public com.project.BankingApplication.entities.User getUserById(Long id) throws NotFoundException {
        Optional<com.project.BankingApplication.entities.User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()) {
            throw new NotFoundException("User not found with id = " + id);
        }
        return optionalUser.get();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
