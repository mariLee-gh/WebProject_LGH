package utils;

import java.io.PrintWriter;
import java.io.Writer;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.JspWriter;

//자주 사용하는 Javascript의 함수를 클래스로 정의
public class JSFunction {
	/*
	 * 매서드 생성시 static을 통해 정적메서드로 정의하면 해당 클래스의 인스턴스를 생성하지 않고도 클래스명으로 즉시 메서드를 호출할수있다.
	 */
	
	
	public static void alertLocation(String msg, String url, JspWriter out) {
		/*java클래스에서는 jsp의 내장객체ㅇ를 즉시 사용할 수 없으므로 반드시 매개변수로 전달받아 사용해야한다.
		여기서는 웹브라우저에 문자열을 출력하기 위해 out내장객체를 JspWriter타입으로 받은 후 사용하고있다*/
		try {
			String script = ""
					+"<script>"
					+"		alert('"+ msg + "');"
					+"		location.href= '" + url + "';"
					+"</script>" ;
					
			out.println(script);
			
		}
		catch (Exception e) {}
	}
	
	public static void alertBack(String msg, JspWriter out) {
		try {
			String script = ""
					+ "<script>"
					+"		alert('"+ msg + "');"
					+"		history.bcak()	;"
					+"</script>" ;
			out.println(script);
		}
		catch (Exception e) {}
			// TODO: handle exception
	}
	//////////////////////////////////////////////////////////////////
	/*서블릿에서 JavaScript를 실행할 수 있도록 정의한 메서드
	 * JSP로 포워드 하지않고 즉시 출력해야 하므로 컨텐츠타입을 먼저 설정하고 있다.
	 * */
	public static void alertLocation(HttpServletResponse resp, String msg, String url) {

		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			//경고창을 띄우고 특정 페이지로 이동할 수 있는 JS코드 작성
			String script = ""
					+"<script>"
					+"		alert('"+ msg + "');"
					+"		location.href= '" + url + "';"
					+"</script>" ;
					
			writer.print(script);
			
		}
		catch (Exception e) {}
	}
	//경고장을 띄우고 뒤로이동하는 JS코드 작성
	public static void alertBack(HttpServletResponse resp, String msg ) {
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script = ""
					+ "<script>"
					+"		alert('"+ msg + "');"
					+"		history.back()	;"
					+"</script>" ;
			writer.print(script);
		}
		catch (Exception e) {}
			// TODO: handle exception
	} 
	

}
