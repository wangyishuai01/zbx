package com.cn.zbx.dao;

import com.cn.zbx.pojo.Statistics;
import com.cn.zbx.vo.StatisticsVO;

public interface StatisticsMapper {

    int selectCountBySelectParam(Statistics record);
    
    String selectDateAndCount(StatisticsVO record);
    

}