package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import model.MemberDao;

public class MemsearchAction extends UserLoginAction{

	@Override
	protected ActionForward doExecute(HttpServletRequest request, HttpServletResponse response) {
		String searchid = request.getParameter("searchid");
		MemberDao dao = new MemberDao();
		request.setAttribute("mem", dao.memsearch(searchid));
		return new ActionForward();
	}
}