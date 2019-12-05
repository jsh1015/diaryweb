package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import model.Member;
import model.MemberDao;
/*
 * 1. 파라미터 정보를 Member 객체에 저장(useBean쓸수없음)
 * 2. 저장한 Member객체를 db에 추가
 * 		- 성공 : 회원가입 완료 메세지 출력, loginForm.me로 이동
 * 		- 실패 : 회원가입 실패 메세지 출력, joinForm.me로 이동
 * 
 * 	**request.setCharacterEncoding을 할 필요가 없음 Controller에서 이미 했기 때문에.**
 */
public class JoinAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Member mem = new Member();
		mem.setId(request.getParameter("id"));
		mem.setPass(request.getParameter("pass"));
		mem.setName(request.getParameter("name"));
		mem.setEmail(request.getParameter("email"));
		mem.setPicture(request.getParameter("picture"));
		mem.setDiaryname(request.getParameter("diaryname"));
		System.out.println(mem);
		MemberDao dao = new MemberDao();
		String msg = "회원가입 실패";
		String url = "joinForm.me";
		if(dao.insert(mem) > 0) {
			msg = "회원가입 성공";
			url = "loginForm.me";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}

}
