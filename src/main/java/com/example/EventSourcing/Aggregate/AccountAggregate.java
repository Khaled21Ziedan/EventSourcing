package com.example.EventSourcing.Aggregate;

import com.example.EventSourcing.adapter.rest.dto.AccountType;
import com.example.EventSourcing.commands.*;
import com.example.EventSourcing.common.events.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.*;

@Aggregate
public class AccountAggregate {
    @AggregateIdentifier
    private String id;
    private AccountType accountType;
    private String name;
    private int balance;
    private boolean active = true;

    public AccountAggregate() {
    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand){
        AccountCreatedEvent accountCreatedEvent = AccountCreatedEvent.builder()
                .accountId(createAccountCommand.getId())
                .name(createAccountCommand.getName())
                .accountType(createAccountCommand.getAccountType())
                .initialBalance(createAccountCommand.getInitialBalance())
                .build();
        this.id = createAccountCommand.getId();
        apply(accountCreatedEvent);
    }
    @CommandHandler
    public void AccountDeposit(DepositAccountCommand depositAccountCommand){
        if(!active){
            throw new RuntimeException("cannot deposit");
        }
        AccountDepositedEvent accountDepositedEvent = AccountDepositedEvent.builder()
                .amount(depositAccountCommand.getAmount())
                .accountId(depositAccountCommand.getId())
                .build();
        apply(accountDepositedEvent);

    }
    @CommandHandler
        public void AccountWithdraw(WithdrawAccountCommand withdrawAccountCommand){
        if(!active){
            throw new RuntimeException("cannot withdraw with inactive account");
        }
        AccountWithdrawEvent accountwithdrawEvent = AccountWithdrawEvent.builder()
                .amount(withdrawAccountCommand.getAmount())
                .accountId(withdrawAccountCommand.getId())
                .build();
        apply(accountwithdrawEvent);

    }
    @CommandHandler
    public void AccountInactive(InactiveAccountCommand InactiveAccountCommand){
        if(active){
            return;
        }
        AccountInActiveEvent accountInactiveEvent = AccountInActiveEvent.builder()
                .accountId(InactiveAccountCommand.getId())
                .build();
        apply(accountInactiveEvent);

    }
    @CommandHandler
    public void accountActive(ActiveAccountCommand activeAccountCommand){
        if(active)
            return;
        AccountActiveEvent accountActiveEvent = AccountActiveEvent.builder()
                .accountId(activeAccountCommand.getId())
                .build();
        apply(accountActiveEvent);
    }
    @EventSourcingHandler
    public void accountCreated(AccountCreatedEvent accountCreatedEvent){
        System.out.println(accountCreatedEvent);
        this.id = accountCreatedEvent.getAccountId();
        this.balance = accountCreatedEvent.getInitialBalance();
        this.name = accountCreatedEvent.getName();
        this.accountType = accountCreatedEvent.getAccountType();
    }
    @EventSourcingHandler
    public void accountDeposited(AccountDepositedEvent accountDepositedEvent){
        System.out.println(accountDepositedEvent);
        System.out.println(this.balance);
        int newBalance = this.balance=balance+accountDepositedEvent.getAmount();
        System.out.println(newBalance);
    }
    @EventSourcingHandler
    public void accountDeposited(AccountWithdrawEvent accountwithdrawEvent){
        System.out.println(accountwithdrawEvent);
        System.out.println(this.balance);
        int newBalance = this.balance=balance-accountwithdrawEvent.getAmount();
        System.out.println(newBalance);
    }
    @EventSourcingHandler
    public void accountInactive(AccountInActiveEvent accountInActiveEvent){
        System.out.println(accountInActiveEvent);
        this.active=false;
    }
    @EventSourcingHandler
    public void accountActive(AccountActiveEvent accountActiveEvent){
        System.out.println(accountActiveEvent);
        this.active=true;
    }
}
