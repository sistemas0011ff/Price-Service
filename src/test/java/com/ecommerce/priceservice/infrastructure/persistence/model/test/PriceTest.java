package com.ecommerce.priceservice.infrastructure.persistence.model.test;

import org.junit.jupiter.api.Test;

import com.ecommerce.priceservice.infrastructure.persistence.model.Price;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PriceTest {


    @Test
    void hashCodeVerifier() {
        Price price = new Price(1L, 10L, LocalDateTime.now(), LocalDateTime.now().plusDays(1), 
                                1, 42L, 5, new BigDecimal("29.99"), "EUR");
        assertNotNull(price.hashCode());
    }

    @Test
    void toStringVerifier() {
        Price price = new Price(1L, 10L, LocalDateTime.now(), LocalDateTime.now().plusDays(1), 
                                1, 42L, 5, new BigDecimal("29.99"), "EUR");
        String priceString = price.toString();
        assertNotNull(priceString);
        assertTrue(priceString.contains("Price"));
    }
    
    @Test
    void equalsVerifierWithDifferentObjects() {
        Price price1 = new Price(1L, 10L, LocalDateTime.now(), LocalDateTime.now().plusDays(1), 
                                 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        Price price2 = new Price(1L, 10L, LocalDateTime.now(), LocalDateTime.now().plusDays(1), 
                                 1, 42L, 5, new BigDecimal("30.99"), "EUR");

        assertNotEquals(price1, price2, "Prices with different values should not be equal");
        
        price2 = new Price(1L, 11L, LocalDateTime.now(), LocalDateTime.now().plusDays(1), 
                                 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        assertNotEquals(price1, price2, "Prices with different brandId should not be equal");

        assertNotEquals(price1, null, "Price should not be equal to null");
        assertNotEquals(price1, new Object(), "Price should not be equal to an object of a different type");
    }

    @Test
    void equalsVerifierWithDifferentAttributes() {
        LocalDateTime now = LocalDateTime.now();
        Price price1 = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR");

        Price priceDifferentBrandId = new Price(1L, 11L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        assertNotEquals(price1, priceDifferentBrandId, "Different brandId should not be equal");

        Price priceDifferentStartDate = new Price(1L, 10L, now.minusDays(1), now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        assertNotEquals(price1, priceDifferentStartDate, "Different startDate should not be equal");

    }
	
    @Test
    void testPriceGettersAndSetters() {
        Long expectedId = 1L;
        Long expectedBrandId = 10L;
        LocalDateTime expectedStartDate = LocalDateTime.of(2023, 1, 1, 0, 0);
        LocalDateTime expectedEndDate = LocalDateTime.of(2023, 12, 31, 23, 59);
        Integer expectedPriceList = 2;
        Long expectedProductId = 42L;
        Integer expectedPriority = 5;
        BigDecimal expectedPrice = new BigDecimal("29.99");
        String expectedCurrency = "EUR";

        Price price = new Price();
        price.setId(expectedId);
        price.setBrandId(expectedBrandId);
        price.setStartDate(expectedStartDate);
        price.setEndDate(expectedEndDate);
        price.setPriceList(expectedPriceList);
        price.setProductId(expectedProductId);
        price.setPriority(expectedPriority);
        price.setPrice(expectedPrice);
        price.setCurrency(expectedCurrency);

        assertEquals(expectedId, price.getId());
        assertEquals(expectedBrandId, price.getBrandId());
        assertEquals(expectedStartDate, price.getStartDate());
        assertEquals(expectedEndDate, price.getEndDate());
        assertEquals(expectedPriceList, price.getPriceList());
        assertEquals(expectedProductId, price.getProductId());
        assertEquals(expectedPriority, price.getPriority());
        assertEquals(0, expectedPrice.compareTo(price.getPrice()));
        assertEquals(expectedCurrency, price.getCurrency());
    }
    
    @Test
    void fullConstructorVerifier() {
        LocalDateTime startDate = LocalDateTime.of(2023, 1, 1, 0, 0);
        LocalDateTime endDate = startDate.plusDays(1);
        Price price = new Price(1L, 10L, startDate, endDate, 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        
        assertNotNull(price);
        assertEquals(1L, price.getId().longValue());
        assertEquals(10L, price.getBrandId().longValue());
        assertEquals(startDate, price.getStartDate());
        assertEquals(endDate, price.getEndDate());
        assertEquals(1, price.getPriceList().intValue());
        assertEquals(42L, price.getProductId().longValue());
        assertEquals(5, price.getPriority().intValue());
        assertEquals(0, new BigDecimal("29.99").compareTo(price.getPrice()));
        assertEquals("EUR", price.getCurrency());
    }
    
    @Test
    void equalsVerifier() {
        LocalDateTime now = LocalDateTime.now();
        Price price1 = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR");
 
        Price price2 = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        assertEquals(price1, price2, "Prices with the same values should be equal");
       
        assertEquals(price1, price1, "Price should be equal to itself");
       
        assertNotEquals(null, price1, "Price should not be equal to null");
        assertNotEquals(price1, new Object(), "Price should not be equal to a different type");
 
        assertNotEquals(price1, new Price(2L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR"), "Prices with different id should not be equal");
        assertNotEquals(price1, new Price(1L, 11L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR"), "Prices with different brandId should not be equal");
        assertNotEquals(price1, new Price(1L, 10L, now.minusDays(2), now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR"), "Prices with different startDate should not be equal");
        assertNotEquals(price1, new Price(1L, 10L, now, now.plusDays(2), 1, 42L, 5, new BigDecimal("29.99"), "EUR"), "Prices with different endDate should not be equal");
        assertNotEquals(price1, new Price(1L, 10L, now, now.plusDays(1), 2, 42L, 5, new BigDecimal("29.99"), "EUR"), "Prices with different priceList should not be equal");
        assertNotEquals(price1, new Price(1L, 10L, now, now.plusDays(1), 1, 43L, 5, new BigDecimal("29.99"), "EUR"), "Prices with different productId should not be equal");
        assertNotEquals(price1, new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 6, new BigDecimal("29.99"), "EUR"), "Prices with different priority should not be equal");
        assertNotEquals(price1, new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("30.99"), "EUR"), "Prices with different price should not be equal");
        assertNotEquals(price1, new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "USD"), "Prices with different currency should not be equal");
    }


    @Test
    void hashCodeConsistencyCheck() {
        Price price = new Price(1L, 10L, LocalDateTime.now(), LocalDateTime.now().plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        int initialHashCode = price.hashCode();

        assertEquals(initialHashCode, price.hashCode(), "Hash code is inconsistent across multiple calls.");
    }

    @Test
    void hashCodeEqualityCheck() {
        LocalDateTime now = LocalDateTime.now();
        Price price1 = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        Price price2 = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR");

        assertEquals(price1.hashCode(), price2.hashCode(), "Equal objects must have equal hash codes");
    }

    @Test
    void hashCodeDifferenceCheck() {
        LocalDateTime now = LocalDateTime.now();
        Price price1 = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        Price price2 = new Price(2L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR");

        assertNotEquals(price1.hashCode(), price2.hashCode(), "Non-equal objects should have different hash codes");
    }
    
    @Test
    void allArgsConstructorTest() {
        LocalDateTime startDate = LocalDateTime.of(2023, 1, 1, 0, 0);
        LocalDateTime endDate = startDate.plusDays(1);
        Price price = new Price(1L, 10L, startDate, endDate, 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        
        assertNotNull(price);
        assertEquals(1L, price.getId());
        assertEquals(10L, price.getBrandId());
        assertEquals(startDate, price.getStartDate());
        assertEquals(endDate, price.getEndDate());
        assertEquals(1, price.getPriceList());
        assertEquals(42L, price.getProductId());
        assertEquals(5, price.getPriority());
        assertEquals(new BigDecimal("29.99"), price.getPrice());
        assertEquals("EUR", price.getCurrency());
    }
 
    @Test
    void equalsCompleteCoverageTest() {
        LocalDateTime startDate = LocalDateTime.of(2023, 1, 1, 0, 0);
        LocalDateTime endDate = startDate.plusDays(1);
        Price basePrice = new Price(1L, 10L, startDate, endDate, 1, 42L, 5, new BigDecimal("29.99"), "EUR");
    
        assertNotEquals(basePrice, new Price(2L, 10L, startDate, endDate, 1, 42L, 5, new BigDecimal("29.99"), "EUR"), "Different IDs should not be equal");
        assertNotEquals(basePrice, new Price(1L, 11L, startDate, endDate, 1, 42L, 5, new BigDecimal("29.99"), "EUR"), "Different brand IDs should not be equal");
        assertNotEquals(basePrice, new Price(1L, 10L, startDate.minusDays(2), endDate, 1, 42L, 5, new BigDecimal("29.99"), "EUR"), "Different start dates should not be equal");
        assertNotEquals(basePrice, new Price(1L, 10L, startDate, endDate.plusDays(2), 1, 42L, 5, new BigDecimal("29.99"), "EUR"), "Different end dates should not be equal");
        assertNotEquals(basePrice, new Price(1L, 10L, startDate, endDate, 2, 42L, 5, new BigDecimal("29.99"), "EUR"), "Different price lists should not be equal");
        assertNotEquals(basePrice, new Price(1L, 10L, startDate, endDate, 1, 43L, 5, new BigDecimal("29.99"), "EUR"), "Different product IDs should not be equal");
        assertNotEquals(basePrice, new Price(1L, 10L, startDate, endDate, 1, 42L, 6, new BigDecimal("29.99"), "EUR"), "Different priorities should not be equal");
        assertNotEquals(basePrice, new Price(1L, 10L, startDate, endDate, 1, 42L, 5, new BigDecimal("30.99"), "EUR"), "Different prices should not be equal");
        assertNotEquals(basePrice, new Price(1L, 10L, startDate, endDate, 1, 42L, 5, new BigDecimal("29.99"), "USD"), "Different currencies should not be equal");
    }
    
    @Test
    void hashCodeCompleteCoverageTest() {
        LocalDateTime startDate = LocalDateTime.of(2023, 1, 1, 0, 0);
        LocalDateTime endDate = startDate.plusDays(1);
        Price price1 = new Price(1L, 10L, startDate, endDate, 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        

        Price price2 = new Price(1L, 10L, startDate, endDate, 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        assertEquals(price1.hashCode(), price2.hashCode(), "Hash codes should be equal for equal objects");


        Price priceDifferentId = new Price(2L, 10L, startDate, endDate, 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        assertNotEquals(price1.hashCode(), priceDifferentId.hashCode(), "Hash codes should not be equal when IDs are different");

        Price priceDifferentBrandId = new Price(1L, 11L, startDate, endDate, 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        assertNotEquals(price1.hashCode(), priceDifferentBrandId.hashCode(), "Hash codes should not be equal when brand IDs are different");

        Price priceDifferentStartDate = new Price(1L, 10L, startDate.minusDays(10), endDate, 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        assertNotEquals(price1.hashCode(), priceDifferentStartDate.hashCode(), "Hash codes should not be equal when start dates are different");

        Price priceDifferentEndDate = new Price(1L, 10L, startDate, endDate.plusDays(10), 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        assertNotEquals(price1.hashCode(), priceDifferentEndDate.hashCode(), "Hash codes should not be equal when end dates are different");

        Price priceDifferentPriceList = new Price(1L, 10L, startDate, endDate, 2, 42L, 5, new BigDecimal("29.99"), "EUR");
        assertNotEquals(price1.hashCode(), priceDifferentPriceList.hashCode(), "Hash codes should not be equal when price lists are different");

        Price priceDifferentProductId = new Price(1L, 10L, startDate, endDate, 1, 43L, 5, new BigDecimal("29.99"), "EUR");
        assertNotEquals(price1.hashCode(), priceDifferentProductId.hashCode(), "Hash codes should not be equal when product IDs are different");

        Price priceDifferentPriority = new Price(1L, 10L, startDate, endDate, 1, 42L, 6, new BigDecimal("29.99"), "EUR");
        assertNotEquals(price1.hashCode(), priceDifferentPriority.hashCode(), "Hash codes should not be equal when priorities are different");

        Price priceDifferentPrice = new Price(1L, 10L, startDate, endDate, 1, 42L, 5, new BigDecimal("39.99"), "EUR");
        assertNotEquals(price1.hashCode(), priceDifferentPrice.hashCode(), "Hash codes should not be equal when prices are different");

        Price priceDifferentCurrency = new Price(1L, 10L, startDate, endDate, 1, 42L, 5, new BigDecimal("29.99"), "USD");
        assertNotEquals(price1.hashCode(), priceDifferentCurrency.hashCode(), "Hash codes should not be equal when currencies are different");
    }
    
    @Test
    void equalsWithAllFieldsEqual() {
        LocalDateTime now = LocalDateTime.now();
        Price price1 = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        Price price2 = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        assertEquals(price1, price2, "Prices should be equal if all fields are the same.");
    }

 
    @Test
    void hashCodeWithAllFields() {
        LocalDateTime now = LocalDateTime.now();
        Price price = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        int hashCode1 = price.hashCode();
        int hashCode2 = new Price(2L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR").hashCode();
        assertNotEquals(hashCode1, hashCode2, "Hashcodes should be different when IDs are different.");
    }
 
    @Test
    void allArgsConstructorCompleteCoverage() {
        LocalDateTime startDate = LocalDateTime.of(2023, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 12, 31, 23, 59);
        Price price = new Price(1L, 10L, startDate, endDate, 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        assertAll(
                () -> assertEquals(1L, price.getId()),
                () -> assertEquals(10L, price.getBrandId()),
                () -> assertEquals(startDate, price.getStartDate()),
                () -> assertEquals(endDate, price.getEndDate()),
                () -> assertEquals(1, price.getPriceList()),
                () -> assertEquals(42L, price.getProductId()),
                () -> assertEquals(5, price.getPriority()),
                () -> assertEquals(0, new BigDecimal("29.99").compareTo(price.getPrice())),
                () -> assertEquals("EUR", price.getCurrency())
        );
    }
    
    @Test
    void equalsVerifierForDifferentProductIds() {
        LocalDateTime now = LocalDateTime.now();
        Price price1 = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        Price priceDifferentProductId = new Price(1L, 10L, now, now.plusDays(1), 1, 43L, 5, new BigDecimal("29.99"), "EUR");
        assertNotEquals(price1, priceDifferentProductId, "Prices with different product IDs should not be equal.");
    }
    
    @Test
    void hashCodeVerifierForDifferentValues() {
        LocalDateTime now = LocalDateTime.now();
        Price price1 = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        Price priceDifferentProductId = new Price(1L, 10L, now, now.plusDays(1), 1, 43L, 5, new BigDecimal("29.99"), "EUR");
        assertNotEquals(price1.hashCode(), priceDifferentProductId.hashCode(), "Different Price objects should have different hash codes.");
    }
    
    
    @Test
    void equalsVerifierForSameAttributes() {
        LocalDateTime now = LocalDateTime.now();
        Price price1 = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        Price price2 = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        assertEquals(price1, price2, "Prices with same attributes should be equal.");
    }
 
    @Test
    void equalsVerifierForDifferentPriority() {
        LocalDateTime now = LocalDateTime.now();
        Price price1 = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        Price priceDifferentPriority = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 4, new BigDecimal("29.99"), "EUR");
        assertNotEquals(price1, priceDifferentPriority, "Prices with different priority should not be equal.");
    }
 
    @Test
    void hashCodeVerifierForDifferentAttributes() {
        LocalDateTime now = LocalDateTime.now();
        Price price1 = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        Price priceDifferentPrice = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("39.99"), "EUR");
        assertNotEquals(price1.hashCode(), priceDifferentPrice.hashCode(), "Hash codes should be different for Prices with different price values.");
    }


    @Test
    void hashCodeConsistencyForSameObject() {
        LocalDateTime now = LocalDateTime.now();
        Price price = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        int firstCallHashCode = price.hashCode();
        int secondCallHashCode = price.hashCode();
        assertEquals(firstCallHashCode, secondCallHashCode, "Hash code should be consistent across multiple calls for the same object.");
    }
    
    @Test
    void equalsWhenAllFieldsAreEqualButObjectsAreDifferent() {
        LocalDateTime now = LocalDateTime.now();
        Price price1 = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        Price price2 = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR");
        assertEquals(price1, price2, "Two prices with identical fields should be considered equal.");
    }
 
    @Test
    void equalsWhenFieldsAreDifferent() {
        LocalDateTime now = LocalDateTime.now();
        Price price1 = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("29.99"), "EUR");

        Price priceDifferentPriceList = new Price(1L, 10L, now, now.plusDays(1), 2, 42L, 5, new BigDecimal("29.99"), "EUR");
        assertNotEquals(price1, priceDifferentPriceList, "Prices should not be equal if the priceList is different.");

        Price priceDifferentPriority = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 6, new BigDecimal("29.99"), "EUR");
        assertNotEquals(price1, priceDifferentPriority, "Prices should not be equal if the priority is different.");

    }
    
    
    @Test
    void hashCodeWhenFieldsAreDifferent() {
        LocalDateTime now = LocalDateTime.now();
        BigDecimal priceValue = new BigDecimal("29.99");
        Price basePrice = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, priceValue, "EUR");
 
        Price priceDifferentId = new Price(2L, 10L, now, now.plusDays(1), 1, 42L, 5, priceValue, "EUR");
        assertNotEquals(basePrice.hashCode(), priceDifferentId.hashCode(), "Hash codes should be different if the id is different.");

        Price priceDifferentBrandId = new Price(1L, 11L, now, now.plusDays(1), 1, 42L, 5, priceValue, "EUR");
        assertNotEquals(basePrice.hashCode(), priceDifferentBrandId.hashCode(), "Hash codes should be different if the brandId is different.");

        Price priceDifferentStartDate = new Price(1L, 10L, now.minusDays(1), now.plusDays(1), 1, 42L, 5, priceValue, "EUR");
        assertNotEquals(basePrice.hashCode(), priceDifferentStartDate.hashCode(), "Hash codes should be different if the startDate is different.");

        Price priceDifferentEndDate = new Price(1L, 10L, now, now.plusDays(2), 1, 42L, 5, priceValue, "EUR");
        assertNotEquals(basePrice.hashCode(), priceDifferentEndDate.hashCode(), "Hash codes should be different if the endDate is different.");

        Price priceDifferentPriceList = new Price(1L, 10L, now, now.plusDays(1), 2, 42L, 5, priceValue, "EUR");
        assertNotEquals(basePrice.hashCode(), priceDifferentPriceList.hashCode(), "Hash codes should be different if the priceList is different.");

        Price priceDifferentProductId = new Price(1L, 10L, now, now.plusDays(1), 1, 43L, 5, priceValue, "EUR");
        assertNotEquals(basePrice.hashCode(), priceDifferentProductId.hashCode(), "Hash codes should be different if the productId is different.");

        Price priceDifferentPriority = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 6, priceValue, "EUR");
        assertNotEquals(basePrice.hashCode(), priceDifferentPriority.hashCode(), "Hash codes should be different if the priority is different.");

        Price priceDifferentPrice = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, new BigDecimal("39.99"), "EUR");
        assertNotEquals(basePrice.hashCode(), priceDifferentPrice.hashCode(), "Hash codes should be different if the price is different.");

        Price priceDifferentCurrency = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, priceValue, "USD");
        assertNotEquals(basePrice.hashCode(), priceDifferentCurrency.hashCode(), "Hash codes should be different if the currency is different.");
    }
    @Test
    void hashCodeWhenPriceIsDifferent() { 
        LocalDateTime now = LocalDateTime.now();
        BigDecimal basePriceValue = new BigDecimal("29.99");
        Price basePrice = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, basePriceValue, "EUR");
 
        BigDecimal differentPriceValue = new BigDecimal("39.99");
        Price priceWithDifferentPrice = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, differentPriceValue, "EUR");
        assertNotEquals(basePrice.hashCode(), priceWithDifferentPrice.hashCode(), "Hash codes should be different if the prices are different.");
    }

    @Test
    void hashCodeWhenCurrencyIsDifferent() {
        LocalDateTime now = LocalDateTime.now();
        BigDecimal priceValue = new BigDecimal("29.99");
        Price basePrice = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, priceValue, "EUR");

        Price priceWithDifferentCurrency = new Price(1L, 10L, now, now.plusDays(1), 1, 42L, 5, priceValue, "USD");
        assertNotEquals(basePrice.hashCode(), priceWithDifferentCurrency.hashCode(), "Hash codes should be different if the currencies are different.");
    }

}