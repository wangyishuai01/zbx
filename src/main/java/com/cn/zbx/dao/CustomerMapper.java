package com.cn.zbx.dao;

import java.util.List;

import com.cn.zbx.pojo.Customer;
import com.cn.zbx.vo.CustomerVO;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
    
    List<CustomerVO> selectBySelectParam(Customer record);
    
    int selectCountBySelectParam(Customer record);
    
    List<Customer> selectByUserName(String userName);
}