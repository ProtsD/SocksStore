package ru.skypro.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.skypro.dto.ComparisonOperator;
import ru.skypro.dto.SocksDto;
import ru.skypro.service.StockService;

@RestController
@RequestMapping("/socks")
@AllArgsConstructor
@Validated
public class SocksController {
    private final StockService stockService;

    @PostMapping("/income")

    public ResponseEntity<?> receiveSocks(@RequestBody @Valid SocksDto socksDto) {
        stockService.receiveSocks(socksDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/outcome")
    public ResponseEntity<?> dispatchSocks(@RequestBody @Valid SocksDto socksDto) {
        stockService.dispatchSocks(socksDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<Integer> getSocksInfo(
            @RequestParam(name = "color") String color,
            @RequestParam(name = "operation") ComparisonOperator operation,
            @RequestParam(name = "cottonPart") @Min(0) @Max(100) Integer cottonPart) {
        return ResponseEntity.ok().body(stockService.getSocksInfo(color, operation, cottonPart));
    }
}
