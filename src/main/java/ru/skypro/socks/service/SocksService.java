package ru.skypro.socks.service;

import ru.skypro.socks.dto.Operation;
import ru.skypro.socks.dto.SocksProperties;

public interface SocksService {
    void socksIncome(SocksProperties properties);

    void socksOutcome(SocksProperties properties);

    int getSocksCount(String color, Operation operation, Integer cottonPart);
}
