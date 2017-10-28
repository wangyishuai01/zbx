package com.cn.zbx.service;


import java.util.List;

import com.cn.zbx.pojo.Customer;
import com.cn.zbx.vo.CustomerVO;

/**
 * @Class Name ICustomerService
 * @Author zhangdl
 * @Create In 2017-10-01
 */

public interface ICustomerService {

	List<CustomerVO> selectBySelectParam(Customer record);

	int selectCountBySelectParam(Customer record);
	
	int updateByPrimaryKeySelective(Customer record);
	
}
