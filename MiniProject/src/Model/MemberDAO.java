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
				String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
				String db_id = "campus_k_0830_2";
				String db_pw = "smhrd2";
				
				conn = DriverManager.getConnection(url, db_id, db_pw);
				
				
				
				if(conn == null)
					System.out.println("접속 실패");

				//사용자한테 계속 접속 성공이 뜨니까 주석처리
//				if(conn != null)
//					System.out.println("접속 성공");
//				else
//					System.out.println("접속 실패");
				
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
				String sql = "insert into minimember values(?, ?, ?)";//그냥 문장이기 때문에 try-catch에 넣어주지 않아도 됨
				
				psmt = conn.prepareStatement(sql);
				
				psmt.setString(1, id);
				psmt.setString(2, pw);
				psmt.setString(3, nick);
				
				cnt = psmt.executeUpdate();//sql문장 실행해달라는 명령어, 테이블의 값이 수정, 삭제될 때는 Update쓰기
				
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
				String sql = "select mem_id, mem_pw, user_nick from minimember";
				psmt = conn.prepareStatement(sql);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					String id = rs.getString("mem_id");
					String pw = rs.getString("mem_pw");
					String nickname = rs.getString("user_nick");
					
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
		
		//[3]탈퇴
		public int delete(String id, String pw) {
			
			cnt=0;
			
			getCon();//DB접속

			try {
				String sql="delete from Minimember where mem_id='"+id+"'";
				psmt=conn.prepareStatement(sql);
				
				 cnt=psmt.executeUpdate();//sql문 실행해달라는 명령어
				 
				 String sql1="select * from Minimember where mem_id='"+id+"'";
				 rs=psmt.executeQuery(sql1);
				 if(rs.next()==true) {
					 cnt=0; //실패
				 }else {
					 cnt=1;//성공
				 }
				 
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				close();
			}
			return cnt;
			
		}
		
}
