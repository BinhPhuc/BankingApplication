package com.project.BankingApplication.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserResponse {
    private Long id;
    private String email;
    @JsonProperty("role_id")
    private Long roleId;
}
