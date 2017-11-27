package com.cn.zbx.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

	/**
	 * 根据条件查询客户信息
	 */
	@Override
	public List<CustomerVO> selectBySelectParam(Customer record) {
		// TODO Auto-generated method stub
		return customerMapper.selectBySelectParam(record);
	}

	/**
	 * 根据条件查询客户数
	 */
	@Override
	public int selectCountBySelectParam(Customer record) {
		// TODO Auto-generated method stub
		return customerMapper.selectCountBySelectParam(record);
	}

	/**
	 * 根据条件修改客户信息
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public int updateByPrimaryKeySelective(Customer record) {
		// TODO Auto-generated method stub
		return customerMapper.updateByPrimaryKeySelective(record);
	}

}
