package com.example.webstudio.service;

import com.example.webstudio.dto.ProductDto;
import com.example.webstudio.dto.response.BoardResponseDto;
import com.example.webstudio.dto.response.ServiceResponseDto;
import com.example.webstudio.dto.request.*;
import com.example.webstudio.dto.response.BoolResponseDto;
import com.example.webstudio.dto.response.ProductResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public interface WebService {

    ArrayList<ServiceResponseDto> getAllService();

    ArrayList<ProductResponseDto> getAllProduct();

    BoolResponseDto reservation(ReservationRequestDto request);

    BoolResponseDto addService(ServiceRequestDto request);

    BoolResponseDto updateService(ServiceRequestDto request);

    BoolResponseDto deleteService(ServiceIdRequestDto request);

    BoolResponseDto addProduct(ProductRequestDto request);

    BoolResponseDto updateProduct(ProductDto request);

    BoolResponseDto deleteProduct(ProductIdRequestDto request);

    BoolResponseDto writeArticle(BoardRequestDto request, String author);

    ResponseEntity<ArrayList<BoardResponseDto>> getAllArticles();
}
