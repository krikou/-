package com.example.demo.base;

/**
 * @author nan.gai
 */
public class BaseService {
    public ResponseBase setResultError(Integer code, String msg) {
        return setResult(code, msg, null);
    }

    /**
     * 返回错误，可以传msg

     */
    public ResponseBase setResultError(String msg) {
        return setResult(500, msg, null);
    }

    /**返回成功，可以传data值
     *
     * @param data
     * @return
     */
    public ResponseBase setResultSuccess(Object data) {
        return setResult(200, "处理成功", data);
    }

    /**返回成功，沒有data值
     *
     * @return
     */
    public ResponseBase setResultSuccess() {
        return setResult(200, "处理成功", null);
    }

    /**返回成功，沒有data值
     *
     * @param msg
     * @return
     */
    public ResponseBase setResultSuccess(String msg) {
        return setResult(200, msg, null);
    }

    /**通用封装
     *
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public ResponseBase setResult(Integer code, String msg, Object data) {
        return new ResponseBase(code, msg, data);
    }
}
