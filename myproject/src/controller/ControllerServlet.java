package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import action.ActionForward;

@WebServlet(urlPatterns= {"*.me"},
		initParams = {@WebInitParam(name="properties", value = "action.properties")})
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Map<String,Action> commandMap = new HashMap<String,Action>();
    public ControllerServlet() {
        super();
    }
    @Override
    public void init(ServletConfig config) throws ServletException{
    	String props = config.getInitParameter("properties");
    	Properties pr = new Properties();
    	FileInputStream f = null;
    	try { 
    		f = new FileInputStream(config.getServletContext().getRealPath("/")+"WEB-INF/"+props);
    		pr.load(f);
    		// /model2/ action.AddAction을 가져옴
    	}catch(IOException e) {
    		throw new ServletException(e);
    	}finally {
    		try {
    			if(f!=null) f.close();
    		}catch(IOException e) {}
    	}
    	for(Object k : pr.keySet()) {
    		String command =(String)k;
    		String className = pr.getProperty(command);
    		try {
    			Class commandClass = Class.forName(className.trim()); 
    			//commandObj : action.HelloAction또는 AddAction
    			Object commandObj = commandClass.newInstance(); //AddAction을 객체화
    			commandMap.put(command,(Action)commandObj); //commandMap객체에 등록
    		}catch(Exception e) {
    			throw new ServletException(e);
    		}
    	}
    }
    /*request, response 
     * command로부터 action을 가져옴*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		Action action = null;
		ActionForward forward = null;
		String command = null;
		try {
			//command : /model2/hello.me 를 추출하는과정 (key로쓰일내용)
			command =request.getRequestURI().substring(request.getContextPath().length());
			//action : Action 인터페이스의 구현 객체
			//			action.HelloAction 객체
			action = commandMap.get(command);
			forward = action.execute(request, response);//AddAction에서 받아옴
			//execute가 꼭있음 그래야 forward시켜줌
			/* redirecte:false view:hello.jsp/addform.jsp
			 * execute(request,response)	(response영역)
			 * -->key:hello value:hello world  (request영역)*/
		}catch(NullPointerException e) {
			e.printStackTrace();
	//		forward = new ActionForward(false,null);
			forward = new ActionForward();
		}catch(Exception e) {
			throw new ServletException(e);
		}
		
		if(forward != null) {
			if(forward.isRedirect()) { //redirect:false
				response.sendRedirect(forward.getView());
			}else {
				if(forward.getView()==null) { //뷰가없으면 그냥 현재 자기이름으로 실행
					forward.setView(command.replace(".me", ".jsp"));
				}
				//forward.getView() : action클래스에서 전달 view이름
				//					  addForm.jsp
				//forward 대상을 선정											addForm.jsp
				RequestDispatcher disp = request.getRequestDispatcher(forward.getView());
				disp.forward(request, response);
				//			파라미터3개, 속성:result
			}
		}else response.sendRedirect("nopage.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
