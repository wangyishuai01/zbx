package com.cn.zbx.service;


import java.util.List;

import com.cn.zbx.pojo.ArticleMain;
import com.cn.zbx.pojo.Tclassify;
import com.cn.zbx.vo.TClassifyVO;

/**
 * @Class Name IArticleMainService
 * @Author zhangdl
 * @Create In 2017-10-01
 */

public interface IArticleMainService {

	List<TClassifyVO> selectCountByClassIds(List<Tclassify> list);
	List<ArticleMain> AllList(ArticleMain record);

	
}
