package com.liuyi.springbootdemo.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuyi.springbootdemo.sys.entity.User;
import com.liuyi.springbootdemo.sys.mapper.UserMapper;
import com.liuyi.springbootdemo.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-06-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findUserList() {
        //测试多线程下的线程安全问题
        LocalDateTime date = LocalDateTime.now();
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 3; i++) {
            Task task = new Task(i,date,this);
            service.execute(task);
        }
        service.shutdown();
        return userMapper.selectByMap(new HashMap<>());
    }

    /**
     * @Author liuyi
     * @Description //验证是否执行完毕
     * @Date 22:02 2020/6/21
     * @Param []
     * @return void
     **/
    public synchronized void verify(LocalDateTime date,int i){
        User user = new User();
        user.setAge(10+i);
        user.setCreateDate(date);
        user.setEmail(i+"10924556.qq.com");
        user.setName("ly测试"+i);
        userMapper.insert(user);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("create_date",date);
        List<User> users = userMapper.selectList(queryWrapper);
        if(users.size()==3){
            System.out.println("本次执行完毕");
        }

    }

}
class Task implements Runnable{

    private int i;

    private LocalDateTime date;

    private IUserService userService;

    public Task(){

    }

    public Task(int i, LocalDateTime date, IUserService userService) {
        this.i = i;
        this.date = date;
        this.userService = userService;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(i*20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userService.verify(date,i);
    }
}
