package com.project.BankingApplication.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LoginDTO {
    private String email;
    private String password;
}
