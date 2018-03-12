package CarRental;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import org.dom4j.Document;
import org.dom4j.Element;

public class PersonService {
	private String name;
	public Scanner sc = new Scanner(System.in);
	public static final String USERS_XML = "libs/Users.xml";
	static Document userDoc = XmlUtils.getDocument(new File(USERS_XML));
	public static final String CARS_XML="libs/Cars.xml";
	public static Document doc = XmlUtils.getDocument(new File (CARS_XML));
	public static boolean isExist(String name) {
		List l = userDoc.selectNodes("//user[@name='" + name + "']");
		if (l.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isExist_id(String id) {
		List l = userDoc.selectNodes("//user[@id='" + id + "']");
		if (l.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void disAll() {
		List<Element> l = userDoc.selectNodes("/users/user");
		String str = null;
		System.out.println("�û���\t����\t\t�ֻ���\t\tID���");
		for (Element e : l) {
			str = e.attributeValue("name");
			str += "\t" + e.attributeValue("realName");
			str += "\t\t" + e.attributeValue("phone");
			str += "\t" + e.attributeValue("id");
			System.out.println(str);
		}
	}

	// TODO ɾ���û�
	public void del_User() {
		// ����Ҫ¼���û����˺�
		disAll();
		System.out.println("������Ҫɾ�����û��˺�id��");
		String id = sc.next();
		// �ҵ����˺Ŷ�Ӧ���û���Ϣ�Ľڵ�
		String xpath = "//user[@id='" + id + "' and @type='2']";
		List<Element> list = userDoc.selectNodes(xpath);
		if (list.size() == 1) {
			Element e = list.get(0);
			e.getParent().remove(e);// ɾ���ڵ�
			// ����
			XmlUtils.save(userDoc, new File(USERS_XML));
			System.out.println("ɾ���û��ɹ���������");
		} else if (list.size() > 1) {
			System.out.println("����ϵͳ��������ϵϵͳ������");
		} else {
			//
			System.out.println("û���Ҽ��˺�Ϊ��" + id + "�û�");
		}
	}

	/**
	 * �����û�����
	 */
	public void goRestPw() {
		// ¼���û����˺�
		System.out.println("�������û���ID�˺ţ�");
		String id = sc.next();
		// �����û����˺�ȥ��ѯ�û�(�ڵ�)
		String xpath = "//user[@id='" + id + "' and @type='2']";
		List<Element> list = userDoc.selectNodes(xpath);
		System.out.println("......." + list.size());
		if (list.size() == 1) {
			Element ue = list.get(0);
			// �޸�����
			System.out.println("�޸�ǰ���룺" + ue.attributeValue("password"));
			ue.addAttribute("password", "123456");
			// ����
			XmlUtils.save(userDoc, new File(USERS_XML));
			System.out.println("�޸ĺ����룺" + ue.attributeValue("password"));
			System.out.println("�������óɹ�(��ʼ����123456)������");
		} else if (list.size() > 1) {
			System.out.println("����ϵͳ��������ϵ���̣���");
		} else {
			System.out.println("û���ҵ��˺ţ�" + id + "���û���Ϣ");
		}
	}

	public void changePw() {
		// ����Ҫ¼���û����˺�
		System.out.println("�����������˻�id��");
		String id = sc.next();
		// �ҵ����˺Ŷ�Ӧ���û���Ϣ�Ľڵ�
		String xpath = "//user[@id='" + id + "' and @type='2']";
		List<Element> list = userDoc.selectNodes(xpath);
		if (list.size() == 1) {
			Element e = list.get(0);
			System.out.println("��������ԭ���룺");
			while (true) {
				if (sc.next().equals(e.attributeValue("password"))) {
					System.out.println("��֤�ɹ���������������");
					e.addAttribute("password", sc.next());
					System.out.println("��������ɹ�");
					XmlUtils.save(userDoc, new File(USERS_XML));
					break;
				} else {
					System.out.println("��֤ʧ�ܣ�����������ԭ���룺");
				}
			}

		} else if (list.size() > 1) {
			System.out.println("����ϵͳ��������ϵϵͳ������");
		} else {
			//
			System.out.println("û�е�idΪ��" + id + "�û�");
		}
	}
	public void changeName() {
		// ����Ҫ¼���û����˺�
		System.out.println("�����������˻�id��");
		String id = sc.next();
		// �ҵ����˺Ŷ�Ӧ���û���Ϣ�Ľڵ�
		String xpath = "//user[@id='" + id + "' and @type='2']";
		List<Element> list = userDoc.selectNodes(xpath);
		if (list.size() == 1) {
			Element e = list.get(0);
			System.out.println("��������ԭ���룺");
			while (true) {
				if (sc.next().equals(e.attributeValue("password"))) {
					System.out.println("��֤�ɹ������������û���");
					e.addAttribute("name", sc.next());
					System.out.println("�����û����ɹ�");
					XmlUtils.save(userDoc, new File(USERS_XML));
					break;
				} else {
					System.out.println("��֤ʧ�ܣ�����������ԭ���룺");
				}
			}

		} else if (list.size() > 1) {
			System.out.println("����ϵͳ��������ϵϵͳ������");
		} else {
			//
			System.out.println("û�е�idΪ��" + id + "�û�");
		}
	}
	public void checkCar(String name) {
		String xpath = "//user[@name='" + name + "' and @type='2']";
		List<Element> list = userDoc.selectNodes(xpath);
		Element e = list.get(0);
		if (list.size() == 1) {
			System.out.print("����������ĳ�:\n"+e.attributeValue("car"));
			System.out.println("\t���:"+e.attributeValue("calRent"));
			}
		} 
	
	//���������Ϣ���û�
	public void addCarToUser(String userid,String carid) {
		String xpath = "//user[@id='" + userid + "' and @type='2']";
		Element e = (Element) userDoc.selectSingleNode(xpath);
		String xpath_car = "//car[@id='"+carid+"']";
		Element e_car = (Element)doc.selectSingleNode(xpath_car);
		String car = e_car.attributeValue("brand");
		car += "\t"+e_car.attributeValue("num");
		String rent = e_car.attributeValue("calRent");
		e.addAttribute("car", car);
		e.addAttribute("calRent",rent);
		XmlUtils.save(userDoc, new File(USERS_XML));
	} 
	
	public static void main(String[] args) {
		PersonService p = new PersonService();
//		p.del_User();
//	    p.goRestPw();
//		p.changePw();
		p.changeName();
	}
}
