package week1;

public class Mergesort {
    public static void main(String[] args) {
        int[] nums = {5,1,1,2,0,0,8,10,11,5,3,2,1,0,2,1,2,3,9};
        sortArray(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
    public static int[] sortArray(int[] nums) {
        int len = nums.length;
        int[] temp = new int[len];
        mergeSort(nums, 0, len - 1, temp);
        return nums;
    }

    private static void mergeSort(int[] nums, int left, int right, int[] temp) {
        //base case
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        mergeSort(nums, left, mid, temp);
        mergeSort(nums, mid + 1, right, temp);

        mergeOfTwoSortedArrays(nums, left, mid, right, temp);
    }

    private static void mergeOfTwoSortedArrays(int[] nums, int left, int mid, int right, int[] temp) {
        System.arraycopy(nums, left, temp, left, right - left + 1);

        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            // 提前跳出，如果i或j达到了边界值，那么自动复制另外一组数据到nums中
            if (i == mid + 1) {
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                // 注意写成 < 就丢失了稳定性（相同元素原来靠前的排序以后依然靠前）
                nums[k] = temp[i];
                i++;
            } else {
                // temp[i] > temp[j]
                nums[k] = temp[j];
                j++;
            }
        }
    }
}
