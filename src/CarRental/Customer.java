package CarRental;

/**
 * 
 * @author Administrator
 * @since 2017-08-03
 *
 */
public class Customer extends Person {
	CarService s = new CarService();
	PersonService p = new PersonService();// 创建用户管理对象
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

	public static final String TYPE = "2";// 类型2表示用户

	public static void dis() {

		System.out.println();
	}

	/**
	 * 显示会员功能
	 * 
	 */

	@Override
	public void disFun(String name) {
		while (true) {
			System.out.println("---会员功能---");
			System.out.println("1.查看车辆信息");
			System.out.println("2.更改密码");
			System.out.println("3.更改用户名");
			System.out.println("4.查看你当前所租车辆");
			System.out.println("0.退出");
			System.out.println("请输入您的选择：");
			switch (sc.nextInt()) {
			case 1:
				s.disAll_user();
				break;
			case 2:
				p.changePw();// 更改密码
				break;
			case 3:
				p.changeName();// 更改用户名
				break;
			case 4:
				String username = name;
				p.checkCar(username);// 查看绑定的车
				break;
			default:
				System.out.println("正在返回上一级>>>>>");
				break;
			}
			break;
		}
	}
}
