package com.coderman.system.mapper;


import com.coderman.common.model.system.Log;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author zhangyukang
 * @Date 2020/4/2 20:27
 * @Version 1.0
 **/
@Repository
public interface LogMapper extends Mapper<Log> {
}
