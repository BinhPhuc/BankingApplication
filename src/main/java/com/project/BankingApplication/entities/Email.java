package com.project.BankingApplication.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Email {
    private String recipient;
    @JsonProperty("msg_body")
    private String msgBody;
    private String subject;
}
