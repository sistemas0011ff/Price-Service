package com.ecommerce.priceservice.common.test;

import org.junit.jupiter.api.Test;

import com.ecommerce.priceservice.common.GeneralResponseDTO;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class GeneralResponseDTOTest {

    @Test
    void builderShouldConstructObjectCorrectly() {
        String expectedCodigo = "200";
        String expectedMessage = "Success";
        String expectedData = "Data";
        List<String> expectedErrors = List.of("Error1", "Error2");
        Boolean expectedWarnings = false;

        GeneralResponseDTO<String> response = GeneralResponseDTO.<String>builder()
                .codigo(expectedCodigo)
                .message(expectedMessage)
                .data(expectedData)
                .errors(expectedErrors)
                .warnings(expectedWarnings)
                .build();

        assertEquals(expectedCodigo, response.getCodigo());
        assertEquals(expectedMessage, response.getMessage());
        assertEquals(expectedData, response.getData());
        assertEquals(expectedErrors, response.getErrors());
        assertEquals(expectedWarnings, response.getWarnings());
    }

    @Test
    void gettersAndSettersShouldFunctionCorrectly() {
        GeneralResponseDTO<String> response = new GeneralResponseDTO<>("", "", null, null, null);

        String expectedCodigo = "404";
        String expectedMessage = "Not Found";
        String expectedData = "NotFoundData";
        List<String> expectedErrors = List.of("NotFound Error");
        Boolean expectedWarnings = true;

        response.setCodigo(expectedCodigo);
        response.setMessage(expectedMessage);
        response.setData(expectedData);
        response.setErrors(expectedErrors);
        response.setWarnings(expectedWarnings);

        assertEquals(expectedCodigo, response.getCodigo());
        assertEquals(expectedMessage, response.getMessage());
        assertEquals(expectedData, response.getData());
        assertEquals(expectedErrors, response.getErrors());
        assertEquals(expectedWarnings, response.getWarnings());
    }
}