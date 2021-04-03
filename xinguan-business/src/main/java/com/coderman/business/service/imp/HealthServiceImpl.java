package com.coderman.business.service.imp;

import com.coderman.business.converter.ConsumerConverter;
import com.coderman.business.mapper.HealthMapper;
import com.coderman.business.service.HealthService;
import com.coderman.common.error.BusinessCodeEnum;
import com.coderman.common.error.BusinessException;
import com.coderman.common.model.business.Consumer;
import com.coderman.common.model.business.Health;
import com.coderman.common.vo.business.ConsumerVO;
import com.coderman.common.vo.business.HealthVO;
import com.coderman.common.vo.system.PageVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @Author zhangyukang
 * @Date 2020/5/7 10:21
 * @Version 1.0
 **/
@Service
public class HealthServiceImpl implements HealthService {

    @Autowired
    private HealthMapper healthMapper;


    /**
     * 安全打卡列表
     * @param pageNum
     * @param pageSize
     * @param healthVO
     * @return
     */
    @Override
    public PageVO<Health> findHealthList(Integer pageNum, Integer pageSize, HealthVO healthVO) {
        PageHelper.startPage(pageNum, pageSize);
        Example o = new Example(Health.class);
        Example.Criteria criteria = o.createCriteria();
        o.setOrderByClause("id asc");
        if(healthVO.getStartTime()!=null){
            criteria.andGreaterThanOrEqualTo("createTime",healthVO.getStartTime());
        }
        if(healthVO.getEndTime()!=null){
            criteria.andLessThanOrEqualTo("createTime",healthVO.getEndTime());
        }
        if (healthVO.getId() != null && !"".equals(healthVO.getId())) {
            criteria.andLike("userId", "%" + healthVO.getId() + "%");
        }
        if (healthVO.getAddress() != null && !"".equals(healthVO.getAddress())) {
            criteria.andLike("address", "%" + healthVO.getAddress() + "%");
        }
        if (healthVO.getSituation() != null && !"".equals(healthVO.getSituation())) {
            criteria.andLike("situation", "%" + healthVO.getSituation() + "%");
        }
        if (healthVO.getTouch() != null && !"".equals(healthVO.getTouch())) {
            criteria.andLike("touch", "%" + healthVO.getTouch() + "%");
        }
        if (healthVO.getPassby()!= null && !"".equals(healthVO.getPassby())) {
            criteria.andLike("passby", "%" + healthVO.getPassby() + "%");
        }
        if (healthVO.getReception() != null && !"".equals(healthVO.getReception())) {
            criteria.andLike("reception", "%" + healthVO.getReception() + "%");
        }
        List<Health> healths = healthMapper.selectByExample(o);
        //List<HealthVO> categoryVOS= ConsumerConverter.converterToVOList(healths);
        PageInfo<Health> info = new PageInfo<>(healths);
        return new PageVO<>(info.getTotal(),info.getList());
    }
    /**
     * 健康上报
     * @param healthVO
     */
    @Override
    public void report(HealthVO healthVO) throws BusinessException {
        Health report = isReport(healthVO.getUserId());
        if(report!=null) {
            throw new BusinessException(BusinessCodeEnum.PARAMETER_ERROR, "今日已经打卡,无法重复打卡！");
        }
        Health health = new Health();
        BeanUtils.copyProperties(healthVO,health);
        health.setCreateTime(new Date());
        healthMapper.insert(health);
    }

    /**
     * 今日是否已报备
     * @param id
     * @return
     */
    @Override
    public Health isReport(Long id) {
        List<Health> health=healthMapper.isReport(id);
        if(health.size()>0){
            return  health.get(0);
        }
        return null;
    }

    /**
     * 签到历史记录
     * @return
     */
    @Override
    public PageVO<Health> history(Long id,Integer pageNum,Integer pageSize) {
        Example o = new Example(Health.class);
        o.setOrderByClause("create_time desc");
        PageHelper.startPage(pageNum,pageSize);
        o.createCriteria().andEqualTo("userId",id);
        List<Health> health = healthMapper.selectByExample(o);
        PageInfo<Health> pageInfo=new PageInfo<>(health);
        return new PageVO<>(pageInfo.getTotal(),pageInfo.getList());
    }
}
