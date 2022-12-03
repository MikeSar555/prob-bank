package com.example.probbank.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Table("gate_pay")
public class GatePay {

    @Id
    private int id;
    private LocalDateTime eventDate;
    private String imId;
    private String receiveId;
    private String request;
    private String response;
    private String extId;
    private Integer stat;
    private Integer bankId;
    private String termId;
    private String freeSum;
    private String params;
    private Integer cReceivetype;
    private String clientFio;
    private String clientAccNum;
    private Double amount;

}

