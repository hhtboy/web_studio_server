package com.example.webstudio.repository;

import com.example.webstudio.Entity.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MysqlWebRepository implements WebRepository {


    String url = "jdbc:mysql://localhost:3306/new_web?serverTimezone=UTC";

    Connection conn = null;

    private void getConnection() {
        try {
            conn = DriverManager.getConnection(url, "root", "142857");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public ArrayList<Service> getAllService() throws SQLException {
        getConnection();

        ArrayList<Service> result = new ArrayList<>();

        String query = "select * from service;";

        PreparedStatement p = null;
        p = conn.prepareStatement(query);
        ResultSet r = p.executeQuery();

        while (r.next()) {
            int serviceId = r.getInt(1);
            String serviceName = r.getString(2);
            int price = r.getInt(3);
            String info = r.getString(4);
            int timeTaken = r.getInt(5);
            String json = r.getString(6);
            String picture = r.getString(7);

            JSONArray jsonArray = new JSONArray(json);

            ArrayList<Course> courses = new ArrayList<Course>();
            for(int i = 0 ; i < jsonArray.length() ; i ++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int courseId = jsonObject.getInt("courseId");
                String courseTitle = jsonObject.getString("courseTitle");
                String courseInfo = jsonObject.getString("courseInfo");
                int coursePrice = jsonObject.getInt("price");
                String imageURL = jsonObject.getString("imageURL");
                Course course = new Course().builder()
                        .courseId(courseId)
                        .courseTitle(courseTitle)
                        .courseInfo(courseInfo)
                        .price(coursePrice)
                        .imageURL(imageURL)
                        .build();
                courses.add(course);
            }

            Service service = new Service().builder()
                    .serviceId(serviceId)
                    .serviceName(serviceName)
                    .price(price)
                    .info(info)
                    .timeTaken(timeTaken)
                    .picture(picture)
                    .courses(courses)
                    .build();
            result.add(service);
        }

        if (conn != null) {
            conn.close();
        }

        return result;
    }

    @Override
    public ArrayList<Product> getAllProduct() throws SQLException {
        getConnection();

        ArrayList<Product> result = new ArrayList<>();

        String query = "select * from product;";

        PreparedStatement p = null;
        p = conn.prepareStatement(query);
        ResultSet r = p.executeQuery();

        while (r.next()) {
            int productId = r.getInt(1);
            String productName = r.getString(2);
            int price = r.getInt(3);
            String info = r.getString(4);
            String picture = r.getString(5);


            Product product = new Product().builder()
                    .productId(productId)
                    .productName(productName)
                    .price(price)
                    .info(info)
                    .picture(picture)
                    .build();
            result.add(product);
        }


        if (conn != null) {
            conn.close();
        }

        return result;
    }

    @Override
    public ArrayList<Reservation> getAllReservation() throws SQLException {
        getConnection();
        ArrayList<Reservation> result = new ArrayList<>();

        String query = "select * from reservation;";
        PreparedStatement p = conn.prepareStatement(query);
        ResultSet r = p.executeQuery();

        while(r.next()) {
            int serviceId = r.getInt(2);
            String customerName = r.getString(3);
            String phoneNumber = r.getString(4);
            String date = r.getString(5);
            String courseId = r.getString(6);
            int totalPrice = r.getInt(7);
            ArrayList<Integer> courses = new ArrayList<>();

            JSONArray jsonArray = new JSONArray(courseId);
            for(int i = 0 ; i < jsonArray.length() ; i ++) {
                courses.add(jsonArray.getInt(i));
            }

            Reservation reservation = new Reservation().builder()
                    .serviceId(serviceId)
                    .customerName(customerName)
                    .phoneNumber(phoneNumber)
                    .date(date)
                    .courseId(courses)
                    .totalPrice(totalPrice)
                    .build();

            result.add(reservation);
        }
        if (conn != null) {
            conn.close();
        }

        return result;
    }


    @Override
    public void insertReservation(Reservation reservation) throws SQLException {
        getConnection();

        String query = "insert into reservation(serviceId, customerName, phoneNumber, date, courseId, totalPrice) values ( "+ reservation.getServiceId() +", '"+ reservation.getCustomerName() +"', '"+ reservation.getPhoneNumber() +"', '"+ reservation.getDate() +"', '"+ reservation.getCourseId().toString() +"', "+ reservation.getTotalPrice() +");";
        PreparedStatement p = conn.prepareStatement(query);
        p.executeUpdate();

        if (conn != null) {
            conn.close();
        }
    }

    @Override
    public void insertService(Service service) throws SQLException {
        getConnection();

        String query = "insert into service(serviceName, price, info, timeTaken, picture, courses) values('" + service.getServiceName() + "', " + service.getPrice() + ", '" + service.getInfo() + "', " + service.getTimeTaken() + ", '" + service.getPicture() + "', '" + service.getCourses().toString() + "');";
        log.info("query : " + query);
        PreparedStatement p = conn.prepareStatement(query);
        p.executeUpdate();

        if (conn != null) {
            conn.close();
        }

    }

    @Override
    public void updateService(Service service) throws SQLException {
        getConnection();

        String query = "update service set serviceId = '" + service.getServiceId() + "', serviceName = '" + service.getServiceName() + "', price = '" + service.getPrice()+"', info = '" + service.getInfo() + "', timeTaken = '" + service.getTimeTaken() + "', courses = '" + service.getCourses().toString() + "', picture = '" + service.getPicture() + "' where serviceId = " + service.getServiceId() + ";";
        PreparedStatement p = conn.prepareStatement(query);
        p.executeUpdate();
        if (conn != null) {
            conn.close();
        }

    }

    @Override
    public void deleteService(int serviceId) throws SQLException {
        getConnection();

        String query = "delete from service where serviceId = " + serviceId + ";";
        PreparedStatement p = conn.prepareStatement(query);
        p.executeUpdate();

        if (conn != null) {
            conn.close();
        }
    }

    @Override
    public void insertProduct(Product product) throws SQLException {
        getConnection();

        String query = "insert into product(productName, price, info, picture) values('" + product.getProductName() + "', " + product.getPrice() + ", '" + product.getInfo() + "', '" +  product.getPicture() + "');";
        PreparedStatement p = conn.prepareStatement(query);
        p.executeUpdate();

        if (conn != null) {
            conn.close();
        }
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        getConnection();

        String query = "update product set productId = '" + product.getProductId() + "', productName = '" + product.getProductName() + "', price = '" + product.getPrice()+"', info = '" + product.getInfo() + "', picture = '" + product.getPicture() + "' where productId = " + product.getProductId() + ";";
        PreparedStatement p = conn.prepareStatement(query);
        p.executeUpdate();

        if (conn != null) {
            conn.close();
        }
    }

    @Override
    public void deleteProduct(int productId) throws SQLException {
        getConnection();

        String query = "delete from product where productId = " + productId + ";";
        PreparedStatement p = conn.prepareStatement(query);
        p.executeUpdate();

        if (conn != null) {
            conn.close();
        }
    }

    @Override
    public void insertUser(String userid, String userpw) throws SQLException {
        getConnection();

        String query = "insert into users values('" + userid + "', '" + userpw + "');";
        PreparedStatement p = conn.prepareStatement(query);
        p.executeUpdate();

        if (conn != null) {
            conn.close();
        }
    }

    @Override
    public Member getUser(String userid, String userpw) throws SQLException {
        getConnection();

        String[] regs = new String[4];
        regs[0] = "'";
        regs[1] = ")";
        regs[2] = "(";
        regs[3] = "union";
        for(String reg : regs) {
            if(userpw.contains(reg)) {
                return null;
            }
        }
        String query = "select * from users where userid = '" + userid + "' and userpw = '" + userpw + "';";
        PreparedStatement p = conn.prepareStatement(query);
        ResultSet r = p.executeQuery();

        Member member = null;
        while (r.next()) {
            String id = r.getString(1);
            String pw = r.getString(2);

            member = new Member().builder()
                    .userid(id)
                    .userpw(pw)
                    .build();
            break;
        }

        if (conn != null) {
            conn.close();
        }
        return member;
    }

    @Override
    public ArrayList<Article> getAllArticles() throws SQLException {
        ArrayList<Article> articles = new ArrayList<>();
        getConnection();

        String query = "select * from articles;";

        PreparedStatement p = null;
        p = conn.prepareStatement(query);
        ResultSet r = p.executeQuery();

        while (r.next()) {
            int articleId = r.getInt(1);
            String title = r.getString(2);
            String content = r.getString(3);
            String author = r.getString(4);
            String date = r.getString(5);


            Article article = new Article().builder()
                    .articleId(articleId)
                    .title(title)
                    .content(content)
                    .author(author)
                    .date(date)
                    .build();
            articles.add(article);
        }


        if (conn != null) {
            conn.close();
        }

        return articles;
    }

    @Override
    public void insertArticle(Article article) throws SQLException {
        getConnection();

        String query = "insert into articles(title, content, author, date) values('" + article.getTitle() + "', '" + article.getContent() + "', '" + article.getAuthor() + "', '" + article.getDate() + "');";
        PreparedStatement p = conn.prepareStatement(query);
        p.executeUpdate();

        if (conn != null) {
            conn.close();
        }
    }
}
