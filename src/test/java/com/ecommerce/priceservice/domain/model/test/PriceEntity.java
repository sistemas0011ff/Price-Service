package com.ecommerce.priceservice.domain.model.test;

import com.ecommerce.priceservice.domain.model.PriceEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PriceEntityTest {

    @Test
    void givenPriceEntityWhenSetValuesThenCorrectValuesReturned() {
        // Arrange
        PriceEntity priceEntity = new PriceEntity();
        Long expectedId = 1L;
        Long expectedBrandId = 1L;
        LocalDateTime expectedStartDate = LocalDateTime.now();
        LocalDateTime expectedEndDate = LocalDateTime.now().plusDays(1);
        Integer expectedPriceList = 1;
        Long expectedProductId = 100L;
        Integer expectedPriority = 1;
        BigDecimal expectedPrice = BigDecimal.valueOf(19.99);
        String expectedCurrency = "USD";

        priceEntity.setId(expectedId);
        priceEntity.setBrandId(expectedBrandId);
        priceEntity.setStartDate(expectedStartDate);
        priceEntity.setEndDate(expectedEndDate);
        priceEntity.setPriceList(expectedPriceList);
        priceEntity.setProductId(expectedProductId);
        priceEntity.setPriority(expectedPriority);
        priceEntity.setPrice(expectedPrice);
        priceEntity.setCurrency(expectedCurrency);

        // Act & Assert
        assertEquals(expectedId, priceEntity.getId());
        assertEquals(expectedBrandId, priceEntity.getBrandId());
        assertEquals(expectedStartDate, priceEntity.getStartDate());
        assertEquals(expectedEndDate, priceEntity.getEndDate());
        assertEquals(expectedPriceList, priceEntity.getPriceList());
        assertEquals(expectedProductId, priceEntity.getProductId());
        assertEquals(expectedPriority, priceEntity.getPriority());
        assertEquals(expectedPrice, priceEntity.getPrice());
        assertEquals(expectedCurrency, priceEntity.getCurrency());
    }

    @Test
    void equalsVerifier() {
        PriceEntity priceEntity1 = new PriceEntity();
        priceEntity1.setId(1L);
        PriceEntity priceEntity2 = new PriceEntity();
        priceEntity2.setId(1L);
        PriceEntity priceEntity3 = new PriceEntity();
        priceEntity3.setId(2L);

        assertTrue(priceEntity1.equals(priceEntity2));
        assertFalse(priceEntity1.equals(null));
        assertFalse(priceEntity1.equals(priceEntity3));
        assertFalse(priceEntity1.equals(new Object()));
    }

    @Test
    void hashCodeVerifier() {
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setId(1L);
        priceEntity.setProductId(100L);

        PriceEntity samePriceEntity = new PriceEntity();
        samePriceEntity.setId(1L);
        samePriceEntity.setProductId(100L);

        assertEquals(priceEntity.hashCode(), samePriceEntity.hashCode());

        PriceEntity differentPriceEntity = new PriceEntity();
        differentPriceEntity.setId(2L);
        differentPriceEntity.setProductId(100L);

        assertNotEquals(priceEntity.hashCode(), differentPriceEntity.hashCode());
    }

    @Test
    void equalsVerifierWithDifferentId() {
        PriceEntity priceEntity1 = new PriceEntity();
        priceEntity1.setId(1L);
        PriceEntity priceEntity2 = new PriceEntity();
        priceEntity2.setId(2L);

        assertFalse(priceEntity1.equals(priceEntity2));
    }

    @Test
    void hashCodeVerifierWithDifferentId() {
        PriceEntity priceEntity1 = new PriceEntity();
        priceEntity1.setId(1L);
        PriceEntity priceEntity2 = new PriceEntity();
        priceEntity2.setId(2L);

        assertNotEquals(priceEntity1.hashCode(), priceEntity2.hashCode());
    }
    
    @Test
    void toStringVerifier() {
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setId(1L);
        priceEntity.setBrandId(1L);
        priceEntity.setProductId(100L);
        priceEntity.setPriceList(1);
        priceEntity.setPriority(1);
        priceEntity.setPrice(BigDecimal.valueOf(19.99));
        priceEntity.setCurrency("USD");

        String priceEntityString = priceEntity.toString();
        assertNotNull(priceEntityString);
        assertTrue(priceEntityString.contains("PriceEntity"));
        assertTrue(priceEntityString.contains("id=1"));
        assertTrue(priceEntityString.contains("brandId=1"));
        assertTrue(priceEntityString.contains("productId=100"));
        assertTrue(priceEntityString.contains("priceList=1"));
        assertTrue(priceEntityString.contains("priority=1"));
        assertTrue(priceEntityString.contains("price=19.99")); 
        assertTrue(priceEntityString.contains("currency=USD"));

    }
}
