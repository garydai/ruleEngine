package xyz.sally.common.api;

import lombok.Data;
import xyz.sally.common.enums.ErrorCode;
import xyz.sally.common.enums.ResponseCode;

@Data
public class Response {

    private int code;

    private String message;

    public Response() {
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getMessage();
    }

//
//    /**
//     * error response
//     */
//    public Response(ErrorCode resp) {
//        this.code = resp.getCode();
//        this.message = resp.getMessage();
//    }
//
//    public Response(int code, String message) {
//        this.code = code;
//        this.message = message;
//    }
}
