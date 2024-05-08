package com.project.BankingApplication.services;

import com.project.BankingApplication.dtos.LoginDTO;
import com.project.BankingApplication.dtos.RegisterDTO;
import com.project.BankingApplication.entities.User;
import com.project.BankingApplication.exceptions.InvalidException;
import com.project.BankingApplication.exceptions.NotFoundException;

public interface IUserService {
    User register(RegisterDTO registerDTO) throws NotFoundException;
    String login(LoginDTO loginDTO) throws NotFoundException, InvalidException;
}
