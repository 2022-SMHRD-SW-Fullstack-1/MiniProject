package JDBCController;

public class MemDTO {
	
	//회원관리를 위한 설계도
		//아이디, 비밀번호, 닉네임
		
		private String id;
		private String pw;
		private String usernick;//닉네임
		
		
		public MemDTO(String id, String pw, String usernick) {
			this.id=id;
			this.pw=pw;
			this.usernick=usernick;
		}
		
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPw() {
			return pw;
		}
		public void setPw(String pw) {
			this.pw = pw;
		}
		public String getUsernick() {
			return usernick;
		}
		public void setUsernick(String usernick) {
			this.usernick = usernick;
		}
		
		

		
		
	
	

}
