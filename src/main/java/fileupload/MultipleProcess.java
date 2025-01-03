package fileupload;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//서블릿매핑
@WebServlet("/MultipleProcess.do")
//업로드제한용량
@MultipartConfig(
		maxFileSize = 1024 * 1024 * 50, //(*1은 용량)
		maxRequestSize = 1024 * 1024 * 100
		)

public class MultipleProcess extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			//저장 디렉토리의 물리적 경로 얻어오기
			String saveDirectory = getServletContext().getRealPath("/Uploads");
			//멀티 파일 업로드를 위한 함수 호출
			ArrayList<String> listFileName = FileUtil.multipleFile(req, saveDirectory);
			
			//업로드한 파일의 갯수만큼 반복하여 파일명을 변경
			for(String originalFileName : listFileName); {
				String savedFileName = FileUtil.renameFile(saveDirectory, saveDirectory);
			}
			
			//파일 목록으로 이동한다.
			resp.sendRedirect("FreeList.jsp");
			
		}
		
		catch (Exception e) {
			e.printStackTrace();
			//업로드에 실패하면 request영역에 메세지를 저장 후 포워드
			req.setAttribute("errorMessage", "파일업로드오류");
			req.getRequestDispatcher("MultiUploadMain.jsp").forward(req, resp);
		}
	}

}