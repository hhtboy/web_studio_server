package com.example.webstudio.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDto {
    private String courseTitle;
    private String courseInfo;
    private int price;
    private String imageURL;

    public String toString() {
        return  courseTitle + ", " + courseInfo + ", " + price + ", " + imageURL;
    }
}