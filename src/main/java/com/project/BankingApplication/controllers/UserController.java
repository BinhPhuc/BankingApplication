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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class UserController {
    private final IUserService userService;
    private final EmailService emailService;
    @PostMapping("/api/v1/user/register")
    public ResponseEntity<?> register (@RequestBody RegisterDTO registerDTO) throws NotFoundException {
        User user = userService.register(registerDTO);
        return ResponseEntity.ok(UserResponse
                .builder()
                .email(user.getEmail())
                .id(user.getId())
                .roleId(user.getRole().getId())
                .build());
    }
    @PostMapping("/api/v1/user/login")
    public ResponseEntity<?> login (@RequestBody LoginDTO loginDTO) throws NotFoundException, InvalidException {
        String token = userService.login(loginDTO);
        return ResponseEntity.ok(token);
    }
    @PostMapping("api/v1/user/send-mail")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> sendMail(@RequestBody Email email) {
        EmailResponse emailResponse = emailService.sendSimpleEmail(email);
        return ResponseEntity.ok(emailResponse);
    }
}
