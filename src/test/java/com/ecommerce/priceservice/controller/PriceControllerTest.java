package com.ecommerce.priceservice.controller;

import com.ecommerce.priceservice.application.dto.PriceDTO;
import com.ecommerce.priceservice.application.dto.PriceResponse;
import com.ecommerce.priceservice.application.service.IPriceService;
import com.ecommerce.priceservice.common.GeneralResponseDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals; 

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @Mock
    private IPriceService priceService;

    @InjectMocks
    private PriceController priceController;

    private final LocalDateTime testDate = LocalDateTime.now();
    private final long testProductId = 1L;
    private final long testBrandId = 1L;
    private final String validDateString = testDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
  
    @BeforeEach
    void setUp() {
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setId(1L);
        priceDTO.setProductId(testProductId);
        priceDTO.setBrandId(testBrandId);
        priceDTO.setPriceList(1);
        priceDTO.setPrice(BigDecimal.valueOf(100.0));
        priceDTO.setCurrency("EUR");

        PriceResponse priceResponse = new PriceResponse(Collections.singletonList(priceDTO), "Prices retrieved successfully.");

        when(priceService.getPrice(any(LocalDateTime.class), any(Long.class), any(Long.class)))
                .thenReturn(priceResponse);
    }

    @Test
    void whenGetPricesCalledWithValidDate_thenReturnOkAndPriceResponse() {
        ResponseEntity<GeneralResponseDTO<List<PriceDTO>>> response = priceController.getPrices(validDateString, testProductId, testBrandId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Prices retrieved successfully.", response.getBody().getMessage());
        assertEquals(1, response.getBody().getData().size());
        assertEquals(testProductId, response.getBody().getData().get(0).getProductId());
    }
 
}
