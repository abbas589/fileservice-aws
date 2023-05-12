package com.bazztech.fileservice.integration;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {

    private String status;
    private String message;
    private Integer code;
    private T data;

    public ApiResponse(int code, String message, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public ApiResponse(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public ApiResponse() {
    }
}
