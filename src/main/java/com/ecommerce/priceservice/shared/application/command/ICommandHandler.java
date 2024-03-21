package com.ecommerce.priceservice.shared.application.command;
 
public interface ICommandHandler<T extends ICommand, R> {
    R handle(T command);
    Class<T> getCommandType();
}