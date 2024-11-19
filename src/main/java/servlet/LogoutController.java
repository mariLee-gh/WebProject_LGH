package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout.do")
public class LogoutController extends HttpServlet {
	
	@Override
	// GET 요청 처리
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 로그아웃 처리
        logout(request, response);
    }

    // POST 요청 처리
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 로그아웃 처리
        logout(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 세션 가져오기
        HttpSession session = request.getSession(false); // 기존 세션이 있으면 가져오고, 없으면 null 반환
        
        if (session != null) {
            // 세션에서 사용자 정보 삭제
            session.removeAttribute("UserID");
            session.removeAttribute("UserName");
            
            // 세션 무효화
            session.invalidate();
        }
        
        // 로그인 페이지로 리다이렉트
        response.sendRedirect("LoginForm.jsp");
    }

}
