package xyz.sally.common.enums;

import lombok.ToString;

public enum ResponseCode implements ErrorCode {
    /**
     * 处理成功
     */
    SUCCESS(200, "Success"),
    /**
     * 参数异常
     */
    INVALID_PARAMETERS(300, "Invalid Parameters"),
    /**
     * Headers参数异常
     */
    INVALID_HEADERS(301, "Invalid Headers"),
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(5000, "Internal Server Error");

    private int code;
    private String message;

    private ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
