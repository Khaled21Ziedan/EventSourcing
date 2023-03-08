package com.example.EventSourcing.adapter.rest;

import com.example.EventSourcing.commands.*;
import com.example.EventSourcing.adapter.rest.dto.CreatingAccountDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final CommandGateway commandGateway;

    public AccountController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }
    @PostMapping
    public String creatingAccount(@RequestBody CreatingAccountDTO accountDTO){
        CreateAccountCommand createAccountCommand =
                CreateAccountCommand.builder()
                .id(UUID.randomUUID().toString())
                .accountType(accountDTO.getAccountType())
                .name(accountDTO.getName())
                .build();
        return commandGateway.sendAndWait(createAccountCommand);
    }
    @PatchMapping("/{id}/deposit")
    public void depositingAccount(@RequestBody DepositAccountCommand depositAccountCommand){
        commandGateway.send(depositAccountCommand);
    }
    @PatchMapping("/{id}/withdraw")
    public void withdrawAccount(@RequestBody WithdrawAccountCommand withdrawAccountCommand){
        commandGateway.send(withdrawAccountCommand);
    }
    @PatchMapping("/{id}/inactive")
    public void inactiveAccount(@RequestBody InactiveAccountCommand InactiveAccountCommand){
        commandGateway.send(InactiveAccountCommand);
    }
    @PatchMapping("/{id}/active")
    public void activeAccount(@RequestBody ActiveAccountCommand activeAccountCommand){
        commandGateway.send(activeAccountCommand);
    }
}
