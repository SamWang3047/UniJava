package week1;

public class test {
    public static void main(String[] args) {
        evenOddBit(17);
        evenOddBit(2);
        int[] nums = evenOddBit(2);
        for (int num : nums) {
            System.out.println(num);
        }

    }
    public static int[] evenOddBit(int n) {
        String binary = Integer.toBinaryString(n);
        System.out.println(binary);
        int[] res = new int[2];

        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') {
                if (i % 2 == 0) {
                    res[0]++;
                } else {
                    res[1]++;
                }
            }

        }
        return res;
    }
}
