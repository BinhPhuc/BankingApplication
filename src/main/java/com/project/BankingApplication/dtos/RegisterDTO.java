package com.project.BankingApplication.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RegisterDTO {
    @JsonProperty("full_name")
    private String fullName;
    private String email;
    private String password;
    @JsonProperty("role_id")
    private Long roleId;
    private Double balance;
}
