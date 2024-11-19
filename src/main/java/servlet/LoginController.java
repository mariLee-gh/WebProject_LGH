package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.MemberDAO;
import member.MemberDTO;


@WebServlet("/login.do")
public class LoginController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String username= req.getParameter("username");
      String password = req.getParameter("password");
      
      MemberDAO dao = new MemberDAO();
      
      MemberDTO user = dao.getUser(username, password);
      dao.close();
      
      if(user != null) {
         HttpSession session = req.getSession();
         session.setAttribute("Username", user.getUsername());
         resp.sendRedirect("LoginSuccessful.jsp");
         
      } else {
         req.setAttribute("LoginErrMsg", "아이디 또는 비밀번호를 확인해주세요");
         req.getRequestDispatcher("LoginForm.jsp").forward(req, resp);
      }
   }


}
