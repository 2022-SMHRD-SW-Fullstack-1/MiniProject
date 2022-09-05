package View;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int result = 0;

		while (true) { // 회원가입
			System.out.println("[1] 회원가입 [2] 로그인 [3] 탈퇴 [4]종료 ");
			int loginMenu = sc.nextInt();

			if (loginMenu == 1) {
				System.out.print("회원가입 할 id를 입력해주세요 >> ");
				String id = sc.next();
				System.out.print("사용 할 패스워드를 입력해주세요");
				String pw = sc.next();
				System.out.println("사용 할 닉네임을 입력해주세요");
				String nickname = sc.next();

				// result= .Register(id , pw ,nickname) <<dao에 넘겨줄 회원가입할 정보
				if (result > 0) {
					System.out.println("회원가입 성공");
				} else {
					System.out.println("회원가입 실패");
				}
			} else if (loginMenu == 2) { // 로그인

				// select 작업
				ArrayList<MemberDTO> arr = con.conLogin();
				int index = -1;
				System.out.print("아이디를 입려하세요. >> ");
				String id = sc.next();
				for (int i = 0; i < arr.size(); i++)
					if (id.equals(arr.get(i).getId())) {
						index = i;
						break;
					}
				if (index == -1) { // 아이디 없을때
					System.out.println("아이디가 존재하지 않습니다.");
				} else { // 아이디 있을때
					System.out.print("비밀번호를 입력하세요. >> ");
					String pw = sc.next();

					if (pw.equals(arr.get(index).getPw())) { // 비밀번호 일치
						System.out.println("환영합니다~~ " + arr.get(index).getNickname() + "님");

						while (true) {
							System.out.println("[1] 캐릭터 신규 생성 [2] 캐릭터 선택 [3] 캐릭터 삭제 [4]이전");
							int CharacterMenu = sc.nextInt();
							if (CharacterMenu == 1) {
								System.out.println("생성할 캐릭터를 골라주세요");
								System.out.println("[1] 춘식이 [2] 라이언 [3] 프로도");
								int Characterselect = sc.nextInt(); // ArrayList 수정 해야함

							} else if (CharacterMenu == 2) {
								if (true) {// 캐릭터를 생성하지 않고 2번을 골랐을경우
									System.out.println("캐릭터가 생성되지 않았습니다 !! \n캐릭터를 신규생성해주세요!!");

								} else {
									System.out.println("캐릭터를 골라주세요");

									while (true) { // 원하는 캐릭터 선택시
										System.out.println("[1]갓생 살기 [2] 캐릭터 상태  [3] 이전");
										int Charactermenu = sc.nextInt();
										if (Charactermenu == 1) {
											System.out.println("갓생살자");
											while (true) {
												System.out.println("[1]행정 [2]미팅 [3]워크샵 [4]ott시청 [5]드라이브 [6]월급루팡 [7]이전");

												int activitymenu = sc.nextInt();
												if (activitymenu == 1) {
													//행정업무를 하면 능력치와 스트레스 수치 저장 해야함

												} else if (activitymenu == 2) {
													

												} else if (activitymenu == 3) {

												} else if (activitymenu == 4) {
													//휴식하면서 에너지 경험치 스트레스 수치 

												} else if (activitymenu == 5) {

												} else if (activitymenu == 6) {

												} else if (activitymenu == 7)
													break;

											} // activitymenu 선택 반복문

										} else if (Charactermenu == 2) {
											// controller에서 가져와야함
											// nickname, character type ,level, energy, work ability, xp, stress Gauge
											// 출력

										} else if (Charactermenu == 3)
											break;
										else
											System.out.println("메뉴에 없는 번호를 선택했습니다.\n정확하게 입력해주세요");

									} // 원하는 캐릭터 선택 반복문
								}

							} else if (CharacterMenu == 3) {
								if (true) {// << if 변수안에 캐릭터가 생성됐는지 판단한다
									System.out.println("삭제할 캐릭터를 골라주세요");
									// [1] [2] [3] ArrayList에 저장되어있는 캐릭터중 번호를 지정해 삭제 (수정필요)

								} else {
									System.out.println("캐릭터가 존재하지 않습니다");
								}

							} else if (CharacterMenu == 4)
								break;
							else
								System.out.println("메뉴에 없는 번호를 선택했습니다.\n정확하게 입력해주세요");

						} // 캐릭터 생성/선택 반복문

					} else // 비밀번호 불일치
						System.out.println("비밀번호가 일치하지 않습니다.");
				}

			} else if (loginMenu == 3) {//회원가입 탈퇴

			} else if (loginMenu == 4) {
				System.out.println("종료 합니다");
				break;
			} else {
				System.out.println("메뉴에 없는 번호를 선택했습니다.\n정확하게 입력해주세요");
			}

		} // 회원가입/로그인 반복문
		sc.close();
	}
}

