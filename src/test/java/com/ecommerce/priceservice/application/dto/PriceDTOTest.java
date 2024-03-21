package com.ecommerce.priceservice.application.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class PriceDTOTest {

    @Test
    void testPriceDTO() {
        // Arrange
        Long expectedId = 1L;
        Long expectedBrandId = 1L;
        LocalDateTime expectedStartDate = LocalDateTime.now();
        LocalDateTime expectedEndDate = LocalDateTime.now().plusDays(1);
        Integer expectedPriceList = 1;
        Long expectedProductId = 100L;
        Integer expectedPriority = 0;
        BigDecimal expectedPrice = BigDecimal.valueOf(99.99);
        String expectedCurrency = "EUR";

        // Act
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setId(expectedId);
        priceDTO.setBrandId(expectedBrandId);
        priceDTO.setStartDate(expectedStartDate);
        priceDTO.setEndDate(expectedEndDate);
        priceDTO.setPriceList(expectedPriceList);
        priceDTO.setProductId(expectedProductId);
        priceDTO.setPriority(expectedPriority);
        priceDTO.setPrice(expectedPrice);
        priceDTO.setCurrency(expectedCurrency);

        // Assert
        assertThat(priceDTO.getId()).isEqualTo(expectedId);
        assertThat(priceDTO.getBrandId()).isEqualTo(expectedBrandId);
        assertThat(priceDTO.getStartDate()).isEqualTo(expectedStartDate);
        assertThat(priceDTO.getEndDate()).isEqualTo(expectedEndDate);
        assertThat(priceDTO.getPriceList()).isEqualTo(expectedPriceList);
        assertThat(priceDTO.getProductId()).isEqualTo(expectedProductId);
        assertThat(priceDTO.getPriority()).isEqualTo(expectedPriority);
        assertThat(priceDTO.getPrice()).isEqualTo(expectedPrice);
        assertThat(priceDTO.getCurrency()).isEqualTo(expectedCurrency);
    }
}
