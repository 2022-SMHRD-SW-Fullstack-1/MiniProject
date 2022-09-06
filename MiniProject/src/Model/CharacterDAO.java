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
			// 1. Class.forName()
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. 데이터베이스의 url, id, pw 연결
			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String db_id = "campus_k_0830_2";
			String db_pw = "smhrd2";

			conn = DriverManager.getConnection(url, db_id, db_pw);

			if (conn != null)
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
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int charNumCheck(String id) {
		getCon();
		int charnum = 0;

		try {
			String sql = "select count(*) from minicharacter where mem_id ='" + id + "'";

			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			if (rs.next())
				charnum = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return charnum;

	}

	public int insert(CharacterDTO chardto, String id) {
		getCon();

		try {
			String sql = "insert into minicharacter values(?, ?, ?,?,?,?,?,?,?)";

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, chardto.getChanick());
			psmt.setString(2, chardto.getType());
			psmt.setInt(3, chardto.getExperience()[0]);
			psmt.setInt(4, chardto.getLevel());
			psmt.setInt(5, chardto.getEnergy()[0]);
			psmt.setInt(6, chardto.getAbility());
			psmt.setInt(7, chardto.getStress());
			psmt.setInt(8, chardto.getPay());
			psmt.setString(9, id);

			cnt = psmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;

	}
	
	public String[] nickList(String id) {
		getCon();

		String[] nickarr = new String[3];
		try {
			String sql = "select char_nick  from minicharacter where mem_id='" + id + "'";

			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			int index = 0;
			while (rs.next()) {
				String char_nick = rs.getString("char_nick");

				nickarr[index++] = char_nick;

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close();
		}
		return nickarr;
	}

	public int Chardelete(String nick) {

		cnt = 0;

		getCon();// DB접속

		try {
			String sql = "delete from minicharacter where char_nick='" + nick + "'";
			psmt = conn.prepareStatement(sql);

			cnt = psmt.executeUpdate();// sql문 실행해달라는 명령어

			String sql1 = "select * from minicharacter where char_nick='" + nick + "'";
			rs = psmt.executeQuery(sql1);
			if (rs.next() == true) {
				cnt = 0; // 실패
			} else {
				cnt = 1;// 성공
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;

	}

	public CharacterDTO getchar(String nick) {
		getCon();
		CharacterDTO myC = new CharacterDTO();

		try {
			String sql = "select * from minicharacter where char_nick='" + nick + "'";

			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(sql);

			if (rs.next()) {
				myC.setChanick(rs.getString(1));
				myC.setType(rs.getString(2));
				int[] temp1 = { rs.getInt(3), (rs.getInt(4) - 1) * 10 + 50 };
				myC.setExperience(temp1);
				myC.setLevel(rs.getInt(4));
				int[] temp2 = { rs.getInt(5), (rs.getInt(4) - 1) * 10 + 100 };
				myC.setEnergy(temp2);
				myC.setAbility(rs.getInt(6));
				myC.setStress(rs.getInt(7));
				myC.setPay(rs.getInt(8));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return myC;
	}

	public int setchar(CharacterDTO myC) {
		getCon();
	

		try {
			String sql = "update minicharacter set char_exp ="+myC.getExperience()[0]+", char_lv ="+myC.getLevel()+
					", char_energy="+myC.getEnergy()[0]+",char_ability="+myC.getAbility()+", char_stress="+myC.getStress()+
					", char_pay="+myC.getPay()+"where char_nick='"+myC.getChanick()+"'";

			psmt = conn.prepareStatement(sql);
			cnt = psmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

}
