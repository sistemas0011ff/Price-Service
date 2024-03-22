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
    
    @Test
    void testPriceDTOWithNullValues() {
        // Arrange
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setId(null);
        priceDTO.setBrandId(null);
        priceDTO.setStartDate(null);
        priceDTO.setEndDate(null);
        priceDTO.setPriceList(null);
        priceDTO.setProductId(null);
        priceDTO.setPriority(null);
        priceDTO.setPrice(null);
        priceDTO.setCurrency(null);

        // Act & Assert
        assertThat(priceDTO.getId()).isNull();
        assertThat(priceDTO.getBrandId()).isNull();
        assertThat(priceDTO.getStartDate()).isNull();
        assertThat(priceDTO.getEndDate()).isNull();
        assertThat(priceDTO.getPriceList()).isNull();
        assertThat(priceDTO.getProductId()).isNull();
        assertThat(priceDTO.getPriority()).isNull();
        assertThat(priceDTO.getPrice()).isNull();
        assertThat(priceDTO.getCurrency()).isNull();
    }
    
    @Test
    void testEquals() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        PriceDTO priceDTO1 = new PriceDTO();
        priceDTO1.setId(1L);
        priceDTO1.setBrandId(2L);
        priceDTO1.setStartDate(now);
        priceDTO1.setEndDate(now.plusDays(1));
        priceDTO1.setPriceList(1);
        priceDTO1.setProductId(100L);
        priceDTO1.setPriority(10);
        priceDTO1.setPrice(BigDecimal.valueOf(100.0));
        priceDTO1.setCurrency("EUR");

        PriceDTO priceDTO2 = new PriceDTO();
        priceDTO2.setId(1L);
        priceDTO2.setBrandId(2L);
        priceDTO2.setStartDate(now);
        priceDTO2.setEndDate(now.plusDays(1));
        priceDTO2.setPriceList(1);
        priceDTO2.setProductId(100L);
        priceDTO2.setPriority(10);
        priceDTO2.setPrice(BigDecimal.valueOf(100.0));
        priceDTO2.setCurrency("EUR");

        // Act & Assert
        assertThat(priceDTO1).isEqualTo(priceDTO2);
    }

    @Test
    void testHashCode() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setId(1L);
        priceDTO.setBrandId(2L);
        priceDTO.setStartDate(now);
        priceDTO.setEndDate(now.plusDays(1));
        priceDTO.setPriceList(1);
        priceDTO.setProductId(100L);
        priceDTO.setPriority(10);
        priceDTO.setPrice(BigDecimal.valueOf(100.0));
        priceDTO.setCurrency("EUR");

        // Act
        int hashCode = priceDTO.hashCode();

        // Assert
        assertThat(hashCode).isNotNull();
    }

    @Test
    void testCanEqual() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setId(1L);
        priceDTO.setBrandId(2L);
        priceDTO.setStartDate(now);
        priceDTO.setEndDate(now.plusDays(1));
        priceDTO.setPriceList(1);
        priceDTO.setProductId(100L);
        priceDTO.setPriority(10);
        priceDTO.setPrice(BigDecimal.valueOf(100.0));
        priceDTO.setCurrency("EUR");

        // Act
        boolean canEqual = priceDTO.canEqual(new PriceDTO());

        // Assert
        assertThat(canEqual).isTrue();
    }
}
