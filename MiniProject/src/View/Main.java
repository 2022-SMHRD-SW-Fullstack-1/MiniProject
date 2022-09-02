package View;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//주석
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("[1] 회원가입 [2] 로그인 [3] 탈퇴 [4]종료 ");
			int loginMenu=sc.nextInt();
			if(loginMenu==1) {
				System.out.println("회원가입 완료");
			}else if(loginMenu==2) {
				
			}else if(loginMenu==3) {
				
			}else if(loginMenu==4) {
				System.out.println("종료 합니다");
				break;
			}else {
				System.out.println("정확하게 입력해주세요");
			}
		
		
		}
		
		

	}

}
