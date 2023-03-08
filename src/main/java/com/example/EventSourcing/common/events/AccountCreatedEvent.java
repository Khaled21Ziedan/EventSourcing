package com.example.EventSourcing.common.events;

import com.example.EventSourcing.adapter.rest.dto.AccountType;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountCreatedEvent {
    private String accountId;
    private String name;
    private int initialBalance;
    private AccountType accountType;
}
