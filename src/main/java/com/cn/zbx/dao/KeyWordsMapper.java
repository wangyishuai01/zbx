package com.cn.zbx.dao;

import java.util.List;
import java.util.Map;

import com.cn.zbx.pojo.KeyWords;
import com.cn.zbx.vo.KeyWordsVO;

public interface KeyWordsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(KeyWords record);

    int insertSelective(KeyWords record);

    KeyWords selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KeyWords record);

    int updateByPrimaryKey(KeyWords record);
    
    List<KeyWordsVO> selectBySelectParam(KeyWords record);
    
    int selectCountBySelectParam(KeyWords record);
    
    List<KeyWords> selectByProductId(Map<String, Object> mapParam);
}