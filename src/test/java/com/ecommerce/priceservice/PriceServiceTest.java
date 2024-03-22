 package com.ecommerce.priceservice;
 import com.ecommerce.priceservice.application.dto.PriceDTO;
 import com.ecommerce.priceservice.application.dto.PriceResponse;
 import com.ecommerce.priceservice.application.query.GetPriceQuery;
 import com.ecommerce.priceservice.application.service.IPriceService;
 import com.ecommerce.priceservice.application.service.impl.PriceServiceImpl;
 import com.ecommerce.priceservice.infrastructure.IMediatorService;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Test;
 import org.mockito.ArgumentCaptor;
 import org.mockito.Captor;
 import org.mockito.Mock;
 import org.mockito.MockitoAnnotations;
 import java.math.BigDecimal;
 import java.time.LocalDateTime;
 import java.util.Collections;
 import static org.mockito.Mockito.*;
 import static org.junit.jupiter.api.Assertions.*;

 class PriceServiceTest {

     @Mock
     private IMediatorService mediatorServiceMock;

     private IPriceService priceService;

     @Captor
     private ArgumentCaptor<GetPriceQuery> getPriceQueryCaptor;

     @BeforeEach
     void setUp() {
         MockitoAnnotations.openMocks(this);
         priceService = new PriceServiceImpl(mediatorServiceMock);
     }

     private void setupMockPrice(LocalDateTime applicationDate, long productId, long brandId, BigDecimal price) {
         PriceDTO mockPrice = new PriceDTO();
         mockPrice.setStartDate(applicationDate);
         mockPrice.setEndDate(applicationDate.plusHours(1));  // Assuming a 1-hour window for simplicity
         mockPrice.setPrice(price);
         mockPrice.setProductId(productId);
         mockPrice.setBrandId(brandId);
         mockPrice.setCurrency("EUR");

         when(mediatorServiceMock.dispatch(getPriceQueryCaptor.capture())).thenReturn(Collections.singletonList(mockPrice));
     }

     private void assertPrice(LocalDateTime applicationDate, long productId, long brandId, BigDecimal expectedPrice, String expectedMessage) {
         PriceResponse response = priceService.getPrice(applicationDate, productId, brandId);

         assertNotNull(response);
         assertFalse(response.getPrices().isEmpty());
         assertEquals(expectedPrice, response.getPrices().get(0).getPrice());
         assertEquals(expectedMessage, response.getMessage());
     }

     @Test
     void whenRequestedAtTenAmOnFourteenth_thenApplyCorrectPrice() {
         LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
         setupMockPrice(applicationDate, 35455, 1, new BigDecimal("35.50"));
         assertPrice(applicationDate, 35455, 1, new BigDecimal("35.50"), "Prices successfully retrieved.");
     }

     @Test
     void whenRequestedAtFourPmOnFourteenth_thenApplyCorrectPrice() {
         LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0);
         setupMockPrice(applicationDate, 35455, 1, new BigDecimal("25.45"));
         assertPrice(applicationDate, 35455, 1, new BigDecimal("25.45"), "Prices successfully retrieved.");
     }

     @Test
     void whenRequestedAtNinePmOnFourteenth_thenApplyCorrectPrice() {
         LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 21, 0);
         setupMockPrice(applicationDate, 35455, 1, new BigDecimal("35.50"));
         assertPrice(applicationDate, 35455, 1, new BigDecimal("35.50"), "Prices successfully retrieved.");
     }

     @Test
     void whenRequestedAtTenAmOnFifteenth_thenApplyCorrectPrice() {
         LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0);
         setupMockPrice(applicationDate, 35455, 1, new BigDecimal("30.50"));
         assertPrice(applicationDate, 35455, 1, new BigDecimal("30.50"), "Prices successfully retrieved.");
     }

     @Test
     void whenRequestedAtNinePmOnSixteenth_thenApplyCorrectPrice() {
         LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 16, 21, 0);
         setupMockPrice(applicationDate, 35455, 1, new BigDecimal("38.95"));
         assertPrice(applicationDate, 35455, 1, new BigDecimal("38.95"), "Prices successfully retrieved.");
     }
 }
