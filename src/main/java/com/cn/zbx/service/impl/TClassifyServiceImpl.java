package com.cn.zbx.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.zbx.dao.TclassifyMapper;
import com.cn.zbx.pojo.Tclassify;
import com.cn.zbx.service.ITClassifyService;

/**
 * @Class Name TClassifyServiceImpl
 * @Author zhangdl
 * @Create In 2017-10-01
 */
@Service(value="tClassifyService")
public class TClassifyServiceImpl implements ITClassifyService {
	
	@Autowired
	TclassifyMapper tClassifyMapper;

	@Override
	public List<Tclassify> selectBySelectParam(Tclassify record) {
		// TODO Auto-generated method stub
		return tClassifyMapper.selectBySelectParam(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return tClassifyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Tclassify record) {
		// TODO Auto-generated method stub
		return tClassifyMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int selectCountBySelectParam(Tclassify record) {
		// TODO Auto-generated method stub
		return tClassifyMapper.selectCountBySelectParam(record);
	}

	@Override
	public int insertSelective(Tclassify record) {
		// TODO Auto-generated method stub
		return tClassifyMapper.insertSelective(record);
	}

	@Override
	public Tclassify selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return tClassifyMapper.selectByPrimaryKey(id);
	}
	
}
