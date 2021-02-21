package class01;

import java.util.TreeSet;

/**
 * 求子数组累加和中，最接近K的值，数组中有整数和负数，
 * 只能使用有序表，如果全部是整数，可以使用窗口来解决
 */
public class Code02_MaxSubArraySumLessOrEqualK {
	public static void main(String[] args) {
		int [] arr = new int[]{4};
		int K = 11;
		System.out.println(getMaxLessOrEqualK(arr,K));
	}

	// 请返回arr中，求个子数组的累加和，是<=K的，并且是最大的。
	// 返回这个最大的累加和
	public static int getMaxLessOrEqualK(int[] arr, int K) {
		// 记录i之前的，前缀和，按照有序表组织
		TreeSet<Integer> set = new TreeSet<Integer>();
		// 一个数也没有的时候，就已经有一个前缀和是0了
		set.add(0);

		int max = Integer.MIN_VALUE;
		int sum = 0;
		// 每一步的i，都求子数组必须以i结尾的情况下，求个子数组的累加和，是<=K的，并且是最大的
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i]; // sum -> arr[0..i];
			if (set.ceiling(sum - K) != null) {
				max = Math.max(max, sum - set.ceiling(sum - K));
			}
			set.add(sum); // 当前的前缀和加入到set中去
		}
		return max;

	}

}
