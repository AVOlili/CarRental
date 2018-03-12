package MyCar;

public class Bus extends Car{
	public String Seat;//座位
	public Bus(String brand, String number, double dayRent,String Seat) {
		super(brand,number,dayRent);
		this.Seat = Seat;
	}
	Bus b1 = new Bus("金杯","京6566754",800,"16座");
	Bus b2 = new Bus("金龙","京8696997",800,"16座");
	Bus b3 = new Bus("金杯","京9696996",1500,"32座");
	Bus b4 = new Bus("金龙","京8696998",1500,"32座");
	
	@Override
  	public double calRent(int days){
	  	if(days>=3&&days<7){
	  		return Rent = 0.9*DayRent*days;
	  	}else if(days>=7&&days<30){
	  		return Rent = 0.8*DayRent*days;
	  	}else if(days>=30){
	  		return Rent = 0.7*DayRent*days;
	  	}else if(days>=150){
	  		return Rent = 0.6*DayRent*days;
 		}else{
	  		return Rent = DayRent*days;
	  	}
 }

}
