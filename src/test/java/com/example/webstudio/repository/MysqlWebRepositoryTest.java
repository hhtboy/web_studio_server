package com.example.webstudio.repository;

import com.example.webstudio.Entity.Course;
import com.example.webstudio.Entity.Product;
import com.example.webstudio.Entity.Reservation;
import com.example.webstudio.Entity.Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class MysqlWebRepositoryTest {

    MysqlWebRepository repository = new MysqlWebRepository();

    @Test
    void test1() {
        String[] regs = new String[4];
        regs[0] = "'";
        regs[1] = ")";
        regs[2] = "(";
        regs[3] = "union";
        String pw = "' or '1' = '1";
        for(String reg : regs) {
            if(pw.contains(reg)) {
                log.info("yes");
                break;
            }
        }


//        try {
//            ArrayList<Integer> array = new ArrayList<Integer>();
//            array.add(1);
//            array.add(2);
//            array.add(3);
//            Service hello = new Service().builder().serviceName("hello")
//                    .price(1000)
//                    .picture("url")
//                    .info("readme")
//                    .courseId(array)
//                    .timeTaken(100)
//                    .build();
//            repository.insertService(hello);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        ArrayList<Service> allService = null;
        try {
            allService = repository.getAllService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for(Service service : allService) {
            log.info(service.getCourses().toString());
        }

//        ArrayList<Product> allProduct = null;
//        try {
//            allProduct = repository.getAllProduct();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        for (Product product : allProduct) {
//            log.info(product.getProductName().toString());
//        }

//        try {
//            Service service = allService.get(0);
//            service.setServiceName("new service name");
//
//            repository.updateService(service);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            Service service = allService.get(0);
//            repository.deleteService(service.getServiceId());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        Product product = allProduct.get(0);
//        product.setProductName("new product name");
//        try {
//            repository.insertProduct(product);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }


//        product.setProductName("update product name");
//        try {
//            repository.updateProduct(product);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            repository.deleteProduct(product.getProductId());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            Reservation reservation = new Reservation().builder()
//                    .serviceId(12)
//                    .customerName("hwang")
//                    .phoneNumber("010-1234-5678")
//                    .date("2020-10-24 10:00:00")
//                    .courseId(allService.get(0).getCourseId())
//                    .totalPrice(100000)
//                    .build();
//            repository.insertReservation(reservation);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }


    }
}