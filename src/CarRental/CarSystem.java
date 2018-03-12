package CarRental;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.Element;

public class CarSystem {
	private String name;
	public Scanner sc = new Scanner(System.in);
	public static final String USERS_XML = "libs/Users.xml";
	Document userDoc = null;

	public CarSystem(String name) {
		this.name = name;
		userDoc = XmlUtils.getDocument(new File(USERS_XML));
	}

	public boolean validateUser(String name, String pw) {
		boolean re = false;

		return re;
	}

	public void disWelcom() {
		System.out.print("--------");
		System.out.print(this.name);
		System.out.println("--------");

		System.out.println("1.ע���Ϊ��Ա");
		System.out.println("2.��½");
		System.out.println("3.�˳�ϵͳ");
	}

	public void regist() {
		while (true) {
			System.out.println("----ע��-----");
			// ��ʾ�����û� ������������ϵ�绰���������� ȷ������
			String un = null, realName = null, phone = null, pw1 = null, pw2 = null, type = null;
			System.out.println("�������û���");
			un = sc.next();
			System.out.println("��������ʵ����");
			realName = sc.next();
			System.out.println("��������ϵ�绰");
			phone = sc.next();
			System.out.println("����������1.����Ա 2.�û�");
			type = sc.next();
			while (true) {
				System.out.println("����������");
				pw1 = sc.next();
				System.out.println("������ȷ������");
				pw2 = sc.next();
				// ��֤�������������Ƿ���ͬ
				if (!pw2.equals(pw1)) {
					System.out.println("������������벻ͬ");
				} else {
					break;
				}
			}
			// ��֤�û����Ƿ��Ѿ�����
			if (!PersonService.isExist(un)) {
				Customer c = new Customer(String.valueOf(System.currentTimeMillis()/100000), un, realName, pw1, phone);
				// ����Ϣ����user.xml��
				save(c);
				System.out.println("�û�ע��ɹ���������");
				break;
			} else {
				System.out.println("�Ѿ�����ϵͳ���Ƶ��û�,����������0;���򷵻�������");
				if (!"0".equals(sc.next())) {
					break;
				}
			}
		}
	}

	private void save(Person p) {
		Document doc = XmlUtils.getDocument(new File(USERS_XML));
		Element root = doc.getRootElement();
		Element u = root.addElement("user");
		u.addAttribute("id", p.getId());
		u.addAttribute("name", p.getName());
		u.addAttribute("realName", p.getRealName());
		u.addAttribute("password", p.getPassword());
		u.addAttribute("phone", p.getPhone());
		if (p instanceof Customer) {
			u.addAttribute("type", "2");
		} else {
			u.addAttribute("type", "1");
		}
		XmlUtils.save(doc, new File(USERS_XML));
	}

	public void login() {
		while (true) {
			System.out.println("----��½----");
			String un, pw;
			System.out.println("�������û�����");
			un = sc.next();
			System.out.println("���������룺");
			pw = sc.next();

			Person p = findPerson(un, pw);
			if (p == null) {
				System.out.println("�û��������������");
				System.out.println("����,������0;���򷵻�ϵͳ������");
				if (!"0".equals(sc.next())) {
					break;
				}
			} else {
				System.out.println("��֤�ɹ�.................");
				// ��½�����Ҫ����
				p.disFun(un);
				break;
			}
		}
	}

	private Person findPerson(String un, String pw) {
		Element root = userDoc.getRootElement();
		String id = "", realName = "", phone = "", type = "";
		Person p = null;
		Element e = (Element) root.selectSingleNode("//user[@name='" + un
				+ "' and @password='" + pw + "']");
		if (e != null) {
			type = e.attributeValue("type");
			id = e.attributeValue("id");
			realName = e.attributeValue("realName");
			phone = e.attributeValue("phone");
			if ("2".equals(type)) {
				p = new Customer(id, name, realName, pw, phone);
			} else {
				p = new Admin(id, name, realName, pw, phone);
			}
		}

		return p;
	}


	
}
