package com.coderman.business.mapper;


import com.coderman.common.model.business.Product;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author zhangyukang
 * @Date 2020/3/17 09:18
 * @Version 1.0
 **/
@Repository
public interface ProductMapper extends Mapper<Product> {
}
