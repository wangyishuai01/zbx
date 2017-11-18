package com.cn.zbx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.zbx.dao.PriceMapper;
import com.cn.zbx.pojo.Price;
import com.cn.zbx.service.IPriceService;

/**
 * @Class Name PriceServiceImpl
 * @Author zhangdl
 * @Create In 2017-10-01
 */
@Service(value="PriceService")
public class PriceServiceImpl implements IPriceService {
	
	@Autowired
	PriceMapper PriceMapper;

	@Override
	public List<Price> selectPriceBySelectParam(Price record) {
		// TODO Auto-generated method stub
		return PriceMapper.selectPriceBySelectParam(record);
	}

	@Override
	public int selectCountBySelectParam(Price record) {
		// TODO Auto-generated method stub
		return PriceMapper.selectCountBySelectParam(record);
	}

	@Override
	public int insertSelective(Price record) {
		// TODO Auto-generated method stub
		return PriceMapper.insertSelective(record);
	}

}
