package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import model.mapper.BoardMapper;

public class BoardDao {
	private Map<String,Object> map = new HashMap<String,Object>();
	private Class<BoardMapper> cls = BoardMapper.class;
	public int maxnum() {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).maxnum();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return 0;
	}
	
	public int decomaxnum() {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).decomaxnum();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return 0;
	}
	
	//게시물등록
	public boolean insert(Board board) {
		SqlSession session = DBConnection.getConnection();
		try {
			int cnt = session.getMapper(cls).insert(board);
			if(cnt>0)return true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return false;
	}
	
	//게시물 건수
	public int boardCount(int boardnum, String column, String find) {
		SqlSession session = DBConnection.getConnection();
		try {
			map.clear();
			map.put("boardnum",boardnum);
			if(column != null) {	//둘다 null이 아니라는 뜻
				String[] col = column.split(",");
				map.put("col1", col[0]);
				if(col.length == 2) {
					map.put("col2",col[1]);
				}
				map.put("find",find);
			}
			return session.getMapper(cls).boardCount(map);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return 0;
	}
	
	public List<Board> list(int boardnum, int pageNum, int limit, String column, String find, String id){
		SqlSession session = DBConnection.getConnection();
		try {
			map.clear();
			map.put("boardnum",boardnum);
			map.put("start",(pageNum-1)*limit);
			map.put("limit", limit);
			map.put("id", id);
			if(column != null) {
				String[] col = column.split(",");
				map.put("col1",col[0]);
				if(col.length ==2) {
					map.put("col2",col[1]);
				}
				map.put("find",find);
			}
			return session.getMapper(cls).select(map);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return null;
	}
	
	public List<Board> stickerlikelist(){ //스티커 좋아요순 리스트 가져오기
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).stickerlikelist();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return null;
	}
	
	public List<Board> backgroundlikelist(){ //배경 좋아요순 리스트 가져오기
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).backgroundlikelist();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return null;
	}
	
	public List<Board> minihomelist(int boardnum, int pageNum, int limit, String id){
		SqlSession session = DBConnection.getConnection();
		try {
			map.clear();
			map.put("boardnum",boardnum);
			map.put("start",pageNum-1);
			map.put("limit", limit);
			map.put("id", id);
			return session.getMapper(cls).minihomeboard(map);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return null;
	}
	
	public List<Deco> decolist(String id, int pageNum, int limit, int deconum){
		SqlSession session = DBConnection.getConnection();
		try {
			map.clear();
			map.put("id",id);
			map.put("deconum",deconum);
			return session.getMapper(cls).decoselect(map);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return null;
	}
	
	public Board selectOne(int boardnum, int num) {
		SqlSession session = DBConnection.getConnection();
		try {
			map.clear();
			map.put("num",num);
			map.put("boardnum",boardnum);
			return session.getMapper(cls).select(map).get(0);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return null;
	}
	
	//조회수 증가
	public void readcntadd(int num) {
		SqlSession session = DBConnection.getConnection();
		try {
			session.getMapper(cls).readcndadd(num);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
	}
	
	//좋아요 증가
	public void update_Like(int num, String mode) {
		SqlSession session = DBConnection.getConnection();
		try {
			if(mode.equals("add")) {
				session.getMapper(cls).updatelikeadd(num); 				
			}else if (mode.equals("minus")){
				session.getMapper(cls).updatelikeminus(num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
	}
	
	//좋아요 테이블에 넣음
	public boolean likeinsert(Board board) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).likeinsert(board);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return false;
	}
	
	public int likeselect(Board board) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).likeselect(board);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return 0;
	}
	
	public boolean likedelete(Board board) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).likedelete(board);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return false;
	}
	
	public boolean decoinsert(Deco deco) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).decoinsert(deco);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return false;
	}
	
	public boolean decodelete(Deco d) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).decodelete(d);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return false;
	}
	
	public void grpStepAdd(int grp, int grpstep) {
		SqlSession session = DBConnection.getConnection();
		try {
			session.getMapper(cls).grpStepAdd(grp,grpstep);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
	}
	
	public boolean update(Board b) {
		SqlSession session = DBConnection.getConnection();
		try {
			int cnt =session.getMapper(cls).update(b);
			if(cnt>0)return true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return false;
	}
	
	public boolean delete(int num) {
		SqlSession session = DBConnection.getConnection();
		try {
			int cnt = session.getMapper(cls).delete(num);
			if(cnt>0) return true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return false;
	}

	public int decoboardCount(String id) {
		SqlSession session = DBConnection.getConnection();
		try {
			map.clear();
			map.put("id",id);
			return session.getMapper(cls).decoboardCount(map);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return 0;
	}

	public List<Member> minihomemem(String id) {
		SqlSession session = DBConnection.getConnection();
		try {
			map.clear();
			map.put("id",id);
			return session.getMapper(cls).minihome(map);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return null;
	}

	public boolean colorupdate(String id, String color) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).colorupdate(id,color);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return false;
	}

	public List<Deco> minidecolist(String id, int deconum) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).deco(id,deconum);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return null;
	}

	public int miniboardCount(int boardnum,String id) {
		SqlSession session = DBConnection.getConnection();
		try {
			map.clear();
			map.put("id",id);
			map.put("boardnum",boardnum);
			return session.getMapper(cls).miniboardCount(map);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return 0;
	}

	public boolean minihomeinsert(Board b) {
		SqlSession session = DBConnection.getConnection();
		try {
			int cnt = session.getMapper(cls).minihomeinsert(b);
			if(cnt>0)return true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(session);
		}
		return false;
	}

	public int nameupdate(String name,String id) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).nameupdate(name,id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return 0;
	}

	public boolean minihomeupdate(Board b) {
		SqlSession session = DBConnection.getConnection();
		try {
			return session.getMapper(cls).minihomeupdate(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(session);
		}
		return false;
	}

}