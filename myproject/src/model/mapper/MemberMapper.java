package model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.Deco;
import model.Member;

public interface MemberMapper {
	@Select({"<script>",
		"select * from member ",
		"<if test='id != null'> where binary id=#{id}</if>",
		"</script>"})
	List<Member> select(Map<String,Object> map);
	
	
	@Select("select * from deco where id=#{id}") 
	List<Deco> decoselect(Map<String,Object> map);
	
	@Insert("insert into deco (id,num,img,deconum) values(#{id},#{num},#{img},#{deconum}")
	List<Deco> decoinsert(Deco d);
	
	@Insert("insert into member "
			+"(id,pass,name,email,picture,diaryname)"
			+" values (#{id},#{pass},#{name},#{email},#{picture},#{diaryname})")
	int insert(Member m);
	
	@Update("update member set name=#{name}, email=#{email}, "
			+ "picture=#{picture}, diaryname=#{diaryname} where id=#{id}")
	int update(Member m);
	
	@Delete("delete from member where id=#{id}")
	int delete(String id);

	@Update("update member set pass=#{pass} where id=#{id}")
	int passupdate(@Param("id") String id, @Param("pass") String pass);

	@Select("select id from member where email=#{email}")
	String idselect(String email);
	
	@Select("select pass from member where id=#{id} and email=#{email} and tel=#{tel}")
	String pwselect(@Param("id") String id, @Param("email") String email);

	@Select("select id, name, email, picture from member where id like CONCAT('%', #{searchid}, '%')")
	List<Member> memsearch(String searchid);

	@Delete("delete from board_like where id=#{id}")
	boolean likedelete(String id);

	@Delete("delete from deco where id=#{id}")
	boolean decodelete(String id);

}