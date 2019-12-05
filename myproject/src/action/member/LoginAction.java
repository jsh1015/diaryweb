package action.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import model.Board;
import model.BoardDao;
import model.Member;
import model.MemberDao;
/*
 * 1. id, pass 파라미터 저장
 * 2. id 해당하는 정보를 db에서 조회
 * 		- 존재하면 비밀번호 확인
 * 		- 존재하지 않으면 id 없음 메세지를 출력하고 loginForm.me 페이지로 이동
 * 3. id가 존재하는 경우 비밀번호 검증
 * 		- 비밀번호 맞는 경우 : session 로그인 정보 저장
 * 						   로그인 성공 메세지 출력, main.me 페이지로 이동
 * 		- 비밀번호 틀리는 경우 : 로그인 실패 메세지 출력, loginForm.me 페이지로 이동
 */
public class LoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass"); // 1. 
		String msg = "해당아이디가 존재하지 않습니다.";	//2. 존재X
		String url = "loginForm.me"; 
		Member mem = new MemberDao().selectOne(id);
		/*
		 * Board b = new Board(); b.setId(id);
		 */
		/* List<Board_like> like = new BoardDao().likelistselect(b); */
		if(mem != null) {
			if(pass.equals(mem.getPass())) {
				request.getSession().setAttribute("login",id); 
				request.getSession().setAttribute("nickname",mem.getName()); 
				request.getSession().setAttribute("picture",mem.getPicture()); 
				//session에러 (jsp의 내장객체이고 java의 내장객체가 아님)
				//그래서 request.getSession()으로 가져와야함
				
				msg = mem.getName() + "님이 로그인 하셨습니다.";
				url = "main.me";
			}else {
				msg = "비밀번호가 틀립니다."; //어차피 로그인 화면으로감
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}

}
