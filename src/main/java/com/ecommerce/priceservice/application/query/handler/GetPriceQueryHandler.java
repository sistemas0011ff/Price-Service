package com.ecommerce.priceservice.application.query.handler;

import com.ecommerce.priceservice.application.dto.PriceDTO;
import com.ecommerce.priceservice.application.query.GetPriceQuery;
import com.ecommerce.priceservice.domain.port.PricePort;
import com.ecommerce.priceservice.domain.dto.PriceDomainDTO;
import com.ecommerce.priceservice.shared.application.query.IQueryHandler;
import org.modelmapper.ModelMapper; 
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List; 

@Component
public class GetPriceQueryHandler implements IQueryHandler<GetPriceQuery, List<PriceDTO>> {

   private final PricePort pricePort;
   private final ModelMapper modelMapper;
 
    public GetPriceQueryHandler(PricePort pricePort, ModelMapper modelMapper) {
        this.pricePort = pricePort;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PriceDTO> handle(GetPriceQuery query) {
        System.out.println("[GetPriceQueryHandler] Entering handle method with GetPriceQuery: " + query);

        List<PriceDomainDTO> priceDomainDTOList = pricePort.findApplicablePrices(
            query.getApplicationDate(),
            query.getProductId(),
            query.getBrandId()
        );
 
        PriceDTO highestPriorityPrice = priceDomainDTOList.isEmpty() ? null : modelMapper.map(priceDomainDTOList.get(0), PriceDTO.class);

        System.out.println("[GetPriceQueryHandler] Exiting handle method");
        return highestPriorityPrice == null ? Collections.emptyList() : Collections.singletonList(highestPriorityPrice);
    }
    
    @Override
    public Class<GetPriceQuery> getQueryType() {
        return GetPriceQuery.class;
    }
}
