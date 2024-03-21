package com.ecommerce.priceservice.infrastructure.adapter;

import com.ecommerce.priceservice.domain.dto.PriceDomainDTO;
import com.ecommerce.priceservice.domain.port.PricePort;
import com.ecommerce.priceservice.infrastructure.repository.PriceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JpaPricePortAdapter implements PricePort {

    private final PriceRepository priceRepository;
    private final ModelMapper modelMapper;

    public JpaPricePortAdapter(PriceRepository priceRepository, ModelMapper modelMapper) {
        this.priceRepository = priceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PriceDomainDTO> findApplicablePrices(LocalDateTime date, long productId, long brandId) {
        return priceRepository.findApplicablePrices(date, productId, brandId)
                              .stream()
                              .map(entity -> modelMapper.map(entity, PriceDomainDTO.class))
                              .collect(Collectors.toList());
    }
}
