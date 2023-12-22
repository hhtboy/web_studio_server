package com.example.webstudio.service;

import com.example.webstudio.Entity.Article;
import com.example.webstudio.Entity.Course;
import com.example.webstudio.Entity.Product;
import com.example.webstudio.Entity.Reservation;
import com.example.webstudio.dto.ProductDto;
import com.example.webstudio.dto.response.*;
import com.example.webstudio.dto.request.*;
import com.example.webstudio.repository.WebRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainWebService implements WebService{

    private final WebRepository repository;

    @Override
    public ArrayList<ServiceResponseDto> getAllService() {
        ArrayList<ServiceResponseDto> result = new ArrayList<>();
        ArrayList<com.example.webstudio.Entity.Service> allService = null;
        try {
            allService = repository.getAllService();
        } catch (SQLException e) {
            return result;
        }
        for(com.example.webstudio.Entity.Service service:allService) {
            ArrayList<CourseResponseDto> courses = new ArrayList<CourseResponseDto>();
            for(Course course : service.getCourses()) {
                CourseResponseDto dto = new CourseResponseDto().builder()
                        .courseId(course.getCourseId())
                        .courseTitle(course.getCourseTitle())
                        .courseInfo(course.getCourseInfo())
                        .price(course.getPrice())
                        .imageURL(course.getImageURL())
                        .build();
                courses.add(dto);
            }
            ServiceResponseDto serviceResponseDto = new ServiceResponseDto().builder()
                    .serviceId(service.getServiceId())
                    .serviceName(service.getServiceName())
                    .price(service.getPrice())
                    .info(service.getInfo())
                    .timeTaken(service.getTimeTaken())
                    .course(courses)
                    .picture(service.getPicture())
                    .build();
            result.add(serviceResponseDto);
        }

        return result;
    }

    @Override
    public ArrayList<ProductResponseDto> getAllProduct() {
        ArrayList<ProductResponseDto> result = new ArrayList<>();

        try {
            ArrayList<Product> allProduct = repository.getAllProduct();
            for(Product product : allProduct) {
                ProductResponseDto dto = new ProductResponseDto().builder()
                        .productId(product.getProductId())
                        .productName(product.getProductName())
                        .price(product.getPrice())
                        .info(product.getInfo())
                        .picture(product.getPicture())
                        .build();
                result.add(dto);
            }
        } catch (SQLException e) {
            return result;
        }

        return result;
    }

    @Override
    public ArrayList<ReservationRequestDto> getAllReservation() {
        ArrayList<ReservationRequestDto> result = new ArrayList<>();

        try {
            ArrayList<Reservation> allReservation = repository.getAllReservation();
            for(Reservation reservation:allReservation) {
                ReservationRequestDto dto = new ReservationRequestDto().builder()
                        .serviceId(reservation.getServiceId())
                        .customerName(reservation.getCustomerName())
                        .totalPrice(reservation.getTotalPrice())
                        .courseId(reservation.getCourseId())
                        .date(reservation.getDate())
                        .phoneNumber(reservation.getPhoneNumber())
                        .build();
                result.add(dto);
            }
        } catch (SQLException e) {
            return result;
        }

        return result;
    }

    @Override
    public BoolResponseDto reservation(ReservationRequestDto request) {
        Reservation reservation = new Reservation().builder()
                .serviceId(request.getServiceId())
                .customerName(request.getCustomerName())
                .phoneNumber(request.getPhoneNumber())
                .date(request.getDate())
                .courseId(request.getCourseId())
                .totalPrice(request.getTotalPrice())
                .build();
        try {
            repository.insertReservation(reservation);
        } catch (SQLException e) {
            return new BoolResponseDto().builder().result(Boolean.FALSE).build();
        }
        return new BoolResponseDto().builder().result(Boolean.TRUE).build();
    }

    @Override
    public BoolResponseDto addService(ServiceRequestDto request) {
        ArrayList<Course> courses = new ArrayList<>();
        int count = 1;
        for(CourseRequestDto courseDto : request.getCourse()) {
            Course course = new Course().builder()
                    .courseId(count)
                    .courseTitle(courseDto.getCourseTitle())
                    .courseInfo(courseDto.getCourseInfo())
                    .price(courseDto.getPrice())
                    .imageURL(courseDto.getImageURL())
                    .build();
            courses.add(course);
            count ++;
        }
        com.example.webstudio.Entity.Service service = new com.example.webstudio.Entity.Service().builder()
                .serviceName(request.getServiceName())
                .price(request.getPrice())
                .info(request.getInfo())
                .timeTaken(request.getTimeTaken())
                .courses(courses)
                .picture(request.getPicture())
                .build();
        try {
            repository.insertService(service);
            return new BoolResponseDto().builder().result(Boolean.TRUE).build();
        } catch (SQLException e) {
            return new BoolResponseDto().builder().result(Boolean.FALSE).build();
        }
    }

    @Override
    public BoolResponseDto updateService(ServiceRequestDto request) {
        ArrayList<Course> courses = new ArrayList<>();

        int count = 1;
        for(CourseRequestDto dto : request.getCourse()) {
            Course course = new Course().builder()
                    .courseId(count)
                    .courseTitle(dto.getCourseTitle())
                    .courseInfo(dto.getCourseInfo())
                    .price(dto.getPrice())
                    .imageURL(dto.getImageURL())
                    .build();
            courses.add(course);
            count ++;
        }
        com.example.webstudio.Entity.Service service = new com.example.webstudio.Entity.Service().builder()
                .serviceId(request.getServiceId())
                .serviceName(request.getServiceName())
                .price(request.getPrice())
                .info(request.getInfo())
                .timeTaken(request.getTimeTaken())
                .courses(courses)
                .picture(request.getPicture())
                .build();
        try {
            repository.updateService(service);
            return new BoolResponseDto().builder().result(Boolean.TRUE).build();
        } catch (SQLException e) {
            return new BoolResponseDto().builder().result(Boolean.FALSE).build();
        }
    }

    @Override
    public BoolResponseDto deleteService(ServiceIdRequestDto request) {
        try {
            repository.deleteService(request.getServiceId());
            return new BoolResponseDto().builder().result(Boolean.TRUE).build();
        } catch (SQLException e) {
            return new BoolResponseDto().builder().result(Boolean.FALSE).build();
        }
    }

    @Override
    public BoolResponseDto addProduct(ProductRequestDto request) {
        Product product = new Product().builder()
                .productName(request.getProductName())
                .price(request.getPrice())
                .info(request.getInfo())
                .picture(request.getPicture())
                .build();
        try {
            repository.insertProduct(product);
            return new BoolResponseDto().builder().result(Boolean.TRUE).build();
        } catch (SQLException e) {
            return new BoolResponseDto().builder().result(Boolean.FALSE).build();
        }
    }

    @Override
    public BoolResponseDto updateProduct(ProductDto request) {
        Product product = new Product().builder()
                .productId(request.getProductId())
                .productName(request.getProductName())
                .price(request.getPrice())
                .info(request.getInfo())
                .picture(request.getPicture())
                .build();
        try {
            repository.updateProduct(product);
            return new BoolResponseDto().builder().result(Boolean.TRUE).build();
        } catch (SQLException e) {
            return new BoolResponseDto().builder().result(Boolean.FALSE).build();
        }
    }

    @Override
    public BoolResponseDto deleteProduct(ProductIdRequestDto request) {
        try {
            repository.deleteProduct(request.getProductId());
            return new BoolResponseDto().builder().result(Boolean.TRUE).build();
        } catch (SQLException e) {
            return new BoolResponseDto().builder().result(Boolean.FALSE).build();
        }
    }

    @Override
    public BoolResponseDto writeArticle(BoardRequestDto request, String author) {
        SimpleDateFormat format1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        String time1 = format1.format(time);


        Article article = new Article().builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(author)
                .date(time1)
                .build();

        try {
            repository.insertArticle(article);
            return new BoolResponseDto().builder().result(Boolean.TRUE).build();
        } catch (SQLException e) {
            return new BoolResponseDto().builder().result(Boolean.FALSE).build();
        }
    }

    @Override
    public ResponseEntity<ArrayList<BoardResponseDto>> getAllArticles() {
        try {
            ArrayList<Article> articles = repository.getAllArticles();
            ArrayList<BoardResponseDto> dtos = new ArrayList<>();
            for(Article article : articles) {
                BoardResponseDto dto = new BoardResponseDto().builder()
                        .title(article.getTitle())
                        .content(article.getContent())
                        .author(article.getAuthor())
                        .date(article.getDate())
                        .articleId(article.getArticleId())
                        .build();
                dtos.add(dto);
            }
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
