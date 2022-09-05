package Controller;

import java.util.ArrayList;

import Model.MemberDAO;
import Model.MemberDTO;

public class mainController {
	
	MemberDAO dao = new MemberDAO();
	
	int cnt = 0;
	
	// 회원가입
	public int Register(String id, String pw, String nick) { 
		// view가 넘겨준 회원가입에 대한 정보들을 DAO로 연결해주는 메소드
		cnt = dao.insert(id, pw, nick);
		
		return cnt;
	}
	// 로그인
	public ArrayList<MemberDTO> Login() {
		
		
		return dao.login();
	}
	
	// 탈퇴
	public void Dropout(String id, String pw) {
		dao.delete(id, pw);
	}
	
	
	// 캐릭터 신규 생성
	public void newCharacter() {
		
	}
	
	
	// 캐릭터 삭제
	public void deleteCharacter() {
		
	}
	
}
