package com.vti.testing.DATN.dto;

import com.vti.testing.DATN.entity.Color;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarDTO {
    private int id;
    private String name;
    private String price;
    private String image;
    private Color color;
}
