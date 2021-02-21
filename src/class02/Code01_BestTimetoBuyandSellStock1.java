package class02;

/**
 * 只能买卖一次，且只能买一股，求昨天买股票的最大收益，
 * 数组表示昨天的股票的价格
 * 如 [3 2 6 9 7] 那最好的时间是在2买入，在9卖出 得到7元
 */
public class Code01_BestTimetoBuyandSellStock1 {

	public int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int min = prices[0];
		int ans = 0;
		for (int i = 0; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			ans = Math.max(ans, prices[i] - min);
		}
		return ans;
	}

}
