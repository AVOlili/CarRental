package CarRental;

/**
 * 汽车类
 * @author Administrator
 *
 */
public abstract class Car {
	// 车牌号
	String num = null;
	// 品牌
	String brand;
	// 日租金
	int dailyHire;

	/**
	 * 计算租金
	 * @param days
	 * @return
	 */
	public abstract double calRent(int days);
}
