package test;

public class test {
    public static void main(String[] args) {
        int[][] grade = new int [][] {{10,10,10}, {2,0,1}, {8,6,9},{8,4,10}};
        int[] studentAverage = new int[grade.length];
        int[] quizAverage = new int[grade[0].length];

        for (int i = 0; i < grade.length; i++) {
            int studentSum = 0;
            for (int j = 0; j < grade[0].length; j++) {
                studentSum += grade[i][j];
            }
            studentAverage[i] = studentSum / grade[0].length;
        }
        for (int i = 0; i < grade[0].length; i++) {
            int sum = 0;
            for (int j = 0; j < grade.length; j++) {
                sum += grade[j][i];
            }
            quizAverage[i] = sum / grade[0].length;
        }
    }
}
