package com.example.EventSourcing.EventHandler;

import com.example.EventSourcing.common.events.AccountActiveEvent;
import com.example.EventSourcing.common.events.AccountCreatedEvent;
import com.example.EventSourcing.common.events.AccountInActiveEvent;
import com.example.EventSourcing.common.events.AccountWithdrawEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class AccountEventHandler {
    @EventHandler
    public void accountCreatedHandler(AccountCreatedEvent accountCreatedEvent){
        System.out.println(accountCreatedEvent);
    }
    @EventHandler
    public void accountActiveHandler(AccountActiveEvent accountActiveEvent){
        System.out.println(accountActiveEvent);
    }
    @EventHandler
    public void accountInActiveHandler(AccountInActiveEvent accountInActiveEvent){
        System.out.println(accountInActiveEvent);
    }
    @EventHandler
    public void accountWithdrawHandler(AccountWithdrawEvent accountWithdrawEvent){
        System.out.println(accountWithdrawEvent);
    }
}
