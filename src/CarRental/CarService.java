package CarRental;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import org.dom4j.Document;
import org.dom4j.Element;

/**
 * ������ҵ�񷽷�
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
	 * TODO �⳵ҵ��
	 */
	public void rent(String id, String user_name) {
		Element e = find(id);// ͨ��id�ڳ������ҵ�����
		Car car = findCar(e);// �жϳ������ͷ��ض�Ӧ����
		System.out.println("������Ҫ�⳵������:");
		int days = sc.nextInt();
		String calRent = car.calRent(days) + "";
		System.out.println("��Ҫ�������Ϊ:" + calRent);
		XmlUtils.ChangeState(id, "����", calRent, user_name);
	}

	private Element find(String id) {
		String xpath_car = "//car[@id='" + id + "']";
		Element e_car = (Element) doc.selectSingleNode(xpath_car);
		return e_car;
	}

	// TODO ͨ��id�ж����ͷ�������
	private Car findCar(Element e_car) {

		if (e_car.attributeValue("lx").equals("�γ�")) {
			return new SmallCar(e_car);
		} else if (e_car.attributeValue("lx").equals("�ͳ�")) {
			return new Bus(e_car);
		}
		return null;
	}

	/**
	 * TODO ����ҵ��
	 */
	public void back(String id, String user_name) {
		List<Element> l = doc.selectNodes("/cars/car");
		for (Element e : l) {
			if (e.attributeValue("id").equals(id)) {
				System.out.println("��Ӧ�ý������:" + e.attributeValue("calRent"));
				XmlUtils.ChangeState(id, "δ��", "0", user_name);
			}
		}

	}

	/*
	 * �û��鿴��������
	 */
	public void disAll_user() {
		List<Element> l = doc.selectNodes("/cars/car");
		String str = null;
		System.out.println("���\t����\tƷ��\t���ƺ�\t\t�����\t״̬");
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
	 * ��ʾ���еĳ�����Ϣ(����Ա�鿴)
	 */
	public void disAll() {
		List<Element> l = doc.selectNodes("/cars/car");
		String str = null;
		System.out.println("���\t����\tƷ��\t���ƺ�\t\t��ע\t�����\t״̬\t�⳵��\t���");
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
		System.out.println("����������Ʒ��:");
		String brand = sc.next();
		System.out.println("�����������ͺ�(�ͺ�/��λ��):");
		String type = sc.next();
		System.out.println("��������������(�γ�,�ͳ�):");
		String lx = sc.next();
		System.out.println("���������������:");
		String dailyHire = sc.next();
		System.out.println("�����복�ƺ�:");
		String num = sc.next();
		e.addAttribute("id",
				String.valueOf(System.currentTimeMillis() / 100000));
		e.addAttribute("dailyHire", dailyHire);
		e.addAttribute("type", type);
		e.addAttribute("brand", brand);
		e.addAttribute("num", num);
		e.addAttribute("lx", lx);
		XmlUtils.save(doc, new File(CARS_XML));
		System.out.println("����ɹ�!!");
	}

	public void DelCar() {
		// ����Ҫ¼���û����˺�
		disAll();
		System.out.println("������Ҫɾ���ĳ���id��");
		String id = sc.next();
		// �ҵ����˺Ŷ�Ӧ���û���Ϣ�Ľڵ�
		String xpath = "//car[@id='"+ id +"']";
		List<Element> list = doc.selectNodes(xpath);
		if (list.size() == 1) {
			Element e = list.get(0);
			e.getParent().remove(e);// ɾ���ڵ�
			// ����
			XmlUtils.save(doc, new File(CARS_XML));
			System.out.println("ɾ�������ɹ���������");
		} else if (list.size() > 1) {
			System.out.println("����ϵͳ��������ϵϵͳ������");
		} else {
			System.out.println("û���Ҽ�idΪ��" + id + "�ĳ���");
		}
	}

	public static void main(String[] args) {
		CarService s = new CarService();
		// s.rent("005","avo");
		// s.disAll();
		s.DelCar();

	}
}
