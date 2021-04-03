package com.coderman.business.mapper;

import com.coderman.common.model.business.Health;
import com.coderman.common.vo.business.HealthVO;
import com.coderman.common.vo.system.PageVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/5/7 10:16
 * @Version 1.0
 **/
@Repository
public interface HealthMapper extends Mapper<Health> {
    /**
     * 今日是否打卡
     * @param id
     * @return
     */
    @Select("select * from biz_health where create_time < (CURDATE()+1) " +
            " and create_time>CURDATE() and user_id=#{id}")
    List<Health> isReport(@Param("id") Long id);
    /**
     * 打卡列表
     * @param healthVO
     * @return
     */
//    @Select("select * from biz_health where create_time < (CURDATE()+1) " +"and create_time>CURDATE()")
//    PageVO<Health> findHealthList(Integer pageNum, Integer pageSize, HealthVO healthVO);
}
