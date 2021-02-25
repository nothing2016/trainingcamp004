package class03;

/**
 * 给定一个数组arr, 其中一个数出现了一次，其他的数都出现了K次，求这个数
 * 要求，使用的空间复杂度为O(1)
 *
 * 题解：使用K进制的异或
 */
public class Code04_KTimesOneTime {

	public static int onceNum(int[] arr, int k) {
		int[] eO = new int[32];
		for (int i = 0; i != arr.length; i++) {
			// 当前数是arr[i], 请把arr[i]变成K进制的形式，每一位累加到eO
			setExclusiveOr(eO, arr[i], k);
		}
		int res = getNumFromKSysNum(eO, k);
		return res;
	}

	public static void setExclusiveOr(int[] eO, int value, int k) {
		int[] curKSysNum = getKSysNumFromNum(value, k);
		for (int i = 0; i != eO.length; i++) {
			eO[i] = (eO[i] + curKSysNum[i]) % k;
		}
	}

	/**
	 * 由十进制的数变成k进制的数,低位放在res[0] 的位置
	 * @param value
	 * @param k
	 * @return
	 */
	public static int[] getKSysNumFromNum(int value, int k) {
		int[] res = new int[32];
		int index = 0;
		while (value != 0) {
			res[index++] = value % k;
			value = value / k;
		}
		return res;
	}

	/**
	 * 由k进制的数变成10进制的数,低位放在res[0] 的位置
	 * @param eO
	 * @param k
	 * @return
	 */
	public static int getNumFromKSysNum(int[] eO, int k) {
		int res = 0;
		for (int i = eO.length - 1; i != -1; i--) {
			res = res * k + eO[i];
		}
		return res;
	}

	public static void main(String[] args) {
//		int[] test1 = { 1, 1, 1, 2, 6, 6, 2, 2, 10, 10, 10, 12, 12, 12, 6, 9 };
//		System.out.println(onceNum(test1, 3));
//
//		int[] test2 = { -1, -1, -1, -1, -1, 2, 2, 2, 4, 2, 2 };
//		System.out.println(onceNum(test2, 5));

		System.out.println(getNumFromKSysNum(new int[]{1,2,0},3));
		int[] kSysNumFromNum = getKSysNumFromNum(7, 3);
		for(int i=0;i< kSysNumFromNum.length;i++){
			System.out.print(kSysNumFromNum[i]+",");
		}
	}

}