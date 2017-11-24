package com.cn.zbx.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cn.zbx.dao.ArticleMainMapper;
import com.cn.zbx.dao.PriceMapper;
import com.cn.zbx.pojo.ArticleMain;
import com.cn.zbx.pojo.Price;
import com.cn.zbx.pojo.Tclassify;
import com.cn.zbx.service.IArticleMainService;
import com.cn.zbx.util.MapUtil;
import com.cn.zbx.util.StringUtils;
import com.cn.zbx.vo.ArticleVO;
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
	
	@Autowired
	PriceMapper PriceMapper;

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
	public List<ArticleVO> selectBySelectParam(ArticleMain record) {
		// TODO Auto-generated method stub
		List<ArticleVO> resultList = articleMainMapper.selectBySelectParam(record);
		return resultList;
	}

	@Override
	public Integer selectCountBySelectParam(ArticleMain record) {
		// TODO Auto-generated method stub
		return articleMainMapper.selectCountBySelectParam(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articleMainMapper.deleteByPrimaryKey(id);
	}

	@Override
	public ArticleMain selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articleMainMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ArticleMain record) {
		// TODO Auto-generated method stub
		return articleMainMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<ArticleMain> selectArticleNoContentBySelectParam(
			ArticleMain record) {
		// TODO Auto-generated method stub
		List<ArticleMain> resultList = new ArrayList<ArticleMain>();
		List<ArticleVO> articleVOList = articleMainMapper.selectBySelectParam(record);
		ArticleMain oo;
		for(ArticleVO o : articleVOList){
			oo = new ArticleMain();
			oo.setId(o.getId());
			oo.setTitle(o.getTitle());
			oo.setImgurl(o.getImgurl());
			oo.setClassid(o.getClassid());
			oo.setExcerpt(o.getExcerpt());
			oo.setIsfree(o.getIsfree());
			oo.setNocomment(o.getNocomment());
			oo.setState(o.getState());
			oo.setMakedate(o.getMakedate());
			oo.setModifydate(o.getModifydate());
			resultList.add(oo);
		}
		return resultList;
	}

	@Override
	public Map<String, Object> selectClassifyByArticleId(Integer id) {
		// TODO Auto-generated method stub
		return articleMainMapper.selectClassifyByArticleId(id);
	}

	/**
	 * 编辑文章信息功能 包括价格
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean editArticleInfoByVideoId(Map<String, Object> mapParam) {
		// TODO Auto-generated method stub
		Date currentDate = new Date();
		ArticleMain article = new ArticleMain();
		Price price = new Price();
		String articlePriceId = String.valueOf(mapParam.get("articlePriceId"));
		String articlePrice = String.valueOf(mapParam.get("articlePrice"));
		String articlePriceOld = String.valueOf(mapParam.get("articlePriceOld"));
		try {
			article = (ArticleMain)MapUtil.mapToBean(mapParam, article.getClass());
			int num = articleMainMapper.updateByPrimaryKeySelective(article);
			
			if(num > 0 && !articlePrice.equals(articlePriceOld) && article.getIsfree() == 0){
				if(!StringUtils.isNotEmpty(articlePriceId)){
					price.setType(1);
					price.setProductId(article.getId());
					price.setPrice(Double.valueOf(articlePrice));
					price.setPlayNumber(0);
					price.setSumPrice(0.00);
					price.setAgainPayDays(10);
					price.setMakedate(currentDate);
					price.setModifydate(currentDate);
					int num1 = PriceMapper.insertSelective(price);
					if(num1 <= 0){
						return false;
					}
				} else {
					price.setId(Integer.valueOf(articlePriceId));
					price.setPrice(Double.valueOf(articlePrice));
					price.setModifydate(currentDate);
					int num1 = PriceMapper.updateByPrimaryKeySelective(price);
					if(num1 <= 0){
						return false;
					}
				}
			} else if(num <= 0){
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 新增文章信息 包括价格
	 * @throws Exception 
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean addArticleInfo(Map<String, Object> mapParam) throws Exception {
		// TODO Auto-generated method stub
		Date currentDate = new Date();
		ArticleMain article = new ArticleMain();
		Price price = new Price();
		String articlePrice = String.valueOf(mapParam.get("articlePrice"));
		article = (ArticleMain)MapUtil.mapToBean(mapParam, article.getClass());
		int num = articleMainMapper.insertSelective(article);
		
		if(num > 0 && article.getIsfree() == 0){
			price.setType(1);
			price.setProductId(article.getId());
			price.setPrice(Double.valueOf(articlePrice));
			price.setPlayNumber(0);
			price.setSumPrice(0.00);
			price.setAgainPayDays(10);
//			price.setMakedate(currentDate);
//			price.setModifydate(currentDate);
			throw new RuntimeException("hehe"); 
//			int num1 = PriceMapper.insertSelective(price);
//			if(num1 <= 0){
//				return false;
//			}
		} else if(num <= 0){
			return false;
		}
		return true;
	}

}
