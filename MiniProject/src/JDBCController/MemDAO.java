package JDBCController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemDAO {

	//DAO: Data Access Object
		//데이터베이스의 data에 접근을 위한 객체(접근 로직)
		
		//객체 생성
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		//executeUpdate의 결과값을 담아주는 변수
		int cnt=0;
		
		//데이터베이스 접속을 위한 연결 메서드
		public void getCon() {
			try {
				//1. Class.forName()
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				//2. 데이터베이스의 url, id, pw연결
				String url="jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
				String db_id="campus_k_0803_2";
				String db_pw="smhrd2";
				
				conn=DriverManager.getConnection(url, db_id, db_pw);//접속가능한지 확인
				
				if(conn!=null) {
					System.out.println("접속 성공!");
				}else {
					System.out.println("접속 실패..");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		
		//사용된 객체를 닫아주는 메서드
		public void close() {
				try {
					if(rs!=null) {
					rs.close();
					} if(psmt!=null) {
						psmt.close();
					} if(conn!=null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		//[1]회원가입
			public int insert(String id, String pw, String usernick) {
				getCon();//연결, 위에서 이미 try=catch로 확인을 하기 때문에 안 넣어줘도 됨
				
				try {
					String sql="insert into memberInfo values(?,?,?)";//그냥 문장이기 때문에 try-catch에 넣어주지 않아도 됨
					psmt=conn.prepareStatement(sql);
					
					psmt.setString(1,id);
					psmt.setString(2,pw);
					psmt.setString(3,usernick);
					
					cnt=psmt.executeUpdate();//sql문장 실행해달라는 명령어, 테이블의 값이 수정, 삭제될 때는 Update쓰기
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				finally {
					close();
				}
				return cnt;
			}
		
		
			//[2]로그인
			public String login(String id, String pw) {
			
			String nick=null;//결과값 리턴을 위한 변수
			
			getCon();//DB접속
			
			
			try {
				String sql="select nick from memberInfo where id=? and pw=?";
				psmt=conn.prepareStatement(sql);
				psmt.setString(1, id);
				psmt.setString(2, pw);
				
				rs=psmt.executeQuery();
				
				if(rs.next()) {//While문은 반복해서 데이터가 없을 때까지 모든 목록을 다 보여줌(물론 조건을 설정하면 하나만 보여주기도 가능함) if는 하나만 보여줌
					nick=rs.getString(1);//rs.getString("nick"); 라고 써도 됨 -> 1번을 넣은 이유 sql문장에서 조회를 하는 컬럼을 보면 됨 nick컬럼을 첫번째로 조회를 하고 첫번째로 나오니까 1번 인덱스가 되는 것임!
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			finally {
				close();
			}
			
			return nick;
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
