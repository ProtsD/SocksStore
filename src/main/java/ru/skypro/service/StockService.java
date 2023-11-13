package ru.skypro.service;

import ru.skypro.dto.ComparisonOperator;
import ru.skypro.dto.SocksDto;

public interface StockService {
    void receiveSocks(SocksDto socksDto);

    void dispatchSocks(SocksDto socksDto);

    int getSocksInfo(String color, ComparisonOperator operation, Integer cottonPart);
}
