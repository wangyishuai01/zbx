package com.cn.zbx.service;


import java.util.List;
import java.util.Map;

import com.cn.zbx.pojo.VideoMain;
import com.cn.zbx.vo.VideoVO;

/**
 * @Class Name IVideoMainService
 * @Author zhangdl
 * @Create In 2017-10-01
 */

public interface IVideoMainService {

	List<VideoVO> selectBySelectParam(VideoVO record);

	int deleteByPrimaryKey(Integer id);
	
	int updateByPrimaryKeySelective(VideoMain record);
	
	int selectCountBySelectParam(VideoVO record);
	
	int insertSelective(VideoMain record);
	
	List<VideoMain> selectBySelectParam(VideoMain record);
	
	int selectCountBySelectParam(VideoMain record);
	
	boolean editVideoInfoByVideoId(Map<String, Object> mapParam);
	
}
