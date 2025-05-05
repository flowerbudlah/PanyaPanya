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

	@Lazy
	@Resource(name = "loginMemberDTO")
	private MemberDTO loginMemberDTO;

	// 1. 1) 게시물 목록 (in 게시판 메인화면) 
	public List<PostDTO> getPostList(int board_idx, int page) {
		// 10
		int start = (page - 1) * page_listcnt;
		RowBounds rowBounds = new RowBounds(start, page_listcnt);

		List<PostDTO> postList = boardDAO.getPostList(board_idx, rowBounds);
		return postList;
	}
	
	// 1. 2) 게시판 이름 가져오기 (in 게시판 메인화면)
	public String getBoardName(int board_idx) {
		String board_name = boardDAO.getBoardName(board_idx);
		return board_name;
	}
	
	// 1. 3) 게시판 메인화면에서의 페이지 처리
	public PageDTO getPostCnt(int post_board_idx, int currentPage) {

		int postCnt = boardDAO.getPostCnt(post_board_idx);
		PageDTO pageDTO = new PageDTO(postCnt, currentPage, page_listcnt, page_paginationcnt);

		return pageDTO;
	}

	// 1. 4) 검색된 게시글 목록
	public List<PostDTO> selectSearchList(PostDTO searchPostDTO, int page) {

		int start = (page - 1) * page_listcnt;
		RowBounds rowBounds = new RowBounds(start, page_listcnt);

		List<PostDTO> searchList = boardDAO.selectSearchList(searchPostDTO, rowBounds);

		return searchList;
	}

	// 1. 5) 검색된 게시글 수(결과 게시글)
	public int searchResultCount(PostDTO searchPostDTO) {
		int search_result_count = boardDAO.searchResultCount(searchPostDTO);
		return search_result_count;
	}

	// 1. 6) 게시판 메인화면에서 특정게시물을 검색한 후의 화면에서의 페이지 처리
	public PageDTO searchListCount(PostDTO searchPostDTO, int currentPage) {

		int search_result_count = boardDAO.searchResultCount(searchPostDTO);
		PageDTO pageDTO = new PageDTO(search_result_count, currentPage, page_listcnt, page_paginationcnt);

		return pageDTO;
	}

	// 2. 1) 글쓰기
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

	// 2. 2) Image File Uploading
	private String saveUploadFile(MultipartFile upload_file) {

		try {

			// 개인별 고유의 ID
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

			// 업로드된 이미지 링크
			String link = json.getJSONObject("data").getString("link");

			// DB에 저장할 링크 반환
			return link;

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		}

		// 배포 전 로컬에서 이미지 파일을 업로드 했을 때의 함수
		// String file_name = System.currentTimeMillis() + "_" +
		// upload_file.getOriginalFilename();
		// try {
		// upload_file.transferTo(new File(path_load + "/" + file_name));
		// } catch (IllegalStateException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// return file_name;

	}

	// 3. 1) 특정한 게시글 하나 읽기 Reading
	public PostDTO getPostInfo(int post_idx) {
		PostDTO readPostDTO = boardDAO.getPostInfo(post_idx);
		return readPostDTO;
	}
	
	// 3. 2) 특정 게시글의 조회수 증가
	public void viewCount(int post_idx) {
		boardDAO.viewCount(post_idx);
	}

	// 4. 글 수정하기 Updating
	public void modifyPostInfo(PostDTO modifyPostDTO) {

		MultipartFile upload_file = modifyPostDTO.getUpload_file();

		if (upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			modifyPostDTO.setPost_file(file_name);
		}

		boardDAO.modifyPostInfo(modifyPostDTO);
	}

	// 5. 특정 글 삭제 Deleting
	public void delete(int post_idx) {
		boardDAO.delete(post_idx);
	}

}