package COMP90041.Week10.Worksheet6;

import COMP90041.Week10.Lab7Coin.InvalidCoinFileException;
import COMP90041.Week9.Lab6TownSimulator.Entity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GoalAnalysis {

    public final static String DATAFILE = "football.csv";

    public static void main (String[] args) throws IOException {

        System.out.println("Source file: " + DATAFILE);
        
        // TODO: your code goes here
        HashMap<Integer, ArrayList<String>> map = new HashMap<>();

        ArrayList<String[]> playerList =  readStrings();

        int maxDiff = -1;

        for (String[] playerStats : playerList) {
            ArrayList<String> teams;
            int difference = scoreDifference(playerStats);
            if (difference > maxDiff) {
                maxDiff = difference;
            }

            if (map.containsKey(difference)) {
                teams = map.get(difference);
                teams.add(playerStats[0]);
            } else {
                teams = new ArrayList<>();
                teams.add(playerStats[0]);
            }
            map.put(difference, teams);
        }

        ArrayList<String> finalTeams = map.get(maxDiff);
        for (String team : finalTeams) {
            System.out.println("<" + team + ">" + ": " + "<" + maxDiff + ">");
        }
    }
    private static ArrayList<String[]> readStrings() throws IOException {
        ArrayList<String[]> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DATAFILE));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                list.add(line.split(";"));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can not find file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    private static int scoreDifference(String[] playerStats) throws IndexOutOfBoundsException {
        int playerFor = 0;
        int playerAgainst = 0;
        try {
            playerFor = Integer.parseInt(playerStats[4]);
            playerAgainst = Integer.parseInt(playerStats[5]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("index out of bound!");
        }
        return playerFor - playerAgainst;
    }
}
