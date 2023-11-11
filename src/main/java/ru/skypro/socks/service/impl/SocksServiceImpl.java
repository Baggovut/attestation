package ru.skypro.socks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.socks.dto.Operation;
import ru.skypro.socks.dto.SocksProperties;
import ru.skypro.socks.entity.Socks;
import ru.skypro.socks.exception.BadRequestException;
import ru.skypro.socks.exception.NotFoundException;
import ru.skypro.socks.repository.SocksRepository;
import ru.skypro.socks.service.SocksService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService {
    private final SocksRepository socksRepository;

    @Override
    public void socksIncome(SocksProperties properties) {
        Socks existedSocks = socksRepository.findByColorAndCottonPart(properties.getColor(), properties.getCottonPart()).orElse(null);

        if (existedSocks == null) {
            existedSocks = new Socks()
                    .setColor(properties.getColor())
                    .setCottonPart(properties.getCottonPart())
                    .setQuantity(properties.getQuantity());
        } else {
            existedSocks.setQuantity(existedSocks.getQuantity() + properties.getQuantity());
        }

        socksRepository.save(existedSocks);
    }

    @Override
    public void socksOutcome(SocksProperties properties) {
        Socks existedSocks = socksRepository.findByColorAndCottonPart(properties.getColor(), properties.getCottonPart())
                .orElseThrow(NotFoundException::new);

        if (existedSocks.getQuantity() < properties.getQuantity()) {
            throw new BadRequestException();
        }

        existedSocks.setQuantity(existedSocks.getQuantity() - properties.getQuantity());

        if (existedSocks.getQuantity() == 0) {
            socksRepository.deleteById(existedSocks.getId());
        } else {
            socksRepository.save(existedSocks);
        }
    }

    @Override
    public int getSocksCount(String color, Operation operation, Integer cottonPart) {
        int socksCount = 0;
        List<Socks> socks = null;

        if (operation.equals(Operation.moreThan)) {
            socks = socksRepository.findAllByColorAndCottonPartGreaterThan(color, cottonPart).orElseThrow();
        } else if (operation.equals(Operation.lessThan)) {
            socks = socksRepository.findAllByColorAndCottonPartLessThan(color, cottonPart).orElseThrow();
        } else if (operation.equals(Operation.equal)) {
            socks = socksRepository.findAllByColorAndCottonPartEquals(color, cottonPart).orElseThrow();
        }

        if (socks != null) {
            for (Socks currentSocks : socks) {
                socksCount += currentSocks.getQuantity();
            }
        }

        return socksCount;
    }
}
