package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ActionForward;
import action.board.BoardAllAction;

@WebServlet(urlPatterns = {"*.do"}, //.do가들어오면 나를 호출해라
initParams= {@WebInitParam(name="properties",value="method.properties")})

public class ControllerMethodServlet extends HttpServlet{ //클래스상태로 들어와있다가 객체화 되어있지않으면 톰캣이 객체화를 시킴
	private static final long serialVersionUID = 1L;
	private Properties pr = new Properties();
	private BoardAllAction action = new BoardAllAction(); 
	private Class[] paramType = new Class[] {HttpServletRequest.class,HttpServletResponse.class};
	
	@Override 
	//init : 서블릿이 객체화 된 이후 바로 호출되는 메서드(한번만)
	//config 객체 : 파라미터 전달
	//				properties = method.properties
	public void init(ServletConfig config) throws ServletException{ //객체화되면 init메서드가 실행됨
//		props : method.properties
		String props = config.getInitParameter("properties");
		FileInputStream f = null;
		try {
//			f : WEB-INF/method.properties 파일 읽기 위한 입력 스트림
			f = new FileInputStream(config.getServletContext().getRealPath("/")+"WEB-INF/"+props);
//			key = /model2/hello.do
//			value = hello
			pr.load(f); // Map객체의 하위객체 Properties pr
			f.close();
		}catch(IOException e) {e.printStackTrace();}
	}
//	클라이언트  GET 방식 요청시 호출되는 메서드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		Object[] paramObjs = new Object[] {request,response};
		ActionForward forward = null;
		String command = null;
		try {
//			System.out.println(request.getRequestURI());	/jspstudy2/*.do
//			System.out.println(request.getContextPath());	/jspstudy2
//			프로젝트를 제외한 요청 url : /model2/hello.do
			command = request.getRequestURI().substring(request.getContextPath().length());
//			methodName : hello
			String methodName = pr.getProperty(command);
//			method : 메서드 객체 생성
//			BoardAllAction 클래스 중 메서드의 이름이 methodName 이고
//			매개변수가 paramType인 메서드를 객체로 리턴
			Method method = BoardAllAction.class.getMethod(methodName, paramType);
//			invoke : 메서드를 호출하는 기능			BoardAllAction
//					 action.hello(request,response) 호출 //리턴값이 ActionForward
			forward = (ActionForward)method.invoke(action, paramObjs);
		}catch(NullPointerException e){
			forward = new ActionForward();
			e.printStackTrace();
		}catch(Exception e) {
			throw new ServletException(e);
		}
		//View부분
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getView());
			}else {
				if(forward.getView() == null) {
					forward.setView(command.replace(".do", ".jsp")); //jsp로 바로실행하게 함
				}
				RequestDispatcher disp = request.getRequestDispatcher(forward.getView());
				disp.forward(request, response);
			}
		}else response.sendRedirect("nopage.jsp");
	}
	
//	클라이언트  POST 방식 요청시 호출되는 메서드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
