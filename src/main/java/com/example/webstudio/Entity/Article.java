package com.example.webstudio.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private int articleId;
    private String title;
    private String content;
    private String author;
    private String date;
}
