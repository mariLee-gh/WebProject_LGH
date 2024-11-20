package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.MemberDAO;
import member.MemberDTO;

@WebServlet("/regiForm.do")
public class RegiController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		
		// 입력 검증 
        if (firstname == null || firstname.trim().isEmpty() ||
            lastname == null || lastname.trim().isEmpty() ||
            username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            email == null || email.trim().isEmpty()) {  	
            req.setAttribute("error", "모든 정보를 기입해 주세요.");
            req.getRequestDispatcher("/regiForm.jsp").forward(req, resp);
            return;
            
            
        }
        
        MemberDAO dao = new MemberDAO();  // MemberDAO 객체 생성
        boolean isUsernameDuplicate = dao.isUsernameDuplicate(username);  // 인스턴스를 통해 호출
        boolean  isEmailDuplicate = dao. isEmailDuplicate(email);
        
        // MemberDTO 객체에 입력값 설정
        MemberDTO newUser = new MemberDTO();
        newUser.setFirst_Name(firstname);
        newUser.setLast_Name(lastname);
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);

        // 아이디 중복 검사
        if (dao.isUsernameDuplicate(username)) {
            req.setAttribute("usernameError", "아이디가 이미 존재합니다.");
            req.getRequestDispatcher("regiForm.jsp").forward(req, resp);
            return;
        }

        // 이메일 중복 검사
        if (dao.isEmailDuplicate(email)) {
            req.setAttribute("emailError", "이메일이 이미 존재합니다.");
            req.getRequestDispatcher("regiForm.jsp").forward(req, resp);
            return;
        }

        // 회원가입 처리
        int result = dao.regiUser(newUser);
        if (result > 0) {
            resp.sendRedirect("LoginForm.jsp"); // 회원가입 성공 후 로그인 페이지로 리디렉션
        } else {
            req.setAttribute("registerError", "회원가입 실패. 잠시 후 다시 시도해 주세요.");
            req.getRequestDispatcher("regiForm.jsp").forward(req, resp);
        }

	}
}
