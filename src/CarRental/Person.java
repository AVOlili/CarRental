package CarRental;

import java.util.Scanner;

public abstract class Person {
	
	public static Scanner sc = new Scanner(System.in);

	private String id;//ID��
	private String name;//�û���
	private String realName;//��ʵ����
	private String password;//����
	private	String phone;//�绰
	
	public Person(){}
	/**
	 * @param id
	 * @param name
	 * @param realName
	 * @param password
	 * @param phone
	 */
	public Person(String id, String name, String realName, String password,
			String phone) {
		super();
		this.id = id;
		this.name = name;
		this.realName = realName;
		this.password = password;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public abstract void disFun(String name);
	
	
}
