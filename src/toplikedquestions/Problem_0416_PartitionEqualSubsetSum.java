package toplikedquestions;

/**
 *
 */
public class Problem_0416_PartitionEqualSubsetSum {

    public static boolean canPartition(int[] nums) {
        int N = nums.length;

        // 求和
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += nums[i];
        }

        // 如果和不是偶数一定不能均分
        if ((sum & 1) != 0) {
            return false;
        }

        //
        sum >>= 1;
        boolean[][] dp = new boolean[N][sum + 1];

        // 第0列
        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }

        // 第0行
        if (nums[0] <= sum) {
            dp[0][nums[0]] = true;
        }


        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - nums[i] >= 0) {
                    dp[i][j] |= dp[i - 1][j - nums[i]];
                }
            }
            if (dp[i][sum]) {
                return true;
            }
        }
        return false;
    }

}
