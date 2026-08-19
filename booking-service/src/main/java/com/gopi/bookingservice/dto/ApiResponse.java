package com.gopi.bookingservice.dto;

import lombok.Getter;

@Getter
public class ApiResponse<T>{

    private T data;
}
