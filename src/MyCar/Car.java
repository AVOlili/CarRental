package MyCar;

public class Car {
	public String Brand;//属性:品牌
	public String number;//属性车牌
	public double DayRent;//属性日租金
	double Rent;
	int days;
	
	
	public Car(String brand, String number, double dayRent) {
		super();
		this.Brand = brand;
		this.number = number;
		this.DayRent = dayRent;
	}



	//租金方法
	public double calRent(int days){
		return Rent;}
}
