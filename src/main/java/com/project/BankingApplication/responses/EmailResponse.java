package com.project.BankingApplication.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class EmailResponse {
    private String sender;
    private String recipient;
    private String subject;
    @JsonProperty("msg_body")
    private String msgBody;

}
