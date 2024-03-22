package com.ecommerce.priceservice.controller;

import com.ecommerce.priceservice.application.dto.PriceDTO;
import com.ecommerce.priceservice.application.dto.PriceResponse;
import com.ecommerce.priceservice.application.service.IPriceService;
import com.ecommerce.priceservice.common.GeneralResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PriceController {

    private final IPriceService priceService;

    public PriceController(IPriceService priceService) {
        this.priceService = priceService;
    }

    @Operation(summary = "Get applicable prices for a product on a specific date", description = "Provides all applicable prices of a product for the given brand and date")
    @GetMapping("/prices/{date}/{productId}/{brandId}")
    public ResponseEntity<GeneralResponseDTO<List<PriceDTO>>> getPrices(
            @Parameter(description = "Date for the price application in yyyy-MM-ddTHH:mm:ss format", required = true, schema = @Schema(type = "string", format = "date-time")) @PathVariable("date") String dateStr,
            @Parameter(description = "ID of the product for which prices are requested", required = true) @PathVariable("productId") long productId,
            @Parameter(description = "ID of the brand of the product", required = true) @PathVariable("brandId") long brandId) {
        
        try {
            LocalDateTime applicationDate = LocalDateTime.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            PriceResponse priceResponse = priceService.getPrice(applicationDate, productId, brandId);

            GeneralResponseDTO<List<PriceDTO>> responseDTO = GeneralResponseDTO.<List<PriceDTO>>builder()
                    .codigo("200")
                    .message(priceResponse.getMessage())
                    .data(priceResponse.getPrices())
                    .build();

            return ResponseEntity.ok(responseDTO);
        } catch (DateTimeParseException ex) {
            GeneralResponseDTO<List<PriceDTO>> responseDTO = GeneralResponseDTO.<List<PriceDTO>>builder()
                .codigo("400")
                .message("Invalid date format. Use yyyy-MM-ddTHH:mm:ss.")
                .errors(Collections.singletonList(ex.getMessage()))
                .warnings(false)
                .build();

            return ResponseEntity.badRequest().body(responseDTO);
        } catch (Exception ex) {
            GeneralResponseDTO<List<PriceDTO>> responseDTO = GeneralResponseDTO.<List<PriceDTO>>builder()
                    .codigo("500")
                    .message("Internal server error.")
                    .errors(Collections.singletonList(ex.getMessage()))
                    .warnings(false)
                    .build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }
}
