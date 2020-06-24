package com.liuyi.springbootdemo.sys.controller;


import com.alibaba.fastjson.JSON;
import com.liuyi.springbootdemo.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-06-21
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("findUserList")
    public String findUserList(){
        return JSON.toJSONString(userService.findUserList());
    }
}
