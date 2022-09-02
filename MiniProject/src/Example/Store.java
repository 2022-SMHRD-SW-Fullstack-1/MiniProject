package Example;

public abstract class Store {

	String name; //가게상호 이름을 저장할 필드
	String event; //가게종목을 저장할 필드
	double price; //가게 평점 중 가격점수를 저장할 필드
	
	
	
	abstract public  double grade();



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEvent() {
		return event;
	}



	public void setEvent(String event) {
		this.event = event;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}
	
}