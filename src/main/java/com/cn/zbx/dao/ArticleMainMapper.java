package com.cn.zbx.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cn.zbx.pojo.ArticleMain;
import com.cn.zbx.vo.ArticleVO;
import com.cn.zbx.vo.TClassifyVO;

public interface ArticleMainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleMain record);

    int insertSelective(ArticleMain record);

    ArticleMain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleMain record);

    int updateByPrimaryKeyWithBLOBs(ArticleMain record);

    int updateByPrimaryKey(ArticleMain record);
    
    List<TClassifyVO> selectCountByClassIds(List<Integer> list);
    
    List<ArticleVO> selectBySelectParam(ArticleMain record);
    
    Integer selectCountBySelectParam(ArticleMain record);
    
    Map<String, Object> selectClassifyByArticleId(@Param(value="id") Integer id);
    
    List<ArticleVO> selectBySelectParamNoRelationVideo(ArticleMain record);
    
    Integer selectCountBySelectParamNoRelationVideo(ArticleMain record);
    
    List<ArticleVO> selectBySelectParamV2(ArticleVO record);
    
    Integer selectCountBySelectParamV2(ArticleVO record);
}