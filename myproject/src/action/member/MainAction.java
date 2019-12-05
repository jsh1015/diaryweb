package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ActionForward;
import model.BoardDao;

public class MainAction extends UserLoginAction{

	@Override
	protected ActionForward doExecute(HttpServletRequest request, HttpServletResponse response) {
		BoardDao dao = new BoardDao();
		request.setAttribute("stickerlist", dao.stickerlikelist());
		request.setAttribute("backgroundlist", dao.backgroundlikelist());
		return new ActionForward();
	}
}