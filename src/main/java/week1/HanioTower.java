package week1;

public class HanioTower {
    static int count = 0;
    public static void main(String[] args) {
        int n = 24;
        char a = 'A', b = 'B', c = 'C';
        hanioTower(n, a, b, c);
        System.out.println("## The minimum step of moving " + n + " disks is " + count + " ##");
    }

    /**
     *
     * @param n the disks in total
     * @param a the original peg
     * @param b the spare peg
     * @param c the destination peg
     */
    public static void hanioTower(int n, char a, char b, char c) {
        if (n == 1) {
            move(n, a, c);
            count++;
        } else {
            //借助 c 从 a 移动到 b
            hanioTower(n - 1, a, c, b);
            //把第n个disk从 a 移动到 c
            move(n, a, c);
            //借助 a 从 b 移动到 c
            hanioTower(n - 1, b, a, c);
            count++;
        }
    }

    private static void move(int n, char a, char b) {
        System.out.println("Put the disk " + n + " from " + a + " to " + b);
    }


}
