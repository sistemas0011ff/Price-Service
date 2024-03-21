package com.ecommerce.priceservice.application.query; 
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime; 
import static org.assertj.core.api.Assertions.assertThat; 

class GetPriceQueryTest {

    @Test
    void testGetPriceQuery() {
        // Arrange
        LocalDateTime expectedApplicationDate = LocalDateTime.now();
        long expectedProductId = 1L;
        long expectedBrandId = 100L;

        // Act
        GetPriceQuery query = new GetPriceQuery(expectedApplicationDate, expectedProductId, expectedBrandId);

        // Assert
        assertThat(query.getApplicationDate()).isEqualTo(expectedApplicationDate);
        assertThat(query.getProductId()).isEqualTo(expectedProductId);
        assertThat(query.getBrandId()).isEqualTo(expectedBrandId);
    }

    
}
