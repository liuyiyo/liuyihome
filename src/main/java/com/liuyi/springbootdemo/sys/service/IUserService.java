package com.liuyi.springbootdemo.sys.service;

import com.liuyi.springbootdemo.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-06-21
 */
public interface IUserService extends IService<User> {
    public List<User> findUserList();

    public void verify(LocalDateTime date,int i);
}
