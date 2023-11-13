package ru.skypro.dto;

import org.springframework.stereotype.Component;
import ru.skypro.entity.SocksStock;
@Component
public class SocksMapper {

    public SocksStock fromDto(SocksDto socksDto) {
        return new SocksStock()
                .setColor(socksDto.getColor())
                .setCottonPart(socksDto.getCottonPart())
                .setQuantity(socksDto.getQuantity());
    }

    public SocksDto toDto(SocksStock socksStock) {
        return new SocksDto()
                .setColor(socksStock.getColor())
                .setCottonPart(socksStock.getCottonPart())
                .setQuantity(socksStock.getQuantity());
    }
}
