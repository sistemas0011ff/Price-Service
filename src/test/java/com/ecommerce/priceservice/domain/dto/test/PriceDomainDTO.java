package com.ecommerce.priceservice.domain.dto.test;

import com.ecommerce.priceservice.domain.dto.PriceDomainDTO;
import com.ecommerce.priceservice.domain.model.PriceEntity;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class PriceDomainDTOTest {

    @Test
    void testPriceDomainDTOGettersAndSetters() {
        // Arrange
        Long expectedId = 1L;
        Long expectedBrandId = 2L;
        LocalDateTime expectedStartDate = LocalDateTime.of(2023, 4, 20, 0, 0);
        LocalDateTime expectedEndDate = LocalDateTime.of(2023, 12, 31, 23, 59);
        Integer expectedPriceList = 1;
        Long expectedProductId = 42L;
        Integer expectedPriority = 10;
        BigDecimal expectedPrice = BigDecimal.valueOf(199.99);
        String expectedCurrency = "EUR";

        // Act
        PriceDomainDTO priceDomainDTO = new PriceDomainDTO();
        priceDomainDTO.setId(expectedId);
        priceDomainDTO.setBrandId(expectedBrandId);
        priceDomainDTO.setStartDate(expectedStartDate);
        priceDomainDTO.setEndDate(expectedEndDate);
        priceDomainDTO.setPriceList(expectedPriceList);
        priceDomainDTO.setProductId(expectedProductId);
        priceDomainDTO.setPriority(expectedPriority);
        priceDomainDTO.setPrice(expectedPrice);
        priceDomainDTO.setCurrency(expectedCurrency);

        // Assert
        assertAll("PriceDomainDTO getters and setters should work as expected",
            () -> assertEquals(expectedId, priceDomainDTO.getId()),
            () -> assertEquals(expectedBrandId, priceDomainDTO.getBrandId()),
            () -> assertEquals(expectedStartDate, priceDomainDTO.getStartDate()),
            () -> assertEquals(expectedEndDate, priceDomainDTO.getEndDate()),
            () -> assertEquals(expectedPriceList, priceDomainDTO.getPriceList()),
            () -> assertEquals(expectedProductId, priceDomainDTO.getProductId()),
            () -> assertEquals(expectedPriority, priceDomainDTO.getPriority()),
            () -> assertEquals(0, expectedPrice.compareTo(priceDomainDTO.getPrice())),
            () -> assertEquals(expectedCurrency, priceDomainDTO.getCurrency())
        );
    }

    @Test
    private PriceEntity createSampleEntity() {
        PriceEntity entity = new PriceEntity();
        entity.setId(1L);
        entity.setBrandId(2L);
        entity.setStartDate(LocalDateTime.of(2023, 4, 20, 0, 0));
        entity.setEndDate(LocalDateTime.of(2023, 12, 31, 23, 59));
        entity.setPriceList(1);
        entity.setProductId(42L);
        entity.setPriority(10);
        entity.setPrice(BigDecimal.valueOf(199.99));
        entity.setCurrency("EUR");
        return entity;
    }
    
    @Test
    void testPriceDomainDTOInequalityOnEachField() {
        // Arrange
        PriceDomainDTO baseDTO = createSampleDTO();
 
        PriceDomainDTO differentIdDTO = createSampleDTO();
        differentIdDTO.setId(2L);
        assertNotEquals(baseDTO, differentIdDTO, "DTOs with different IDs should not be equal");

        PriceDomainDTO differentBrandIdDTO = createSampleDTO();
        differentBrandIdDTO.setBrandId(3L);
        assertNotEquals(baseDTO, differentBrandIdDTO, "DTOs with different brand IDs should not be equal");

        PriceDomainDTO differentStartDateDTO = createSampleDTO();
        differentStartDateDTO.setStartDate(LocalDateTime.of(2024, 1, 1, 0, 0));
        assertNotEquals(baseDTO, differentStartDateDTO, "DTOs with different start dates should not be equal");

        PriceDomainDTO differentEndDateDTO = createSampleDTO();
        differentEndDateDTO.setEndDate(LocalDateTime.of(2024, 12, 31, 23, 59));
        assertNotEquals(baseDTO, differentEndDateDTO, "DTOs with different end dates should not be equal");

        PriceDomainDTO differentPriceListDTO = createSampleDTO();
        differentPriceListDTO.setPriceList(2);
        assertNotEquals(baseDTO, differentPriceListDTO, "DTOs with different price lists should not be equal");

        PriceDomainDTO differentProductIdDTO = createSampleDTO();
        differentProductIdDTO.setProductId(43L);
        assertNotEquals(baseDTO, differentProductIdDTO, "DTOs with different product IDs should not be equal");

        PriceDomainDTO differentPriorityDTO = createSampleDTO();
        differentPriorityDTO.setPriority(11);
        assertNotEquals(baseDTO, differentPriorityDTO, "DTOs with different priorities should not be equal");

        PriceDomainDTO differentPriceDTO = createSampleDTO();
        differentPriceDTO.setPrice(BigDecimal.valueOf(299.99));
        assertNotEquals(baseDTO, differentPriceDTO, "DTOs with different prices should not be equal");

        PriceDomainDTO differentCurrencyDTO = createSampleDTO();
        differentCurrencyDTO.setCurrency("USD");
        assertNotEquals(baseDTO, differentCurrencyDTO,"DTOs with different currencies should not be equal");
    }
    
    @Test
    void testPriceDomainDTOEquality() { 
        PriceDomainDTO priceDomainDTO1 = createSampleDTO();
        PriceDomainDTO priceDomainDTO2 = createSampleDTO();

        // Assert - they should be equal
        assertEquals(priceDomainDTO1, priceDomainDTO2, "Identical PriceDomainDTO objects should be equal");
    }
 

    private PriceDomainDTO createSampleDTO() {
        PriceDomainDTO dto = new PriceDomainDTO();
        dto.setId(1L);
        dto.setBrandId(2L);
        dto.setStartDate(LocalDateTime.of(2023, 4, 20, 0, 0));
        dto.setEndDate(LocalDateTime.of(2023, 12, 31, 23, 59));
        dto.setPriceList(1);
        dto.setProductId(42L);
        dto.setPriority(10);
        dto.setPrice(BigDecimal.valueOf(199.99));
        dto.setCurrency("EUR");
        return dto;
    }
    
    @Test
    void testPriceDomainDTOHashCodeConsistency() {
        // Arrange
        PriceDomainDTO priceDomainDTO = createSampleDTO();

        // Act
        int initialHashCode = priceDomainDTO.hashCode();
        int repeatedHashCode = priceDomainDTO.hashCode();

        // Assert
        assertEquals(initialHashCode, repeatedHashCode, "HashCode should be consistent across multiple calls.");
    }

    @Test
    void testPriceDomainDTOHashCodeEquality() {
        // Arrange
        PriceDomainDTO priceDomainDTO1 = createSampleDTO();
        PriceDomainDTO priceDomainDTO2 = createSampleDTO();

        // Assert
        assertEquals(priceDomainDTO1.hashCode(), priceDomainDTO2.hashCode(), "Equal objects should have equal hash codes.");
    }

    @Test
    void testPriceDomainDTOToString() {
        // Arrange
        PriceDomainDTO priceDomainDTO = createSampleDTO();

        // Act
        String toStringResult = priceDomainDTO.toString();

        // Assert
        assertNotNull(toStringResult, "toString should not return null");
        assertTrue(toStringResult.contains("PriceDomainDTO"), "toString should contain the class name");
    }
 

    @Test
    void testPriceDomainDTOEqualsNull() {
        // Arrange
        PriceDomainDTO priceDomainDTO = createSampleDTO();

        // Assert
        assertNotEquals(priceDomainDTO, null, "A DTO should not be equal to null.");
    }

    @Test
    void testPriceDomainDTOEqualsDifferentClass() {
        // Arrange
        PriceDomainDTO priceDomainDTO = createSampleDTO();
        Object otherObject = new Object();
 
        assertNotEquals(priceDomainDTO, otherObject, "A DTO should not be equal to an object of a different class.");
    }

    @Test
    void testPriceDomainDTOEqualsSelf() {
      
        PriceDomainDTO priceDomainDTO = createSampleDTO();
 
        assertEquals(priceDomainDTO, priceDomainDTO, "A DTO should be equal to itself.");
    }
    
    @Test
    void testPriceDomainDTOEquals() {
        PriceDomainDTO dto1 = createSampleDTO();
        PriceDomainDTO dto2 = createSampleDTO();
 
        assertEquals(dto1, dto2, "Dos DTOs con los mismos valores deben ser iguales.");
 
        dto2.setId(2L);
        assertNotEquals(dto1, dto2, "Dos DTOs con diferentes IDs no deben ser iguales.");
 
        dto2 = createSampleDTO();
        dto2.setBrandId(3L);
        assertNotEquals(dto1, dto2, "Dos DTOs con diferentes brandId no deben ser iguales.");
 
        dto2 = createSampleDTO();
        dto2.setStartDate(LocalDateTime.of(2024, 1, 1, 0, 0));
        assertNotEquals(dto1, dto2, "Dos DTOs con diferentes startDate no deben ser iguales.");
 
        dto2 = createSampleDTO();
        dto2.setEndDate(LocalDateTime.of(2024, 12, 31, 23, 59));
        assertNotEquals(dto1, dto2, "Dos DTOs con diferentes endDate no deben ser iguales.");
 
        dto2 = createSampleDTO();
        dto2.setPriceList(2);
        assertNotEquals(dto1, dto2, "Dos DTOs con diferentes priceList no deben ser iguales.");
 
        dto2 = createSampleDTO();
        dto2.setProductId(43L);
        assertNotEquals(dto1, dto2, "Dos DTOs con diferentes productId no deben ser iguales.");
 
        dto2 = createSampleDTO();
        dto2.setPriority(11);
        assertNotEquals(dto1, dto2, "Dos DTOs con diferentes priority no deben ser iguales.");
 
        dto2 = createSampleDTO();
        dto2.setPrice(BigDecimal.valueOf(299.99));
        assertNotEquals(dto1, dto2, "Dos DTOs con diferentes prices no deben ser iguales.");
 
        dto2 = createSampleDTO();
        dto2.setCurrency("USD");
        assertNotEquals(dto1, dto2, "Dos DTOs con diferentes currencies no deben ser iguales.");
    }
   
    @Test
    void testPriceDomainDTOHashCode() {
        PriceDomainDTO dto1 = createSampleDTO();
        PriceDomainDTO dto2 = createSampleDTO();
 
        assertEquals(dto1.hashCode(), dto2.hashCode(), "Dos objetos iguales deben tener el mismo hashCode.");
 
        dto2.setId(2L);
        assertNotEquals(dto1.hashCode(), dto2.hashCode(), "Dos objetos con diferentes ID deben tener diferentes hashCodes.");
 
        dto2 = createSampleDTO();
        dto2.setBrandId(3L);
        assertNotEquals(dto1.hashCode(), dto2.hashCode(), "Dos objetos con diferentes brandId deben tener diferentes hashCodes.");
 
        dto2 = createSampleDTO();
        dto2.setStartDate(LocalDateTime.of(2024, 1, 1, 0, 0));
        assertNotEquals(dto1.hashCode(), dto2.hashCode(), "Dos objetos con diferentes startDate deben tener diferentes hashCodes.");
 
        dto2 = createSampleDTO();
        dto2.setEndDate(LocalDateTime.of(2024, 12, 31, 23, 59));
        assertNotEquals(dto1.hashCode(), dto2.hashCode(), "Dos objetos con diferentes endDate deben tener diferentes hashCodes.");
 
        dto2 = createSampleDTO();
        dto2.setPriceList(2);
        assertNotEquals(dto1.hashCode(), dto2.hashCode(), "Dos objetos con diferentes priceList deben tener diferentes hashCodes.");
 
        dto2 = createSampleDTO();
        dto2.setProductId(43L);
        assertNotEquals(dto1.hashCode(), dto2.hashCode(), "Dos objetos con diferentes productId deben tener diferentes hashCodes.");
 
        dto2 = createSampleDTO();
        dto2.setPriority(11);
        assertNotEquals(dto1.hashCode(), dto2.hashCode(), "Dos objetos con diferentes priority deben tener diferentes hashCodes.");
 
        dto2 = createSampleDTO();
        dto2.setPrice(BigDecimal.valueOf(299.99));
        assertNotEquals(dto1.hashCode(), dto2.hashCode(), "Dos objetos con diferentes prices deben tener diferentes hashCodes.");
 
        dto2 = createSampleDTO();
        dto2.setCurrency("USD");
        assertNotEquals(dto1.hashCode(), dto2.hashCode(), "Dos objetos con diferentes currencies deben tener diferentes hashCodes.");
    }

     
    @Test
    void testPriceEntityGettersAndSetters() { 
        Long expectedId = 1L;
        Long expectedBrandId = 2L;
        LocalDateTime expectedStartDate = LocalDateTime.of(2023, 4, 20, 0, 0);
        LocalDateTime expectedEndDate = LocalDateTime.of(2023, 12, 31, 23, 59);
        Integer expectedPriceList = 1;
        Long expectedProductId = 42L;
        Integer expectedPriority = 10;
        BigDecimal expectedPrice = BigDecimal.valueOf(199.99);
        String expectedCurrency = "EUR";
 
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setId(expectedId);
        priceEntity.setBrandId(expectedBrandId);
        priceEntity.setStartDate(expectedStartDate);
        priceEntity.setEndDate(expectedEndDate);
        priceEntity.setPriceList(expectedPriceList);
        priceEntity.setProductId(expectedProductId);
        priceEntity.setPriority(expectedPriority);
        priceEntity.setPrice(expectedPrice);
        priceEntity.setCurrency(expectedCurrency);
 
        assertAll("PriceEntity getters and setters should work as expected",
            () -> assertEquals(expectedId, priceEntity.getId()),
            () -> assertEquals(expectedBrandId, priceEntity.getBrandId()),
            () -> assertEquals(expectedStartDate, priceEntity.getStartDate()),
            () -> assertEquals(expectedEndDate, priceEntity.getEndDate()),
            () -> assertEquals(expectedPriceList, priceEntity.getPriceList()),
            () -> assertEquals(expectedProductId, priceEntity.getProductId()),
            () -> assertEquals(expectedPriority, priceEntity.getPriority()),
            () -> assertEquals(0, expectedPrice.compareTo(priceEntity.getPrice())),
            () -> assertEquals(expectedCurrency, priceEntity.getCurrency())
        );
    }

    @Test
    void testPriceEntityEquals() { 
        PriceEntity entity1 = createSampleEntity();
        PriceEntity entity2 = createSampleEntity();
  
        assertEquals(entity1, entity2, "Identical PriceEntity objects should be equal");
 
        entity2.setId(2L);
 
        assertNotEquals(entity1, entity2, "PriceEntity objects with different IDs should not be equal");
 
        entity2 = createSampleEntity();
        entity2.setBrandId(3L);
        assertNotEquals(entity1, entity2, "PriceEntity objects with different brand IDs should not be equal");

        entity2 = createSampleEntity();
        entity2.setStartDate(LocalDateTime.of(2024, 1, 1, 0, 0));
        assertNotEquals(entity1, entity2, "PriceEntity objects with different start dates should not be equal");

        entity2 = createSampleEntity();
        entity2.setEndDate(LocalDateTime.of(2024, 12, 31, 23, 59));
        assertNotEquals(entity1, entity2, "PriceEntity objects with different end dates should not be equal");
 
        assertNotNull(entity1, "Entity should not be equal to null");
 
        assertNotEquals(entity1, new Object(), "Entity should not be equal to an object of a different class");
    }
    
     
}
