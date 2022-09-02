package Example;

import java.util.ArrayList;
import java.util.Scanner;

public class Ex_Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StoreController scon = new StoreController();
		
		ArrayList<Store> R = new ArrayList<>();
		ArrayList<Store> S = new ArrayList<>();
		ArrayList<Store> store = new ArrayList<>();
		
		R.add(new Restaurant("해성식당", "백반", 10, 8, 9, 4.5));
		R.add(new Restaurant("정림이네", "중식", 6, 7.5, 7, 4.0));
		R.add(new Restaurant("왕뼈사랑", "한식", 7, 8, 10, 8.5));

		S.add(new Salon("박승철", "미용실", 89,97, 70));
		S.add(new Salon("이가자", "미용실", 79,87, 70));
		S.add(new Salon("리안", "미용실", 99,97, 70));
		
		store.add(new Restaurant("해성식당", "백반", 10, 8, 9, 4.5));
		store.add(new Restaurant("정림이네", "중식", 6, 7.5, 7, 4.0));
		store.add(new Restaurant("왕뼈", "한식", 7, 8, 10, 8.5));
		store.add(new Salon("박승철", "미용실", 89,97, 70));
		store.add(new Salon("이가자", "미용실", 79,87, 70));
		store.add(new Salon("리안", "미용실", 99,97, 70));
		
		
		int menu = 0;
		
		
		while(true) {
			System.out.print("[1]음식점보기 [2]미용실보기 [3]상세보기 [4]평점보기 [5]종료 >> ");
			menu = sc.nextInt();
			
			if(menu == 1) {
				System.out.println("====음식점보기====");
				scon.viewRestaurant(R);
			} else if(menu == 2) {
				System.out.println("====미용실보기====");
				scon.viewSalon(S);
			} else if(menu == 3) {
				System.out.print("가게명 입력 : ");
				String name = sc.next();
				scon.viewDetail(name, store);
			} else if(menu == 4) {
				scon.viewRate(store);
			} else if(menu == 5) {
				break;
			} else
				System.out.println("정확한 숫자를 입력해주세요.");
			
		}

	}

}
