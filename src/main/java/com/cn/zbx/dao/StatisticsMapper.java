package com.cn.zbx.dao;

import java.util.List;

import com.cn.zbx.pojo.Statistics;
import com.cn.zbx.vo.StatisticsVO;

public interface StatisticsMapper {

    int selectCountBySelectParam(Statistics record);
    List<StatisticsVO> selectDateAndCount(StatisticsVO record);
    
    List<StatisticsVO> selectHourAndCount(StatisticsVO record);

}