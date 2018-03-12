package CarRental;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import org.dom4j.Document;
import org.dom4j.Element;

/**
 * 汽车的业务方法
 * 
 * @author Administrator
 */
public class CarService {
	public static final String CARS_XML = "libs/Cars.xml";
	public Document doc = XmlUtils.getDocument(new File(CARS_XML));;
	Scanner sc = new Scanner(System.in);

	public CarService() {
		doc = XmlUtils.getDocument(new File(CARS_XML));
	}

	/**
	 * TODO 租车业务
	 */
	public void rent(String id, String user_name) {
		Element e = find(id);// 通过id在车库中找到车辆
		Car car = findCar(e);// 判断车辆类型返回对应类型
		System.out.println("请输入要租车的天数:");
		int days = sc.nextInt();
		String calRent = car.calRent(days) + "";
		System.out.println("需要交付租金为:" + calRent);
		XmlUtils.ChangeState(id, "已租", calRent, user_name);
	}

	private Element find(String id) {
		String xpath_car = "//car[@id='" + id + "']";
		Element e_car = (Element) doc.selectSingleNode(xpath_car);
		return e_car;
	}

	// TODO 通过id判断类型返回汽车
	private Car findCar(Element e_car) {

		if (e_car.attributeValue("lx").equals("轿车")) {
			return new SmallCar(e_car);
		} else if (e_car.attributeValue("lx").equals("客车")) {
			return new Bus(e_car);
		}
		return null;
	}

	/**
	 * TODO 还车业务
	 */
	public void back(String id, String user_name) {
		List<Element> l = doc.selectNodes("/cars/car");
		for (Element e : l) {
			if (e.attributeValue("id").equals(id)) {
				System.out.println("你应该缴纳租金:" + e.attributeValue("calRent"));
				XmlUtils.ChangeState(id, "未租", "0", user_name);
			}
		}

	}

	/*
	 * 用户查看汽车资料
	 */
	public void disAll_user() {
		List<Element> l = doc.selectNodes("/cars/car");
		String str = null;
		System.out.println("编号\t车型\t品牌\t车牌号\t\t日租金\t状态");
		for (Element e : l) {
			str = e.attributeValue("id");
			str += "\t" + e.attributeValue("lx");
			str += "\t" + e.attributeValue("brand");
			str += "\t" + e.attributeValue("num");
			str += "\t" + e.attributeValue("dailyHire");
			str += "\t" + e.attributeValue("state");
			System.out.println(str);
		}
	}

	/*
	 * 显示所有的车辆信息(管理员查看)
	 */
	public void disAll() {
		List<Element> l = doc.selectNodes("/cars/car");
		String str = null;
		System.out.println("编号\t车型\t品牌\t车牌号\t\t备注\t日租金\t状态\t租车人\t租金");
		for (Element e : l) {
			str = e.attributeValue("id");
			str += "\t" + e.attributeValue("lx");
			str += "\t" + e.attributeValue("brand");
			str += "\t" + e.attributeValue("num");
			str += "\t" + e.attributeValue("type");
			str += "\t" + e.attributeValue("dailyHire");
			str += "\t" + e.attributeValue("state");
			str += "\t" + e.attributeValue("user");
			str += "\t" + e.attributeValue("calRent");
			System.out.println(str);
		}
	}

	public void AddCar() {
		Element root = doc.getRootElement();
		Element e = root.addElement("car");
		System.out.println("请输入汽车品牌:");
		String brand = sc.next();
		System.out.println("请输入汽车型号(型号/座位数):");
		String type = sc.next();
		System.out.println("请输入汽车类型(轿车,客车):");
		String lx = sc.next();
		System.out.println("请输入汽车日租金:");
		String dailyHire = sc.next();
		System.out.println("请输入车牌号:");
		String num = sc.next();
		e.addAttribute("id",
				String.valueOf(System.currentTimeMillis() / 100000));
		e.addAttribute("dailyHire", dailyHire);
		e.addAttribute("type", type);
		e.addAttribute("brand", brand);
		e.addAttribute("num", num);
		e.addAttribute("lx", lx);
		XmlUtils.save(doc, new File(CARS_XML));
		System.out.println("保存成功!!");
	}

	public void DelCar() {
		// 首先要录入用户的账号
		disAll();
		System.out.println("请输入要删除的车辆id：");
		String id = sc.next();
		// 找到该账号对应的用户信息的节点
		String xpath = "//car[@id='"+ id +"']";
		List<Element> list = doc.selectNodes(xpath);
		if (list.size() == 1) {
			Element e = list.get(0);
			e.getParent().remove(e);// 删除节点
			// 保存
			XmlUtils.save(doc, new File(CARS_XML));
			System.out.println("删除车辆成功！！！！");
		} else if (list.size() > 1) {
			System.out.println("发生系统错误，请联系系统开发商");
		} else {
			System.out.println("没有找见id为：" + id + "的车辆");
		}
	}

	public static void main(String[] args) {
		CarService s = new CarService();
		// s.rent("005","avo");
		// s.disAll();
		s.DelCar();

	}
}
