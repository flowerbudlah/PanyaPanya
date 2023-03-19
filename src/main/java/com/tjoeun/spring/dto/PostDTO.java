package com.tjoeun.spring.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PostDTO {
	
	private int post_idx; //ê²Œì‹œê¸? ?¼? ¨ë²ˆí˜¸
	
	@NotBlank
	private String post_subject; //ê²Œì‹œê¸? ? œëª?
	
	@NotBlank
	private String post_text; //ê²Œì‹œê¸? ?‚´?š©
	
	
	private int post_writer_idx; //ê²Œì‹œê¸? ?‘?„±??˜ ?¼? ¨ë²ˆí˜¸ 
	
	private String post_writer_name; // SQLë¬¸ì—?„œ alias(ë³„ì¹­)?œ¼ë¡? ì§?? •?•œ ì»¬ëŸ¼ëª…ì„ PostDTO?˜ ë©¤ë²„ë³??ˆ˜ë¡? ?„ ?–¸?•´?•¼ ê°’ì„ ë°›ì•„?˜¬ ?ˆ˜ ?ˆ?Œ
	
	private int post_board_idx; //?´ ê¸??´ ?†?•˜?Š” ê²Œì‹œ?Œë²ˆí˜¸. 1?´ë©? ê³µì??‚¬?•­, 2?´ë©? ê³ ê°?„¼?„°   
	
	private Date post_date; 
	

	
	private String post_file; // ?°?´?„°ë² ì´?Š¤?— ???¥?˜?–´?ˆ?Š” ?ŒŒ?¼?´ë¦„ì„ ???¥?•˜?Š” ë³??ˆ˜
	private MultipartFile upload_file; // browserê°? ë³´ë‚´?Š” file dataë¥? ???¥?•˜?Š” ë³??ˆ˜
	
	
	private int post_read_count; //ì¡°íšŒ?ˆ˜
	private int reply_cnt;    // **ê²Œì‹œê¸? ?Œ“ê¸??˜ ?ˆ˜ ì¶”ê?(2021?…„ 06?›” 01?¼)
	
	
	private String keyword;//?‚¤?›Œ?“œ 
	private String type; //ê²??ƒ‰ì¢…ë¥˜
	

	
	
	 
}




