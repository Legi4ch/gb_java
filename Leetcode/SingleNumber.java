package Leetcode;

/**
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 * <p>
 * Follow up: Could you implement a solution with a linear runtime complexity and without using extra memory?
 */


public class SingleNumber {
    static int[] arr = {7,1,4,1,2,3,2,3,7};

    public static void main(String[] args) {
        System.out.println(solution(arr));
        System.out.println(singleNumber(arr));
        System.out.println(7 ^ 7 ^ 7);
    }

    public static int solution(int[] nums) {
        boolean flag = false;
        int item;
        for (int i = 0; i < nums.length; i++) {
            item = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == nums[i]) {
                    item++;
                }
            }
            if (item == 1) {
                return nums[i];
            }
        }
        return 0;
    }

    public static int singleNumber(int[] nums) {
        int a = 0;
        for (int i : nums) {
            a ^= i;
        }
        return a;
    }
}