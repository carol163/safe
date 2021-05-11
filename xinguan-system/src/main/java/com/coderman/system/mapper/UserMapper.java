package com.coderman.system.mapper;


import com.coderman.common.model.system.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author zhangyukang
 * @Date 2020/3/7 15:03
 * @Version 1.0
 **/
@Repository
public interface UserMapper extends Mapper<User> {
}
