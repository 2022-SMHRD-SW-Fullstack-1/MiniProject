package Model;

public class CharacterDTO {

	//종류 레벨 에너지 경험치 업무능력 스트레스 월급
	private String type; //종류
	private String chanick; //캐릭터 닉네임
	private int Level;	//레벨(층수)
	private int Experience;	//경험치(인지도) [1] : 가진 경험치, [2] 레벨업할때 필요한 경험치
	private int Energy;		//에너지 [1] : 가진 에너지, [2] 에너지 총량
	private int ability; //업무능력
	private int stress; //스트레스
	private int pay; //월급
	
	
	public CharacterDTO(String type, String chanick, int Level, int Experience,
			int Energy, int ability, int stress, int pay) {
		this.type = type;
		this.chanick = chanick;
		this.Level = Level;
		this.Experience = Experience;
		this.Energy = Energy;
		this.ability = ability;
		this.stress = stress;
		this.pay = pay;
		
	}
	
	
	//캐릭터의 속성(종류 캐릭터닉네임 레벨 에너지 경험치 업무능력 스트레스 월급) / 가지고올수있는메서드
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getChanick() {
		return chanick;
	}
	public void setChanick(String chanick) {
		this.chanick = chanick;
	}
	public int getLevel() {
		return Level;
	}
	public void setLevel(int level) {
		Level = level;
	}
	public int getExperience() {
		return Experience;
	}
	public void setExperience(int experience) {
		Experience = experience;
	}
	public int getEnergy() {
		return Energy;
	}
	public void setEnergy(int energy) {
		Energy = energy;
	}
	public int getAbility() {
		return ability;
	}
	public void setAbility(int ability) {
		this.ability = ability;
	}
	public int getStress() {
		return stress;
	}
	public void setStress(int stress) {
		this.stress = stress;
	}
	public int getPay() {
		return pay;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	
	

}
