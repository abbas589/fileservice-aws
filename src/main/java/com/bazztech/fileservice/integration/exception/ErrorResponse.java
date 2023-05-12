package com.bazztech.fileservice.integration.exception;

import com.bazztech.fileservice.integration.ApiResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class ErrorResponse extends RuntimeException {
    private final ApiResponse<?> apiResponse;

    public ErrorResponse(ApiResponse<?> apiResponse) {
        super(apiResponse == null ? null : apiResponse.getMessage());
        this.apiResponse = apiResponse;
    }
    public ErrorResponse(int code, String message, Object data) {
        super(message);
        ApiResponse<Object> apiResponse = new ApiResponse<Object>();
        apiResponse.setCode(code);
        apiResponse.setMessage(message);
        apiResponse.setData(data);
        this.apiResponse = apiResponse;
    }
    public ErrorResponse(int code, String message) {
        super(message);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(code);
        apiResponse.setMessage(message);
        this.apiResponse = apiResponse;
    }

    public ErrorResponse(int code) {
        super(code + "");
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(code);
        this.apiResponse = apiResponse;
    }
    public ErrorResponse(String message) {
        super(message);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(400);
        apiResponse.setMessage(message);
        this.apiResponse = apiResponse;
    }

    public ApiResponse<?> getApiResponse() {
        return apiResponse;
    }
}
