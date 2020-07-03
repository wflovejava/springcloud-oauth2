package com.wf.provide.dto;

import lombok.Data;

@Data
public class UserLoginParam {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String userPwd;
}
