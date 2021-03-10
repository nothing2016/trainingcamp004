package class05;

/**
 * 给定一个字符串s,子序列中是回文串的总数是多少？其中定义空字符串不是回文
 * 如 ABA 的总数是4中，第一个A和最后一个A,AA 和B,还有ABA，一共 5 种
 */
public class Code04_PalindromeWays {

	/**
	 * 第一种方法没看明白，请看方法二
	 * @param str
	 * @return
	 */
	public static int way1(String str) {
		char[] s = str.toCharArray();
		int len = s.length;
		int[][] dp = new int[len + 1][len + 1];
		for (int i = 0; i <= len; i++) {
			dp[i][i] = 1;
		}
		// dp[i][j]，在空串不算回文串的情况下，求str[i..j]有多少不同的回文子序列
		// index -> dp
		// str[index-1]
		// dp 1 str 0
		// dp 2 str 1
		for (int subLen = 2; subLen <= len; subLen++) {
			for (int l = 1; l <= len - subLen + 1; l++) {
				int r = l + subLen - 1;
				dp[l][r] += dp[l + 1][r];
				dp[l][r] += dp[l][r - 1];
				if (s[l - 1] == s[r - 1])
					dp[l][r] += 1;
				else
					dp[l][r] -= dp[l + 1][r - 1];
			}
		}
		return dp[1][len];
	}

	public static int way2(String str) {
		char[] s = str.toCharArray();
		int n = s.length;
		int[][] dp = new int[n][n];
		// 对角线肯定是回文
		for (int i = 0; i < n; i++) {
			dp[i][i] = 1;
		}
		// 对角线的右上角的第二条的求法，比如相等aa,就是3种（a,a,aa）
		for (int i = 0; i < n - 1; i++) {
			dp[i][i + 1] = s[i] == s[i + 1] ? 3 : 2;
		}
		for (int L = n - 3; L >= 0; L--) {
			for (int R = L + 2; R < n; R++) {
				/**
				 * 普通位置的求法，很难，仔细理解一下，4中可能性
				 * 1. L位置不要，R位置不要，可能性a
				 * 2. L位置要，R位置不要，可能性b
				 * 3. L位置不要，R位置要，可能性c
				 * 4. L位置要，r位置要，可能性d
				 *
				 * 那么 a + b + c + d 就是我们要的结果，当然d的可能性必须是s[l] = s[r]的情况成立的条件下
				 * 那我们的dp位置的对应关系为：
				 * dp[L+1][R] => a + c
				 * dp[L][R-1] => a + b
				 * dp[L-1][R-1] => a
				 *
				 * 那么a + b + c =  dp[L + 1][R] + dp[L][R - 1] - dp[L + 1][R - 1]
				 *
				 * 如果 s[L] == s[R]，就变成了要[L+1,R-1]范围上的数据了，但是真的这样就玩了吗？
				 * [L+1,R-1]的范围什么都不选，为空字符串时，s[L] 和s[R]单独组成的子序列也要考虑，
				 * 可能的数是1
				 */
				dp[L][R] = dp[L + 1][R] + dp[L][R - 1] - dp[L + 1][R - 1];
				if (s[L] == s[R]) {
					dp[L][R] += dp[L + 1][R - 1] + 1;
				}
			}
		}
		return dp[0][n - 1];
	}

	public static void main(String[] args) {
		System.out.println(way1("ABA"));
		System.out.println(way2("ABA"));

		System.out.println(way1("XXY"));
		System.out.println(way2("XXY"));
	}

}
