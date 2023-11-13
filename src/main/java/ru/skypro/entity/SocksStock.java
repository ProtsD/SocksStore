package ru.skypro.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;


@Entity
@Table(name = "socks_stock")
@Data
@Accessors(chain = true)
public class SocksStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "color", nullable = false)
    private String color;
    @Column(name = "cotton_part", nullable = false)
    private Integer cottonPart;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
