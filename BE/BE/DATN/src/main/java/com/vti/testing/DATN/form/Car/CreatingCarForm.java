package com.vti.testing.DATN.form.Car;

import com.vti.testing.DATN.entity.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreatingCarForm {

    private String name;
    private String price;
    private String image;
    private Color color;


}
