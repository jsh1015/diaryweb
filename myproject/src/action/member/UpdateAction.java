package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ActionForward;
import model.Member;
import model.MemberDao;
/*
 *	기능
 *	1. 모든 파라미터 Member 객체에 저장
 *	2. 입력된 비밀번호와 db에 저장된 비밀번호가 같지 않으면
 *		"비밀번호가 틀렸습니다." 메세지 출력후 , updateForm.me페이지로 이동
 *	3. 1번의 내용을 db에 수정
 *		 int MemberDao.update(Member)
 *		결과가 0보다 크면 수정성공 메세지 출력. info.me 페이지 이동
 *			0이하면 수정 실패 메세지 출력. main.me페이지 이동
*/
public class UpdateAction extends UserLoginAction{

	@Override
	protected ActionForward doExecute(HttpServletRequest request, HttpServletResponse response) {
		Member mem = new Member();
		mem.setId(request.getParameter("id"));
		mem.setName(request.getParameter("name"));
		mem.setPicture(request.getParameter("picture"));
		mem.setEmail(request.getParameter("email"));
		mem.setDiaryname(request.getParameter("diaryname"));
		Member m = new MemberDao().selectOne(request.getParameter("id"));
		String msg = null;
		String url = null;
		if(!m.getPass().equals(request.getParameter("pass"))) {
			msg = "비밀번호가 틀렸습니다.";
			url = "mypage.me?id=" + request.getParameter("id");
		}else {
			int result = new MemberDao().update(mem);
			if(result >0 ) {
				msg = "수정완료";
				url = "mypage.me?id=" + request.getParameter("id");
				request.getSession().setAttribute("login",id); 
				request.getSession().setAttribute("nickname",mem.getName()); 
				request.getSession().setAttribute("picture",mem.getPicture()); 
			}else{
				msg = "수정중 오류발생";
				url = "main.me";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
}
