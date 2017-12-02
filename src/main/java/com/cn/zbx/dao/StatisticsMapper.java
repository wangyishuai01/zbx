package com.cn.zbx.dao;

import com.cn.zbx.pojo.BLog;
import com.cn.zbx.vo.LogVO;

public interface StatisticsMapper {

    int selectCountBySelectParam(BLog record);
    
    String selectDateAndCount(LogVO record);
    
    String selectCusBuyHistoryByDateAndCount(LogVO record);
    String selectPayMoneyByDateAndCount(LogVO record);
}