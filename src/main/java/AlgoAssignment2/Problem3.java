package AlgoAssignment2;

import java.util.*;

public class Problem3 {
    public static void main(String[] args) {
//        int[] weight = {1,1,1,1,1,1,1,1,1,1}; //10
//        //int[] value = {1,20,14,7,9,30,77,15,20,60};
//        int[] value = {1,20,14,7,9,30,77,15,20,60};
//        int bagSize = 8;
//        int[] weight = {1,1,1,1,1,1,1,1,1,1}; //10
//        int[] value = {1,2,3,4,5,6,7,8,9,10};
//        int bagSize = 10;
        int[] weight = {1,1,1,1,1,1,1,1,1,1}; //10
        int[] value = {10,3,3,4,5,16,7,8,199,10,12,13};

        //testWeightBagProblem(weight,value,bagSize);
        //System.out.println(lengthOfLIS(value));
        System.out.println(longestSubSequence(value));
    }

    /**
     * 动态规划获得结果
     * @param weight  物品的重量
     * @param value   物品的价值
     * @param bagSize 背包的容量
     */
    public static void testWeightBagProblem(int[] weight, int[] value, int bagSize){

        // 创建dp数组
        int goods = weight.length;  // 获取物品的数量
        int[][] dp = new int[goods][bagSize + 1];

        // 初始化dp数组
        // 创建数组后，其中默认的值就是0
        for (int j = weight[0]; j <= bagSize; j++) {
            dp[0][j] = weight[0];
        }

        // 填充dp数组
        for (int i = 1; i < weight.length; i++) {
            for (int j = 1; j <= bagSize; j++) {
                //if (j < weight[i]) {
                if (value[i] <= value[i - 1]) {
                    /**
                     * 当前物品i的价值没有前一个物品i - 1大时，选择不加入当前背包
                     * 那么前i-1个物品能放下的最大重量就是当前情况的最大重量
                     */
                    dp[i][j] = dp[i - 1][j];
                } else {
                    /**
                     * 当前背包可以放入物品i
                     * 那么此时分两种情况：
                     *    1、不放物品i
                     *    2、放物品i
                     * 比较这两种情况下，哪种背包中物品的重量最大
                     */
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + weight[i]);
                }
            }
        }

        // 打印dp数组
        for (int i = 0; i < goods; i++) {
            System.out.print("i" + (i + 1) + " " + "v" + value[i] + "\t\t");
            for (int j = 0; j <= bagSize; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static List<Integer> longestSubSequence(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 1; i <= 100; i++) {
            map.put(i, new int[]{-1, 1}); // 0-last index 1-max length
        }

        int longest = 1;//maintain the max length of LIS all the time
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            int tempMax = 1;//tempMax to maintain the max length of LIS for nums[i]
            for (int j = 1; j <= 100; j++) {
                if (val <= j) continue;//val must bigger than j to maintain LIS
                int[] tup = map.get(j);
                if (tup[0] != -1) {//check if j is in the previous sequence S
                    tempMax = Math.max(tempMax, tup[1] + 1);
                }
            }
            map.put(val, new int[]{i, tempMax});// update the map with the new index and visited the val
            longest = Math.max(tempMax, longest);
        }
        //to store the subsequence
        List<Integer> subsequence = new ArrayList<>();
        int currLength = longest;
        for (int i = nums.length - 1; i >= 0; i--) {
            //to check the where the map's last curLength appears
            if (map.get(nums[i])[1] == currLength) {
                subsequence.add(nums[i]);//add to the subsequence
                currLength--;//means the next number
            }
        }

        Collections.reverse(subsequence); // since it is added from back to front, need reverse
        return subsequence;
    }
//    public static int longestSubSequence(int[] nums) {
//        Map<Integer, ArrayList<ArrayList<Integer>>> map = new HashMap<>();
//        for (int i = 1; i <= 100; i++) {
//            ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
//            ArrayList<Integer> value = new ArrayList<>();
//            value.add(-1);
//            value.add(1);
//            arrayList.add(value);
//            arrayList.add(new ArrayList<>());
//            map.put(i, arrayList); // 0-last index 1-max length
//        }
//
//        int longest = 1;
//        for (int i = 0; i < nums.length; i++) {
//            ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
//            ArrayList<Integer> value = new ArrayList<>();
//            int val = nums[i];
//            int tempMax = 1;
//            for (int j = 1; j <= 100; j++) {
//                if (val <= j) continue;
//                ArrayList<ArrayList<Integer>> tup = map.get(j);
//                if (tup.get(0).get(0) != -1) {
//                    tempMax = Math.max(tempMax, tup.get(0).get(1) + 1);
//                    tup.get(1).add(j);
//                    value.add(i);
//                    value.add(tempMax);
//                    arrayList.add(value);
//                    arrayList.add(tup.get(1));
//                }
//            }
//            map.put(val, arrayList);
//            longest = Math.max(tempMax, longest);
//        }
//        return longest;
//    }

}
