package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import model.MemberDao;

public class IdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 String email = request.getParameter("email");
		 String tel = request.getParameter("tel");
		 String id = new MemberDao().idselect(email, tel);
		 String msg = null;
		 String url = null;
		 if(id==null ||id.equals("")){
			 msg ="정보에 맞는 id를 찾을 수 없습니다.";
			 url ="idForm.jsp";
			 request.setAttribute("msg", msg);
			 request.setAttribute("url", url);
			 return new ActionForward(false, "../alert.jsp");
		 }else{
			 String send = id.substring(0,id.length()/2+1);
			 request.setAttribute("id", send);
			 return new ActionForward();
		 }
	}

}
