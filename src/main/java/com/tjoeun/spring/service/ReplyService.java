package com.tjoeun.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.spring.dao.ReplyDAO;
import com.tjoeun.spring.dto.ReplyDTO;

@Service
public class ReplyService {
	
	@Autowired
    ReplyDAO replyDAO;
	
	//?κΈ?μ‘°ν
	public List<ReplyDTO> list(int post_idx){
	    return replyDAO.list(post_idx);
	}

	//?κΈ??°κΈ?
	public void write(ReplyDTO writeReplyDTO) {
	    replyDAO.write(writeReplyDTO);
	}

	
	//?κΈ??­? 
	public void delete(int reply_idx){
	    replyDAO.delete(reply_idx);
	}

     
	
}
 