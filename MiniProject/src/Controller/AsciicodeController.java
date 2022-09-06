package Controller;

import Model.Asciicode;

public class AsciicodeController {

	Asciicode ac = new Asciicode();

	public void start(String type) {
		if (type.equals("춘식")) {
			ac.ChunStrat();
		} else if (type.equals("라이언")) {
			ac.ryanStrat();
		} else if (type.equals("프로도")) {
			ac.frodoStrat();
		}
	}

	public void admin(String type) {

		if (type.equals("춘식")) {
			ac.chunAdmin();
		} else if (type.equals("라이언")) {
			ac.ryanAdmin();
		} else if (type.equals("프로도")) {
			ac.frodoAdmin();
		}

	}

	public void Meeting(String type) {

		if (type.equals("춘식")) {
			ac.chunMeeting();
		} else if (type.equals("라이언")) {
			ac.ryanMeeting();
		} else if (type.equals("프로도")) {
			ac.frodoMeeting();
		}

	}
	public void WorkOut(String type) {
		
		if (type.equals("춘식")) {
			ac.ChunWorkout();
		} else if (type.equals("라이언")) {
			ac.ryanWorkout();
		} else if (type.equals("프로도")) {
			ac.frodoWorkout();
		}
		
	}
	public void Ott(String type) {
		
		if (type.equals("춘식")) {
			ac.chunOtt();
		} else if (type.equals("라이언")) {
			ac.ryanOtt();
		} else if (type.equals("프로도")) {
			ac.frodoOtt();
		}
		
	}
	public void Drive(String type) {
		
		if (type.equals("춘식")) {
			ac.chunDrive();
		} else if (type.equals("라이언")) {
			ac.ryanDrive();
		} else if (type.equals("프로도")) {
			ac.frodoDrive();
		}
		
	}
	public void Lupin(String type) {
		
		if (type.equals("춘식")) {
			ac.chunPaylupin();
		} else if (type.equals("라이언")) {
			ac.ryanPaylupin();;
		} else if (type.equals("프로도")) {
			ac.frodoPaylupin();
		}
		
	}
	public void Getout(String type) { // 권고사직
		
		if (type.equals("춘식")) {
			ac.chunOut();
		} else if (type.equals("라이언")) {
			ac.ryanOut();
		} else if (type.equals("프로도")) {
			ac.frodoOut();
		}
		
	}
	public void Goodbye(String type) { //정년퇴직
		
		if (type.equals("춘식")) {
			ac.chunGoodbye();
		} else if (type.equals("라이언")) {
			ac.ryanGoodbye();
		} else if (type.equals("프로도")) {
			ac.frodoGoodbye();
		}
		
	}
	public void ByeBye(String type) { //스트레스 퇴사
		
		if (type.equals("춘식")) {
			ac.chunBye();
		} else if (type.equals("라이언")) {
			ac.ryanBye();
		} else if (type.equals("프로도")) {
			ac.frodoBye();
		}
		
	}
	public void King(String type) { //회장
		
		if (type.equals("춘식")) {
			ac.chunKing();
		} else if (type.equals("라이언")) {
			ac.ryanKing();
		} else if (type.equals("프로도")) {
			ac.frodoKing();
		}
		
	}

}
