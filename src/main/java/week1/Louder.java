package week1;


public class Louder {

    public static void main(String[] args) {

        // TODO: Write your code for solving the problem here.
        String sentence = "I love programming";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sentence.length(); i++) {
            char wordAtI = sentence.charAt(i);
            int wordAtIASCII = wordAtI;
            if (wordAtIASCII >= 97 && wordAtIASCII <= 122) {
                sb.append((char)(wordAtIASCII - 32));
            } else {
                sb.append(wordAtI);
            }
        }
        System.out.println(sb.toString());
    }

}