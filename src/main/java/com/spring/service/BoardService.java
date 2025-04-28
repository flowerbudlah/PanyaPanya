package com.spring.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.dao.BoardDAO;
import com.spring.dto.PostDTO;
import com.spring.dto.PageDTO;
import com.spring.dto.MemberDTO;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {

	// @Value("${path.load}")
	// private String path_load;

	@Value("${page.listcnt}")
	private int page_listcnt;

	@Value("${page.paginationcnt}")
	private int page_paginationcnt;

	@Autowired
	private BoardDAO boardDAO;

	@Lazy@Resource(name = "loginMemberDTO")
	private MemberDTO loginMemberDTO;

	// 게시판 메인화면으로 가기
	public List<PostDTO> getPostList(int board_idx, int page) {
		// 10
		int start = (page - 1) * page_listcnt;
		RowBounds rowBounds = new RowBounds(start, page_listcnt);

		List<PostDTO> postList = boardDAO.getPostList(board_idx, rowBounds);
		return postList;
	}

	// 글쓰기
	public void addPostInfo(PostDTO writePostDTO) {

		MultipartFile upload_file = writePostDTO.getUpload_file();

		// 업로드 파일이 있다면,
		if (upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);

			writePostDTO.setPost_file(file_name);
		}

		// 글쓴이는 로그인을 한 사람
		writePostDTO.setPost_writer_idx(loginMemberDTO.getMember_idx());

		// 글쓰기
		boardDAO.addPostInfo(writePostDTO);

	}

	private String saveUploadFile(MultipartFile upload_file) {
		
		try {
			
			String clientId = "e957565cfb7c026";
			URL url = new URL("https://api.imgur.com/3/image");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Client-ID " + clientId);

			// 파일 내용을 Base64로 인코딩
			byte[] imageBytes = upload_file.getBytes();
			String encodedImage = Base64.getEncoder().encodeToString(imageBytes);
			
			String data = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(encodedImage, "UTF-8");
			
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(data);
			writer.flush();
			writer.close();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			
			String line;
			
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
			reader.close();
			
			// 응답 JSON 파싱
			JSONObject json = new JSONObject(sb.toString());
			
			String link = json.getJSONObject("data").getString("link");  // 업로드된 이미지 링크
			
			return link;  // DB에 저장할 링크 반환
	
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		
		}

	//	String file_name = System.currentTimeMillis() + "_" + upload_file.getOriginalFilename();
	//	try {
	//		upload_file.transferTo(new File(path_load + "/" + file_name));
	//	} catch (IllegalStateException e) {
	//		e.printStackTrace();
	//	} catch (IOException e) {
	//		e.printStackTrace();
	//	}
	//	return file_name;

	}

	
	// 글 수정하기.
	public void modifyPostInfo(PostDTO modifyPostDTO) {

		MultipartFile upload_file = modifyPostDTO.getUpload_file();

		if (upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			modifyPostDTO.setPost_file(file_name);
		}
		boardDAO.modifyPostInfo(modifyPostDTO);
	}

	// 게시판 이름 가져오기
	public String getBoardName(int board_idx) {
		String board_name = boardDAO.getBoardName(board_idx);
		return board_name;
	}


	public PageDTO getPostCnt(int post_board_idx, int currentPage) {

		int postCnt = boardDAO.getPostCnt(post_board_idx);
		PageDTO pageDTO = new PageDTO(postCnt, currentPage, page_listcnt, page_paginationcnt);

		return pageDTO;
	}


	public int searchResultCount(PostDTO searchPostDTO) {
		int search_result_count = boardDAO.searchResultCount(searchPostDTO);
		return search_result_count;
	}

	public List<PostDTO> selectSearchList(PostDTO searchPostDTO, int page) {

		int start = (page - 1) * page_listcnt;
		RowBounds rowBounds = new RowBounds(start, page_listcnt);

		List<PostDTO> searchList = boardDAO.selectSearchList(searchPostDTO, rowBounds);

		return searchList;
	}


	public PageDTO searchListCount(PostDTO searchPostDTO, int currentPage) {

		int search_result_count = boardDAO.searchResultCount(searchPostDTO);
		PageDTO pageDTO = new PageDTO(search_result_count, currentPage, page_listcnt, page_paginationcnt);

		return pageDTO;
	}

	public PostDTO getPostInfo(int post_idx) {
		PostDTO readPostDTO = boardDAO.getPostInfo(post_idx);
		return readPostDTO;
	}

	public void delete(int post_idx) {
		boardDAO.delete(post_idx);
	}

	public void viewCount(int post_idx) {
		boardDAO.viewCount(post_idx);
	}

}
