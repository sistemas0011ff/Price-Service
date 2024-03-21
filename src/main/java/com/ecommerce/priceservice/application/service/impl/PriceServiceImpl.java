package com.ecommerce.priceservice.application.service.impl;

import com.ecommerce.priceservice.application.dto.PriceDTO;
import com.ecommerce.priceservice.application.dto.PriceResponse;
import com.ecommerce.priceservice.application.query.GetPriceQuery;
import com.ecommerce.priceservice.application.service.IPriceService;
import com.ecommerce.priceservice.infrastructure.IMediatorService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceServiceImpl implements IPriceService {

    private final IMediatorService mediatorService;

    public PriceServiceImpl(IMediatorService mediatorService) {
        this.mediatorService = mediatorService;
    }

    @Override
    public PriceResponse getPrice(LocalDateTime applicationDate, long productId, long brandId) {
        System.out.println("[PriceServiceImpl] Entering getPrice: applicationDate=" + applicationDate + ", productId=" + productId + ", brandId=" + brandId);

        GetPriceQuery query = new GetPriceQuery(applicationDate, productId, brandId);
        List<PriceDTO> prices = mediatorService.dispatch(query);

        String message = prices.isEmpty() ? "No price found according to the entered parameters." : "Prices successfully retrieved.";

        System.out.println("[PriceServiceImpl] Exiting getPrice: prices=" + prices);
        
        return new PriceResponse(prices, message);
    }
}
