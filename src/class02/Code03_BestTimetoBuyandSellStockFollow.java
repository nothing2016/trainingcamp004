package class02;

/**
 * 给定一个数组arr,从左到右表示昨晚股票的价格
 * 作为一个时候诸葛亮，你想知道如果交易次数不超过 K 次
 * 且每次交易只能交易一股，返回能挣到的最大钱数
 */
public class Code03_BestTimetoBuyandSellStockFollow {

	// 这个题是动态规划中的斜率优化，很难，需要理解再看一次视频
	public static int dp(int K, int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int N = prices.length;
		// 如果次数大于N/2 认为K是无限次的
		if (K >= N / 2) {
			return allTrans(prices);
		}
		// dp[i][j] 表示在[0,i]上的股票范围内进行不超过j次的交易
		// 那么dp[0][0,j]肯定是0，因为在一个时间点买入和卖出，得到的钱是0
		// dp[0,i][0]肯定也是0，因为进行0次买卖，得到的钱可定是0
		/**
		 * dp[i][j] 依赖下面的结果
		 * 1）dp[i-1][j]  不在[i]位置进行买卖，与i无关
		 *    下面就是与i位置有关的情况
		 * 2）dp[i][j-1] + [i] - [i]  在[i]位置买入，并在i位置卖掉
		 *    dp[i-1][j-1] + [i] - [i-1]  在[i-1]位置买入，并在i位置卖掉
		 *    dp[i-2][j-1] + [i] - [i-2]  在[i-2]位置买入，并在i位置卖掉
		 *    .......
		 *    dp[0][j-1] + [i] - [0]  在[0]位置买入，并在i位置卖掉
		 *    2的这种情况中找到max即可，再与1取max
		 *
		 *
		 *  在1和2两种大的可能性中找最大的值，经观察2的情况可以优化，在下次计算时复用
		 */
		int[][] dp = new int[N][K + 1];
		int ans = 0;
		for (int j = 1; j <= K; j++) {
			// 每次优化的结果
			int t = dp[0][j - 1] - prices[0];
			for (int i = 1; i < N; i++) {
				t = Math.max(t, dp[i][j - 1] - prices[i]);
				dp[i][j] = Math.max(dp[i - 1][j], t + prices[i]);
				ans = Math.max(ans, dp[i][j]);
			}
		}
		return ans;
	}

	public static int maxProfit(int K, int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int N = prices.length;
		if (K >= N / 2) {
			return allTrans(prices);
		}
		// dp一维表，做了空间压缩
		int[] dp = new int[N];
		int ans = 0;
		for (int tran = 1; tran <= K; tran++) {
			int pre = dp[0];
			int best = pre - prices[0];
			for (int index = 1; index < N; index++) {
				pre = dp[index];
				dp[index] = Math.max(dp[index - 1], prices[index] + best);
				best = Math.max(best, pre - prices[index]);
				ans = Math.max(dp[index], ans);
			}
		}
		return ans;
	}

	public static int allTrans(int[] prices) {
		int ans = 0;
		for (int i = 1; i < prices.length; i++) {
			ans += Math.max(prices[i] - prices[i - 1], 0);
		}
		return ans;
	}

	public static void main(String[] args) {
		int[] test = { 4, 1, 231, 21, 12, 312, 312, 3, 5, 2, 423, 43, 146 };
		int K = 3;
		System.out.println(dp(K, test));
		System.out.println(maxProfit(K, test));

	}

}
