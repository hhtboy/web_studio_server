package com.example.webstudio.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    private int serviceId;
    private String serviceName;
    private int price;
    private String info;
    private int timeTaken;
    private ArrayList<Course> courses;
    private String picture;

}
