package Exam;

public class Stairs {
    public static void main(String[] args) {
        System.out.println(countingStairs(100));
    }
    private static long countingStairs(int level) {
        if (level == 1) {
            return 1;
        }
        if (level == 2) {
            return 2;
        }

        return countingStairs(level - 1) + countingStairs(level - 2);
    }
}
