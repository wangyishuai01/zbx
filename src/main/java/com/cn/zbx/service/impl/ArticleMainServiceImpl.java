package com.cn.zbx.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.zbx.dao.ArticleMainMapper;
import com.cn.zbx.pojo.ArticleMain;
import com.cn.zbx.pojo.Tclassify;
import com.cn.zbx.service.IArticleMainService;
import com.cn.zbx.vo.TClassifyVO;

/**
 * @Class Name ArticleMainServiceImpl
 * @Author zhangdl
 * @Create In 2017-10-01
 */
@Service(value="articleMainService")
public class ArticleMainServiceImpl implements IArticleMainService {
	
	@Autowired
	ArticleMainMapper articleMainMapper;

	@Override
	public List<TClassifyVO> selectCountByClassIds(List<Tclassify> list) {
		// TODO Auto-generated method stub
		List<TClassifyVO> resultList = new ArrayList<TClassifyVO>();
		List<Integer> listParam = new ArrayList<Integer>();
		if(list != null && list.size() != 0){
			for(Tclassify s : list){
				listParam.add(s.getId());
			}
			resultList = articleMainMapper.selectCountByClassIds(listParam);
		}
		return resultList;
	}

	@Override
	public List<ArticleMain> AllList(ArticleMain record) {
		// TODO Auto-generated method stub
		List<ArticleMain> resultList = articleMainMapper.AllList(record);
		return resultList;
	}

}
