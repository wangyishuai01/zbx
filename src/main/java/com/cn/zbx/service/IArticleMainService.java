package com.cn.zbx.service;


import java.util.List;
import java.util.Map;

import com.cn.zbx.pojo.ArticleMain;
import com.cn.zbx.pojo.Tclassify;
import com.cn.zbx.vo.ArticleVO;
import com.cn.zbx.vo.TClassifyVO;

/**
 * @Class Name IArticleMainService
 * @Author zhangdl
 * @Create In 2017-10-01
 */

public interface IArticleMainService {

	List<TClassifyVO> selectCountByClassIds(List<Tclassify> list);
	
	List<ArticleVO> selectBySelectParam(ArticleMain record);

	Integer selectCountBySelectParam(ArticleMain record);
	
	int deleteByPrimaryKey(Integer id);
	
	ArticleMain selectByPrimaryKey(Integer id);
	
	int updateByPrimaryKeySelective(ArticleMain record);
	
	List<ArticleMain> selectArticleNoContentBySelectParam(ArticleMain record);
	
	Map<String, Object> selectClassifyByArticleId(Integer id);
}
