package AlgoAssignment2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestAscendingSubsequence {
    public static List<Integer> findLongestAscendingSubsequence(int[] sequence) {
        int[] dp = new int[sequence.length]; // 存储以每个数字结尾的最长上升子序列长度
        int[] bit = new int[101]; // 树状数组，用于查询区间最大值
        int maxLength = 0; // 最长上升子序列的长度

        for (int i = 0; i < sequence.length; i++) {
            int x = sequence[i];
            int prevMax = query(bit, x - 1); // 查询[1, x-1]区间的最大值
            dp[i] = prevMax + 1; // 以数字x结尾的最长上升子序列长度为prevMax + 1
            update(bit, x, dp[i]); // 更新树状数组中数字x的值为dp[i]
            maxLength = Math.max(maxLength, dp[i]); // 更新最长上升子序列的长度
        }

        List<Integer> subsequence = new ArrayList<>();
        int currLength = maxLength;

        for (int i = sequence.length - 1; i >= 0; i--) {
            if (dp[i] == currLength) {
                subsequence.add(sequence[i]);
                currLength--;
            }
        }

        Collections.reverse(subsequence); // 由于从后向前添加，需要将结果反转

        return subsequence;
    }

    // 查询[1, x]区间的最大值
    private static int query(int[] bit, int x) {
        int max = 0;
        while (x > 0) {
            max = Math.max(max, bit[x]);
            x -= x & -x; // 移除最低位的1
        }

        return max;
    }

    // 更新树状数组中的值
    private static void update(int[] bit, int x, int value) {
        while (x < bit.length) {
            bit[x] = Math.max(bit[x], value);
            x += x & -x; // 获取下一个更新的位置
        }
    }

    public static void main(String[] args) {
        int[] sequence = {1, 20, 14, 7, 9, 30, 77, 15, 20, 60};
        List<Integer> longestSubsequence = findLongestAscendingSubsequence(sequence);

        System.out.println("Longest ascending subsequence:");
        for (int num : longestSubsequence) {
            System.out.print(num + " ");
        }
    }
}
