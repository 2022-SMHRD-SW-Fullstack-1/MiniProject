package Example;

import java.util.ArrayList;

public class StoreController {

	public void viewRestaurant(ArrayList<Store> R) {
		for (int i = 0; i < R.size(); i++) {
			System.out.println(i + 1 + ". " + R.get(i).getName() + "\t평점 : " + R.get(i).grade());
		}
	}

	public void viewSalon(ArrayList<Store> S) {
		for (int i = 0; i < S.size(); i++) {
			System.out.println(i + 1 + ". " + S.get(i).getName() + "\t평점 : " + S.get(i).grade());
		}
	}

	public void viewDetail(String name, ArrayList<Store> store) {
		int index = 0;
		for (int i = 0; i < store.size(); i++) {
			if (store.get(i).getName().equals(name)) {
				index = i;
				break;
			}
		}

		for (int k = 10; k <= 100; k += 10) {
			System.out.print(k + "\t");
		}
		System.out.println();
		for (int k = 10; k <= 100; k += 10) { // 점찍는거 구현
			if (store.get(index).grade() >= k)
				System.out.print("*");
			System.out.print("\t");
		}
		System.out.println("\n평점 : " + store.get(index).grade());

	}

	public void viewRate(ArrayList<Store> store) {
		for (int k = 100; k > 0; k -= 10) {
			System.out.print(((k != 0) ? k : " ") + "\t");
			for (int i = 0; i < store.size(); i++) {
				if (store.get(i).grade() >= k)
					System.out.print("*");
				else
					System.out.print("   ");
				System.out.print("\t");
			}

			System.out.println();
		}

		for (int i = 0; i < store.size(); i++) {
			System.out.print("\t" + store.get(i).getName());
		}
		System.out.println();

	}

}
