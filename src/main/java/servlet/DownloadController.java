package servlet;

import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvcboard.MVCboardDAO;

@WebServlet("/download.do")
public class DownloadController extends HttpServlet{
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String ofile = req.getParameter("ofile");
	String sfile = req.getParameter("sfile");
	String board_id = req.getParameter("board_id");
	FileUtil.download(req, resp, "/Uploads" ,sfile, ofile);
	//해당게시물의 다운로드+1
	MVCboardDAO dao = new MVCboardDAO();
	dao.downCountPlus("board_id");
	dao.close();
	}
}
