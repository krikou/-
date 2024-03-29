package com.example.demo.base;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static com.example.demo.util.JwtUtil.decodedToken;

/**
 * @author nan.gai
 */
@Data
@Slf4j
public class ResponseBase {
    private Integer rtnCode;
    private String msg;
    private Object data;

    public ResponseBase() {

    }

    public ResponseBase(Integer rtnCode, String msg, Object data) {
        super();
        this.rtnCode = rtnCode;
        this.msg = msg;
        this.data = data;
    }

    public static void main(String[] args) {
        ResponseBase responseBase = new ResponseBase();
        responseBase.setData("123456");
        responseBase.setMsg("success");
        responseBase.setRtnCode(200);
        System.out.println(responseBase.toString());
        log.info("123...");
    }

    @Override
    public String toString() {
        return "ResponseBase [rtnCode=" + rtnCode + ", msg=" + msg + ", data=" + data + "]";
    }
}
