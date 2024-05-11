package com.project.BankingApplication.controllers;

import com.project.BankingApplication.dtos.LoginDTO;
import com.project.BankingApplication.dtos.RegisterDTO;
import com.project.BankingApplication.entities.Email;
import com.project.BankingApplication.entities.User;
import com.project.BankingApplication.exceptions.InvalidException;
import com.project.BankingApplication.exceptions.NotFoundException;
import com.project.BankingApplication.responses.EmailResponse;
import com.project.BankingApplication.responses.UserResponse;
import com.project.BankingApplication.services.EmailService;
import com.project.BankingApplication.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor

public class UserController {
    private final IUserService userService;
    private final EmailService emailService;
    @PostMapping("/api/v1/user/register")
    public ResponseEntity<?> register (@RequestBody RegisterDTO registerDTO) throws NotFoundException {
        User user = userService.register(registerDTO);
        return ResponseEntity.ok(UserResponse.fromUser(user));
    }
    @PostMapping("/api/v1/user/login")
    public ResponseEntity<?> login (@RequestBody LoginDTO loginDTO) throws NotFoundException, InvalidException {
        String token = userService.login(loginDTO);
        return ResponseEntity.ok(token);
    }
    @PostMapping("/api/v1/user/send-mail")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> sendMail(@RequestBody Email email) {
        EmailResponse emailResponse = emailService.sendSimpleEmail(email);
        return ResponseEntity.ok(emailResponse);
    }
    @GetMapping("/api/v1/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) throws NotFoundException {
        com.project.BankingApplication.entities.User user = userService.getUserById(id);
        return ResponseEntity.ok(UserResponse.fromUser(user));
    }
    @GetMapping("/api/v1/user/get-all-user")
    public ResponseEntity<?> getAllUser() {
        List<User> userList = userService.getAllUser();
        return ResponseEntity.ok(userList.stream().map(UserResponse::fromUser));
    }
}
