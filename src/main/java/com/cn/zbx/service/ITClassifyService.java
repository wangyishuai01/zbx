package com.cn.zbx.service;


import java.util.List;

import com.cn.zbx.pojo.Tclassify;

/**
 * @Class Name ITClassifyService
 * @Author zhangdl
 * @Create In 2017-10-01
 */

public interface ITClassifyService {

	Tclassify selectByPrimaryKey(Integer id);
	
	List<Tclassify> selectBySelectParam(Tclassify record);

	int deleteByPrimaryKey(Integer id);
	
	int updateByPrimaryKeySelective(Tclassify record);
	
	int selectCountBySelectParam(Tclassify record);
	
	int insertSelective(Tclassify record);
	
}
