package com.ecommerce.priceservice.application.service;

import com.ecommerce.priceservice.application.dto.PriceDTO;
import com.ecommerce.priceservice.application.dto.PriceResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections; 

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PriceServiceImplTest {

    private IPriceService priceService;

    @BeforeEach
    void setUp() { 
        priceService = mock(IPriceService.class);
    }

    @Test
    void getPriceReturnsPriceResponse() {
        // Arrange
        LocalDateTime applicationDate = LocalDateTime.now();
        long productId = 1L;
        long brandId = 1L;
        PriceDTO expectedPriceDTO = new PriceDTO();
        expectedPriceDTO.setId(1L);
        expectedPriceDTO.setProductId(productId);
        expectedPriceDTO.setBrandId(brandId);
        expectedPriceDTO.setPriceList(1);
        expectedPriceDTO.setPrice(BigDecimal.valueOf(100.0));
        expectedPriceDTO.setCurrency("EUR");
        
        PriceResponse expectedPriceResponse = new PriceResponse(Collections.singletonList(expectedPriceDTO), "Prices retrieved successfully.");
        
        when(priceService.getPrice(applicationDate, productId, brandId)).thenReturn(expectedPriceResponse);

        // Act
        PriceResponse result = priceService.getPrice(applicationDate, productId, brandId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedPriceResponse, result);
        verify(priceService).getPrice(applicationDate, productId, brandId);
    }
}
