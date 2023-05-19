package AlgoAssignment2;

import java.util.Arrays;

public class Problem4 {
    public static void main(String[] args) {
        int[] nums = {1,14,7,9,30,77,15,20,60};
        Arrays.sort(nums);
        for (int num : nums) {
            System.out.print(num);
            System.out.print(",");
        }
        System.out.println();
        //right bound ->

        System.out.println(predecessor(nums, 28, false));
        System.out.println(successor(nums, 28, false));

        System.out.println(successor(nums,16,false));
        System.out.println(successor(nums, 13,false));
        System.out.println(predecessor(nums,16,false));
        System.out.println(predecessor(nums, 13,false));


        int[] range = {4,19};
        int[] test = rangeArray(nums, range);
        for(int num : test) {
            System.out.print(num + ",");
        }
        System.out.println();
        int numInRange = numInRange(nums,range);
        System.out.println(numInRange);
    }

    int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return nums[mid];
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid;
        }
        return -1;
    }

    /**
     * left bound
     * @param nums
     * @param target
     * @return the successor of target
     */
    static int successor(int[] nums, int target, boolean isIndex) {
        int left = 0;
        int right = nums.length; // 注意

        while (left < right) { // 注意
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return nums[mid];
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid; // 注意
            }
        }
        if (isIndex) {
            return left;
        } else {
            return nums[left];
        }
    }

    /**
     * right bound
     * @param nums
     * @param target
     * @return the predecessor of target
     */
    static int predecessor(int[] nums, int target, boolean isIndex) {
        int left = 0, right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return nums[mid];
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        if (isIndex) {
            return left - 1;
        } else {
            return nums[left - 1];
        }
    }
    static int predecessor(int[] nums, int target) {
        int left = 0, right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return nums[mid];
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        return nums[left - 1];
    }

    static int successor(int[] nums, int target) {
        int left = 0, right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return nums[mid];
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        return nums[left];
    }
    static int[] rangeArray(int[] nums, int[] range) {
        int leftBound = predecessor(nums, range[0], true);
        int rightBound = successor(nums, range[1],true);
        int[] res = Arrays.copyOfRange(nums, leftBound + 1, rightBound);
        return res;
    }

    static int numInRange(int[] nums, int[] range) {
        int leftBound = predecessor(nums, range[0], true);
        int rightBound = successor(nums, range[1],true);
        return rightBound - leftBound - 1;
    }
}
