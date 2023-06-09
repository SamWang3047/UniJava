package COMP90041.Week8;

import java.lang.management.ManagementFactory;
import java.util.Scanner;

public class RoundThings {
    public abstract class RoundThing {
        abstract double area();
        Scanner scanner;
        RoundThing(Scanner scanner) {
            this.scanner = scanner;
        }
    }

    class Circle extends RoundThing {
        double radius;
        Circle(Scanner scanner) {
            super(scanner);
            System.out.println("Enter radius for the circle:");
            this.radius = scanner.nextDouble();
        }

        @Override
        double area() {
            return Math.PI * Math.pow(radius, 2);
        }
    }

    public abstract class Round3DThing extends RoundThing {
        abstract double volume();

        Round3DThing(Scanner scanner) {
            super(scanner);
        }
    }

    class Sphere extends Round3DThing {
        double radius;

        Sphere(Scanner scanner) {
            super(scanner);
            System.out.println("Enter radius for the sphere");
            this.radius = scanner.nextDouble();
        }

        @Override
        double area() {
            return 4 * Math.PI * Math.pow(radius, 2);
        }

        @Override
        double volume() {
            return (4 / 3) * Math.PI * Math.pow(radius, 3);
        }
    }

    class Cylinder extends Round3DThing {
        double radius;
        double height;

        Cylinder(Scanner scanner) {
            super(scanner);
            System.out.println("Enter radius and height for the cylinder:");
            this.radius = scanner.nextDouble();
            this.height = scanner.nextDouble();
        }

        @Override
        double area() {
            return 2 * Math.PI * radius * (radius + height);
        }

        @Override
        double volume() {
            return Math.PI * Math.pow(radius, 2) * height;
        }
    }

//    public class Main() {
//        public static void main (String[] args) {
//            Scanner in = new Scanner(System.in);
//
//            RoundThing firstShape = read (in);
//            RoundThing secondShape = read (in);
//
//            if (firstShape instanceof Round3DThing && secondShape instanceof Round3DThing) {
//                if (((Round3DThing)firstShape).volume() < ((Round3DThing)secondShape).volume()) {
//                    System.out.println("The first shape has smaller volume than the second.");
//                } else {
//                    System.out.println("The second shape has smaller volume than the first.");
//                }
//            } else if (firstShape.area() < secondShape.area()) {
//                System.out.println("The first shape has smaller area than the second.");
//            } else {
//                System.out.println("The second shape has smaller area than the first.");
//            }
//        }
//        static RoundThing read(Scanner in) {
//            System.out.println ("Is object a 1. circle, 2. sphere, 3. cylinder?");
//            if (!in.hasNextLine()) {
//                System.exit(1);
//            }
//            String line = in.nextLine();
//
////            switch (line) {
////                case "1": return new Circle(in);
////                case "2": return new Sphere(in);
////                case "3": return new Cylinder(in);
////            }
//            System.err.println ("Invalid input " + line);
//            System.exit(1);
//            return null; //This line won't be reached but is required to satisfy Java's all paths return requirement.
//        }
//    }
//}

}
