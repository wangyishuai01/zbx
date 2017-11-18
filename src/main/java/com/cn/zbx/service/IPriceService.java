package com.cn.zbx.service;

import java.util.List;

import com.cn.zbx.pojo.Price;


/**
 * @Class Name IPriceService
 * @Author zhangdl
 * @Create In 2017-10-01
 */

public interface IPriceService {

	List<Price> selectPriceBySelectParam(Price record);
	
	int selectCountBySelectParam(Price record);
	
}
