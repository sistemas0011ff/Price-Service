package com.ecommerce.priceservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ecommerce.priceservice.application.dto.PriceDTO;
import com.ecommerce.priceservice.application.dto.PriceResponse;
import com.ecommerce.priceservice.application.query.GetPriceQuery;
import com.ecommerce.priceservice.application.service.IPriceService;
import com.ecommerce.priceservice.application.service.impl.PriceServiceImpl;
import com.ecommerce.priceservice.infrastructure.IMediatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections; 

class PriceServiceTest {

    private IPriceService priceService;
    private IMediatorService mediatorServiceMock;

    @BeforeEach
    void setUp() {
        mediatorServiceMock = mock(IMediatorService.class);
        priceService = new PriceServiceImpl(mediatorServiceMock);
    }

    private void assertPriceResponse(LocalDateTime requestTime, BigDecimal priceValue, String expectedMessage) {
        PriceDTO expectedPriceDTO = new PriceDTO();
        expectedPriceDTO.setPrice(priceValue);

        when(mediatorServiceMock.dispatch(any(GetPriceQuery.class)))
                .thenReturn(Collections.singletonList(expectedPriceDTO));

        PriceResponse priceResponse = priceService.getPrice(requestTime, 35455, 1);

        assertNotNull(priceResponse);
        assertNotNull(priceResponse.getPrices());
        assertFalse(priceResponse.getPrices().isEmpty());
        assertEquals(priceValue, priceResponse.getPrices().get(0).getPrice());
        assertEquals(expectedMessage, priceResponse.getMessage());
    }

    @Test
    void whenRequestedAtTenAmOnFourteenth_thenReturnCorrectPrice() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 14, 10, 0);
        BigDecimal priceValue = BigDecimal.valueOf(35.50);
        assertPriceResponse(requestTime, priceValue, "Prices retrieved successfully.");
    }

    @Test
    void whenRequestedAtFourPmOnFourteenth_thenReturnCorrectPrice() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 14, 16, 0);
        BigDecimal priceValue = BigDecimal.valueOf(25.45);
        assertPriceResponse(requestTime, priceValue, "Prices retrieved successfully.");
    }

    @Test
    void whenRequestedAtNinePmOnFourteenth_thenReturnCorrectPrice() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 14, 21, 0);
        BigDecimal priceValue = BigDecimal.valueOf(35.50);
        assertPriceResponse(requestTime, priceValue, "Prices retrieved successfully.");
    }

    @Test
    void whenRequestedAtTenAmOnFifteenth_thenReturnCorrectPrice() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 15, 10, 0);
        BigDecimal priceValue = BigDecimal.valueOf(30.50);
        assertPriceResponse(requestTime, priceValue, "Prices retrieved successfully.");
    }

    @Test
    void whenRequestedAtNinePmOnSixteenth_thenReturnCorrectPrice() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 16, 21, 0);
        BigDecimal priceValue = BigDecimal.valueOf(38.95);
        assertPriceResponse(requestTime, priceValue, "Prices retrieved successfully.");
    }

    @Test
    void whenNoPricesAvailable_thenReturnEmptyListWithNoPricesFoundMessage() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 16, 21, 0);

        when(mediatorServiceMock.dispatch(any(GetPriceQuery.class)))
                .thenReturn(Collections.emptyList());

        PriceResponse priceResponse = priceService.getPrice(requestTime, 35455, 1);

        assertNotNull(priceResponse);
        assertTrue(priceResponse.getPrices().isEmpty());
        assertEquals("No price found for the entered parameters.", priceResponse.getMessage());
    }
}
