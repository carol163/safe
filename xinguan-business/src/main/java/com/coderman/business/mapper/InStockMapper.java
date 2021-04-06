package com.coderman.business.mapper;


import com.coderman.common.model.business.InStock;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author zhangyukang
 * @Date 2020/3/19 09:53
 * @Version 1.0
 **/
@Repository
public interface InStockMapper extends Mapper<InStock> {
}
