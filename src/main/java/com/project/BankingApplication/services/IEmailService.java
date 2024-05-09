package com.project.BankingApplication.services;

import com.project.BankingApplication.entities.Email;
import com.project.BankingApplication.responses.EmailResponse;

public interface IEmailService {
    EmailResponse sendSimpleEmail(Email email);
}
