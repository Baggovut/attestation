package ru.skypro.socks.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "socks")
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "color", nullable = false)
    private String color;
    @Column(name = "cotton_part", nullable = false)
    private Integer cottonPart;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
