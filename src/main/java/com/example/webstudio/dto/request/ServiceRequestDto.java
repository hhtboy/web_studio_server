package com.example.webstudio.dto.request;

import com.example.webstudio.Entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ServiceRequestDto {
    private int serviceId;
    private String serviceName;
    private int price;
    private String info;
    private int timeTaken;
    private ArrayList<CourseRequestDto> course;
    private String picture;
}
