package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ActionForward;

public class LogoutAction extends UserLoginAction{ 
	//UserLoginAction에서 이미 로그인안한 사람들을 걸러내므로 비교할 필요없이 로그아웃된것만 하면 됨.

	@Override
	protected ActionForward doExecute(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		request.setAttribute("msg", login + "님이 로그아웃 되었습니다."); //login은 UserLoginAction에있음
		request.setAttribute("url", "loginForm.jsp");
		return new ActionForward(false, "../alert.jsp");
	}
}
