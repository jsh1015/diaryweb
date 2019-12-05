package action.board;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import action.ActionForward;
import model.Board;
import model.BoardDao;
import model.Board_like;
import model.Deco;
import model.Member;

public class BoardAllAction{
	private BoardDao dao = new BoardDao();
	/*
	 * 1. multipart/form-data Ÿ���� �����̹Ƿ� MultiparteRequest ��ü ����
	 * 2. �Ķ���� ���� model.Board ��ü ����
	 * 3. �Խù���ȣ num ���� ��ϵ� num�� �ִ밪�� ��ȸ. �ִ밪 +1 ��ϵ� �Խù��� ��ȣ
	 * 		db���� maxnum�� ���ؼ� +1 ������ num �����ϱ�
	 * 4. board ������ db�� ����ϱ�
	 * 		��ϼ��� : �޼��� ���. list.do ������ �̵�
	 * 		��Ͻ��� : �޼��� ���. writeForm.me������ �̵�
	 */
	public ActionForward write(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		String msg = "�Խù� ��� ����";
		String url = "writeForm.jsp";
		String path = request.getServletContext().getRealPath("/")+"diary/board/file/";
		try {
			File f = new File(path);
			if(!f.exists()) f.mkdirs();
			MultipartRequest multi = new MultipartRequest(request,path,10*1024*1024,"utf-8"); //1.
			Board b = new Board();
			int boardnum = Integer.parseInt(multi.getParameter("boardnum"));
			
			b.setBoardnum(Integer.parseInt(multi.getParameter("boardnum")));
			b.setId(multi.getParameter("id"));
			b.setName(multi.getParameter("name"));
			b.setSubject(multi.getParameter("subject"));
			b.setContent(multi.getParameter("content"));
			b.setFile1(multi.getFilesystemName("file1"));	//2.
			b.setImg(multi.getParameter("img"));
			int num = dao.maxnum();	//�ִ밪 ������
			b.setNum(++num);
			/*b.setGrp(num); */
			if(dao.insert(b)) {
				msg = "�Խù� ��� ����";
				if(boardnum==1) {
					url = "stickerboard.do?boardnum=1";
				}else if(boardnum==2) {
					url = "backgroundboard.do?boardnum=2";
				}else if(boardnum ==3) {
					url = "board1.do?boardnum=3";
				}
			}					//3. 4.
		}catch(IOException e) {
			throw new ServletException(e);
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	
	/*
	 * 	1. ���������� 10���� �Խù��� ����ϱ�.
			pageNum �Ķ���Ͱ��� ���� 1-1������ =>���°��� 1�� �����ϱ�.
		2. �ֱ� ��ϵ� �Խù��� ���� ���� ��ġ��.(List����)
		3. ȭ�鿡 �ʿ��� ������ �Ӽ����� ���. =>��� ���� 
	*/
	public ActionForward list(HttpServletRequest request, HttpServletResponse response){
		int limit = 15; //�ѰǴ� ������ 10
		int pageNum = 1;
		int boardnum =0;
		String login = (String) request.getSession().getAttribute("login");
		try {
			boardnum = Integer.parseInt(request.getParameter("boardnum"));
			pageNum = Integer.parseInt(request.getParameter("pageNum"));//1.
		}catch(NumberFormatException e) {}
		String column = request.getParameter("column");
		String find = request.getParameter("find");
		if(column != null && column.trim().equals("")) column=null;
		if(find != null && find.trim().equals("")) find=null;
		if(column == null || find == null) {
			column = null;
			find = null;
		}
		int boardcnt = dao.boardCount(boardnum,column,find); //��ϵ� �ѰԽù��� �Ǽ�
		List<Board> list = dao.list(boardnum, pageNum, limit, column, find, login);
		 	
		int maxpage = (int)((double)boardcnt/limit + 0.95);
		int startpage = ((int)(pageNum/10.0+0.9)-1)*10+1;
		int endpage = startpage + 9;
		if(endpage >maxpage)endpage = maxpage;
		int board = boardcnt - (pageNum - 1) *limit;		//2.
		request.setAttribute("boardnum", boardnum);
		request.setAttribute("boardcnt", boardcnt);
		request.setAttribute("list", list);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("board", board);
		request.setAttribute("pageNum", pageNum);
		return new ActionForward();
	}
	
	/*
	 * 	1. num �Ķ���� ������ ������ ����. (�Խù���ȣ)
		2. num�� �̿��Ͽ� db���� �ش� �Խù� ������ ��ȸ
			Board b = new BoardDao.selectOne(num)
		3. ��ȸ�� ������Ű�� 
			void BoardDao.readcntadd(num)
		4. 2���� ��ȸ�� �Խù��� ȭ�鿡 ���
	 */
	public ActionForward info(HttpServletRequest request, HttpServletResponse response){
		int num = Integer.parseInt(request.getParameter("num")); //1
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		Board b = new BoardDao().selectOne(boardnum, num);//2
		
		//jspstudy2/medel2/info.do : �󼼺������� 
		if(request.getRequestURI().contains("info.do")) { 
			new BoardDao().readcntadd(num);	//3
		}
		request.setAttribute("b", b);	//4
		return new ActionForward();
	}
	
	public ActionForward picture(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getServletContext().getRealPath("") + "diary/board/picture";
	 	String fname = null;
		 try{
		 	File f = new File(path);
		 		if(!f.exists()){//�������� ������
		 			f.mkdirs(); //������ ����
		 		}
		 		MultipartRequest multi = new MultipartRequest(request,path,10*1024*1024,"utf-8");
		 		fname = multi.getFilesystemName("picture");
		 	}catch(IOException e){
		 		e.printStackTrace();
		 	}
		 request.setAttribute("fname", fname);
		 return new ActionForward(); //redirect:false,view:null
	}
	
	public ActionForward delete(HttpServletRequest request, HttpServletResponse response){
		int num = Integer.parseInt(request.getParameter("num"));
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		String login = (String) request.getSession().getAttribute("login");
		Board db = new BoardDao().selectOne(boardnum, num);
		String msg = "";
		String url = "";
		msg = "��������"; 
			if(boardnum==1) {
				url = "imginfo.do?num="+num+"&boardnum="+boardnum;
			}else if(boardnum==2) {
				url = "imginfo.do?num="+num+"&boardnum="+boardnum;
			}else if(boardnum==3) {
				url = "info.do?num="+num+"&boardnum="+boardnum;
			}
		if(db==null) {
			msg = "���� �Խñ��Դϴ�.";
			if(boardnum==1) {
				url = "stickerboard.do?boardnum=1";
			}else if(boardnum==2) {
				url = "backgroundboard.do?boardnum=2";
			}else if(boardnum==3) {
				url = "board1.do?boardnum=3";
			}
		}else {
			if(login.equals(db.getId())) {
				boolean result = new BoardDao().delete(num);
				if(result) {
					msg = "��������";
					if(boardnum==1) {
						url = "stickerboard.do?boardnum=1";
					}else if(boardnum==2) {
						url = "backgroundboard.do?boardnum=2";
					}else if(boardnum==3) {
						url = "board1.do?boardnum=3";
					}
				}
			}else if(!login.equals(db.getId())){
				msg ="���θ� ���� �����մϴ�.";
				if(boardnum==1) {
					url = "stickerboard.do?boardnum=1";
				}else if(boardnum==2) {
					url = "backgroundboard.do?boardnum=2";
				}else if(boardnum==3) {
					url = "board1.do?boardnum=3";
				}
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
	}
	
	public ActionForward update(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String path = request.getServletContext().getRealPath("/")+"diary/board/file/";
		MultipartRequest multi = new MultipartRequest(request,path,10*1024*1024,"utf-8"); //1.
		String login = (String) request.getSession().getAttribute("login");
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		Board b = new Board();
		b.setNum(Integer.parseInt(multi.getParameter("num")));
		b.setName(multi.getParameter("name"));
		b.setSubject(multi.getParameter("subject"));
		b.setContent(multi.getParameter("content"));
		b.setImg(multi.getParameter("img"));
		b.setFile1(multi.getFilesystemName("file1"));
		if(b.getFile1()==null || b.getFile1().equals("")){
			b.setFile1(multi.getParameter("file2"));
		}//������ ���� ���� �ʾ������
		
		Board board = new BoardDao().selectOne(boardnum, b.getNum());
		String msg = "��й�ȣ ����ġ";
		String url = "updateForm.do?num=" + b.getNum();
		if(login.equals(board.getId())) {
			if(new BoardDao().update(b)) {
				msg = "��������";
				if(boardnum==1) {
					url = "stickerboard.do?boardnum=1";
				}else if(boardnum==2) {
					url = "backgroundboard.do?boardnum=2";
				}else if(boardnum==3) {
					url = "board1.do?boardnum=3";
				}
			}else {
				msg = "��������";
				if(boardnum==1) {
					url = "stickerboard.do?boardnum=1";
				}else if(boardnum==2) {
					url = "backgroundboard.do?boardnum=2";
				}else if(boardnum==3) {
					url = "board1.do?boardnum==3";
				}
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
	}
	
	public ActionForward like(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int num = Integer.parseInt(request.getParameter("num"));
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		String id = request.getParameter("id");
		Board b = new Board();
		b.setId(id);
		b.setNum(num);
		String msg = "";
		String url = "";
		if(boardnum==1) {
			url = "stickerboard.do?boardnum=1";
		}else if(boardnum==2) {
			url = "backgroundboard.do?boardnum=2";
		}
		int cnt = dao.likeselect(b);
		if(cnt == 0) { // ���� ���
			dao.update_Like(num, "add");
			if(dao.likeinsert(b)) { //���ƿ����̺� �߰�
				msg = "���ƿ�";
			}
		}else if (cnt == 1) { // �̹� �ִ� ���
			msg = "���ƿ����";
			dao.update_Like(num, "minus");
			dao.likedelete(b);
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
	}
	
	public ActionForward decoinsert(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String login = (String) request.getSession().getAttribute("login");
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		String img= request.getParameter("img");
		Deco d = new Deco();
		d.setId(login);
		int num = dao.decomaxnum();	//�ִ밪 ������
		d.setNum(++num);
		d.setImg(img); //�̹���
		d.setDeconum(boardnum);//1:��ƼĿ,2:���
		
		String msg = "";
		String url = "";
		if(boardnum==1) {
			url = "stickerboard.do?boardnum=1";
		}else if(boardnum==2) {
			url = "backgroundboard.do?boardnum=2";
		}
		if(dao.decoinsert(d)) { // ���� ���
			msg = "����Ǿ����ϴ�.";
		}else{ // �̹� �ִ� ���
			msg = "����� ������ �߻��߽��ϴ�.";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
	}
	
	public ActionForward decolist(HttpServletRequest request, HttpServletResponse response){
		int limit = 10; //�ѰǴ� ������ 10
		int pageNum = 1;
		int deconum =0;
		String id = (String) request.getSession().getAttribute("login");
		int cate = Integer.parseInt(request.getParameter("cate"));
		try {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));//1.
		}catch(NumberFormatException e) {}
		String find = request.getParameter("find");
		int decocnt = dao.decoboardCount(id); //��ϵ� �ٲ� �Ǽ�
		List<Deco> list = dao.decolist(id, pageNum, limit, cate);
		
		int maxpage = (int)((double)decocnt/limit + 0.95);
		int startpage = ((int)(pageNum/10.0+0.9)-1)*10+1;
		int endpage = startpage + 9;
		if(endpage >maxpage)endpage = maxpage;
		int board = decocnt - (pageNum - 1) *limit;		//2.
		request.setAttribute("deconum", deconum);
		request.setAttribute("decocnt", decocnt);
		request.setAttribute("list", list);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("board", board);
		request.setAttribute("pageNum", pageNum);
		return new ActionForward();
	}
	
	public ActionForward decodelete(HttpServletRequest request, HttpServletResponse response){
		int num = Integer.parseInt(request.getParameter("num"));
		String login = (String) request.getSession().getAttribute("login");
		String msg = "";
		String url = "";
		msg = "��������";
		url = "../board/deco.do?cate=1";
		Deco d = new Deco();
		d.setId(login);
		d.setNum(num);
		if(dao.decodelete(d)) {
			msg = "��������";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
	}
	
	public ActionForward minihome(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		List<Member> list = dao.minihome(id);
		request.setAttribute("mem", list.get(0));
		return new ActionForward();
	}
	
	public ActionForward color(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		String id = request.getParameter("id");
		String color = request.getParameter("color");
		String msg = "�������";
		if(dao.colorupdate(id,color)) {
			msg="����Ǿ����ϴ�.";
		}
		request.setAttribute("msg", msg);
		return new ActionForward();
	}
	
}