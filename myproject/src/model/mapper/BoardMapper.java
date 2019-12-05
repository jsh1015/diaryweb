package model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.Board;
import model.Board_like;
import model.Deco;
import model.Member;

public interface BoardMapper {
	@Select("select ifnull(max(num),0) from board")
	int maxnum();
	
	@Select("select ifnull(max(num),0) from deco")
	int decomaxnum();

	@Insert("insert into board (boardnum,num,id,name,subject,content,file1,regdate," 
			+"setdate, cnt, likenum, setpublic, img ) "
			+ "values(#{boardnum},#{num},#{id},#{name},#{subject},#{content},"
			+ "#{file1},now(),#{setdate},0,0,#{setpublic},#{img})")
	int insert(Board b);

	@Select({"<script>",
		"select count(*) from board where boardnum=#{boardnum}",
		"<if test='col1!=null'> and ( ${col1} like '%${find}%'",
		"  <if test='col2!=null'> OR ${col2} like '%${find}%' )</if>",
		"  <if test='col2==null'>)</if>",
		"</if>",
		"</script>"
	})
	Integer boardCount(Map<String, Object> map);

	@Select({"<script>",
		"select *,(SELECT count(*) FROM board_like b2 WHERE b2.num=b1.num AND b2.id =#{id} ) likechk"
		+ " from board b1 where boardnum=#{boardnum} ",
		"<if test='col1!=null'> and ( ${col1} like '%${find}%'",
		"<if test='col2!=null'> OR ${col2} like '%${find}%' )</if>",
		"<if test='col2==null'> ) </if>",
		"</if>",
		"<choose>",
		"<when test='num!=null'> and num=#{num} </when>",
		"<otherwise>"
		+"order by regdate desc limit #{start},#{limit}"
		+"</otherwise>",
		"</choose>",
		"</script>"
		})
	List<Board> select(Map<String, Object> map);

	@Update("update board set cnt=cnt+1 where num=#{num}")
	void readcndadd(int num);
	
	@Update("update board set likenum=likenum+1 where num=#{num}")
	void updatelikeadd(int num);//좋아요 수 증가
	
	@Update("update board set likenum=likenum-1 where num=#{num}")
	void updatelikeminus(int num);//좋아요 눌러져있을때 좋아요 수 -1
	
	@Select("select count(*) from board_like where id=#{id} and num=#{num} ")
	int likeselect(Board b);
	
	@Insert("insert into board_like (num,id) values(#{num},#{id})")
	boolean likeinsert(Board b);
	
	@Delete("delete from board_like where num=#{num} and id=#{id}")
	boolean likedelete(Board board);
	
	@Update("update board set grpstep=grpstep+1 "
			+ "where grp=#{grp} and grpstep>#{grpstep}")
	void grpStepAdd(@Param("grp") int grp, @Param("grpstep") int grpstep);
	
	@Update("update board set name=#{name}, subject=#{subject}, content=#{content}, "
			+ "file1=#{file1}, img=#{img} where num=#{num}")
	int update(Board b);

	@Delete("delete from board where num=#{num}")
	int delete(int num);

	@Insert("insert into deco (deconum, img,id,num) values(#{deconum},#{img},#{id},#{num})")
	boolean decoinsert(Deco deco);

	@Delete("delete from deco where num=#{num} and id=#{id}")
	boolean decodelete(Deco d);
	
	@Select("select * from deco where id=#{id} and deconum=#{deconum} ")
	List<Deco> decoselect(Map<String, Object> map);

	@Select("select count(*) from deco where id=#{id}")
	int decoboardCount(Map<String, Object> map);

	//스티커 좋아요순 리스트
	@Select("select * from board where boardnum=1 order by likenum desc")
	List<Board> stickerlikelist();

	//배경 좋아요순 리스트
	@Select("select * from board where boardnum=2 order by likenum desc")
	List<Board> backgroundlikelist();

	//좋아요한 게시글 조회
	@Select("select * from board_like where id=#{id}")
	List<Board_like> boardlike(String id);

	@Select("select * from member where id=#{id}")
	List<Member> minihome(Map<String, Object> map);

	@Update("update member set color=#{color} where id=#{id}")
	boolean colorupdate(@Param("id") String id,  @Param("color") String color);
}
