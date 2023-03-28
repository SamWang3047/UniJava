package week1;

public class week1_pretest {
    public static void main(String[] args) {
        t1();
        t2();
        t3();

    }
    private static void t1() {
        int b = -1, c = 10, i = 0;
        while (i <= 10) {
            c *= b;
            i++;
        }
        System.out.println(c);
    }
    private static void t2() {
        int sum = 0;
        int n = 5;
        for(int i = 1; i <= n - 1; i++) {
            sum = sum + i;
        }

        System.out.println(sum);
    }
    private static void t3() {
        int sum = 0;
        int n = 4;
        for (int i = 1; i <= 4; ++i) {
            for (int j = 1; j <= i; ++j) {
                sum += j;
            }
        }
        System.out.println(sum);
    }


}
