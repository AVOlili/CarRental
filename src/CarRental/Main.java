package CarRental;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String select="";
		CarSystem cs =new CarSystem("��ӭ�⳵");
		do{
			//��ʾ��ӭ����
			cs.disWelcom();
			System.out.println("���������ѡ��");
			select =sc.next();
			if("1".equals(select)){
				//ע��
				cs.regist();
			}else if("2".equals(select)){
				//��½ϵͳ
				cs.login();
			}else if("3".equals(select)){
				System.out.println("�˳�ϵͳ");
				break;
			}else{
				System.out.println("�������ѡ������ڣ���������");
			}
		
		}while(true);
		
	}

}
