package Controller;

import java.util.ArrayList;

import Model.CharacterDAO;
import Model.CharacterDTO;
import Model.MemberDAO;
import Model.MemberDTO;

public class mainController {
	
	MemberDAO memdao = new MemberDAO();
	CharacterDAO chardao = new CharacterDAO();
	
	
	int cnt = 0;
	
	// 회원가입
	public int Register(String id, String pw, String nick) { 
		// view가 넘겨준 회원가입에 대한 정보들을 DAO로 연결해주는 메소드
		cnt = memdao.insert(id, pw, nick);
		
		return cnt;
	}
	// 로그인
	public ArrayList<MemberDTO> Login() {
		
		
		return memdao.login();
	}
	
	// 탈퇴
	public void Dropout(String id, String pw) {
		memdao.delete(id, pw);
	}
	
	public int cNumCheck(String id) {
		return chardao.charNumCheck(id);

	}
	
	// 캐릭터 신규 생성
	public int newCharacter(String charnickname ,int numtype,String id) {
		
		CharacterDTO chardto = new CharacterDTO(charnickname, numtype);
		
		return chardao.insert(chardto,id);
	}
	
	public String[] nickList(String id) {
		
		return chardao.nickList(id);
	}
	
	// 캐릭터 삭제
	public int deleteCharacter(String nick) {
		return chardao.Chardelete(nick);
	}
	
	public CharacterDTO getMyC(String nick) {
		return chardao.getchar(nick);
	}
	
}
