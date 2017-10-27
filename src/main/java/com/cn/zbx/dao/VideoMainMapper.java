package com.cn.zbx.dao;

import com.cn.zbx.pojo.VideoMain;

public interface VideoMainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VideoMain record);

    int insertSelective(VideoMain record);

    VideoMain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VideoMain record);

    int updateByPrimaryKey(VideoMain record);
}