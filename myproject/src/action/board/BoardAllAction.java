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
import model.MemberDao;

public class BoardAllAction{
	private BoardDao dao = new BoardDao();
	/*
	 * 1. multipart/form-data 타입의 전송이므로 MultiparteRequest 객체 생성
	 * 2. 파라미터 값을 model.Board 객체 저장
	 * 3. 게시물번호 num 현재 등록된 num의 최대값을 조회. 최대값 +1 등록된 게시물의 번호
	 * 		db에서 maxnum을 구해서 +1 값으로 num 설정하기
	 * 4. board 내용을 db에 등록하기
	 * 		등록성공 : 메세지 출력. list.do 페이지 이동
	 * 		등록실패 : 메세지 출력. writeForm.me페이지 이동
	 */
	public ActionForward write(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		String msg = "게시물 등록 실패";
		String url = "writeForm.jsp";
		String path = request.getServletContext().getRealPath("/")+"diary/board/file/";
		try {
			File f = new File(path);
			if(!f.exists()) f.mkdirs();
			MultipartRequest multi = new MultipartRequest(request,path,10*1024*1024,"utf-8"); //1.
			Board b = new Board();
			int boardnum = Integer.parseInt(multi.getParameter("boardnum"));
			String id = multi.getParameter("id");
			b.setBoardnum(Integer.parseInt(multi.getParameter("boardnum")));
			b.setId(multi.getParameter("id"));
			b.setName(multi.getParameter("name"));
			b.setSubject(multi.getParameter("subject"));
			b.setContent(multi.getParameter("content"));
			b.setFile1(multi.getFilesystemName("file1"));	//2.
			b.setImg(multi.getParameter("img"));
			int num = dao.maxnum();	//최대값 가져옴
			b.setNum(++num);
			/*b.setGrp(num); */
			if(dao.insert(b)) {
				msg = "게시물 등록 성공";
				if(boardnum==1) {
					url = "stickerboard.do?boardnum=1";
				}else if(boardnum==2) {
					url = "backgroundboard.do?boardnum=2";
				}else if(boardnum ==3) {
					url = "board1.do?boardnum=3";
				}else if(boardnum ==4) {
					url = "minihome.do?id="+id;
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
	 * 	1. 한페이지당 10건의 게시물을 출력하기.
			pageNum 파라미터값을 저장 1-1페이지 =>없는경우는 1로 설정하기.
		2. 최근 등록된 게시물을 가장 위에 배치함.(List형태)
		3. 화면에 필요한 정보를 속성으로 등록. =>뷰로 전송 
	*/
	public ActionForward list(HttpServletRequest request, HttpServletResponse response){
		int limit = 15; //한건당 페이지 10
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
		int boardcnt = dao.boardCount(boardnum,column,find); //등록된 총게시물의 건수
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
	 * 	1. num 파라미터 정보를 변수에 저장. (게시물번호)
		2. num을 이용하여 db에서 해당 게시물 정보를 조회
			Board b = new BoardDao.selectOne(num)
		3. 조회수 증가시키기 
			void BoardDao.readcntadd(num)
		4. 2번의 조회된 게시물을 화면에 출력
	 */
	public ActionForward info(HttpServletRequest request, HttpServletResponse response){
		int num = Integer.parseInt(request.getParameter("num")); //1
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		Board b = new BoardDao().selectOne(boardnum, num);//2
		
		//jspstudy2/medel2/info.do : 상세보기인지 
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
		 		if(!f.exists()){//존재하지 않으면
		 			f.mkdirs(); //폴더를 만듬
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
		msg = "삭제실패"; 
			if(boardnum==1) {
				url = "imginfo.do?num="+num+"&boardnum="+boardnum;
			}else if(boardnum==2) {
				url = "imginfo.do?num="+num+"&boardnum="+boardnum;
			}else if(boardnum==3) {
				url = "info.do?num="+num+"&boardnum="+boardnum;
			}
		if(db==null) {
			msg = "없는 게시글입니다.";
			if(boardnum==1) {
				url = "stickerboard.do?boardnum=1";
			}else if(boardnum==2) {
				url = "backgroundboard.do?boardnum=2";
			}else if(boardnum==3) {
				url = "board1.do?boardnum=3";
			}
		}else {
			if(login.equals("admin") || login.equals(db.getId())) {
				boolean result = new BoardDao().delete(num);
				if(result) {
					msg = "삭제성공";
					if(boardnum==1) {
						url = "stickerboard.do?boardnum=1";
					}else if(boardnum==2) {
						url = "backgroundboard.do?boardnum=2";
					}else if(boardnum==3) {
						url = "board1.do?boardnum=3";
					}else if(boardnum==4) {
						url = "minihome.do?id="+login+"&boardnum=4&pagenum=1";
					}
				}
			}else if(!login.equals(db.getId())){
				msg ="본인만 삭제 가능합니다.";
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
		}//파일이 변경 되지 않았을경우
		
		Board board = new BoardDao().selectOne(boardnum, b.getNum());
		String msg = "본인만 수정 가능합니다.";
		String url = "main.me";
		if(login.equals("admin") || login.equals(board.getId())) {
			if(new BoardDao().update(b)) {
				msg = "수정성공";
				if(boardnum==1) {
					url = "stickerboard.do?boardnum=1";
				}else if(boardnum==2) {
					url = "backgroundboard.do?boardnum=2";
				}else if(boardnum==3) {
					url = "board1.do?boardnum=3";
				}
			}else {
				msg = "수정실패";
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
		if(cnt == 0) { // 없는 경우
			dao.update_Like(num, "add");
			if(dao.likeinsert(b)) { //좋아요테이블에 추가
				msg = "좋아요";
			}
		}else if (cnt == 1) { // 이미 있는 경우
			msg = "좋아요취소";
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
		int num = dao.decomaxnum();	//최대값 가져옴
		d.setNum(++num);
		d.setImg(img); //이미지
		d.setDeconum(boardnum);//1:스티커,2:배경
		
		String msg = "";
		String url = "";
		if(boardnum==1) {
			url = "stickerboard.do?boardnum=1";
		}else if(boardnum==2) {
			url = "backgroundboard.do?boardnum=2";
		}
		if(dao.decoinsert(d)) { // 없는 경우
			msg = "저장되었습니다.";
		}else{ // 이미 있는 경우
			msg = "저장시 오류가 발생했습니다.";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
	}
	public ActionForward decolist(HttpServletRequest request, HttpServletResponse response){
		int limit = 10; //한건당 페이지 10
		int pageNum = 1;
		int deconum =0;
		String id = (String) request.getSession().getAttribute("login");
		int cate = Integer.parseInt(request.getParameter("cate"));
		try {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));//1.
		}catch(NumberFormatException e) {}
		String find = request.getParameter("find");
		int decocnt = dao.decoboardCount(id); //등록된 다꾸 건수
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
		msg = "삭제실패";
		url = "../board/deco.do?cate=1";
		Deco d = new Deco();
		d.setId(login);
		d.setNum(num);
		if(dao.decodelete(d)) {
			msg = "삭제성공";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
	}
	public ActionForward minihomeupdateForm(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		int num = Integer.parseInt(request.getParameter("num")); //1
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		List<Member> mem = dao.minihomemem(id);
		Board board = new BoardDao().selectOne(boardnum, num);//2
		List<Deco> sticker = dao.minidecolist(id, 1);
		List<Deco> background = dao.minidecolist(id, 2);
		
		request.setAttribute("mem", mem.get(0));
		request.setAttribute("s", sticker);
		request.setAttribute("b", background);
		request.setAttribute("board", board);	//4
		
		return new ActionForward();
	}
	
	public ActionForward minihomeupdate(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String path = request.getServletContext().getRealPath("/")+"diary/board/file/";
		MultipartRequest multi = new MultipartRequest(request,path,10*1024*1024,"utf-8"); //1.
		String login = (String) request.getSession().getAttribute("login");
		int num = Integer.parseInt(request.getParameter("num"));
		String id = multi.getParameter("id");
		String backimg = multi.getParameter("backimg");
		System.out.println(backimg);
		Board b = new Board();
		b.setNum(num);
		b.setContent(multi.getParameter("content"));
		b.setSubject(multi.getParameter("subject"));
		b.setSetpublic(Integer.parseInt(multi.getParameter("setpublic")));
		if(backimg.equals("") || backimg==null) {
			b.setBackimg(null);
		}else {
			b.setBackimg(backimg);
		}
		
		Board board = new BoardDao().selectOne(4, num);
		String msg = "본인만 수정 가능합니다.";
		String url = "minihome.do?id="+id+"&boardnum=4&pagenum=1";
		if(login.equals("admin") || login.equals(board.getId())) {
			if(new BoardDao().minihomeupdate(b)) {
				msg = "수정성공";
			}else {
				msg = "수정실패";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
	}
	
	public ActionForward minihome(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		int limit = 1; //한페이지당 15건
		int pageNum = 1;
		try {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));//1.
		}catch(NumberFormatException e) {}
		List<Member> mem = dao.minihomemem(id);
		int boardcnt = dao.miniboardCount(4,id);
		List<Board> list = dao.minihomelist(4,pageNum,limit,id);
		 	
		int maxpage = (int)((double)boardcnt/limit + 0.95);
		int startpage = ((int)(pageNum/10.0+0.9)-1)*10+1;
		int endpage = startpage + 9;
		if(endpage >maxpage)endpage = maxpage;
		int board = boardcnt - (pageNum - 1) *limit;		//2.
		
		request.setAttribute("boardnum", 4);
		request.setAttribute("boardcnt", boardcnt);
		request.setAttribute("list", list); //미니홈피 게시글
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("board", board);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("mem", mem.get(0));
		
		return new ActionForward();
	}
	
	public ActionForward color(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		String id = request.getParameter("id");
		String color = request.getParameter("color");
		String msg = "저장실패";
		if(dao.colorupdate(id,color)) {
			msg="저장되었습니다.";
		}
		request.setAttribute("msg", msg);
		return new ActionForward();
	}
	
	public ActionForward minihomewriteForm(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		MemberDao m = new MemberDao();
		Member mem = m.selectOne(id);
		List<Deco> background = dao.minidecolist(id, 2);
		List<Deco> sticker = dao.minidecolist(id, 1);
		request.setAttribute("s", sticker);
		request.setAttribute("b", background);
		request.setAttribute("mem", mem);
		return new ActionForward();
	}
	
	
	public ActionForward minihomewrite(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		String path = request.getServletContext().getRealPath("/")+"diary/board/file/";
		String msg ="";
		String url ="";
		try {
			File f = new File(path);
			if(!f.exists()) f.mkdirs();
			MultipartRequest multi = new MultipartRequest(request,path,10*1024*1024,"utf-8"); //1.
			Board b = new Board();
			int boardnum = Integer.parseInt(multi.getParameter("boardnum"));
			String id = multi.getParameter("id");
			String backimg = multi.getParameter("backimg");
			msg = "게시물 등록 실패";
			url = "minihomewriteForm.do?id="+id;
			
			b.setBoardnum(Integer.parseInt(multi.getParameter("boardnum")));
			b.setId(multi.getParameter("id"));
			b.setName(multi.getParameter("name"));
			b.setSubject(multi.getParameter("subject"));
			b.setContent(multi.getParameter("content"));
			b.setSetpublic(Integer.parseInt(multi.getParameter("setpublic")));
			if(backimg.equals("") || backimg==null) {
			}else {
				b.setBackimg(backimg);
			}
			int num = dao.maxnum();	//최대값 가져옴
			b.setNum(++num);
			/*b.setGrp(num); */
			if(dao.minihomeinsert(b)) {
				msg = "게시물 등록 성공";
				if(boardnum==1) {
					url = "stickerboard.do?boardnum=1";
				}else if(boardnum==2) {
					url = "backgroundboard.do?boardnum=2";
				}else if(boardnum ==3) {
					url = "board1.do?boardnum=3";
				}else if(boardnum ==4) {
					url = "minihome.do?id="+id;
				}
			}					//3. 4.
		}catch(IOException e) {
			throw new ServletException(e);
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
}