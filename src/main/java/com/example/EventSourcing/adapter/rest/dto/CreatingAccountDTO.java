package com.example.EventSourcing.adapter.rest.dto;

import lombok.Data;

@Data
public class CreatingAccountDTO {
    private String name;
    private AccountType accountType;
    private int initialBalance;
}
