package com.wf.base.dto;

/**
 * 统一返回code
 */
public class ReturnCode {

    public static final ReturnCode SUCCESS = new ReturnCode("0", "成功");
    public static final ReturnCode FAILURE = new ReturnCode("-1", "失败");


    private String code;
    private String msg;

    protected ReturnCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
