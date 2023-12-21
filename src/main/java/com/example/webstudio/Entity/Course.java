package com.example.webstudio.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private int courseId;
    private String courseTitle;
    private String courseInfo;
    private int price;
    private String imageURL;

    public String toString() {
        return "{\"courseId\" : " + courseId + ", \"courseTitle\" : \"" + courseTitle + "\", \"courseInfo\" : \"" + courseInfo + "\", \"price\" : " + price + ", \"imageURL\" : \"" + imageURL + "\"}";
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
