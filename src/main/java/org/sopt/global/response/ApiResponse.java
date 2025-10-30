package org.sopt.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private final boolean success;
    private final T data;
    private final String message;
    private final String code;
    private final HttpStatus status;

    private ApiResponse(boolean success, T data, String message, String code, HttpStatus status) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.code = code;
        this.status = status;
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, data, null, null, HttpStatus.OK);
    }

    public static ApiResponse<Void> okMessage(String message) {
        return new ApiResponse<>(true, null, message, null, HttpStatus.OK);
    }

    public static ApiResponse<Void> fail(HttpStatus status, String code, String message) {
        return new ApiResponse<>(false, null, message, code, status);
    }

    public boolean isSuccess() { return success; }
    public T getData() { return data; }
    public String getMessage() { return message; }
    public String getCode() { return code; }
    public HttpStatus getStatus() { return status; }
}
