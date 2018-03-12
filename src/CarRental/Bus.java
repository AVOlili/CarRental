package CarRental;

import org.dom4j.Element;

/**
 * �ͳ���
 * @author Administrator
 *
 */
public class Bus extends Car {
	
	public String type;//��λ��

	public Bus(){
		
	}

	public Bus(Element e_car) {
		// TODO Auto-generated constructor stub
		this.type=e_car.attributeValue("type");
		this.dailyHire = Integer.parseInt(e_car.attributeValue("dailyHire"));
	}


	@Override
	public double calRent(int days) {
		double zk=1.0;
		if(days>=3&&days<7){
			//9��
			zk = 0.9;
		}else if(days>=7&&days<30){
			//8��
			zk = 0.8;
		}else if(days>=30&&days<150){
			//7��
			zk = 0.7;
		}else if(days>=150){
			//6��
			zk = 0.6;
		}
		return days*super.dailyHire*zk;
	}
	
	
	
}
