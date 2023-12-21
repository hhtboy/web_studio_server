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
public class Reservation {
    private int serviceId;
    private String customerName;
    private String phoneNumber;
    private String date;
    private ArrayList<Integer> courseId;
    private int totalPrice;
}
