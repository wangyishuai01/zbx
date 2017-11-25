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
import com.cn.zbx.dao.KeyRelationMapper;
import com.cn.zbx.dao.KeyWordsMapper;
import com.cn.zbx.dao.PriceMapper;
import com.cn.zbx.pojo.ArticleMain;
import com.cn.zbx.pojo.KeyRelation;
import com.cn.zbx.pojo.KeyWords;
import com.cn.zbx.pojo.Price;
import com.cn.zbx.pojo.Tclassify;
import com.cn.zbx.service.IArticleMainService;
import com.cn.zbx.util.MapUtil;
import com.cn.zbx.util.StringUtils;
import com.cn.zbx.vo.ArticleVO;
import com.cn.zbx.vo.KeyWordsVO;
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
	
	@Autowired
	KeyWordsMapper keyWordsMapper;
	
	@Autowired
	KeyRelationMapper keyRelationMapper;

	/**
	 * 查询多个 分类对应的文章数
	 */
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

	/**
	 * 根据条件查询文章信息
	 */
	@Override
	public List<ArticleVO> selectBySelectParam(ArticleMain record) {
		// TODO Auto-generated method stub
		List<ArticleVO> resultList = articleMainMapper.selectBySelectParam(record);
		return resultList;
	}

	/**
	 * 根据条件查询文章数
	 */
	@Override
	public Integer selectCountBySelectParam(ArticleMain record) {
		// TODO Auto-generated method stub
		return articleMainMapper.selectCountBySelectParam(record);
	}

	/**
	 * 根据主键删除文章信息
	 */
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articleMainMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据主键查询文章信息
	 */
	@Override
	public ArticleMain selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return articleMainMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据主键修改文章信息
	 */
	@Override
	public int updateByPrimaryKeySelective(ArticleMain record) {
		// TODO Auto-generated method stub
		return articleMainMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 根据条件查询文章信息（不包含文章内容）
	 */
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

	/**
	 * 根据文章id查询对应的分类（二级和一级分类）
	 */
	@Override
	public Map<String, Object> selectClassifyByArticleId(Integer id) {
		// TODO Auto-generated method stub
		return articleMainMapper.selectClassifyByArticleId(id);
	}

	/**
	 * 编辑文章信息功能 包括价格 和 关键词
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public boolean editArticleInfoByArticleId(Map<String, Object> mapParam) {
		// TODO Auto-generated method stub
		Date currentDate = new Date();
		ArticleMain article = new ArticleMain();
		Price price = new Price();
		String articlePriceId = String.valueOf(mapParam.get("articlePriceId"));
		String articlePrice = String.valueOf(mapParam.get("articlePrice"));
		String articlePriceOld = String.valueOf(mapParam.get("articlePriceOld"));
		try {
			article = (ArticleMain)MapUtil.mapToBean(mapParam, article.getClass());
			articleMainMapper.updateByPrimaryKeySelective(article);
			
			if(!articlePrice.equals(articlePriceOld) && article.getIsfree() == 0){
				if(!StringUtils.isNotEmpty(articlePriceId)){
					price.setType(1);
					price.setProductId(article.getId());
					price.setPrice(Double.valueOf(articlePrice));
					price.setPlayNumber(0);
					price.setSumPrice(0.00);
					price.setAgainPayDays(10);
					price.setMakedate(currentDate);
					price.setModifydate(currentDate);
					PriceMapper.insertSelective(price);
					
				} else {
					price.setId(Integer.valueOf(articlePriceId));
					price.setPrice(Double.valueOf(articlePrice));
					price.setModifydate(currentDate);
					PriceMapper.updateByPrimaryKeySelective(price);
					
				}
			}
			if(mapParam.get("keyWords") != null){
				String[] keyWords = (String[])mapParam.get("keyWords");
				KeyWords keyWordsParam = null;
				KeyRelation KeyRelationParam = new KeyRelation();
				KeyRelationParam.setRelationtype(1);
				KeyRelationParam.setProductid(article.getId());
				//修改关键词之前先删除之前所有的关键词
				keyRelationMapper.deleteBySelective(KeyRelationParam);
				for(int i=0; i<keyWords.length; i++){
					int keyWordsId = 0;
					keyWordsParam = new KeyWords();
					KeyRelationParam = new KeyRelation();
					keyWordsParam.setName(keyWords[i]);
					List<KeyWordsVO> keyWordsList = keyWordsMapper.selectBySelectParam(keyWordsParam);
					if(keyWordsList != null && keyWordsList.size() != 0){
						keyWordsId = keyWordsList.get(0).getId();
					} else {
						keyWordsParam.setState(1);
						keyWordsParam.setMakedate(currentDate);
						keyWordsParam.setModifydate(currentDate);
						keyWordsMapper.insertSelective(keyWordsParam);
						keyWordsId = keyWordsParam.getId();
					}
					KeyRelationParam.setKeywordsid(keyWordsId);
					KeyRelationParam.setRelationtype(1);
					KeyRelationParam.setProductid(article.getId());
					//添加新的关键词
					keyRelationMapper.insert(KeyRelationParam);
				}
			} else {//修改关键词时如果不输入则删除之前所有的关键词
				KeyRelation KeyRelationParam = new KeyRelation();
				KeyRelationParam.setRelationtype(1);
				KeyRelationParam.setProductid(article.getId());
				keyRelationMapper.deleteBySelective(KeyRelationParam);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 新增文章信息 包括价格 和 关键词
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
			price.setMakedate(currentDate);
			price.setModifydate(currentDate);
//			throw new RuntimeException("hehe"); 
			int num1 = PriceMapper.insertSelective(price);
			if(num1 <= 0){
				return false;
			}
		} else if(num <= 0){
			return false;
		}
		if(mapParam.get("keyWords") != null){
			String[] keyWords = (String[])mapParam.get("keyWords");
			KeyWords keyWordsParam = null;
			KeyRelation KeyRelationParam = null;
			for(int i=0; i<keyWords.length; i++){
				int keyWordsId = 0;
				keyWordsParam = new KeyWords();
				KeyRelationParam = new KeyRelation();
				keyWordsParam.setName(keyWords[i]);
				List<KeyWordsVO> keyWordsList = keyWordsMapper.selectBySelectParam(keyWordsParam);
				if(keyWordsList != null && keyWordsList.size() != 0){
					keyWordsId = keyWordsList.get(0).getId();
				} else {
					keyWordsParam.setState(1);
					keyWordsParam.setMakedate(currentDate);
					keyWordsParam.setModifydate(currentDate);
					keyWordsMapper.insertSelective(keyWordsParam);
					keyWordsId = keyWordsParam.getId();
				}
				KeyRelationParam.setKeywordsid(keyWordsId);
				KeyRelationParam.setRelationtype(1);
				KeyRelationParam.setProductid(article.getId());
				keyRelationMapper.insert(KeyRelationParam);
			}
		}
		return true;
	}

	/**
	 * 查询文章数，没有关联视频的文章，返回信息没有文章内容
	 */
	@Override
	public Integer selectCountBySelectParamNoRelationVideo(ArticleMain record) {
		// TODO Auto-generated method stub
		return articleMainMapper.selectCountBySelectParamNoRelationVideo(record);
	}

	/**
	 * 查询文章信息，没有关联视频的文章，返回信息没有文章内容
	 */
	@Override
	public List<ArticleMain> selectArticleNoContentBySelectParamNoRelationVideo(
			ArticleMain record) {
		// TODO Auto-generated method stub
		List<ArticleMain> resultList = new ArrayList<ArticleMain>();
		List<ArticleVO> articleVOList = articleMainMapper.selectBySelectParamNoRelationVideo(record);
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

	/**
	 * 查询文章信息，添加关键词查询
	 */
	@Override
	public List<ArticleVO> selectBySelectParamV2(ArticleVO record) {
		// TODO Auto-generated method stub
		return articleMainMapper.selectBySelectParamV2(record);
	}

	/**
	 * 查询文章数，添加关键词查询
	 */
	@Override
	public Integer selectCountBySelectParamV2(ArticleVO record) {
		// TODO Auto-generated method stub
		return articleMainMapper.selectCountBySelectParamV2(record);
	}

}
