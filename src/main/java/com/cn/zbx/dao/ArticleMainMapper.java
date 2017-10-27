package com.cn.zbx.dao;

import java.util.List;

import com.cn.zbx.pojo.ArticleMain;
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
    List<ArticleMain> AllList(ArticleMain record);
}