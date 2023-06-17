package com.altuhin.orderservice.unit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(
        ignoreUnknown = true
)
public class ApiResponse<T> {
    private T data;
    private String apiResponseCode;
    private Integer httpStatusCode;
    private Boolean status;
    private String message;
    
    public ApiResponse(T data, String apiResponseCode, Integer httpStatusCode, Boolean status, String message) {
        this.data = data;
        this.apiResponseCode = apiResponseCode;
        this.httpStatusCode = httpStatusCode;
        this.status = status;
        this.message = message;
    }
    
    public T getData() {
        return this.data;
    }
    
    public String getApiResponseCode() {
        return this.apiResponseCode;
    }
    
    public Integer getHttpStatusCode() {
        return this.httpStatusCode;
    }
    
    public Boolean getStatus() {
        return this.status;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setData(final T data) {
        this.data = data;
    }
    
    public void setApiResponseCode(final String apiResponseCode) {
        this.apiResponseCode = apiResponseCode;
    }
    
    public void setHttpStatusCode(final Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
    
    public void setStatus(final Boolean status) {
        this.status = status;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
}
