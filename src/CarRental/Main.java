package CarRental;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String select="";
		CarSystem cs =new CarSystem("欢迎租车");
		do{
			//显示欢迎界面
			cs.disWelcom();
			System.out.println("请输入你的选择：");
			select =sc.next();
			if("1".equals(select)){
				//注册
				cs.regist();
			}else if("2".equals(select)){
				//登陆系统
				cs.login();
			}else if("3".equals(select)){
				System.out.println("退出系统");
				break;
			}else{
				System.out.println("您输入的选择项不存在，重新输入");
			}
		
		}while(true);
		
	}

}
