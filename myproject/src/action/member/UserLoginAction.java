package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;

public abstract class UserLoginAction implements Action {
	protected String login;	//로그인 정보
	protected String id;	//파라미터 정보
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		login = (String)request.getSession().getAttribute("login");
		id = request.getParameter("id");
		if(login == null || login.trim().equals("")) {
			request.setAttribute("msg", "로그인 후 거래하세요");
			request.setAttribute("url", "loginForm.me");
			return new ActionForward(false, "../alert.jsp"); //doExecute를 실행 하지않음
		}else {
			if(!login.equals("admin") && id!=null && !login.equals(id)) { 
				request.setAttribute("msg", "본인만 가능합니다.");
				request.setAttribute("url", "main.me");
				return new ActionForward(false, "../alert.jsp");
			}
		}
		return doExecute(request, response); //main
	}
	protected abstract ActionForward doExecute(HttpServletRequest request, HttpServletResponse response);
}
