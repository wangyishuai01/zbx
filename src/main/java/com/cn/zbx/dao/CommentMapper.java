package com.cn.zbx.dao;

import java.util.List;

import com.cn.zbx.pojo.Comment;
import com.cn.zbx.vo.CommentVO;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
    
    List<CommentVO> selectBySelectParam(Comment record);
    
    int selectCountBySelectParam(Comment record);
    
    int selectByArticleId(Integer articleId);
}