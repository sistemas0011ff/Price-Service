package com.ecommerce.priceservice.application.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PriceResponseTest {

    private List<PriceDTO> priceList;
    private PriceResponse priceResponse;
    private final String message = "Precios actualizados";

    @BeforeEach
    public void setUp() {
        priceList = new ArrayList<>();
         
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setId(1L);
        priceDTO.setBrandId(2L);
        priceDTO.setStartDate(LocalDateTime.now());
        priceDTO.setEndDate(LocalDateTime.now().plusDays(1));
        priceDTO.setPriceList(1);
        priceDTO.setProductId(100L);
        priceDTO.setPriority(10);
        priceDTO.setPrice(BigDecimal.valueOf(100.0));
        priceDTO.setCurrency("EUR");
        
        priceList.add(priceDTO);
        priceResponse = new PriceResponse(priceList, message);
    }

    @Test
    public void deberiaAlmacenarYRecuperarMensajeCorrectamente() {
        assertThat(priceResponse.getMessage()).isEqualTo(message);
    }

    @Test
    public void deberiaAlmacenarYRecuperarListaDePreciosCorrectamente() {
        assertThat(priceResponse.getPrices()).isEqualTo(priceList);
    }

    @Test
    public void deberiaManejarListaDePreciosVacia() {
        PriceResponse responseConListaVacia = new PriceResponse(new ArrayList<>(), message);
        assertThat(responseConListaVacia.getPrices()).isEmpty();
    }

    @Test
    public void deberiaManejarListaDePreciosNula() {
        PriceResponse responseConListaNula = new PriceResponse(null, message);
        assertThat(responseConListaNula.getPrices()).isNull();
    }
}
