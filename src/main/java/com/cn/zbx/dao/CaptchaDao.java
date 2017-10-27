package com.cn.zbx.dao;


import java.util.List;

import com.cn.zbx.pojo.Captcha;


public interface CaptchaDao {
    int deleteByPrimaryKey(Integer sid);

    int insert(Captcha record);

    int insertSelective(Captcha record);

    Captcha selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Captcha record);

    int updateByPrimaryKey(Captcha record);
    
    List<Captcha> selectBySelective(Captcha record);
    
    int selectCountBySelective(Captcha record);
}