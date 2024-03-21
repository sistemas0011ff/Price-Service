package com.ecommerce.priceservice.application.service;

import com.ecommerce.priceservice.application.dto.PriceResponse;
import java.time.LocalDateTime;

public interface IPriceService {
    PriceResponse getPrice(LocalDateTime applicationDate, long productId, long brandId);
}
