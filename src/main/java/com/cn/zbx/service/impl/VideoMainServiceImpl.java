package com.cn.zbx.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cn.zbx.dao.PriceMapper;
import com.cn.zbx.dao.VideoMainMapper;
import com.cn.zbx.pojo.Price;
import com.cn.zbx.pojo.VideoMain;
import com.cn.zbx.service.IVideoMainService;
import com.cn.zbx.util.MapUtil;
import com.cn.zbx.util.StringUtils;
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
	
	@Autowired
	PriceMapper PriceMapper;

	/**
	 * 根据条件查询视频信息
	 * 参数类型 VideoVO 
	 * 返回 List<VideoVO>
	 */
	@Override
	public List<VideoVO> selectBySelectParam(VideoVO record) {
		// TODO Auto-generated method stub
		return VideoMainMapper.selectBySelectParam(record);
	}

	/**
	 * 根据主键删除视频信息
	 * 
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return VideoMainMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据主键修改视频信息
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public int updateByPrimaryKeySelective(VideoMain record) {
		// TODO Auto-generated method stub
		return VideoMainMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 根据条件查询视频数
	 * 参数类型 VideoVO 
	 */
	@Override
	public int selectCountBySelectParam(VideoVO record) {
		// TODO Auto-generated method stub
		return VideoMainMapper.selectCountBySelectParam(record);
	}

	/**
	 * 插入视频信息
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public int insertSelective(VideoMain record) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 根据条件查询视频信息
	 * 参数类型 VideoMain 
	 * 返回 List<VideoMain>
	 */
	@Override
	public List<VideoMain> selectBySelectParam(VideoMain record) {
		// TODO Auto-generated method stub
		return VideoMainMapper.selectBySelectParam1(record);
	}

	/**
	 * 根据条件查询视频数
	 * 参数类型 VideoMain 
	 * 返回 List<VideoMain>
	 */
	@Override
	public int selectCountBySelectParam(VideoMain record) {
		// TODO Auto-generated method stub
		return VideoMainMapper.selectCountBySelectParam1(record);
	}

	/**
	 * 编辑视频信息功能 包括价格
	 * @throws Exception 
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean editVideoInfoByVideoId(Map<String, Object> mapParam) throws Exception {
		// TODO Auto-generated method stub
		Date currentDate = new Date();
		VideoMain video = new VideoMain();
		Price price = new Price();
		String videoPriceId = String.valueOf(mapParam.get("videoPriceId"));
		String videoPrice = String.valueOf(mapParam.get("videoPrice"));
		String videoPriceOld = String.valueOf(mapParam.get("videoPriceOld"));
		video = (VideoMain)MapUtil.mapToBean(mapParam, video.getClass());
		int num = VideoMainMapper.updateByPrimaryKeySelective(video);
		
		if(num > 0 && !videoPrice.equals(videoPriceOld) && video.getIsfree() == 0){
			if(!StringUtils.isNotEmpty(videoPriceId)){
				price.setType(2);
				price.setProductId(video.getId());
				price.setPrice(Double.valueOf(videoPrice));
				price.setPlayNumber(0);
				price.setSumPrice(0.00);
				price.setAgainPayDays(10);
				price.setMakedate(currentDate);
				price.setModifydate(currentDate);
				int num1 = PriceMapper.insertSelective(price);
				if(num1 <= 0){
					return false;
				}
			} else {
				price.setId(Integer.valueOf(videoPriceId));
				price.setPrice(Double.valueOf(videoPrice));
				price.setModifydate(currentDate);
				int num1 = PriceMapper.updateByPrimaryKeySelective(price);
				if(num1 <= 0){
					return false;
				}
			}
		} else if(num <= 0){
			return false;
		}
		return true;
	}

	/**
	 * 新增视频信息 包括价格
	 * @throws Exception 
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean addVideoInfo(Map<String, Object> mapParam) throws Exception {
		// TODO Auto-generated method stub
		Date currentDate = new Date();
		VideoMain video = new VideoMain();
		Price price = new Price();
		String videoPrice = String.valueOf(mapParam.get("videoPrice"));
		video = (VideoMain)MapUtil.mapToBean(mapParam, video.getClass());
		int num = VideoMainMapper.insertSelective(video);
		
		if(num > 0 && video.getIsfree() == 0){
			price.setType(2);
			price.setProductId(video.getId());
			price.setPrice(Double.valueOf(videoPrice));
			price.setPlayNumber(0);
			price.setSumPrice(0.00);
			price.setAgainPayDays(10);
			price.setMakedate(currentDate);
			price.setModifydate(currentDate);
			int num1 = PriceMapper.insertSelective(price);
			if(num1 <= 0){
				return false;
			}
		} else if(num <= 0){
			return false;
		}
		return true;
	}

	/**
	 * 根据视频id查询视频
	 */
	@Override
	public VideoMain selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return VideoMainMapper.selectByPrimaryKey(id);
	}

}
