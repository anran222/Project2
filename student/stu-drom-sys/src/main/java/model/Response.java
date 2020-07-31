package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author:xiang
 * @Date:2020/7/12 11:44
 */
@Getter
@Setter
@ToString
public class Response {
    private boolean success;
    private String code;
    private String message;
    private Integer total;
    private Object data;
    private String stackTrace;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
