package com.example.EventSourcing.common.events;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountWithdrawEvent {
    private String accountId;
    private int amount;
}
