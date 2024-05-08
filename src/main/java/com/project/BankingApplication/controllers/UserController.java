package com.project.BankingApplication.controllers;

import com.project.BankingApplication.dtos.LoginDTO;
import com.project.BankingApplication.dtos.RegisterDTO;
import com.project.BankingApplication.entities.User;
import com.project.BankingApplication.exceptions.InvalidException;
import com.project.BankingApplication.exceptions.NotFoundException;
import com.project.BankingApplication.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class UserController {
    private final IUserService userService;
    @PostMapping("/api/v1/register")
    public ResponseEntity<?> register (@RequestBody RegisterDTO registerDTO) throws NotFoundException {
        User user = userService.register(registerDTO);
        return ResponseEntity.ok(user);
    }
    @PostMapping("/api/v1/login")
    public ResponseEntity<?> login (@RequestBody LoginDTO loginDTO) throws NotFoundException, InvalidException {
        String token = userService.login(loginDTO);
        return ResponseEntity.ok(token);
    }
}
