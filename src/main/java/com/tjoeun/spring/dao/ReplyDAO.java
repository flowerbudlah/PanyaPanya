package com.tjoeun.spring.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tjoeun.spring.dto.ReplyDTO;

@Repository
public class ReplyDAO {
    
	@Autowired
    SqlSessionTemplate sqlSessionTemplate;
    
	//?κΈ? μ‘°ν
	public List<ReplyDTO> list(int post_idx){
	    return sqlSessionTemplate.selectList("reply.replyList", post_idx);
	}

	// ?κΈ? ??±
	public void write(ReplyDTO writeReplyDTO){
	    sqlSessionTemplate.insert("reply.replyWrite", writeReplyDTO);
	}

	// ?κΈ? ?? 
	public void modify(ReplyDTO modifyReplyDTO){
		sqlSessionTemplate.update("reply.replyModify", modifyReplyDTO);
	}

	// ?κΈ? ?­? 
	public void delete(int reply_idx){
		sqlSessionTemplate.delete("reply.replyDelete", reply_idx);
	}

        
    
    
}

