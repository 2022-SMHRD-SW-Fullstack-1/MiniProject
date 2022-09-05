package View;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.mainController;
import Model.CharacterDTO;
import Model.MemberDTO;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		mainController mc = new mainController();

		int result = 0;
		int cnt = 0;

		while (true) { // 회원가입
			System.out.print("[1] 회원가입 [2] 로그인 [3] 탈퇴 [4]종료 >> ");
			int loginMenu = sc.nextInt();

			if (loginMenu == 1) {
				System.out.print("회원가입 할 id를 입력해주세요 >> ");
				String id = sc.next();
				System.out.print("사용 할 패스워드를 입력해주세요 >>");
				String pw = sc.next();
				System.out.print("사용 할 닉네임을 입력해주세요 >>");
				String nickname = sc.next();

				result = mc.Register(id, pw, nickname);
				if (result > 0) {
					System.out.println("회원가입 성공");
				} else {
					System.out.println("회원가입 실패");
				}
			} else if (loginMenu == 2) { // 로그인

				// select 작업
				ArrayList<MemberDTO> arr = mc.Login();
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
							int CharacterNum = mc.cNumCheck(id);
							if (CharacterMenu == 1) {
								
								if (CharacterNum < 3) {
									System.out.println("생성할 캐릭터를 골라주세요");
									System.out.print("[1] 춘식이 [2] 라이언 [3] 프로도 ");
									int Characterselect = sc.nextInt(); // ArrayList 수정 해야함
									System.out.print("캐릭터 닉네임을 설정 해주세요 >> ");
									String charnickname = sc.next();
									cnt = mc.newCharacter(charnickname, CharacterMenu, id);
									if (cnt > 0) {
										System.out.println("캐릭터 생성 완료");
									} else {
										System.out.println("캐릭터 생성 실패");
									}
								} else {
									System.out.println("캐릭터가 다 찼어요");
								}

							} else if (CharacterMenu == 2) {
								
								if (CharacterNum == 0) {// 캐릭터를 생성하지 않고 2번을 골랐을경우
									System.out.println("캐릭터가 생성되지 않았습니다 !! \n캐릭터를 신규생성해주세요!!");

								} else {
									System.out.println("캐릭터를 골라주세요");
									String[] nickarr = new String[3];

									nickarr = mc.nickList(id);
									for (int i = 0; i < 3; i++) {
										if (nickarr[i] == null)
											break;
										System.out.println((i + 1) + "번째 캐릭터 : " + nickarr[i]);
									}
									int charnum = sc.nextInt();
									String charNick = nickarr[charnum - 1]; // 고른 캐릭터의 pk(char_nick)을 저장
									System.out.println(charNick + "이(가) 갓생에 도전합니다");
									while (true) { // 원하는 캐릭터 선택시
										System.out.println("[1]갓생 살기 [2] 캐릭터 상태  [3] 이전");
										int Charactermenu = sc.nextInt();
										if (Charactermenu == 1) {
											System.out.println("갓생살자");
											while (true) {
												System.out
														.println("[1]행정업무 [2]미팅 [3]외근 [4]ott시청 [5]드라이브 [6]월급루팡 [7]이전");

												int activitymenu = sc.nextInt();
												if (activitymenu == 1) {
													// 행정업무를 하면 능력치와 스트레스 수치 저장 해야함

												} else if (activitymenu == 2) {

												} else if (activitymenu == 3) {

												} else if (activitymenu == 4) {
													// 휴식하면서 에너지 경험치 스트레스 수치

												} else if (activitymenu == 5) {

												} else if (activitymenu == 6) {

												} else if (activitymenu == 7)
													break;
												else
													System.out.println("메뉴에 없는 번호를 선택했습니다.\n정확하게 입력해주세요");
											} // activitymenu 선택 반복문

										} else if (Charactermenu == 2) {
											// controller에서 가져와야함
											// nickname, character type ,level, energy, work ability, xp, stress Gauge
											CharacterDTO myC=mc.getMyC(charNick);
											System.out.println("==========캐릭터 상태창==========");
											System.out.println("캐릭터 닉네임 : " + myC.getChanick());
											System.out.println("캐릭터 타입 : " + myC.getType());
											System.out.println("캐릭터 레벨 : " + myC.getLevel());
											System.out.println("캐릭터 경험치 : " + myC.getExperience()[0] + "/" + myC.getExperience()[1]);
											System.out.println("캐릭터 에너지 : " + myC.getEnergy()[0] + "/" + myC.getEnergy()[1]);
											System.out.println("캐릭터 업무능력 : " + myC.getAbility());
											System.out.println("캐릭터 스트레스 : " + myC.getStress());
											System.out.println("가진 돈 : " + myC.getPay());

										} else if (Charactermenu == 3)
											break;
										else
											System.out.println("메뉴에 없는 번호를 선택했습니다.\n정확하게 입력해주세요");

									} // 원하는 캐릭터 선택 반복문
								}

							} else if (CharacterMenu == 3) {
								if (CharacterNum > 0) {// << if 변수안에 캐릭터가 생성됐는지 판단한다
									System.out.println("삭제할 캐릭터를 골라주세요");

									String[] nickarr = new String[3];

									nickarr = mc.nickList(id);
									for (int i = 0; i < 3; i++) {
										if (nickarr[i] == null)
											break;
										System.out.println((i + 1) + "번째 캐릭터 : " + nickarr[i]);
									}
									int charnum = sc.nextInt();
									
									int temp=mc.deleteCharacter(nickarr[charnum-1]);
									if(temp==0) {
										System.out.println("캐릭터 삭제 실패");
									}else {
										System.out.println("캐릭터 삭제 성공");
									}
									

								} else {
									System.out.println("삭제할 캐릭터가 존재하지 않습니다");
								}

							} else if (CharacterMenu == 4)
								break;
							else
								System.out.println("메뉴에 없는 번호를 선택했습니다.\n정확하게 입력해주세요");

						} // 캐릭터 생성/선택 반복문

					} else // 비밀번호 불일치
						System.out.println("비밀번호가 일치하지 않습니다.");
				}

			} else if (loginMenu == 3) {// 회원가입 탈퇴
				System.out.print("삭제할 아이디 입력: ");
				String id = sc.next();
				System.out.print("비밀번호를 입력하세요: ");
				String pw = sc.next();
				System.out.println("삭제되었습니다.");
				mc.Dropout(id, pw);

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
