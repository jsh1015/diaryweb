package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ActionForward;
import model.Member;
import model.MemberDao;

public class DeleteAction extends UserLoginAction{
	
	@Override
	protected ActionForward doExecute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String msg = null;
		String url = null;
		if(id.equals("admin")) { //1.
			msg = "°ü¸®ÀÚ´Â Å»ÅðÇÒ ¼ö ¾ø½À´Ï´Ù.";
			url = "main.me";
			return new ActionForward(false, "../alert.jsp");
		}
		
		Member m = new MemberDao().selectOne(id);
		int result = new MemberDao().delete(id);
		new MemberDao().decodelete(id);
		new MemberDao().likedelete(id);
		if(login.equals("admin") || m.getPass().equals(pass)) {
			if(result>0) {
				if(login.equals("admin")) {
					msg = id +"´Ô °­Á¦Å»Åð ¼º°ø";
					url = "main.me";
				}else {
					request.getSession().invalidate();
					msg = id + "´ÔÀÇ Å»Åð°¡ ¿Ï·áµÇ¾ú½À´Ï´Ù.";
					url = "loginForm.me";
				}
			}else {
				msg = "Å»Åð ½ÇÆÐ";
				if(login.equals("admin")) {
					url="main.me";
				}else {
					url="mypage.me?id="+id;
				}
			}
		}else {
			msg = "ºñ¹Ð¹øÈ£°¡ Æ²¸³´Ï´Ù.";
			url = "deleteForm.jsp?id=" + id;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
}
