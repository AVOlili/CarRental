package CarRental;

public class Admin extends Person {
	public Admin(String id, String name, String realName, String pw,
			String phone) {
		super(id, name, realName, pw, phone);
	}

	public Admin() {
	}

	public static final String TYPE = "1";// ��ʾϵͳ����Ա
	PersonService p = new PersonService();// �����û��������

	@Override
	public void disFun(String name) {
		while (true) {
			System.out.println("---ϵͳ����Ա����---");
			CarService c = new CarService();
			PersonService p = new PersonService();
			System.out.println("1.��Ա����");
			System.out.println("2.��������");
			System.out.println("3.�����黹");
			System.out.println("4.��������");
			System.out.println("0.�˳�");
			System.out.println("����������ѡ��");
			int x = sc.nextInt();// scanner�İ���ǰ��person�Ѿ�����
			switch (x) {
			case 1:
				// TODO ��Ա����
				System.out.println("<<<<��ӭ�����Ա�������>>>>");
				System.out.println("1.�鿴��Ա");
				System.out.println("2.ɾ����Ա");
				System.out.println("3.��������");
				System.out.println("0.�˳�");
				System.out.println("����������ѡ��");
				switch (sc.nextInt()) {
				case 1:
					p.disAll();
					break;
				case 2:
					p.del_User();
					break;
				case 3:
					p.goRestPw();
					break;
				default:
					System.out.println("<<<<<<���ڷ���������");
					break;
				}
				break;
			case 2:
				System.out.println("<<<<<��ʼ����>>>>>");

				c.disAll();
				// ����Ƿ���ڴ��û�
				System.out.println("���ʿͻ�����id:");
				String id_0 = sc.next();
				boolean s = PersonService.isExist_id(id_0);
				if (s == false) {
					System.out.println("�������,������֤>>>>>>");
					break;
				} else {
					System.out.println("id��֤�ɹ�,������һ��>>>>>>>");
					System.out.println("���ʿͻ�������ʵ����:");
					String user_name = sc.next();
					System.out.println("������Ҫ�⳵���:");
					String id = sc.next();
					c.rent(id, user_name);
					p.addCarToUser(id_0, id);
					break;
				}
			case 3:
				// �黹
				// ����Ƿ���ڴ��û�
				System.out.println("���ʿͻ��ĸ���Ψһid:");
				String id_1 = sc.next();
				boolean s_1 = PersonService.isExist_id(id_1);
				if (s_1 == false) {
					System.out.println("id����,�������");
					break;
				} else {
					System.out.println("id��֤�ɹ�,������һ��>>>>>>>");
					System.out.println("������Ҫ�黹�ĳ����:");
					String id2 = sc.next();
					c.back(id2, "����");
				}
				break;
			case 4:
				// TODO ��������(����)
				while (true) {
					System.out.println("��������1.���ӳ���,2.ɾ������0.�˳�");
					String temp = sc.next();
					if (temp.equals("1")) {
						c.AddCar();
					} else if (temp.equals("2")) {
						c.DelCar();
					} else {
						break;
					}
				}
				break;
			default:
				System.out.println("����Ա�˳�");
				break;
			}
			break;
		}
	}
}
