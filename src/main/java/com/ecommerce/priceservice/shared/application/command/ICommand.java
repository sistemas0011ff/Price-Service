package com.ecommerce.priceservice.shared.application.command;

 
public interface ICommand {
    Class<? extends ICommand> getCommandType();
}