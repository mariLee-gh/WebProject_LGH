package servlet;

import java.io.File;
import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvcboard.MVCboardDAO;
import mvcboard.MVCboardDTO;
import utils.JSFunction;

//게시물 삭제 처리
@WebServlet("/delete.do")
public class DeleteController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		//로그인 확인
		if(session.getAttribute("username")==null) {
			//session영역에 인증에 관련된 속성이 있는지 확인 후 경고장 듸움
			JSFunction.alertLocation(resp, "로그인 후 이용해주세요.", "LoginForm.jsp");
			return;
		}
		//게시물 얻어오기: 얄람에서 사용한 메서드를 그대로 사용한다.
		String board_id = req.getParameter("board_id");
		MVCboardDAO dao = new MVCboardDAO();
		MVCboardDTO dto = dao.selectView(board_id);
		
		/*작성자 본인 확인: DTO에 저장된 아이디와 session영역에 저장된 아이디를 비교하여
		 *  본인이 아니라면 경고장을 띄운다.*/
		if(!dto.getUsername().equals(session.getAttribute("username").toString())){
			JSFunction.alertBack(resp, "작성자 본인만 삭제할 수 있습니다.");
			return;
		}
		
		//게시물 삭제
		int result = dao.deletePost(board_id);
		dao.close();
		if(result==1) { //게시물 삭제 성공시 첨부파일도 삭제
			String saveFileName = dto.getSfile();
			//서버에 저장된 파일명으로 파일을 삭제한다.
			FileUtil.deleteFile(req, "/Uploads", saveFileName);
		}
		//삭제가 완료되면 목록으로 이동한다.
		JSFunction.alertLocation(resp, "삭제되었습니다.", "freeList.do");
		
	}

}
