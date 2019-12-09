package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import model.mapper.MemberMapper;
public class MemberDao {
	private Map<String,Object> map = new HashMap<String,Object>();
	private Class<MemberMapper> cls = MemberMapper.class; //필요한 환경설정파일 지정
	public Member selectOne(String id) {
		SqlSession session = DBConnection.getConnection();
		try {
			map.clear();
			map.put("id",id);
			List<Member> list = session.getMapper(cls).select(map);
			return list.get(0);
		}catch(Exception e) {
			e.printStackTrace();
		}finally { //
			DBConnection.close(session);
		}
		return null;
	}
	
	/*
	public Board minihome(String id) {
		SqlSession session = DBConnection.getConnection();
		try {
			map.clear();
			map.put("id",id);
			List<Deco> list = session.getMapper(cls).minihome(map);
			return (Deco) list;
		}catch(Exception e) {
			e.printStackTrace();
		}finally { //
			DBConnection.close(session);
		}
		return null;
	}
	*/
	
	//내 deco함에있는거 가져오기
	public Deco decoselect(String id) {
		SqlSession session = DBConnection.getConnection();
		try {
			map.clear();
			map.put("id",id);
			List<Deco> list = session.getMapper(cls).decoselect(map);
			return (Deco) list;
		}catch(Exception e) {
			e.printStackTrace();
		}finally { //
			DBConnection.close(session);
		}
		return null;
	}
	//deco함에 넣기
	public Deco decoinsert(Deco d) {
		SqlSession session = DBConnection.getConnection();
		try {
			List<Deco> list = session.getMapper(cls).decoinsert(d);
			return (Deco) list;
		}catch(Exception e) {
			e.printStackTrace();
		}finally { //
			DBConnection.close(session);
		}
		return null;
	}
	
	
	public String idselect(String email, String tel) {
		  SqlSession session = DBConnection.getConnection();
		  try {
			  return session.getMapper(cls).idselect(email);
		  }catch(Exception e) {
			  e.printStackTrace();
		  }finally {
			  DBConnection.close(session);
		  }
		  return null;
	}
	
	public String pwselect(String id, String email, String tel) {
		  SqlSession session = DBConnection.getConnection();
		  try {
			  return session.getMapper(cls).pwselect(id,email);
		  }catch(Exception e) {
			  e.printStackTrace();
		  }finally {
			  DBConnection.close(session);
		  }
		  return null;
	}

	//회원등록
	public int insert(Member m) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).insert(m);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return 0;
	}
	
	public List<Member> memlist() {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).select(null);
		}catch(Exception e) {
			e.printStackTrace();
		}finally { //
			DBConnection.close(session); //접속을 끊어줌
		}
		return null;
	}
	
	public int update(Member m) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).update(m);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return 0;
	}
	
	public int passupdate(String id, String pass) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).passupdate(id,pass);
		}catch(Exception e) {
		   e.printStackTrace();
		}finally {
		   DBConnection.close(session);
		}
		return 0;
	}
	
	public int delete(String id) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).delete(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return 0;
	}

	public List<Member> memsearch(String searchid) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).memsearch(searchid);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return null;
	}

	public boolean decodelete(String id) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).decodelete(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return false;
	}

	public boolean likedelete(String id) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).likedelete(id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return false;
	}
}