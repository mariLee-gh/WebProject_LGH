package servlet;

import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.MemberDAO;
import mvcboard.MVCboardDAO;
import mvcboard.MVCboardDTO;
import utils.JSFunction;
@WebServlet("/write.do")
@MultipartConfig(
		maxFileSize = 1024 * 1024 * 50, //(*1은 용량)
		maxRequestSize = 1024 * 1024 * 100
		)

public class WriteController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		// session영역에 회원인증에 관련된 속성이 없다면 로그인 페이지로 이동한다.
		if (session.getAttribute("username") == null) {
			JSFunction.alertLocation(resp, "로그인 후 이용해 주세요.", "LoginForm.jsp");
			// Java코드가 더이상 실행되지 않도록 차단
			return;
		}
		
		req.getRequestDispatcher("write.jsp").forward(req, resp);
	}
	
	// 글쓰기 처리
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그인 확인(세션은 일정 시간이 지나면 자동으로 헤재되므로 확인 필요함)
		HttpSession session = req.getSession();
		if (session.getAttribute("username") == null) {
			JSFunction.alertLocation(resp, "로그인 후 이용해주세요.", "LoginForm.jsp");
			return;
		}
		
		
		// 로그인이 완료된 상태라면 쓰기페이지를 포워드 한다.
	
		// 1. 파일 업로드 처리
		// 업로드 디텔터리의 물리적 경로 확인
		String saveDirectory = req.getServletContext().getRealPath("/Uploads");

		String originalFileName = "";
		try {
			// 작성폼에서 전송한 파일을 업로드 처리
			originalFileName = FileUtil.uploadFile(req, saveDirectory);
		} catch (Exception e) {
			// 문제가 있는경우 예외처리
			e.printStackTrace();
			JSFunction.alertLocation(resp, "파일업로드 오류입니다.", "write.do");
			return;
		}

		// 2. 파일업로드 외 처리==========
		// 폼값을 DTO에저장
		MVCboardDTO dto = new MVCboardDTO();
		// 작성자 아이디는 session영역에 저장된 데이터를 이용한다.
		dto.setUsername(session.getAttribute("username").toString());
		
		MemberDAO memberDao  = new MemberDAO();
		int userId = memberDao.getUserIdByUserName(session.getAttribute("username").toString());
		
		try {
			  if(userId == -1) {
				  throw new Exception("유저 조회에 실패하였습니다.");
			   }
		}catch(Exception e) {
			e.printStackTrace();
			return;
		}
		
		
		String category = req.getParameter("category");

		
		// 제목과 내용들은 사용자가 전송한 폼값을 받은 후 저장한다
		dto.setUser_id(String.valueOf(userId));
		dto.setTitle(req.getParameter("title"));
		dto.setContent(req.getParameter("content"));
		dto.setCategory(category);

		// 원본 파일명과 저장된 파일이름 설정
		if (originalFileName != "") {
			// 파일명 변경
			String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
			// 파일업로드가 완료되었다면 파일정보를 DTO에 추가한다.
			dto.setOfile(originalFileName); // 원래 파일이름
			dto.setSfile(savedFileName); // 서버에 저장된 파일이름
		}
		// DAO를 통해 DB에 게시 내용 저장 (insert 쿼리문 실헹)
		MVCboardDAO dao = new MVCboardDAO();
		// 입력에 성곤하면1, 실패하면 0을 반환
		int result = dao.insertWrite(dto);

		/*	      *//*********************************************/
		/*
		 * //더미데이터 100개 입력하기 for (int i =1; i<=100 ; i++) {
		 * dto.setTitle(req.getParameter("title")+"-"+i); dao.insertWrite(dto); }
		 *//**********************************************/

		dao.close();

		// 성공 or 실행?
		if (result == 1) { // 글쓰기 성공
			// 게시판 목록으로 이동
			String redirectPage = category + "List.do"; //freeList.do QnAList.do
			resp.sendRedirect(redirectPage);
		} else {// 글쓰기실패
				// 글쓰기 페이지로 다시 돌아간다.
			JSFunction.alertLocation(resp, "글쓰기에 실패했습니다.", "write.do");
		}
	}

}
