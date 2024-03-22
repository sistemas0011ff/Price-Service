package com.ecommerce.priceservice.infrastructure.test;

import com.ecommerce.priceservice.infrastructure.MediatorService;
import com.ecommerce.priceservice.application.dto.PriceDTO;
import com.ecommerce.priceservice.application.query.GetPriceQuery;
import com.ecommerce.priceservice.shared.application.query.IQueryHandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith; 
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MediatorServiceTest {

    @Mock
    private IQueryHandler<GetPriceQuery, List<PriceDTO>> queryHandler;

    private MediatorService mediatorService;

    @BeforeEach
    void setUp() {
        // Mock the behaviors
        when(queryHandler.getQueryType()).thenReturn(GetPriceQuery.class);
        when(queryHandler.handle(any(GetPriceQuery.class))).thenReturn(Collections.emptyList());

        // Manually instantiate the MediatorService
        mediatorService = new MediatorService(Collections.emptyList(), Collections.singletonList(queryHandler));
    }

    @Test
    void dispatch_ShouldHandleQuery_WhenQueryHandlerIsPresent() {
        // Prepare the query
        GetPriceQuery query = new GetPriceQuery(LocalDateTime.now(), 1L, 1L);

        // Perform the dispatch
        List<PriceDTO> result = mediatorService.dispatch(query);

        // Assert the results
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}