package xyz.sally.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.sally.common.enums.ErrorCode;
import xyz.sally.common.enums.ResponseCode;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private int code;

    private String message;

    public Response(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
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
