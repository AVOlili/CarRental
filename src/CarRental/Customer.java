package CarRental;

/**
 * 
 * @author Administrator
 * @since 2017-08-03
 *
 */
public class Customer extends Person {
	CarService s = new CarService();
	PersonService p = new PersonService();// �����û��������
	public Customer() {
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param realName
	 * @param pw
	 * @param phone
	 */
	public Customer(String id, String name, String realName, String pw, String phone) {
		super(id, name, realName, pw, phone);
	}

	public static final String TYPE = "2";// ����2��ʾ�û�

	public static void dis() {

		System.out.println();
	}

	/**
	 * ��ʾ��Ա����
	 * 
	 */

	@Override
	public void disFun(String name) {
		while (true) {
			System.out.println("---��Ա����---");
			System.out.println("1.�鿴������Ϣ");
			System.out.println("2.��������");
			System.out.println("3.�����û���");
			System.out.println("4.�鿴�㵱ǰ���⳵��");
			System.out.println("0.�˳�");
			System.out.println("����������ѡ��");
			switch (sc.nextInt()) {
			case 1:
				s.disAll_user();
				break;
			case 2:
				p.changePw();// ��������
				break;
			case 3:
				p.changeName();// �����û���
				break;
			case 4:
				String username = name;
				p.checkCar(username);// �鿴�󶨵ĳ�
				break;
			default:
				System.out.println("���ڷ�����һ��>>>>>");
				break;
			}
			break;
		}
	}
}
