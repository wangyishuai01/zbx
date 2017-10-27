package com.cn.zbx.service;


import java.util.List;

import com.cn.zbx.pojo.Comment;
import com.cn.zbx.vo.CommentVO;

/**
 * @Class Name ICommentService
 * @Author zhangdl
 * @Create In 2017-10-01
 */

public interface ICommentService {

	List<CommentVO> selectBySelectParam(Comment record);

	int deleteByPrimaryKey(Integer id);
	
	int selectCountBySelectParam(Comment record);
	
	int insertSelective(Comment record);
	
	int selectByArticleId(Integer articleId);
}
