package com.cn.zbx.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

	@Override
	public List<VideoVO> selectBySelectParam(VideoVO record) {
		// TODO Auto-generated method stub
		return VideoMainMapper.selectBySelectParam(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return VideoMainMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(VideoMain record) {
		// TODO Auto-generated method stub
		return VideoMainMapper.updateByPrimaryKeySelective(record);
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

	@Override
	public List<VideoMain> selectBySelectParam(VideoMain record) {
		// TODO Auto-generated method stub
		return VideoMainMapper.selectBySelectParam1(record);
	}

	@Override
	public int selectCountBySelectParam(VideoMain record) {
		// TODO Auto-generated method stub
		return VideoMainMapper.selectCountBySelectParam1(record);
	}

	/**
	 * 编辑视频信息功能 包括价格
	 */
	@Override
	public boolean editVideoInfoByVideoId(Map<String, Object> mapParam) {
		// TODO Auto-generated method stub
		Date currentDate = new Date();
		VideoMain video = new VideoMain();
		Price price = new Price();
		String videoPriceId = String.valueOf(mapParam.get("videoPriceId"));
		String videoPrice = String.valueOf(mapParam.get("videoPrice"));
		String videoPriceOld = String.valueOf(mapParam.get("videoPriceOld"));
		try {
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
					int num1 = PriceMapper.updateByPrimaryKeySelective(price);
					if(num1 <= 0){
						return false;
					}
				}
			} else if(num <= 0){
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 新增视频信息 包括价格
	 */
	@Override
	@Transactional
	public boolean addVideoInfo(Map<String, Object> mapParam) {
		// TODO Auto-generated method stub
		Date currentDate = new Date();
		VideoMain video = new VideoMain();
		Price price = new Price();
		String videoPrice = String.valueOf(mapParam.get("videoPrice"));
		try {
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
