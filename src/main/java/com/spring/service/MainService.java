package com.spring.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.BoardDAO;
import com.spring.dto.PostDTO;

@Service
public class MainService {

	@Autowired
	private BoardDAO boardDAO;

	// 메인화면에 있는 게시판 2개
	public List<PostDTO> getMainList(int board_idx) {
		RowBounds rowBounds = new RowBounds(0, 5);
		List<PostDTO> mainList = boardDAO.getPostList(board_idx, rowBounds);
		return mainList;
	}

}
