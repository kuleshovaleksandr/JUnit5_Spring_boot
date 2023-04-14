package com.example.junit5_spring_boot.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.text.DecimalFormat;

@Data
@RequiredArgsConstructor
public class ItemDto {
    @NonNull
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private Double price;
    @NonNull
    private Integer quantity;

    private Double value;

    public Double getValue() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String roundedString = decimalFormat.format(price * quantity);
        value = Double.valueOf(roundedString.replace(",", "."));
        return value;
    }
}
