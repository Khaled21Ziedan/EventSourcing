package com.example.EventSourcing.common.events;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountDepositedEvent {
    private String accountId;
    private int amount;
}
