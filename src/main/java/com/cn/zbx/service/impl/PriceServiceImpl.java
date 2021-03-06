package com.cn.zbx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

	/**
	 * 根据条件查询价格信息
	 */
	@Override
	public List<Price> selectPriceBySelectParam(Price record) {
		// TODO Auto-generated method stub
		return PriceMapper.selectPriceBySelectParam(record);
	}

	/**
	 * 根据条件查询价格数
	 */
	@Override
	public int selectCountBySelectParam(Price record) {
		// TODO Auto-generated method stub
		return PriceMapper.selectCountBySelectParam(record);
	}

	/**
	 * 插入价格信息
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public int insertSelective(Price record) {
		// TODO Auto-generated method stub
		return PriceMapper.insertSelective(record);
	}

}
