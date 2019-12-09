package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ActionForward;
import model.BoardDao;
import model.Member;
import model.MemberDao;
/*
 *	���
 *	1. ��� �Ķ���� Member ��ü�� ����
 *	2. �Էµ� ��й�ȣ�� db�� ����� ��й�ȣ�� ���� ������
 *		"��й�ȣ�� Ʋ�Ƚ��ϴ�." �޼��� ����� , updateForm.me�������� �̵�
 *	3. 1���� ������ db�� ����
 *		 int MemberDao.update(Member)
 *		����� 0���� ũ�� �������� �޼��� ���. info.me ������ �̵�
 *			0���ϸ� ���� ���� �޼��� ���. main.me������ �̵�
*/
public class UpdateAction extends UserLoginAction{

	@Override
	protected ActionForward doExecute(HttpServletRequest request, HttpServletResponse response) {
		Member mem = new Member();
		String login = (String) request.getSession().getAttribute("login");
		mem.setId(request.getParameter("id"));
		mem.setName(request.getParameter("name"));
		mem.setPicture(request.getParameter("picture"));
		mem.setEmail(request.getParameter("email"));
		mem.setDiaryname(request.getParameter("diaryname"));
		Member m = new MemberDao().selectOne(login);
		String msg = null;
		String url = null;
		if(!login.equals("admin") && !m.getPass().equals(request.getParameter("pass"))) {
			msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�.";
			url = "mypage.me?id=" + request.getParameter("id");
		}else {
			int result = new MemberDao().update(mem);
			int board = new BoardDao().nameupdate(request.getParameter("name"),request.getParameter("id"));
			if(result >0 && board>0 ) {
				msg = "�����Ϸ�";
				if(login.equals("admin")) {
					url = "memlist.me";
				}else {
					url = "mypage.me?id=" + request.getParameter("id");
				}
				request.getSession().setAttribute("login",login); 
				request.getSession().setAttribute("nickname",m.getName()); 
				request.getSession().setAttribute("picture",m.getPicture()); 
			}else{
				msg = "������ �����߻�";
				url = "main.me";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
}
