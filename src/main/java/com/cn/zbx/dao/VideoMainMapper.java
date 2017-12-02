package com.cn.zbx.dao;

import java.util.List;

import com.cn.zbx.pojo.VideoMain;
import com.cn.zbx.vo.VideoVO;

public interface VideoMainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VideoMain record);

    int insertSelective(VideoMain record);

    VideoMain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VideoMain record);

    int updateByPrimaryKey(VideoMain record);
    
    List<VideoVO> selectBySelectParam(VideoVO record);
    
    int selectCountBySelectParam(VideoVO record);
    
    List<VideoMain> selectBySelectParam1(VideoMain record);
    
    int selectCountBySelectParam1(VideoMain record);
    
}