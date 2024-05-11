package com.project.BankingApplication.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.BankingApplication.entities.User;
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
    @JsonProperty("role_name")
    private String roleName;
    private Double balance;
    public static UserResponse fromUser(User user) {
        return UserResponse
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .roleId(user.getRole().getId())
                .roleName(user.getRole().getRoleName())
                .balance(user.getBalance())
                .build();
    }
}
