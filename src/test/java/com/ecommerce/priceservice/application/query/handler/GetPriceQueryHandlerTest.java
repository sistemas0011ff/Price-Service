package com.ecommerce.priceservice.application.query.handler;

import com.ecommerce.priceservice.application.dto.PriceDTO;
import com.ecommerce.priceservice.application.query.GetPriceQuery;
import com.ecommerce.priceservice.domain.port.PricePort;
import com.ecommerce.priceservice.domain.dto.PriceDomainDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GetPriceQueryHandlerTest {

    private PricePort pricePort;
    private ModelMapper modelMapper;
    private GetPriceQueryHandler queryHandler;

    @BeforeEach
    void setUp() {
        pricePort = mock(PricePort.class);
        modelMapper = mock(ModelMapper.class);
        queryHandler = new GetPriceQueryHandler(pricePort, modelMapper);
    }

    @Test
    void whenHandleIsCalled_thenRetrievePrices() {
        // Arrange
        GetPriceQuery query = new GetPriceQuery(LocalDateTime.now(), 1L, 1L);
        PriceDomainDTO domainDto = new PriceDomainDTO(); // Assume this is your domain DTO with proper fields set
        when(pricePort.findApplicablePrices(any(LocalDateTime.class), anyLong(), anyLong()))
            .thenReturn(Collections.singletonList(domainDto));
        when(modelMapper.map(any(PriceDomainDTO.class), eq(PriceDTO.class)))
            .thenReturn(new PriceDTO()); // Assume this is how you would map your domain DTO to your application DTO

        // Act
        List<PriceDTO> result = queryHandler.handle(query);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        verify(pricePort).findApplicablePrices(query.getApplicationDate(), query.getProductId(), query.getBrandId());
        verify(modelMapper).map(any(PriceDomainDTO.class), eq(PriceDTO.class));
    }
}
