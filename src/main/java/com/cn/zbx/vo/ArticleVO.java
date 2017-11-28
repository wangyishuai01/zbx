package com.cn.zbx.vo;

import java.util.Arrays;

import com.cn.zbx.pojo.ArticleMain;

public class ArticleVO extends ArticleMain{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Integer commentCount;
    
    private String[] keyWords;
    
    public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public String[] getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String[] keyWords) {
		this.keyWords = keyWords;
	}

	@Override
	public String toString() {
		return "ArticleVO [commentCount=" + commentCount + ", keyWords="
				+ Arrays.toString(keyWords) + "]";
	}
}