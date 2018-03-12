package CarRental;

import org.dom4j.Element;

/**
 * ½Î³µ
 * @author AVOli
 *
 */
public class SmallCar extends Car {

	public SmallCar(){
		
	}
	
	public SmallCar(Element e_car) {
		this.type=e_car.attributeValue("type");
		this.dailyHire = Integer.parseInt(e_car.attributeValue("dailyHire"));
	}
	private String type;//ĞÍºÅ
	
	

	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	@Override
	public double calRent(int days) {
		double zk=1.0;
		if(days>7&&days<=30){
			//9ÕÛ
			zk = 0.9;
		}else if(days>30&&days<=150){
			//8ÕÛ
			zk = 0.8;
		}else if(days>150){
			//7ÕÛ
			zk = 0.7;
		}
		return days*super.dailyHire*zk;
	}
	
	
}
