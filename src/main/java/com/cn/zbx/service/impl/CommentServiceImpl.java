package com.cn.zbx.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.zbx.dao.CommentMapper;
import com.cn.zbx.pojo.Comment;
import com.cn.zbx.service.ICommentService;
import com.cn.zbx.vo.CommentVO;

/**
 * @Class Name CommentServiceImpl
 * @Author zhangdl
 * @Create In 2017-10-01
 */
@Service(value="commentService")
public class CommentServiceImpl implements ICommentService {
	
	@Autowired
	CommentMapper commentMapper;

	@Override
	public List<CommentVO> selectBySelectParam(Comment record) {
		// TODO Auto-generated method stub
		return commentMapper.selectBySelectParam(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return commentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int selectCountBySelectParam(Comment record) {
		// TODO Auto-generated method stub
		return commentMapper.selectCountBySelectParam(record);
	}

	@Override
	public int insertSelective(Comment record) {
		// TODO Auto-generated method stub
		return commentMapper.insertSelective(record);
	}

	
}
