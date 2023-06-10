package COMP90041.Week10.Lab7Renderable;

public class Puppy implements Renderable{
    @Override
    public void render() {
        String puppy = " ___\n" +
                " __/_  `.  .-\"\"\"-.\n" +
                " \\_,` | \\-'  /   )`-')\n" +
                "  \"\") `\"`    \\  ((`\"`\n" +
                " ___Y  ,    .'7 /|\n" +
                "(_,___/...-` (_/_/ Gary";

        System.out.println(puppy);
    }
}
