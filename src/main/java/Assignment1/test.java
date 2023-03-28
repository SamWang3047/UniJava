package Assignment1;

public class test {
    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            int j = 0;
            while (j < 5 - i) {
                System.out.print('*');
                j++;
            }
            while (j < 10) {
                System.out.print('-');
                j++;
            }
            System.out.println();
        }
    }
}
