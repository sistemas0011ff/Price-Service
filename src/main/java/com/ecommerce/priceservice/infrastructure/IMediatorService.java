package com.ecommerce.priceservice.infrastructure;

import com.ecommerce.priceservice.shared.application.command.ICommand;
import com.ecommerce.priceservice.shared.application.query.IQuery;

public interface IMediatorService {
    <T extends ICommand, R> R dispatch(T command);
    <T extends IQuery<R>, R> R dispatch(T query);
}
