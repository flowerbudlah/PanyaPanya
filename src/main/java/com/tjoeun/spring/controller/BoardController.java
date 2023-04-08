package com.tjoeun.spring.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tjoeun.spring.dto.PostDTO;
import com.tjoeun.spring.dto.ReplyDTO;
import com.tjoeun.spring.dto.PageDTO;
import com.tjoeun.spring.dto.MemberDTO;
import com.tjoeun.spring.service.BoardService;
import com.tjoeun.spring.service.ReplyService;



@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private ReplyService replyService;
	
	@Resource(name="loginMemberDTO")
	@Lazy
	private MemberDTO loginMemberDTO;
	
	//1. 게시판 메인화면으로 간다. 게시글이 리스트로 나열된 
	@GetMapping("/main")
	public String main(@RequestParam("board_idx") int board_idx, @RequestParam(value="page", defaultValue="1") int page, Model model) {
		model.addAttribute("board_idx",board_idx);
		String board_name = boardService.getBoardName(board_idx);
		model.addAttribute("board_name", board_name);

		List<PostDTO> postList = boardService.getPostList(board_idx, page);
		model.addAttribute("postList", postList);
				
		PageDTO pageDTO = boardService.getPostCnt(board_idx, page); 
		model.addAttribute("pageDTO", pageDTO);
		model.addAttribute("page", page);
			
		return "board/main";
	}
		
	//2. 특정한 글 하나를 읽는다. 
	@GetMapping("/read")
	public String read(
	@RequestParam("board_idx") int board_idx,
	@RequestParam("post_idx") int post_idx, 
	@RequestParam("page") int page,
	@ModelAttribute("readPostDTO") PostDTO postDTO, Model model) {
		
		//조회수 증가
		boardService.viewCount(postDTO.getPost_idx()); 
		PostDTO readPostDTO = boardService.getPostInfo(post_idx);
		
		model.addAttribute("readPostDTO", readPostDTO);
		model.addAttribute("board_idx", board_idx);
		model.addAttribute("post_idx", post_idx);

		model.addAttribute("loginMemberDTO", loginMemberDTO);
		model.addAttribute("page", page);
		
		List<ReplyDTO> reply = null;
		reply = replyService.list(post_idx);
		model.addAttribute("reply", reply); 
		
		return "board/read";
	}
	
	//글쓰기
	@GetMapping("/write")
	public String write
	(@ModelAttribute("writePostDTO") PostDTO writePostDTO, @RequestParam("board_idx") int board_idx) {
		writePostDTO.setPost_board_idx(board_idx);
		return "board/write";
	}
	
	//글쓰기 버튼
	@PostMapping("/write_proc")
	public String writeProc
	(@Valid @ModelAttribute("writePostDTO") PostDTO writePostDTO, 
	BindingResult result, MultipartFile file, Model model) {
		
		if(result.hasErrors()) { 
			return "board/write";
		}
		
		boardService.addPostInfo(writePostDTO);//글쓰기 성공
		model.addAttribute("writePostDTO", writePostDTO); 
			
		return "board/write_success";
	}
	

	@GetMapping("/not_writer")
	public String notWriter() {
		return "board/not_writer";
	}
	
	//글 수정하기 페이로 이동한다. 
	@GetMapping("/modify")
	public String modify
	(@RequestParam("post_idx") int post_idx, 
	@ModelAttribute("modifyPostDTO") PostDTO modifyPostDTO, 
	@RequestParam("page") int page, Model model){
		
		model.addAttribute("post_idx", post_idx);
		model.addAttribute("page", page);
		
		//수정하는 글 대상을 DB에서 가져오는 것
		PostDTO fromDBPostDTO = boardService.getPostInfo(post_idx); 
		System.out.println(fromDBPostDTO); 
		System.out.println("before"); 
		System.out.println(modifyPostDTO); 
		System.out.println("after"); 
		
		//DB에서 가져온 그글들이 수정 페이지에 반영(변경적용)
		modifyPostDTO.setPost_idx(post_idx);
		modifyPostDTO.setPost_writer_name(fromDBPostDTO.getPost_writer_name()); 
		
		//문제가 되는 그 부분!
		modifyPostDTO.setPost_date(fromDBPostDTO.getPost_date());
		
		modifyPostDTO.setPost_subject(fromDBPostDTO.getPost_subject());
		modifyPostDTO.setPost_text(fromDBPostDTO.getPost_text());
		modifyPostDTO.setPost_file(fromDBPostDTO.getPost_file());
		modifyPostDTO.setPost_writer_idx(fromDBPostDTO.getPost_writer_idx());
		modifyPostDTO.setPost_board_idx(fromDBPostDTO.getPost_board_idx()); 

		model.addAttribute("modifyPostDTO", modifyPostDTO); 
		System.out.println(modifyPostDTO); 
		System.out.println(modifyPostDTO.getPost_date()); 
		
		return "board/modify";
	}
	
	
	@PostMapping("/modify_proc")
	public String modifyProc
(@Valid @ModelAttribute("modifyPostDTO") PostDTO modifyPostDTO, 
		BindingResult result, Model model,  MultipartFile file, @RequestParam("page") int page) {
	
		model.addAttribute("page", page);
		
		System.out.println("BindingResult result의 에러내용"); //에러추적 
		System.out.println(result); //에러추적 
		System.out.println(result.hasErrors()); //유효성 검증 true나오면 안넘어간다. 
		if(result.hasErrors()) {
			return "board/modify";
		} else {
			//에러가 없다. 글 수정한게 성공했다는 뜻으로, 
			boardService.modifyPostInfo(modifyPostDTO); 
			model.addAttribute("modifyPostDTO", modifyPostDTO); 
			
			
			return "board/modify_success";
		}
		
	}
	
	
	
	
	//글 삭제
	@GetMapping("/delete")
	public String delete
	(PostDTO deletePostDTO, @RequestParam("board_idx") int board_idx, Model model){

		boardService.delete(deletePostDTO.getPost_idx());
		model.addAttribute("board_idx", board_idx);
		
		return "board/delete";
	}

	

	@GetMapping("/search_result")
	private String getSearchList(
		@RequestParam("post_board_idx") int board_idx, 
		@RequestParam("type") String type, 
		@RequestParam("keyword") String keyword,
		@RequestParam(value="page", defaultValue="1") int page, 
		Model model) throws Exception{
			
		PostDTO searchPostDTO = new PostDTO();
		searchPostDTO.setPost_board_idx(board_idx); 
		searchPostDTO.setType(type); 
		searchPostDTO.setKeyword(keyword); 

		//�??��결과 ?�� 
		int search_result_count = boardService.searchResultCount(searchPostDTO);
		model.addAttribute("search_result_count", search_result_count);
		
		//�??��결과 리스?�� 
		List<PostDTO> searchList = boardService.selectSearchList(searchPostDTO, page);
		model.addAttribute("searchList", searchList);
		
		//?��?���?
		PageDTO pageDTO = boardService.searchListCount(searchPostDTO, page); 
		model.addAttribute("board_idx", board_idx);
		model.addAttribute("pageDTO", pageDTO);
		model.addAttribute("page", page);
		model.addAttribute("type", type);
		model.addAttribute("keyword", keyword);
		
		return "board/search_result";
	}
	
	
	
	

}
