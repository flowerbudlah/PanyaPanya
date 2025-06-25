package com.spring.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.dto.ReplyDTO;

@Repository
public class ReplyDAO {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	// 1. 특정한 글에 딸린 댓글목록 가져오기 Taking a list of comments attached to a specific post
	public List<ReplyDTO> list(int post_idx) {
		return sqlSessionTemplate.selectList("reply.replyList", post_idx);
	}

	// 2. 댓글 하나 작성하기
	public void write(ReplyDTO writeReplyDTO) {
		sqlSessionTemplate.insert("reply.replyWrite", writeReplyDTO);
	}

	// 3. 댓글 삭제
	public void delete(int reply_idx) {
		sqlSessionTemplate.delete("reply.replyDelete", reply_idx);
	}

}