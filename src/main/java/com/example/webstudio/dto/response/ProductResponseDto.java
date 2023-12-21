package com.example.webstudio.dto.response;

import com.example.webstudio.Entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {
    private int productId;
    private String productName;
    private int price;
    private String info;
    private String picture;

    public String toString() {
        return productId + ", " + productName + ", " + price + ", " + info + ", " + picture;
    }
}
