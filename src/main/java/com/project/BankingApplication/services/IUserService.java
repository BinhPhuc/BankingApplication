package com.project.BankingApplication.services;

import com.project.BankingApplication.dtos.LoginDTO;
import com.project.BankingApplication.dtos.RegisterDTO;
import com.project.BankingApplication.entities.User;
import com.project.BankingApplication.exceptions.InvalidException;
import com.project.BankingApplication.exceptions.NotFoundException;

import java.util.List;

public interface IUserService {
    com.project.BankingApplication.entities.User register(RegisterDTO registerDTO) throws NotFoundException;
    String login(LoginDTO loginDTO) throws NotFoundException, InvalidException;
    com.project.BankingApplication.entities.User getUserById(Long id) throws NotFoundException;

    List<User> getAllUser();
}
