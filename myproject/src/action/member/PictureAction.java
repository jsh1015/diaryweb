package action.member;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import action.Action;
import action.ActionForward;
/*
 *  1. 파일 업로드
 */
public class PictureAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 	String path = request.getServletContext().getRealPath("") + "diary/member/picture";
		 	String fname = null;
		 try{
		 	File f = new File(path);
		 		if(!f.exists()){//존재하지 않으면
		 			f.mkdirs(); //폴더를 만듬
		 		}
		 	
		 		MultipartRequest multi = new MultipartRequest(request,path,10*1024*1024,"euc-kr");
		 		fname = multi.getFilesystemName("picture");
		 	}catch(IOException e){
		 		e.printStackTrace();
		 	}
		 request.setAttribute("fname", fname);
		 return new ActionForward(); //redirect:false,view:null
	}

}
