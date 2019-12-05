package model;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBConnection {
	private static SqlSessionFactory sqlMap;
	//static으로 사용 전체중 한개만 사용
	static {
		String resource = "model/mapper/mybatis-config.xml";
		//model.mapper이 아닌 model/mapper : 파일이기때문
		//mybatis-config.xml 파일의 위치
		
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		//build : Connection 객체 설정.
		//			SQL 구문 저장하는 컨테이너 설정(reader로부터 읽어옴)
		sqlMap = new SqlSessionFactoryBuilder().build(reader);
	}
	static SqlSession getConnection() {
		return sqlMap.openSession();//db와 연결하기
		//sqlMap에있는 connection객체를 이용해서
	}
	//외부에서는 접근할 수 없고 같은패키지에서만 사용
	static void close(SqlSession session) {
		session.commit();
		session.close();
	}
}