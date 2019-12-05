package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import model.MemberDao;

public class PwAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		String pass = new MemberDao().pwselect(id, email, tel);
		if(pass==null || pass.equals("")){
			String msg = "정보에 맞는 비밀번호를 찾을 수 없습니다.";
			String url ="pwForm.jsp";
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return new ActionForward(false, "../alert.jsp");
		}else{
			String sendpw = "" ;
			for(int i=0; i<pass.length()/2; i++){
				sendpw += "*";
			}
			sendpw += pass.substring(pass.length()/2,pass.length());
			request.setAttribute("pass", sendpw);
			return new ActionForward();
		}
	}
}
