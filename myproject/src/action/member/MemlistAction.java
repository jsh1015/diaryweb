package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ActionForward;
import model.MemberDao;

public class MemlistAction extends AdminLoginAction{

	@Override
	protected ActionForward adminExecute(HttpServletRequest request, HttpServletResponse response) {
		MemberDao dao = new MemberDao();
		request.setAttribute("memlist", dao.memlist());
		return new ActionForward();
	}
}
