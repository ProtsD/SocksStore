package ru.skypro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.entity.SocksStock;

import java.util.List;
import java.util.Optional;

@Repository
public interface SocksRepository extends JpaRepository<SocksStock, Integer> {
    Optional<SocksStock> findByColorAndCottonPart(String color, int cottonPart);

    Optional<List<SocksStock>> findAllByColorAndCottonPartGreaterThan(String color, int cottonPart);

    Optional<List<SocksStock>> findAllByColorAndCottonPartEquals(String color, int cottonPart);

    Optional<List<SocksStock>> findAllByColorAndCottonPartLessThan(String color, int cottonPart);
}
