package ru.skypro.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

@Component
@Data
@Accessors(chain = true)
public class SocksDto {
    private String color;
    @Min(0)
    @Max(100)
    private int cottonPart;
    @Min(0)
    private int quantity;
}
