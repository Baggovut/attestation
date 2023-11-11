package ru.skypro.socks.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.socks.entity.Socks;

import java.util.List;
import java.util.Optional;

public interface SocksRepository extends CrudRepository<Socks, Integer> {
    Optional<Socks> findByColorAndCottonPart(String color, Integer cottonPart);

    Optional<List<Socks>> findAllByColorAndCottonPartGreaterThan(String color, Integer cottonPart);
    Optional<List<Socks>> findAllByColorAndCottonPartLessThan(String color, Integer cottonPart);
    Optional<List<Socks>> findAllByColorAndCottonPartEquals(String color, Integer cottonPart);
}
