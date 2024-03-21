package com.ecommerce.priceservice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class PriceResponse {
    private final List<PriceDTO> prices;
    private final String message;
}
