package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {

	// DAO : Data Access Object
		// 데이터베이스의 data에 접근을 위한 객체! (접근 로직)
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int cnt = 0; // executeUpdate의 결과값을 담아주는 변수
		
		// 데이터베이스 접속을 위한 연결 메소드
		public void getCon() {
			try {
				// 1. Class.forName()
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				// 2. 데이터베이스의 url, id, pw 연결
				//String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
				String db_id = "campus_k_0830_2";
				String db_pw = "smhrd2";
				
				conn = DriverManager.getConnection(url, db_id, db_pw);
				
				if(conn != null)
					System.out.println("접속 성공");
				else
					System.out.println("접속 실패");
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// 사용된 객체를 닫아주는 메소드
		public void close() {
				try {
					if(rs != null)
						rs.close();
					if(psmt != null)
						psmt.close();
					if(conn != null)
						conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		// [1] 회원가입
		// - DB 접속 -> 중복되므로 메소드로 로직을 따로 정리하기!
		// - sql 문장 실행
		// - 연결 종료
		public int insert(String id, String pw, String nick) {
			getCon();
			
			
			try {
				String sql = "insert into minimember values(?, ?, ?)";
				
				psmt = conn.prepareStatement(sql);
				
				psmt.setString(1, id);
				psmt.setString(2, pw);
				psmt.setString(3, nick);
				
				cnt = psmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 가방에 담는다.
			finally {
				close();
			}
			return cnt;
		}
		
		
		// [2] 로그인
		// - DB 접속
		// - sql 문장 실행
		// - 연결 종료
		
		public ArrayList<MemberDTO> login() {
			getCon();
			
			ArrayList<MemberDTO> arr = new ArrayList<>();
			
			
			try {
				String sql = "select id, pw, nickname from memberInfo";
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					String id = rs.getString("id");
					String pw = rs.getString("pw");
					String nickname = rs.getString("nickname");
					
					arr.add(new MemberDTO(id, pw, nickname));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close();
			}
			
			return arr;
			
			
			
		}
		
}
