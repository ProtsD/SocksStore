package ru.skypro.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.dto.ComparisonOperator;
import ru.skypro.dto.SocksDto;
import ru.skypro.dto.SocksMapper;
import ru.skypro.entity.SocksStock;
import ru.skypro.exсeption.BadRequestException;
import ru.skypro.exсeption.NotFoundException;
import ru.skypro.repository.SocksRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements StockService {
    private final SocksRepository socksRepository;
    private final SocksMapper socksMapper;

    @Override
    public void receiveSocks(SocksDto socksDto) {
        Optional<SocksStock> socksIncomeType = socksRepository.findByColorAndCottonPart(socksDto.getColor(), socksDto.getCottonPart());
        if (socksIncomeType.isPresent()) {
            SocksStock socksEntity = socksIncomeType.orElseThrow();
            int updateValue = socksEntity.getQuantity() + socksDto.getQuantity();
            socksRepository.save(socksEntity.setQuantity(updateValue));
        } else {
            socksRepository.save(socksMapper.fromDto(socksDto));
        }
    }

    @Override
    public void dispatchSocks(SocksDto socksDto) {
        Optional<SocksStock> socksOutcomeType = socksRepository.findByColorAndCottonPart(socksDto.getColor(), socksDto.getCottonPart());
        if (socksOutcomeType.isPresent()) {

            SocksStock socksEntity = socksOutcomeType.orElseThrow(NotFoundException::new);
            if (socksEntity.getQuantity() < socksDto.getQuantity()) throw new BadRequestException();

            int updateValue = socksEntity.getQuantity() - socksDto.getQuantity();
            if (updateValue == 0) {
                socksRepository.deleteById(socksEntity.getId());
            } else {
                socksRepository.save(socksEntity.setQuantity(updateValue));
            }

        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public int getSocksInfo(String color, ComparisonOperator operation, Integer cottonPart) {
        List<SocksStock> socksList = null;
        int socksUnits = 0;

        if (operation.equals(ComparisonOperator.MORETHAN)) {
            socksList = socksRepository.findAllByColorAndCottonPartGreaterThan(color, cottonPart).orElseThrow();
        } else if (operation.equals(ComparisonOperator.EQUAL)) {
            socksList = socksRepository.findAllByColorAndCottonPartEquals(color, cottonPart).orElseThrow();
        } else if (operation.equals(ComparisonOperator.LESSTHAN)) {
            socksList = socksRepository.findAllByColorAndCottonPartLessThan(color, cottonPart).orElseThrow();
        }

        if (socksList != null) {
            for (SocksStock socksItem : socksList) {
                socksUnits += socksItem.getQuantity();
            }
        }
        return socksUnits;
    }
}
