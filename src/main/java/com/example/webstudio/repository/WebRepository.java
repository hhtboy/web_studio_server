package com.example.webstudio.repository;

import com.example.webstudio.Entity.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface WebRepository {

    ArrayList<Service> getAllService() throws SQLException;

    ArrayList<Product> getAllProduct()throws SQLException;

    ArrayList<Reservation> getAllReservation()throws SQLException;

    void insertReservation(Reservation reservation)throws SQLException;

    void insertService(Service service) throws SQLException;

    void updateService(Service service)throws SQLException;

    void deleteService(int serviceId)throws SQLException;

    void insertProduct(Product product)throws SQLException;

    void updateProduct(Product product)throws SQLException;

    void deleteProduct(int productId)throws SQLException;

    void insertUser(String userid, String userpw)throws SQLException;

    Member getUser(String userid, String userpw)throws SQLException;

    ArrayList<Article> getAllArticles() throws SQLException;

    void insertArticle(Article article) throws SQLException;
}
