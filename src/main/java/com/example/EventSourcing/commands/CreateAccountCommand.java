package com.example.EventSourcing.commands;

import com.example.EventSourcing.adapter.rest.dto.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountCommand {
    @TargetAggregateIdentifier
    private String id; // -> ref to account aggregate
    private String name;
    private AccountType accountType;
    private int initialBalance;
}
