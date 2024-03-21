package com.ecommerce.priceservice.shared.application.query;
 
public interface IQueryHandler<TQuery extends IQuery<R>, R> {
    R handle(TQuery query);
    Class<TQuery> getQueryType();
}