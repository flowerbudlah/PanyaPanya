package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.spring.dto.ReplyDTO;
import com.spring.service.ReplyService;

@Controller
@RequestMapping("/board/reply")
public class ReplyController {

	@Autowired
	ReplyService replyService;

	// 1. 게시판에서 리플 달기 Adding a comment in the main board. 
	@PostMapping("/write")
	public String write(ReplyDTO writeReplyDTO, @RequestParam("board_idx") int board_idx, Model model) throws Exception {

		model.addAttribute("board_idx", board_idx);
		replyService.write(writeReplyDTO);
		return "redirect:/board/read?board_idx={board_idx}&post_idx=" + writeReplyDTO.getPost_idx() + "&page=1";
	}

	// 2. 게시판에서 리플 삭제 Removing the comment in the main board.
	@RequestMapping("/delete")
	public void delete(int reply_idx, @RequestParam("board_idx") int board_idx, @RequestParam("post_idx") int post_idx,
			Model model) {

		model.addAttribute("board_idx", board_idx);
		model.addAttribute("post_idx", post_idx);

		replyService.delete(reply_idx);
	}

}