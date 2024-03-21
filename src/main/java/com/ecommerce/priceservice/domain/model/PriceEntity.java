package com.ecommerce.priceservice.domain.model;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data 
public class PriceEntity {
	private Long id;
    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priceList;
    private Long productId;
    private Integer priority;
    private BigDecimal price;
    private String currency;
}
