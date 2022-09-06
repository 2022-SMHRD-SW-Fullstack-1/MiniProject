package View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Controller.mainController;
import Model.CharacterDTO;
import Model.MemberDTO;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		mainController mc = new mainController();
		Random rd = new Random();

		int result = 0;
		int cnt = 0;

		while (true) { // 회원가입
			System.out.print("\n[1] 회원가입 [2] 로그인 [3] 탈퇴 [4]종료 >> ");
			int loginMenu = sc.nextInt();

			if (loginMenu == 1) {
				System.out.print("회원가입 할 id를 입력해주세요 >> ");
				String id = sc.next();
				ArrayList<MemberDTO> arr = mc.Login();
				boolean idck = true;

				for (int i = 0; i < arr.size(); i++) {
					if (arr.get(i).getId().equals(id)) {
						System.out.println("아이디가 중복되어 사용할 수 없습니다.");
						idck = false;
						break;
					}
				}
				if (idck == true) {

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
							System.out.print("\n[1] 캐릭터 신규 생성 [2] 캐릭터 선택 [3] 캐릭터 삭제 [4] Tip [5] 이전 >> ");
							int CharacterMenu = sc.nextInt();
							int CharacterNum = mc.cNumCheck(id);
							if (CharacterMenu == 1) {

								if (CharacterNum < 3) {
									System.out.println("생성할 캐릭터를 골라주세요");
									System.out.print("[1] 춘식이 [2] 라이언 [3] 프로도 ");
									int Characterselect = sc.nextInt(); // ArrayList 수정 해야함
									System.out.print("캐릭터 닉네임을 설정 해주세요 >> ");
									String charnickname = sc.next();
									ArrayList<String> nickList = mc.charnickck();

									boolean nickck = true;

									for (int i = 0; i < nickList.size(); i++) {
										if (nickList.get(i).equals(charnickname)) {
											System.out.println("닉네임이 중복되어 사용할 수 없습니다.");
											nickck = false;
											break;
										}
									}
									if (nickck == true) {
										cnt = mc.newCharacter(charnickname, Characterselect, id);
										if (cnt > 0) {
											System.out.println("캐릭터 생성 완료");
										} else {
											System.out.println("캐릭터 생성 실패");
										}
									}
								} else {
									System.out.println("캐릭터가 다 찼어요");
								}

							} else if (CharacterMenu == 2) {

								if (CharacterNum == 0) {// 캐릭터를 생성하지 않고 2번을 골랐을경우
									System.out.println("캐릭터가 생성되지 않았습니다 !! \n캐릭터를 신규생성해주세요!!");

								} else {
									String[] nickarr = new String[3];
									int temp99 = 0;
									nickarr = mc.nickList(id);
									for (int i = 0; i < 3; i++) {
										if (nickarr[i] == null) {
											temp99 = i;
											break;
										}
										System.out.println((i + 1) + "번째 캐릭터 : " + nickarr[i]);
									}
									if (temp99 == 0 && nickarr[2] != null)
										temp99 = 3;
									System.out.print("캐릭터를 골라주세요 >> ");
									int charnum = sc.nextInt();
									if (charnum <= temp99) {
										String charNick = nickarr[charnum - 1]; // 고른 캐릭터의 pk(char_nick)을 저장
										System.out.println(charNick + "이(가) 갓생에 도전합니다");
										int ismyCout = 0;
										while (true) { // 원하는 캐릭터 선택시
											if (ismyCout != 0)
												break;
											System.out.print("\n[1]갓생 살기 [2] 캐릭터 상태  [3] 이전 >> ");
											int Charactermenu = sc.nextInt();
											if (Charactermenu == 1) {
												System.out.println("갓생살자");
												while (true) {
													System.out.print(
															"\n[1]행정업무 [2]미팅 [3]외근 [4]ott시청 [5]드라이브 [6]월급루팡 [7]이전 >> ");

													int activitymenu = sc.nextInt();
													if (activitymenu == 1) { // 행정업무
														CharacterDTO temp = mc.getMyC(charNick);
														int rdtemp = rd.nextInt(11) + 10;
														
														if (rdtemp > temp.getEnergy()[0]) {
															System.out.println("에너지가 부족하여 행정업무를 할 수 없습니다");
															break;
														} else {
															System.out.println(charNick + "(이)가 행정업무를 시작합니다");
															// temp의 스트레스+rd(10~20), 에너지-rd(10~20), 경험치+rd(10~20),
															// 월급+50+보너스(rd*업무능력*)
															
															System.out.println("에너지를 " + rdtemp + "만큼 사용했습니다");
															int[] temparr = { temp.getEnergy()[0] - rdtemp,
																	temp.getEnergy()[1] };
															temp.setEnergy(temparr);
															
															rdtemp = rd.nextInt(11) + 10;
															System.out.println("스트레스가 " + rdtemp + "만큼 올랐습니다");
															temp.setStress(rdtemp + temp.getStress());

															rdtemp = rd.nextInt(11) + 10;
															System.out.println("경험치가 " + rdtemp + "만큼 올랐습니다");
															int[] temarr2 = { temp.getExperience()[0] + rdtemp,
																	temp.getExperience()[1] };
															temp.setExperience(temarr2);

															rdtemp = rd.nextInt(5) * temp.getAbility()
																	/ temp.getLevel(); // 보너스값
															System.out.println("월급 50과 보너스 " + rdtemp + "를 받았습니다");
															temp.setPay(temp.getPay() + 50 + rdtemp);

															if (temp.getStress() >= 100) {
																System.out.println();
																System.out.println(charNick + "(이)가 퇴사합니다");
																// 보따리들고 떠나는 아스키코드 추가
																mc.deleteCharacter(charNick);
																ismyCout++;
																break;
															} else {

																if (temp.getExperience()[0] >= 50
																		+ 10 * (temp.getLevel() - 1)) {
																	temp = mc.levelup(temp);
																	System.out.println(charNick + "(이)가 "
																			+ temp.getLevel() + "레벨로 올랐습니다");
																}

																rdtemp = mc.setMyC(temp);

																if (rdtemp == 1) {
																	System.out.println("저장성공");
																} else {
																	System.out.println("저장실패");
																}
															}
														}

													} else if (activitymenu == 2) { // temp의 스트레스+rd(20~30),
																					// 에너지-rd(10~20), 경험치+rd(10~20),
														// 월급+150+보너스(rd*업무능력*)
														CharacterDTO temp = mc.getMyC(charNick);
														int rdtemp = 0;
														rdtemp = rd.nextInt(11) + 10;
														if (rdtemp > temp.getEnergy()[0]) {
															System.out.println("에너지가 부족하여 미팅을 할 수 없습니다");
															break;
														} else {
															System.out.println(charNick + "(이)가 미팅을 시작합니다");

															System.out.println("에너지를 " + rdtemp + "만큼 사용했습니다");
															int[] temparr = { temp.getEnergy()[0] - rdtemp,
																	temp.getEnergy()[1] };
															temp.setEnergy(temparr);

															rdtemp = rd.nextInt(21) + 10;
															System.out.println("스트레스가 " + rdtemp + "만큼 올랐습니다");
															temp.setStress(rdtemp + temp.getStress());

															rdtemp = rd.nextInt(11) + 10;
															System.out.println("경험치가 " + rdtemp + "만큼 올랐습니다");
															int[] temarr2 = { temp.getExperience()[0] + rdtemp,
																	temp.getExperience()[1] };
															temp.setExperience(temarr2);

															rdtemp = rd.nextInt(5) * temp.getAbility()
																	/ temp.getLevel(); // 보너스값
															System.out.println("월급 150과 보너스 " + rdtemp + "를 받았습니다");
															temp.setPay(temp.getPay() + 150 + rdtemp);

															if (temp.getStress() >= 100) {
																System.out.println();
																System.out.println(charNick + "(이)가 퇴사합니다");
																// 보따리들고 떠나는 아스키코드 추가
																mc.deleteCharacter(charNick);
																ismyCout++;
																break;
															} else {

																if (temp.getExperience()[0] >= 50
																		+ 10 * (temp.getLevel() - 1)) {
																	temp = mc.levelup(temp);
																	System.out.println(charNick + "(이)가 "
																			+ temp.getLevel() + "레벨로 올랐습니다");
																}
																rdtemp = mc.setMyC(temp);

																if (rdtemp == 1) {
																	System.out.println("저장성공");
																} else {
																	System.out.println("저장실패");
																}
															}

														}

													} else if (activitymenu == 3) {// 외근
														CharacterDTO temp = mc.getMyC(charNick);

														int rdtemp = 0;

														rdtemp = rd.nextInt(11) + 20;
														if (rdtemp > temp.getEnergy()[0]) {
															System.out.println("에너지가 부족합니다");
															break;
														} else {
															System.out.println(charNick + "(이)가 외근 나가 열심히 일을 합니다");
															// temp의 스트레스+rd(10~20), 에너지-rd(10~20), 경험치+rd(10~20),
															// 월급+50+보너스(rd*업무능력*)
															System.out.println("에너지를 " + rdtemp + "만큼 사용했습니다");
															int[] temparr = { temp.getEnergy()[0] - rdtemp,
																	temp.getEnergy()[1] };
															temp.setEnergy(temparr);

															rdtemp = rd.nextInt(11) + 10;
															System.out.println("스트레스가 " + rdtemp + "만큼 올랐습니다");
															temp.setStress(rdtemp + temp.getStress());

															rdtemp = rd.nextInt(11) + 10;
															System.out.println("경험치가 " + rdtemp + "만큼 올랐습니다");
															int[] temarr2 = { temp.getExperience()[0] + rdtemp,
																	temp.getExperience()[1] };
															temp.setExperience(temarr2);

															rdtemp = rd.nextInt(5) * temp.getAbility()
																	/ temp.getLevel(); // 보너스값
															System.out.println("월급 100과 보너스 " + rdtemp + "를 받았습니다");
															temp.setPay(temp.getPay() + 100 + rdtemp);

															if (temp.getStress() >= 100) {
																System.out.println();
																System.out.println(charNick + "(이)가 퇴사합니다");

																mc.deleteCharacter(charNick);
																ismyCout++;
																break;
															} else {

																if (temp.getExperience()[0] >= 50
																		+ 10 * (temp.getLevel() - 1)) {
																	temp = mc.levelup(temp);
																	System.out.println(charNick + "(이)가 "
																			+ temp.getLevel() + "레벨로 올랐습니다");
																}

																rdtemp = mc.setMyC(temp);

																if (rdtemp == 1) {
																	System.out.println("저장성공");
																} else {
																	System.out.println("저장실패");
																}
															}

														}

													} else if (activitymenu == 4) { // ott 시청

														CharacterDTO temp = mc.getMyC(charNick);
														int rdtemp = 0;
														if (temp.getPay() >= 50) {
															// 휴식하면서 에너지 경험치 스트레스 수치
															// temp의 스트레스-rd(10~50) 0일 때 음수로 가지 않게!,
															// 에너지+rd(10~20), 경험치+rd(5~10), 월급-50

															System.out.println(charNick + "(이)가 ott를 보면서 힐링합니다");
															rdtemp = rd.nextInt(11) + 10;
															if (temp.getStress() - rdtemp < 0) {
																rdtemp = temp.getStress();
															}
															System.out.println("스트레스가 " + rdtemp + "만큼 감소합니다");
															temp.setStress(temp.getStress() - rdtemp);

															rdtemp = rd.nextInt(11) + 10;
															if (temp.getEnergy()[0] + rdtemp > 100
																	+ 10 * (temp.getLevel() - 1))
																rdtemp = rdtemp - (temp.getEnergy()[0] + rdtemp - 100
																		- 10 * (temp.getLevel() - 1));
															System.out.println("에너지가 " + rdtemp + "만큼 회복됩니다");
															int[] temparr = { temp.getEnergy()[0] + rdtemp,
																	temp.getEnergy()[1] };
															temp.setEnergy(temparr);

															rdtemp = rd.nextInt(6) + 5;
															System.out.println("경험치가 " + rdtemp + "만큼 올랐습니다");
															int[] temarr2 = { temp.getExperience()[0] + rdtemp,
																	temp.getExperience()[1] };
															temp.setExperience(temarr2);

															// 월급 소모
															rdtemp = temp.getPay() - 50;
															System.out.println(
																	"ott 시청을 위해 월급을 소모하여 " + rdtemp + "만큼 남았습니다");
															temp.setPay(temp.getPay() - 50);

														} else {
															System.out.println("가진 돈이 부족하여 ott시청이 불가합니다");
														}

														if (temp.getExperience()[0] >= 50
																+ 10 * (temp.getLevel() - 1)) {
															temp = mc.levelup(temp);
															System.out.println(
																	charNick + "(이)가 " + temp.getLevel() + "레벨로 올랐습니다");
														}

														rdtemp = mc.setMyC(temp);

														if (rdtemp == 1) {
															System.out.println("저장성공");
														} else {
															System.out.println("저장실패");
														}

													} else if (activitymenu == 5) {// 드라이브

														CharacterDTO temp = mc.getMyC(charNick);
														int rdtemp = rd.nextInt(101) + 50;
														if (temp.getPay() < rdtemp) {
															System.out.println("월급이 부족해서 드라이브를 떠날 수 없습니다");
														} else {

															System.out.println(charNick + "(이)가 페라리를 타고 드라이브를 시작합니다");

															int cost = rdtemp;
															System.out.println("든을 " + cost + "만큼 썼습니다");
															temp.setPay(temp.getPay() - cost);

															rdtemp = (rd.nextInt(11) + 10)*(cost/50);
															if (temp.getStress() - rdtemp < 0)
																rdtemp = temp.getStress();
															System.out.println("스트레스가 " + rdtemp + "만큼 감소했습니다");
															temp.setStress(temp.getStress() - rdtemp);

															rdtemp = (rd.nextInt(11) + 10)*(cost/50);
															if (temp.getEnergy()[0] + rdtemp > 100
																	+ 10 * (temp.getLevel() - 1))
																rdtemp = rdtemp - (temp.getEnergy()[0] + rdtemp - 100
																		- 10 * (temp.getLevel() - 1));
															System.out.println("에너지가 " + rdtemp + "만큼 증가했습니다");
															int[] temparr = { temp.getEnergy()[0] + rdtemp,
																	temp.getEnergy()[1] };
															temp.setEnergy(temparr);

															rdtemp = rd.nextInt(6) + 5;
															System.out.println("경험치가 " + rdtemp + "만큼 올랐습니다");
															int[] temarr2 = { temp.getExperience()[0] + rdtemp,
																	temp.getExperience()[1] };
															temp.setExperience(temarr2);

															if (temp.getExperience()[0] >= 50
																	+ 10 * (temp.getLevel() - 1)) {
																temp = mc.levelup(temp);
																System.out.println(charNick + "(이)가 " + temp.getLevel()
																		+ "레벨로 올랐습니다");
															}

															rdtemp = mc.setMyC(temp);

															if (rdtemp == 1) {
																System.out.println("저장성공");
															} else {
																System.out.println("저장실패");
															}
														}

													} else if (activitymenu == 6) {// 월급루팡

														System.out.println(charNick + "(이)가 월급루팡를 시작합니다");
														CharacterDTO temp = mc.getMyC(charNick);
														int rdtemp = 0;

														rdtemp = rd.nextInt(11) + 10;
														if (temp.getStress() - rdtemp < 0)
															rdtemp = temp.getStress();
														System.out.println("스트레스가 " + rdtemp + "만큼 감소했습니다");
														temp.setStress(temp.getStress() - rdtemp);

														rdtemp = rd.nextInt(11) + 10;
														if (temp.getEnergy()[0] + rdtemp > 100
																+ 10 * (temp.getLevel() - 1))
															rdtemp = rdtemp - (temp.getEnergy()[0] + rdtemp - 100
																	- 10 * (temp.getLevel() - 1));
														System.out.println("에너지가 " + rdtemp + "만큼 증가했습니다");
														int[] temparr = { temp.getEnergy()[0] + rdtemp,
																temp.getEnergy()[1] };
														temp.setEnergy(temparr);

														rdtemp = rd.nextInt(6) + 5;
														System.out.println("경험치가 " + rdtemp + "만큼 올랐습니다");
														int[] temarr2 = { temp.getExperience()[0] + rdtemp,
																temp.getExperience()[1] };
														temp.setExperience(temarr2);

														System.out.println("월급 50을 받았습니다");
														temp.setPay(temp.getPay() + 50);

														rdtemp = rd.nextInt(9) - 4;
														if (rdtemp > 0) {
															if (temp.getAbility() <= rdtemp)
																rdtemp = temp.getAbility();
															System.out
																	.println("상사에게 걸려서 업무능력이 " + rdtemp + "만큼 감소했습니다");
															temp.setAbility(temp.getAbility() - rdtemp);
														}

														if (temp.getAbility() <= 0) {
															System.out.println();
															System.out.println(charNick + "(이)의 업무능력 미달로 권고사직당했습니다");
															// 짐싸서 떠나는 아스키코드 추가
															mc.deleteCharacter(charNick);
															ismyCout++;
															break;
														} else {
															if (temp.getExperience()[0] >= 50
																	+ 10 * (temp.getLevel() - 1)) {
																temp = mc.levelup(temp);
																System.out.println(charNick + "(이)가 " + temp.getLevel()
																		+ "레벨로 올랐습니다");
															}

															rdtemp = mc.setMyC(temp);

															if (rdtemp == 1) {
																System.out.println("저장성공");
															} else {
																System.out.println("저장실패");
															}
														}

													} else if (activitymenu == 7)
														break;
													else
														System.out.println("메뉴에 없는 번호를 선택했습니다.\n정확하게 입력해주세요");
												} // activitymenu 선택 반복문

											} else if (Charactermenu == 2) {
												// controller에서 가져와야함
												// nickname, character type ,level, energy, work ability, xp, stress
												// Gauge
												CharacterDTO myC = mc.getMyC(charNick);
												System.out.println("==========캐릭터 상태창==========");
												System.out.println("캐릭터 닉네임 : " + myC.getChanick());
												System.out.println("캐릭터 타입 : " + myC.getType());
												System.out.println("캐릭터 레벨 : " + myC.getLevel());
												System.out.println("캐릭터 경험치 : " + myC.getExperience()[0] + "/"
														+ myC.getExperience()[1]);
												System.out.println(
														"캐릭터 에너지 : " + myC.getEnergy()[0] + "/" + myC.getEnergy()[1]);
												System.out.println("캐릭터 업무능력 : " + myC.getAbility());
												System.out.println("캐릭터 스트레스 : " + myC.getStress());
												System.out.println("가진 돈 : " + myC.getPay());

											} else if (Charactermenu == 3)
												break;
											else
												System.out.println("메뉴에 없는 번호를 선택했습니다.\n정확하게 입력해주세요");

										} // 원하는 캐릭터 선택 반복문
									} else
										System.out.println("선택할 수 없는 번호입니다.");
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

									int temp = mc.deleteCharacter(nickarr[charnum - 1]);
									if (temp == 0) {
										System.out.println("캐릭터 삭제 실패");
									} else {
										System.out.println("캐릭터 삭제 성공");
									}

								} else {
									System.out.println("삭제할 캐릭터가 존재하지 않습니다");
								}

							} else if (CharacterMenu == 4) {
								System.out.println("스트레스 수치가 너무 높을 시 퇴사합니다");
								System.out.println("월급루팡을 상사에게 걸리지 않도록 조심하세요. 권고사직 당할 수 있습니다");
							} else if (CharacterMenu == 5)
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
				mc.idlinknickdelete(id);
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
