package com.cn.zbx.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.zbx.dao.CustomerMapper;
import com.cn.zbx.pojo.Customer;
import com.cn.zbx.service.ICustomerService;
import com.cn.zbx.vo.CustomerVO;

/**
 * @Class Name CustomerServiceImpl
 * @Author zhangdl
 * @Create In 2017-10-01
 */
@Service(value="ustomerService")
public class CustomerServiceImpl implements ICustomerService {
	
	@Autowired
	CustomerMapper customerMapper;

	@Override
	public List<CustomerVO> selectBySelectParam(Customer record) {
		// TODO Auto-generated method stub
		return customerMapper.selectBySelectParam(record);
	}

	@Override
	public int selectCountBySelectParam(Customer record) {
		// TODO Auto-generated method stub
		return customerMapper.selectCountBySelectParam(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Customer record) {
		// TODO Auto-generated method stub
		return customerMapper.updateByPrimaryKeySelective(record);
	}

	

	
}
