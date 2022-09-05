package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CharacterDAO {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	
	int cnt = 0; 
		
	public void getCon() {
	try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
		
	String url = "jdbc:oracle:thin:@localhos:1521:xe";
	String db_id = "hr";
	String db_pw = "hr";
	
	
	conn = DriverManager.getConnection(url,db_id,db_pw);
		

	}catch(Exception e) {
		e.printStackTrace();
	}
	}
	
	public int insert(String type, String chanick,int Level,
			int Experience,int Energy,int ability,int stress,int pay) {
		getCon();
		try {
			String sql = "insert into characterInfo values(?,?,?,?,?,?,?,?)";

			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, type);
			psmt.setString(2, chanick);
			psmt.setInt(3, Level);
			psmt.setInt(4, Experience);
			psmt.setInt(5, Energy);
			psmt.setInt(6, ability);
			psmt.setInt(7, stress);
			psmt.setInt(8, pay);
			
			cnt = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

	
	public void select() {
		
		try {
			String sql = "select type,chanick,Level,Experience,Energy,ability,stress,pay";
			
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}



