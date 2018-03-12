package MyCar;

public class SmallCar extends Car{
	public String Type;

	public SmallCar(String brand, String number, double dayRent,String Type) {
		super(brand, number, dayRent);
		this.Type = Type;
	}
	
	SmallCar s1 = new SmallCar("����", "��NY2858", 800,"X6");
	SmallCar s2 = new SmallCar("����", "��CNY3284", 600,"550i");
	SmallCar s3 = new SmallCar("���", "��NT3765", 300,"������");
	SmallCar s4 = new SmallCar("���", "��NT96968", 600,"GL8");
	
	@Override
  	public double calRent(int days){
	  	if(days>7&&days<30){
	  		return Rent = 0.9*DayRent*days;
	  	}else if(days>30&&days<150){
	  		return Rent = 0.8*DayRent*days;
	  	}else if(days>150){
	  		return Rent = 0.7*DayRent*days;
	  	}else{
	  		return Rent = DayRent*days;
	  	}
   }
}
