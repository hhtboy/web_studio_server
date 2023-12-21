package com.example.webstudio.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDto {
    private int courseId;
    private String courseTitle;
    private String courseInfo;
    private int price;
    private String imageURL;

    public String toString() {
        return courseId + ", " + courseTitle + ", " + courseInfo + ", " + price + ", " + imageURL;
    }
}
