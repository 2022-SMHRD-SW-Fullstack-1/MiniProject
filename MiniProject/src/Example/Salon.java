package Example;

public class Salon extends Store {

	public double technology; // 기술 점수를 저장할 필드
	public double kindness; // 친절 점수를 저장할 필드

	public Salon(String name, String event, double technology, double kindness, double price) {
		this.name = name;
		this.event = event;
		this.technology = technology;
		this.kindness = kindness;
		this.price = price;

	}

	public double grade() { // 각 가게들의 점수의 평균 반환할 메서드
		double avg = (technology + kindness + price) / 3;
		return Math.round(avg*100)/100.0;
	}

	public double getTechnology() {
		return technology;
	}

	public void setTechnology(double technology) {
		this.technology = technology;
	}

	public double getKindness() {
		return kindness;
	}

	public void setKindness(double kindness) {
		this.kindness = kindness;
	}
	
	
}