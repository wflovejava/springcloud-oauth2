package com.wf.base.dto;

/**
 * 定义统一返回值
 * @param <T>
 */
public class Response<T> {

    /**
     * 统一编码
     */
    private String code;

    /**
     * 返回内容
     */
    private String msg;

    /**
     * 返回描述
     */
    private String desc;


    private T data;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
