package com.ecommerce.priceservice.application.query;

import java.time.LocalDateTime;
import java.util.List;

import com.ecommerce.priceservice.application.dto.PriceDTO;
import com.ecommerce.priceservice.shared.application.query.IQuery;

public class GetPriceQuery implements IQuery<List<PriceDTO>> {

    private final LocalDateTime applicationDate;
    private final long productId;
    private final long brandId;

    public GetPriceQuery(LocalDateTime applicationDate, long productId, long brandId) {
        this.applicationDate = applicationDate;
        this.productId = productId;
        this.brandId = brandId;
    }
 
    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public long getProductId() {
        return productId;
    }

    public long getBrandId() {
        return brandId;
    }
}