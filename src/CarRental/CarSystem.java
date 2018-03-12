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

		System.out.println("1.注册成为会员");
		System.out.println("2.登陆");
		System.out.println("3.退出系统");
	}

	public void regist() {
		while (true) {
			System.out.println("----注册-----");
			// 提示输入用户 真是姓名、联系电话、输入密码 确认密码
			String un = null, realName = null, phone = null, pw1 = null, pw2 = null, type = null;
			System.out.println("请输入用户名");
			un = sc.next();
			System.out.println("请输入真实姓名");
			realName = sc.next();
			System.out.println("请输入联系电话");
			phone = sc.next();
			System.out.println("请输入类型1.管理员 2.用户");
			type = sc.next();
			while (true) {
				System.out.println("请输入密码");
				pw1 = sc.next();
				System.out.println("请输入确认密码");
				pw2 = sc.next();
				// 验证两次输入密码是否相同
				if (!pw2.equals(pw1)) {
					System.out.println("两次输入的密码不同");
				} else {
					break;
				}
			}
			// 验证用户名是否已经存在
			if (!PersonService.isExist(un)) {
				Customer c = new Customer(String.valueOf(System.currentTimeMillis()/100000), un, realName, pw1, phone);
				// 把信息保存user.xml中
				save(c);
				System.out.println("用户注册成功！！！！");
				break;
			} else {
				System.out.println("已经存在系统名称的用户,继续请输入0;否则返回主界面");
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
			System.out.println("----登陆----");
			String un, pw;
			System.out.println("请输入用户名：");
			un = sc.next();
			System.out.println("请输入密码：");
			pw = sc.next();

			Person p = findPerson(un, pw);
			if (p == null) {
				System.out.println("用户名或者密码错误！");
				System.out.println("继续,请输入0;否则返回系统主界面");
				if (!"0".equals(sc.next())) {
					break;
				}
			} else {
				System.out.println("验证成功.................");
				// 登陆后的主要功能
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
