package com.cn.zbx.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.zbx.dao.VideoMainMapper;
import com.cn.zbx.pojo.VideoMain;
import com.cn.zbx.service.IVideoMainService;
import com.cn.zbx.vo.VideoVO;

/**
 * @Class Name VideoMainServiceImpl
 * @Author zhangdl
 * @Create In 2017-10-01
 */
@Service(value="VideoService")
public class VideoMainServiceImpl implements IVideoMainService {
	
	@Autowired
	VideoMainMapper VideoMainMapper;

	@Override
	public List<VideoVO> selectBySelectParam(VideoVO record) {
		// TODO Auto-generated method stub
		return VideoMainMapper.selectBySelectParam(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(VideoMain record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectCountBySelectParam(VideoVO record) {
		// TODO Auto-generated method stub
		return VideoMainMapper.selectCountBySelectParam(record);
	}

	@Override
	public int insertSelective(VideoMain record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
