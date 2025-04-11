package com.spring.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.dto.PostDTO;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 1. 1) 게시판 메인화면으로 이동(Going to main board)
	public List<PostDTO> getPostList(int board_idx, RowBounds rowBounds) {
		List<PostDTO> postList = sqlSessionTemplate.selectList("board.getPostList", board_idx, rowBounds);
		return postList;
	}
	
	// 1. 2) 게시판 이름 가져오기
	public String getBoardName(int board_idx) {
		String board_name = sqlSessionTemplate.selectOne("board.getBoardName", board_idx);
		return board_name;
	}

	// 1. 3) 게시글 수 가져오기
	public int getPostCnt(int post_board_idx) {
		int postCnt = sqlSessionTemplate.selectOne("board.getPostCnt", post_board_idx);
		return postCnt;
	}
	
	// 2. 글쓰기(Adding a post)
	public void addPostInfo(PostDTO writePostDTO) {
		sqlSessionTemplate.insert("board.addPostInfo", writePostDTO);
	}



	public int searchResultCount(PostDTO searchPostDTO) {
		int search_result_count = sqlSessionTemplate.selectOne("board.searchResultCount", searchPostDTO);
		return search_result_count;
	}

	public List<PostDTO> selectSearchList(PostDTO searchPostDTO, RowBounds rowBounds) {
		return sqlSessionTemplate.selectList("board.selectSearchList", searchPostDTO, rowBounds);
	}

	public PostDTO getPostInfo(int post_idx) {
		PostDTO readPostDTO = sqlSessionTemplate.selectOne("board.getPostInfo", post_idx);
		return readPostDTO;
	}

	// 글 수정하기
	public void modifyPostInfo(PostDTO modifyPostDTO) {
		sqlSessionTemplate.update("board.modifyPostInfo", modifyPostDTO);
	}

	// 글 삭제(Deleting)
	public void delete(int post_idx) {
		sqlSessionTemplate.delete("board.replyDelete", post_idx);
		sqlSessionTemplate.delete("board.delete", post_idx);
	}

	// 조회수 
	public void viewCount(int post_idx) {
		sqlSessionTemplate.update("board.viewCount", post_idx);
	}

}