package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
/*
 * 1. 로그아웃 상태 : 로그인이 필요합니다. 메세지 출력
 * 					loginForm.me 페이지 이동
 * 2. 로그인 상태
 * 		일반사용자 : 관리자만 가능한 거래입니다. 메세지출력
 * 					main.me로 페이지 이동
 */
public abstract class AdminLoginAction implements Action {
	protected String login;	//로그인 정보
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		login = (String)request.getSession().getAttribute("login");
		if(login == null || login.equals("")) {
			request.setAttribute("msg", "로그인이 필요합니다.");
			request.setAttribute("url", "loginForm.jsp");
			return new ActionForward(false, "../alert.jsp");
		}else if(!login.equals("admin")) {
				request.setAttribute("msg", "관리자만 가능한 거래입니다.");
				request.setAttribute("url", "main.jsp");
				return new ActionForward(false, "../alert.jsp");
		}
		return adminExecute(request,response); //실행하는부분
	}
	protected abstract ActionForward adminExecute(HttpServletRequest request, HttpServletResponse response);
}
