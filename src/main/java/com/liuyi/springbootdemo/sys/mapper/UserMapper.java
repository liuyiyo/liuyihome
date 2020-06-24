package com.liuyi.springbootdemo.sys.mapper;

import com.liuyi.springbootdemo.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-06-21
 */
@Component
public interface UserMapper extends BaseMapper<User> {

}
