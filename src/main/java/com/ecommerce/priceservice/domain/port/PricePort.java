package com.ecommerce.priceservice.domain.port;
 
import java.time.LocalDateTime;
import java.util.List;

import com.ecommerce.priceservice.domain.dto.PriceDomainDTO;

public interface PricePort {
    List<PriceDomainDTO> findApplicablePrices(LocalDateTime date, long productId, long brandId);
}
