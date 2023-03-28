package week4;

import com.sun.source.tree.TryTree;

import java.util.Scanner;

public class JavaTute2_2 {
    static int speed = 0;
    public static void main(String[] args) {
        boolean isDrunk = false;
//        try {
//            isDrunk = speedInput();
//        }catch (Exception e) {
//            System.out.println("Invalid Input");
//            isDrunk = speedInput();
//        }
        isDrunk = speedInput();
        calculateAndPrint(isDrunk);

    }
    private static boolean speedInput() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Please enter speed: ");
        speed = Integer.parseInt(sc.nextLine());
        boolean isDrunk = false;
        System.out.print("Is the driver drunk? ('Y' for drunk, 'N' otherwise): ");
        String drunk = sc.nextLine();

        if (drunk.equals("N") || drunk.equals("Y")) {
            isDrunk = drunk.equals("Y");
        }

        return isDrunk;
    }
    private static void calculateAndPrint(boolean isDrunk) {
        if (!isDrunk) {
            if (speed > 60 && speed < 65) {
                switchDrunkCase(1);
            } else if (speed >= 65 && speed <= 70) {
                switchDrunkCase(3);
            } else if (speed > 70) {
                switchDrunkCase(5);
            }
        } else {
            if (speed > 60 && speed < 65) {
                switchDrunkCase(2);
            } else if (speed >= 65 && speed <= 70) {
                switchDrunkCase(4);
            } else if (speed > 70) {
                switchDrunkCase(6);
            }

        }
    }
    private static void printSomething(String something) {
        if (something.equals("*")) {
            System.out.println("****************************************************");
        } else if (something.equals("-")) {
            System.out.println("----------------------------------------------------");
        }
    }
    private static void switchDrunkCase(int caseNum) {
        double fine = 0;
        double exceededSpeed = speed - 60;
        switch (caseNum) {
            case 1:
                printSomething("*");
                System.out.println("Warning\n");
                printSomething("-");
                System.out.printf("You have a fine of $%.2f%n", fine);
                printSomething("*");
                break;
            case 2:
                printSomething("*");
                System.out.println("Warning + Take a shower\n");
                printSomething("-");
                System.out.printf("You have a fine of $%.2f%n", fine);
                printSomething("*");
                break;
            case 3:
                printSomething("*");
                System.out.println("$5.0 fine for each km/hr over 60 km/hr\n");
                printSomething("-");
                fine = 5.0;
                System.out.printf("You have a fine of $%.2f%n", fine * exceededSpeed);
                printSomething("*");
                break;
            case 4:
                printSomething("*");
                System.out.println("$7.0 fine for each km/hr over 60 km/hr + Take a shower\n");
                printSomething("-");
                fine = 7.0;
                System.out.printf("You have a fine of $%.2f%n", fine * exceededSpeed);
                printSomething("*");
                break;
            case 5:
                printSomething("*");
                System.out.println("$10.0 fine for each km/hr over 60 km/hr\n");
                printSomething("-");
                fine = 10.0;
                System.out.printf("You have a fine of $%.2f%n", fine * exceededSpeed);
                printSomething("*");
                break;
            case 6:
                printSomething("*");
                System.out.println("$15.0 fine for each km/hr over 60 km/hr");
                System.out.println("Spend the day/night in cell until become sober\n");
                printSomething("-");
                fine = 15.0;
                System.out.printf("You have a fine of $%.2f%n", fine * exceededSpeed);
                printSomething("*");
                break;
        }
    }
}
