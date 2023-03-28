package week1;

import java.util.*;
class Calculator {
    public static void main (String[] args) {
        try {
            calculator();
        } catch(Exception e) {
            System.out.println("Invalid input! Please try again!");
            calculator();
        }
        String a = "a";
        String b = a.toUpperCase();

    }
    private static int calculator () {
        System.out.println ("Enter either  num1 + num2  or num1 * num2");
        Scanner sc = new Scanner(System.in);
        String strInput = sc.nextLine();
        String[] nums = strInput.split(" ");

        int num1 = Integer.parseInt(nums[0]);
        int num2 = Integer.parseInt(nums[2]);
        if (nums[1].equals("+")) {
            System.out.println(num1 + num2);
            return num1 + num2;
        } else if (nums[1].equals("*")) {
            System.out.println(num1 * num2);
            return num1 * num2;
        } else {
            System.out.print("Wrong input ! ");
            return calculator();
        }
    }
}