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
		System.out.println("用户名\t姓名\t\t手机号\t\tID编号");
		for (Element e : l) {
			str = e.attributeValue("name");
			str += "\t" + e.attributeValue("realName");
			str += "\t\t" + e.attributeValue("phone");
			str += "\t" + e.attributeValue("id");
			System.out.println(str);
		}
	}

	// TODO 删除用户
	public void del_User() {
		// 首先要录入用户的账号
		disAll();
		System.out.println("请输入要删除的用户账号id：");
		String id = sc.next();
		// 找到该账号对应的用户信息的节点
		String xpath = "//user[@id='" + id + "' and @type='2']";
		List<Element> list = userDoc.selectNodes(xpath);
		if (list.size() == 1) {
			Element e = list.get(0);
			e.getParent().remove(e);// 删除节点
			// 保存
			XmlUtils.save(userDoc, new File(USERS_XML));
			System.out.println("删除用户成功！！！！");
		} else if (list.size() > 1) {
			System.out.println("发生系统错误，请联系系统开发商");
		} else {
			//
			System.out.println("没有找见账号为：" + id + "用户");
		}
	}

	/**
	 * 重置用户密码
	 */
	public void goRestPw() {
		// 录入用户的账号
		System.out.println("请输入用户的ID账号：");
		String id = sc.next();
		// 根据用户的账号去查询用户(节点)
		String xpath = "//user[@id='" + id + "' and @type='2']";
		List<Element> list = userDoc.selectNodes(xpath);
		System.out.println("......." + list.size());
		if (list.size() == 1) {
			Element ue = list.get(0);
			// 修改密码
			System.out.println("修改前密码：" + ue.attributeValue("password"));
			ue.addAttribute("password", "123456");
			// 保存
			XmlUtils.save(userDoc, new File(USERS_XML));
			System.out.println("修改后密码：" + ue.attributeValue("password"));
			System.out.println("密码重置成功(初始密码123456)！！！");
		} else if (list.size() > 1) {
			System.out.println("发生系统错误，请联系厂商！！");
		} else {
			System.out.println("没有找到账号：" + id + "的用户信息");
		}
	}

	public void changePw() {
		// 首先要录入用户的账号
		System.out.println("请输入您的账户id：");
		String id = sc.next();
		// 找到该账号对应的用户信息的节点
		String xpath = "//user[@id='" + id + "' and @type='2']";
		List<Element> list = userDoc.selectNodes(xpath);
		if (list.size() == 1) {
			Element e = list.get(0);
			System.out.println("请输入你原密码：");
			while (true) {
				if (sc.next().equals(e.attributeValue("password"))) {
					System.out.println("验证成功，请输入新密码");
					e.addAttribute("password", sc.next());
					System.out.println("更改密码成功");
					XmlUtils.save(userDoc, new File(USERS_XML));
					break;
				} else {
					System.out.println("验证失败，请重新输入原密码：");
				}
			}

		} else if (list.size() > 1) {
			System.out.println("发生系统错误，请联系系统开发商");
		} else {
			//
			System.out.println("没有到id为：" + id + "用户");
		}
	}
	public void changeName() {
		// 首先要录入用户的账号
		System.out.println("请输入您的账户id：");
		String id = sc.next();
		// 找到该账号对应的用户信息的节点
		String xpath = "//user[@id='" + id + "' and @type='2']";
		List<Element> list = userDoc.selectNodes(xpath);
		if (list.size() == 1) {
			Element e = list.get(0);
			System.out.println("请输入你原密码：");
			while (true) {
				if (sc.next().equals(e.attributeValue("password"))) {
					System.out.println("验证成功，请输入新用户名");
					e.addAttribute("name", sc.next());
					System.out.println("更改用户名成功");
					XmlUtils.save(userDoc, new File(USERS_XML));
					break;
				} else {
					System.out.println("验证失败，请重新输入原密码：");
				}
			}

		} else if (list.size() > 1) {
			System.out.println("发生系统错误，请联系系统开发商");
		} else {
			//
			System.out.println("没有到id为：" + id + "用户");
		}
	}
	public void checkCar(String name) {
		String xpath = "//user[@name='" + name + "' and @type='2']";
		List<Element> list = userDoc.selectNodes(xpath);
		Element e = list.get(0);
		if (list.size() == 1) {
			System.out.print("你现在所租的车:\n"+e.attributeValue("car"));
			System.out.println("\t租金:"+e.attributeValue("calRent"));
			}
		} 
	
	//添加汽车信息到用户
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
