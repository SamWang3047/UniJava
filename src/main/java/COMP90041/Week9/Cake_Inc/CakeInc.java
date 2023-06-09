package COMP90041.Week9.Cake_Inc;

import java.util.Scanner;

public class CakeInc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a factory layout: ");
        System.out.print("> ");
        String firstRow = scanner.nextLine();
        int size = firstRow.length();
        String[] layout = new String[size];
        layout[0] = firstRow;
        for(int i=1; i<size; i++){
            System.out.print("> ");
            layout[i] = scanner.nextLine();
        }
        System.out.println();
        Factory factory = new Factory(layout);
        factory.render();
        factory.printIngredients();

    }
}
