package com.ecommerce.priceservice.infrastructure.adapter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import com.ecommerce.priceservice.domain.dto.PriceDomainDTO;
import com.ecommerce.priceservice.infrastructure.repository.PriceRepository;
import com.ecommerce.priceservice.infrastructure.persistence.model.Price;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JpaPricePortAdapterTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private JpaPricePortAdapter adapter;

    @Test
    void whenFindApplicablePricesIsCalled_thenReturnsPriceDomainDTOList() {
        // Arrange
        LocalDateTime date = LocalDateTime.now();
        long brandId = 1L;
        long productId = 1L;
        BigDecimal priceValue = new BigDecimal("100.00");
        String currency = "EUR";
        int priceList = 1;
        int priority = 1;

        // Create Price mock with all properties set
        Price mockPrice = new Price(1L, brandId, date.minusDays(1), date.plusDays(1), priceList, productId, priority, priceValue, currency);
        List<Price> mockPriceList = Collections.singletonList(mockPrice);

        // Setup the repository mock to return the list of prices
        when(priceRepository.findApplicablePrices(date, productId, brandId)).thenReturn(mockPriceList);

        // Create PriceDomainDTO mock with all properties set
        PriceDomainDTO mockPriceDomainDTO = new PriceDomainDTO();
        mockPriceDomainDTO.setId(mockPrice.getId());
        mockPriceDomainDTO.setBrandId(mockPrice.getBrandId());
        mockPriceDomainDTO.setStartDate(mockPrice.getStartDate());
        mockPriceDomainDTO.setEndDate(mockPrice.getEndDate());
        mockPriceDomainDTO.setPriceList(mockPrice.getPriceList());
        mockPriceDomainDTO.setProductId(mockPrice.getProductId());
        mockPriceDomainDTO.setPriority(mockPrice.getPriority());
        mockPriceDomainDTO.setPrice(mockPrice.getPrice());
        mockPriceDomainDTO.setCurrency(mockPrice.getCurrency());

        // Setup the model mapper to return the PriceDomainDTO when mapping
        when(modelMapper.map(any(Price.class), eq(PriceDomainDTO.class))).thenReturn(mockPriceDomainDTO);

        // Act
        List<PriceDomainDTO> result = adapter.findApplicablePrices(date, productId, brandId);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        PriceDomainDTO resultDTO = result.get(0);
        assertEquals(mockPriceDomainDTO.getId(), resultDTO.getId());
        assertEquals(mockPriceDomainDTO.getBrandId(), resultDTO.getBrandId());
        assertEquals(mockPriceDomainDTO.getStartDate(), resultDTO.getStartDate());
        assertEquals(mockPriceDomainDTO.getEndDate(), resultDTO.getEndDate());
        assertEquals(mockPriceDomainDTO.getPriceList(), resultDTO.getPriceList());
        assertEquals(mockPriceDomainDTO.getProductId(), resultDTO.getProductId());
        assertEquals(mockPriceDomainDTO.getPriority(), resultDTO.getPriority());
        assertEquals(0, mockPriceDomainDTO.getPrice().compareTo(resultDTO.getPrice()));
        assertEquals(mockPriceDomainDTO.getCurrency(), resultDTO.getCurrency());
    }
}

