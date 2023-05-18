package AlgoAssignment2;

public class Problem3 {
    public static void main(String[] args) {
        int[] weight = {1,1,1,1,1,1,1,1,1,1}; //10
        int[] value = {1,20,14,7,9,30,77,15,20,60};
        int bagSize = 8;
//        int[] weight = {1,1,1,1,1,1,1,1,1,1}; //10
//        int[] value = {1,2,3,4,5,6,7,8,9,10};
//        int bagSize = 10;
//        int[] weight = {1,1,1,1,1,1,1,1,1,1}; //10
//        int[] value = {1,3,3,4,5,16,7,8,9,10};
//        int bagSize = 10;
        testWeightBagProblem(weight,value,bagSize);
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
}
