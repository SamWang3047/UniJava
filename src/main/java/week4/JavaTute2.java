package week4;

import java.util.Scanner;

public class JavaTute2 {
    static String[] weekdays = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    static String[] weekdaysShort = new String[] {"Mon", "Tue", "Wed", "Thu", "Fri"};
    static int dayNum = 5;
    public static void main(String[] args) {
        int[] dayTemps = new int[dayNum];
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            System.out.print("Please enter temperature for " + weekdays[i] + ": ");
            dayTemps[i] = sc.nextInt();
        }
        histTemp(dayTemps);
    }

    private static void histTemp(int[] dayTemps) {
        System.out.println();
        System.out.println("Histogram of Temperatures");
        System.out.println("--------------------------");
        for (int i = 0; i < dayNum; i++) {
            System.out.print(weekdaysShort[i] + " | ");
            for (int j = 0; j < dayTemps[i]; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }


}
