package CarRental;

/**
 * ������
 * @author Administrator
 *
 */
public abstract class Car {
	// ���ƺ�
	String num = null;
	// Ʒ��
	String brand;
	// �����
	int dailyHire;

	/**
	 * �������
	 * @param days
	 * @return
	 */
	public abstract double calRent(int days);
}
