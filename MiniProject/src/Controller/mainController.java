package Controller;

import java.util.ArrayList;
import java.util.Random;

import Model.CharacterDAO;
import Model.CharacterDTO;
import Model.MemberDAO;
import Model.MemberDTO;

public class mainController {

	MemberDAO memdao = new MemberDAO();
	CharacterDAO chardao = new CharacterDAO();
	Random rd = new Random();
	AsciicodeController ac= new AsciicodeController();
	
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
	public int newCharacter(String charnickname, int numtype, String id) {

		CharacterDTO chardto = new CharacterDTO(charnickname, numtype);

		return chardao.insert(chardto, id);
	}

	public String[] nickList(String id) {

		return chardao.nickList(id);
	}

	// 캐릭터 삭제
	public int deleteCharacter(String nick) {
		return chardao.Chardelete(nick);
	}

	// 캐릭터 스탯 창
	public CharacterDTO getMyC(String nick) {
		return chardao.getchar(nick);
	}

	// 데이터 베이스에 저장할 캐릭터 스탯
	public int setMyC(CharacterDTO myC) {
		return chardao.setchar(myC);
	}

	// 캐릭터 레벨업
	// 레벨업 하면 올라가는 수치
//	업무능력 + rd(2~7)
//	에너지 최대치 +10, 그냥 에너지 +10
//	경험치 최대치 +10,
//	레벨 +1

	public CharacterDTO levelup(CharacterDTO myC) {

		CharacterDTO temp = myC;

		int temparr[] = { myC.getExperience()[0] - (50 + 10 * (myC.getLevel() - 1)), 50 + 10 * (myC.getLevel() - 1) };
		temp.setExperience(temparr);

		int temparr2[] = { myC.getEnergy()[0] + 10, 100 + 10 * (myC.getLevel() - 1) };
		temp.setEnergy(temparr2);

		temp.setAbility(myC.getAbility() + (rd.nextInt(6) + 2));

		temp.setLevel(myC.getLevel() + 1);

		if (temp.getLevel() == 10 && myC.getAbility() >= 60) {
			ac.King(temp.getType());
			System.out.println(myC.getChanick()+"이(가) 카카5 회장이 되었습니다 축하합니다 ◟(ᵔ ̮ ᵔ)͜💐");

		} else if (temp.getLevel() == 10 && myC.getAbility() < 60) {
			ac.Goodbye(temp.getType());
			System.out.println(myC.getChanick()+"이(가) 무사히 정년퇴직을 합니다 고생하셨습니다 ✺◟(•‿•)◞✺");
		}

		return myC;
	}

	public ArrayList<String> charnickck() {

		return chardao.charnickck();
	}
//아이디 삭제시 해당 아이디의 캐릭터 삭제
	public void idlinknickdelete(String id) {

		chardao.idlinknickdelete(id);
	}

}
