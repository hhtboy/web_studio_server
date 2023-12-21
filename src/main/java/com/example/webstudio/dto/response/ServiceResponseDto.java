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
public class ServiceResponseDto {

    private int serviceId;
    private String serviceName;
    private int price;
    private String info;
    private int timeTaken;
    private ArrayList<CourseResponseDto> course;
    private String picture;

    @Override
    public String toString() {
        return serviceId + ", " + serviceName + ", " + price + ", " + info + ", " + timeTaken + ", " + course.toString() + ", " + picture;
    }
}
