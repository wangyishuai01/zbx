package com.cn.zbx.service;


import java.util.List;

import com.cn.zbx.pojo.KeyWords;
import com.cn.zbx.vo.KeyWordsVO;

/**
 * @Class Name IKeyWordsService
 * @Author zhangdl
 * @Create In 2017-10-01
 */

public interface IKeyWordsService {

	List<KeyWordsVO> selectBySelectParam(KeyWords record);

	int deleteByPrimaryKey(Integer id);
	
	int updateByPrimaryKeySelective(KeyWords record);
	
	int selectCountBySelectParam(KeyWords record);
	
	int insertSelective(KeyWords record);
	
}
