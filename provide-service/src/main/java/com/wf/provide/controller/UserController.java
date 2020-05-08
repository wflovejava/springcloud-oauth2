package com.wf.provide.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wf
 * @Date: 2020-05-08 09:33
 * @describe
 */
@RestController
@RequestMapping("/test")
public class UserController {
    @RequestMapping("/test1")
    public  Object test1(){
        return "test1 ok";
    }
    @RequestMapping("/test2")
    public  Object test2(){
        return "test2 ok";
    }
}
