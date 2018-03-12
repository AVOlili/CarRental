package CarRental;

import org.dom4j.Element;

/**
 * �γ�
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
	private String type;//�ͺ�
	
	

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
			//9��
			zk = 0.9;
		}else if(days>30&&days<=150){
			//8��
			zk = 0.8;
		}else if(days>150){
			//7��
			zk = 0.7;
		}
		return days*super.dailyHire*zk;
	}
	
	
}
