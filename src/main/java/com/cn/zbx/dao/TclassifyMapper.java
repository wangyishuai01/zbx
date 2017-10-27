package com.cn.zbx.dao;

import java.util.List;

import com.cn.zbx.pojo.Tclassify;

public interface TclassifyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tclassify record);

    int insertSelective(Tclassify record);

    Tclassify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tclassify record);

    int updateByPrimaryKey(Tclassify record);
    
    List<Tclassify> selectBySelectParam(Tclassify record);
    
    int selectCountBySelectParam(Tclassify record);
}