package com.example.EventSourcing.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InactiveAccountCommand {
    @TargetAggregateIdentifier
    private String id; // -> ref to account aggregate
}
