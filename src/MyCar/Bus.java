package MyCar;

public class Bus extends Car{
	public String Seat;//��λ
	public Bus(String brand, String number, double dayRent,String Seat) {
		super(brand,number,dayRent);
		this.Seat = Seat;
	}
	Bus b1 = new Bus("��","��6566754",800,"16��");
	Bus b2 = new Bus("����","��8696997",800,"16��");
	Bus b3 = new Bus("��","��9696996",1500,"32��");
	Bus b4 = new Bus("����","��8696998",1500,"32��");
	
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
