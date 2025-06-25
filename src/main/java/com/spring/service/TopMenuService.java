package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.TopMenuDAO;
import com.spring.dto.BoardDTO;

@Service
public class TopMenuService {

	@Autowired
	private TopMenuDAO topMenuDAO;

	// 1. 메인화면에서 아래부분에 게시판 미리보기
	public List<BoardDTO> getTopMenuList() {
		List<BoardDTO> topMenuList = topMenuDAO.getTopMenuList();
		return topMenuList;
	}

}
