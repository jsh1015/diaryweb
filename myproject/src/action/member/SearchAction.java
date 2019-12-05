package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import model.MemberDao;

public class SearchAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String id = request.getParameter("id");
		if(id == null) {
			return idSearch(request,tel,email);
		}else {
			return pwSearch(request,id,tel,email);
		}
	}
	private ActionForward idSearch(HttpServletRequest request,String tel,String email) {
		MemberDao dao = new MemberDao();
		String id = dao.idselect(email,tel);
		String msg = null;
		String url = null;
		if(id!=null) {
			id = id.substring(0,id.length()-2);
			request.setAttribute("id", id);
			return new ActionForward();
		}else {
			request.setAttribute("msg", "정보에 맞는 id를 찾을 수 없습니다.");
			request.setAttribute("url", "idForm.jsp");
			return new ActionForward(false, "../alert.jsp");
		}
	}
	
	private ActionForward pwSearch(HttpServletRequest request,String id, String tel,String email) {
		MemberDao dao = new MemberDao();
		String pw = dao.pwselect(id,email, tel);
		String msg = null;
		String url = null;
		if(pw!=null) { //비밀번호 찾기 성공
			request.setAttribute("pass", pw.substring(2,pw.length()));
			return new ActionForward();
		}else { //비밀번호 찾기 실패
			request.setAttribute("msg", "정보에 맞는 비밀번호를 찾을 수 없습니다.");
			request.setAttribute("url", "pwForm.me");
			return new ActionForward(false, "../alert.jsp");
		}
	}

}
