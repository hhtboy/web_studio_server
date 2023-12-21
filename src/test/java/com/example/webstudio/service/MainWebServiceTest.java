package com.example.webstudio.service;

import com.example.webstudio.Entity.Course;
import com.example.webstudio.dto.ProductDto;
import com.example.webstudio.dto.response.CourseResponseDto;
import com.example.webstudio.dto.response.ServiceResponseDto;
import com.example.webstudio.dto.request.*;
import com.example.webstudio.dto.response.ProductResponseDto;
import com.example.webstudio.repository.MysqlWebRepository;
import com.example.webstudio.repository.WebRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

@Slf4j
class MainWebServiceTest {

    WebRepository repository = new MysqlWebRepository();
    WebService service = new MainWebService(repository);

    @Test
    void getAllService() {
        ArrayList<ServiceResponseDto> allService = service.getAllService();

        for (ServiceResponseDto serviceResponseDto : allService) {
            log.info(serviceResponseDto.toString());
        }

    }

    @Test
    void getAllProduct() {
        ArrayList<ProductResponseDto> allProduct = service.getAllProduct();

        for (ProductResponseDto dto : allProduct) {
            log.info(dto.toString());
        }
    }

    @Test
    void insertReservation() {
        ArrayList<Integer> courseId = new ArrayList<>();
        courseId.add(1);
        courseId.add(2);
        courseId.add(3);
        ReservationRequestDto dto = new ReservationRequestDto().builder()
                .serviceId(12)
                .customerName("hwang")
                .phoneNumber("010-1234-5678")
                .date("2023-10-24 10:00:00")
                .courseId(courseId)
                .totalPrice(10000)
                .build();
        service.reservation(dto);

    }

    @Test
    void addService() {
        ArrayList<CourseRequestDto> courses = new ArrayList<>();
        CourseRequestDto courseDto = new CourseRequestDto().builder()
                .courseTitle("new course")
                .courseInfo("new course info")
                .price(1000)
                .imageURL("url")
                .build();
        CourseRequestDto courseDto2 = new CourseRequestDto().builder()
                .courseTitle("new course2")
                .courseInfo("new course info2")
                .price(2000)
                .imageURL("url2")
                .build();
        courses.add(courseDto);
//        courses.add(courseDto2);
        ServiceRequestDto dto = new ServiceRequestDto().builder()
                .serviceName("new service 100")
                .price(1000)
                .info("new serviec")
                .timeTaken(10)
                .course(courses)
                .picture("url")
                .build();
        log.info(service.addService(dto).getResult().toString());
    }

    @Test
    void updateService() {
        ArrayList<CourseRequestDto> courses = new ArrayList<>();
        CourseRequestDto dto1 = new CourseRequestDto().builder()
                .courseTitle("new course")
                .courseInfo("new course info")
                .price(1000)
                .imageURL("url")
                .build();


        courses.add(dto1);
        ServiceRequestDto dto = new ServiceRequestDto().builder()
                .serviceId(2)
                .serviceName("update service12")
                .price(100000)
                .info("update info")
                .timeTaken(10)
                .picture("new picture")
                .course(courses)
                .build();
        service.updateService(dto);
    }

    @Test
    void deleteService() {
        ServiceIdRequestDto dto = new ServiceIdRequestDto().builder()
                .serviceId(2)
                .build();
        log.info(service.deleteService(dto).getResult().toString());
    }

    @Test
    void insertProduct() {
        ProductRequestDto dto = new ProductRequestDto().builder()
                .productName("new product")
                .price(100)
                .info("info")
                .picture("url")
                .build();
        service.addProduct(dto);
    }


    @Test
    void updateProduct() {
        ProductDto dto = new ProductDto().builder()
                .productId(3)
                .productName("new product name")
                .price(10)
                .info("new info")
                .picture("new url")
                .build();
        service.updateProduct(dto);

    }

    @Test
    void deleteProduct() {
        ProductIdRequestDto dto = new ProductIdRequestDto().builder()
                .productId(3)
                .build();
        service.deleteProduct(dto);
    }
}