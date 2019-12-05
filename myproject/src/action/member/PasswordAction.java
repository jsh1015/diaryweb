package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import model.Member;
import model.MemberDao;

/*
 * 1. 로그아웃상태인 경우, 로그인하세요 메세지 출력
 * 		다른 사용자의 비밀번호 변경 불가
 * 		opener 페이지를 loginForm.me 페이지로 이동
 * 		현재 페이지 닫기
 * 2. pass, rpass1 파라미터값 저장
 * 3. pass 비밀번호가 db에 저장된 비밀번호와 틀리면 
 * 		비밀번호 오류 메세지 출력. passwordForm.me 페이지 이동
 * 4. pass 비밀번호가 db에 저장된 비밀번호와 같으면 비밀번호 수정.
 * 		opener 페이지를 info.me 페이지 이동.
 * 		현재페이지 닫기
 */
public class PasswordAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String login = (String)request.getSession().getAttribute("login");
		boolean op = false;
		boolean c = false;
		String msg = "비밀번호 오류입니다. 확인하세요";
		String url = "passwordForm.me";
		if(login == null || login.trim().equals("")) { //로그아웃상태
			msg = "로그인 하세요";
			url = "loginForm.me";
			op = true;
			c = true;
		}else { //로그인 상태
			String pass = request.getParameter("pass");
			Member mem = new MemberDao().selectOne(login); //다른사용자 변경 못하게 하기위해
			if(pass.equals(mem.getPass())) {
				c = true;
				String chpass = request.getParameter("rpass1");
				int result = new MemberDao().passupdate(login, chpass);
				if(result>0) {
					msg="비밀번호 변경완료, 재로그인 하세요";
					url="loginForm.jsp";
					op = true;
				}else {
					msg = "비밀번호 변경실패";
				}
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		request.setAttribute("c", c);
		request.setAttribute("op", op);
		return new ActionForward();
	}

}
