package CarRental;

public class Admin extends Person {
	public Admin(String id, String name, String realName, String pw,
			String phone) {
		super(id, name, realName, pw, phone);
	}

	public Admin() {
	}

	public static final String TYPE = "1";// 表示系统管理员
	PersonService p = new PersonService();// 创建用户管理对象

	@Override
	public void disFun(String name) {
		while (true) {
			System.out.println("---系统管理员功能---");
			CarService c = new CarService();
			PersonService p = new PersonService();
			System.out.println("1.会员管理");
			System.out.println("2.汽车租赁");
			System.out.println("3.汽车归还");
			System.out.println("4.汽车管理");
			System.out.println("0.退出");
			System.out.println("请输入您的选择：");
			int x = sc.nextInt();// scanner的包在前面person已经定义
			switch (x) {
			case 1:
				// TODO 会员管理
				System.out.println("<<<<欢迎进入会员管理界面>>>>");
				System.out.println("1.查看会员");
				System.out.println("2.删除会员");
				System.out.println("3.重置密码");
				System.out.println("0.退出");
				System.out.println("请输入您的选择：");
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
					System.out.println("<<<<<<正在返回主界面");
					break;
				}
				break;
			case 2:
				System.out.println("<<<<<开始租赁>>>>>");

				c.disAll();
				// 检测是否存在此用户
				System.out.println("请问客户您的id:");
				String id_0 = sc.next();
				boolean s = PersonService.isExist_id(id_0);
				if (s == false) {
					System.out.println("输入错误,从新验证>>>>>>");
					break;
				} else {
					System.out.println("id验证成功,进行下一步>>>>>>>");
					System.out.println("请问客户您的真实名字:");
					String user_name = sc.next();
					System.out.println("请输入要租车编号:");
					String id = sc.next();
					c.rent(id, user_name);
					p.addCarToUser(id_0, id);
					break;
				}
			case 3:
				// 归还
				// 检测是否存在此用户
				System.out.println("请问客户的个人唯一id:");
				String id_1 = sc.next();
				boolean s_1 = PersonService.isExist_id(id_1);
				if (s_1 == false) {
					System.out.println("id错误,请从输入");
					break;
				} else {
					System.out.println("id验证成功,进行下一步>>>>>>>");
					System.out.println("请输入要归还的车编号:");
					String id2 = sc.next();
					c.back(id2, "暂无");
				}
				break;
			case 4:
				// TODO 车辆管理(增减)
				while (true) {
					System.out.println("车辆管理1.增加车辆,2.删除车辆0.退出");
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
				System.out.println("管理员退出");
				break;
			}
			break;
		}
	}
}
