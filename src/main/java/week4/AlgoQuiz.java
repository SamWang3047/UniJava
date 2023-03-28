package week4;

public class AlgoQuiz {
    public static void main(String[] args) {
        System.out.println(cal(11));

    }
    private static int cal(int n) {
        if (n >= 2) {
            return cal(n - 2) * n;
        }
        return 2;
    }

}
