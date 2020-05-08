package com.wf.oauth.db;

import lombok.Data;

/**
 * @Author: wf
 * @Date: 2020-05-07 11:27
 * @describe
 */
@Data
public class User {
    private int id;
    private String username;
    private String password;
}
