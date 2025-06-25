package com.spring.controller;

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
import com.spring.dto.PostDTO;
import com.spring.dto.ReplyDTO;
import com.spring.dto.PageDTO;
import com.spring.dto.MemberDTO;
import com.spring.service.BoardService;
import com.spring.service.ReplyService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ReplyService replyService;

	@Lazy
	@Resource(name = "loginMemberDTO")
	private MemberDTO loginMemberDTO;

	// 1. 게시글들이 목록으로 나열된 게시판 메인화면으로 간다. (Going to the main board where posts are listed.)
	@GetMapping("/main")
	public String main(@RequestParam("board_idx") int board_idx,
			@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		model.addAttribute("board_idx", board_idx);
		String board_name = boardService.getBoardName(board_idx);
		model.addAttribute("board_name", board_name);

		List<PostDTO> postList = boardService.getPostList(board_idx, page);
		model.addAttribute("postList", postList);

		PageDTO pageDTO = boardService.getPostCnt(board_idx, page);
		model.addAttribute("pageDTO", pageDTO);
		model.addAttribute("page", page);

		return "board/main";
	}

	// 2. 특정한 글 하나를 읽는다. Reading a specific post in board.
	@GetMapping("/read")
	public String read(@RequestParam("board_idx") int board_idx, @RequestParam("post_idx") int post_idx,
			@RequestParam("page") int page, @ModelAttribute("readPostDTO") PostDTO postDTO, Model model) {

		// 조회수 증가
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

	// 3. 1) 글쓰기 페이지로 이동한다. Transferring to the Page to write
	@GetMapping("/write")
	public String write(@ModelAttribute("writePostDTO") PostDTO writePostDTO,
			@RequestParam("board_idx") int board_idx) {
		writePostDTO.setPost_board_idx(board_idx);
		return "board/write";
	}

	// 3. 2) 글쓰기 버튼을 누르고 글 하나를 추가한다. Creating, Writing
	@PostMapping("/write_proc")
	public String writeProc(@Valid @ModelAttribute("writePostDTO") PostDTO writePostDTO, BindingResult result,
			MultipartFile file, Model model) {

		// 만약 글쓰기 버튼을 눌렀는데, 이글이 등록할 수 없는 에러가 있으면 글 추가 안되고, 글쓰기 화면으로 그대로 정지된다.
		// If the post has the error that makes you not write a post Although You press the button To write, 
		// The post will not be added and It just freezes on the writing page. 
		if (result.hasErrors()) {
			return "board/write";
		}

		// 글쓰기 성공
		boardService.addPostInfo(writePostDTO);
		model.addAttribute("writePostDTO", writePostDTO);

		return "board/write_success";
	}

	// 4. 1) 글 수정하기 페이지로 이동 Transferring into a Page to modify a specific post.
	@GetMapping("/modify")
	public String modify(@RequestParam("post_idx") int post_idx, @RequestParam("page") int page,
			@ModelAttribute("modifyPostDTO") PostDTO modifyPostDTO, Model model) {

		model.addAttribute("post_idx", post_idx);
		model.addAttribute("page", page);

		// 수정하는 글 대상을 DB에서 가져오는 것
		PostDTO fromDBPostDTO = boardService.getPostInfo(post_idx);

		// DB에서 가져온 그글들이 수정 페이지에 반영(변경적용)
		modifyPostDTO.setPost_idx(post_idx);
		modifyPostDTO.setPost_writer_name(fromDBPostDTO.getPost_writer_name());
		modifyPostDTO.setPost_date(fromDBPostDTO.getPost_date());
		modifyPostDTO.setPost_subject(fromDBPostDTO.getPost_subject());
		modifyPostDTO.setPost_text(fromDBPostDTO.getPost_text());
		modifyPostDTO.setPost_file(fromDBPostDTO.getPost_file());
		modifyPostDTO.setPost_writer_idx(fromDBPostDTO.getPost_writer_idx());
		modifyPostDTO.setPost_board_idx(fromDBPostDTO.getPost_board_idx());

		model.addAttribute("modifyPostDTO", modifyPostDTO);

		return "board/modify";
	}

	// 4. 2) 글 수정 버튼 누르고 글 수정 성공! The success of modifying the post
	@PostMapping("/modify_proc")
	public String modifyProc(@Valid @ModelAttribute("modifyPostDTO") PostDTO modifyPostDTO, BindingResult result,
			Model model, MultipartFile file, @RequestParam("page") int page) {

		model.addAttribute("page", page);

		// 유효성 검증 true나오면(에러가 있다는 의미) 안넘어간다.
		if (result.hasErrors()) {
			
			return "board/modify";

		// 에러가 없다.
		} else {
			
			// 글 수정한게 성공했다는 의미
			boardService.modifyPostInfo(modifyPostDTO);
			model.addAttribute("modifyPostDTO", modifyPostDTO);

			return "board/modify_success";
		}
	}

	// 4. 3) 글쓴이가 아니면, 접속할 수 없는 경우 
	// Interceptor Function: You cannot access Unless You are writer.
	@GetMapping("/not_writer")
	public String notWriter() {
		return "board/not_writer";
	}

	// 5. 글 삭제 Deleting a post 
	@GetMapping("/delete")
	public String delete(PostDTO deletePostDTO, @RequestParam("board_idx") int board_idx, Model model) {

		boardService.delete(deletePostDTO.getPost_idx());
		model.addAttribute("board_idx", board_idx);

		return "board/delete";
	}

	// 6. 특정 게시글 검색 Searching a specific post in the Board.
	@GetMapping("/search_result")
	private String getSearchList(@RequestParam("post_board_idx") int board_idx, @RequestParam("type") String type,
			@RequestParam("keyword") String keyword, @RequestParam(value = "page", defaultValue = "1") int page,
			Model model) throws Exception {

		PostDTO searchPostDTO = new PostDTO();
		searchPostDTO.setPost_board_idx(board_idx);
		searchPostDTO.setType(type);
		searchPostDTO.setKeyword(keyword);

		// 검색된 게시글 수(결과 게시글)
		int search_result_count = boardService.searchResultCount(searchPostDTO);
		model.addAttribute("search_result_count", search_result_count);

		// 검색된 게시글 목록
		List<PostDTO> searchList = boardService.selectSearchList(searchPostDTO, page);
		model.addAttribute("searchList", searchList);

		// 페이지 처리(Pagination)
		PageDTO pageDTO = boardService.searchListCount(searchPostDTO, page);
		model.addAttribute("board_idx", board_idx);
		model.addAttribute("pageDTO", pageDTO);
		model.addAttribute("page", page);
		model.addAttribute("type", type);
		model.addAttribute("keyword", keyword);

		return "board/search_result";
	}

}