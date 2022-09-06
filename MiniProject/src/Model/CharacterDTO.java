package Model;

import java.util.Random;

public class CharacterDTO {

	//종류 레벨 에너지 경험치 업무능력 스트레스 월급
	private String chanick; //캐릭터 닉네임 - 입력
	private String type; //종류 - 입력
	private int[] Experience = new int[2];	//경험치(인지도) [1] : 가진 경험치, [2] 레벨업할때 필요한 경험치 - 0/50+10
	private int Level;	//레벨(층수) - 1
	private int[] Energy=new int[2];		//에너지 [1] : 가진 에너지, [2] 에너지 총량 - 100/100+10
	private int ability; //업무능력 - 랜덤
	private int stress; //스트레스 - 0
	private int pay; //월급 - 0
	
	public CharacterDTO() {
		
	}
	
	Random rd=new Random();
	public CharacterDTO(String chanick, int type) {
		this.chanick = chanick;
		if(type ==1)
		this.type = "춘식";
		else if(type==2){
			this.type="라이언";
		}else {
			this.type="프로도";
		}
		this.Experience[0]= 0;
		this.Experience[1]= 50;
		this.Level=1;
		this.Energy[0]=100;
		this.Energy[1]=100;
		this.ability=rd.nextInt(5)+5;
		this.stress=0;
		this.pay=0;
		
	}
	public String getChanick() {
		return chanick;
	}
	public void setChanick(String chanick) {
		this.chanick = chanick;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int[] getExperience() {
		return Experience;
	}
	public void setExperience(int[] experience) {
		Experience = experience;
	}
	public int getLevel() {
		return Level;
	}
	public void setLevel(int level) {
		Level = level;
	}
	public int[] getEnergy() {
		return Energy;
	}
	public void setEnergy(int[] energy) {
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
	
	
	//캐릭터의 속성(종류 캐릭터닉네임 레벨 에너지 경험치 업무능력 스트레스 월급) / 가지고올수있는메서드
	
	
	

}
