package com.cn.zbx.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.zbx.dao.KeyWordsMapper;
import com.cn.zbx.pojo.KeyWords;
import com.cn.zbx.service.IKeyWordsService;
import com.cn.zbx.vo.KeyWordsVO;

/**
 * @Class Name KeyWordsServiceImpl
 * @Author zhangdl
 * @Create In 2017-10-01
 */
@Service(value="keyWordsService")
public class KeyWordsServiceImpl implements IKeyWordsService {
	
	@Autowired
	KeyWordsMapper keyWordsMapper;

	@Override
	public List<KeyWordsVO> selectBySelectParam(KeyWords record) {
		// TODO Auto-generated method stub
		return keyWordsMapper.selectBySelectParam(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return keyWordsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(KeyWords record) {
		// TODO Auto-generated method stub
		return keyWordsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int selectCountBySelectParam(KeyWords record) {
		// TODO Auto-generated method stub
		return keyWordsMapper.selectCountBySelectParam(record);
	}

	@Override
	public int insertSelective(KeyWords record) {
		// TODO Auto-generated method stub
		return keyWordsMapper.insertSelective(record);
	}

	@Override
	public KeyWords selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return keyWordsMapper.selectByPrimaryKey(id);
	}
	
}
