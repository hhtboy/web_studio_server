package com.example.webstudio.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductRequestDto {
    private String productName;
    private int price;
    private String info;
    private String picture;
}
