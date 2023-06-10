package COMP90041.Week10.Lab7Coin;

import java.io.*;

/**
 * A program for counting the number of "coins" in a coin file.
 * 
 * The name of the coin file to use as input should be provided as a
 * command line argument when this program is run.
 * 
 * The result of the count is saved to a file called "count.txt".
 * 
 * @author TODO: Write your name here.
 *
 */
public class CoinCounter {

	public static void main(String[] args) {
		// TODO: Implement this class.
		if (args.length < 1) {
			System.out.println("Please provide the name of the coin file as a command line argument.");
			return;
		}
		String fileName = args[0];
		String outputFileName = "count.txt";
		
		try {
			int coinCount = countCoins(fileName);
			saveCoinCount(outputFileName, coinCount);
			System.out.println("Coin count file saved.");
		} catch (InvalidCoinFileException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("An error occurred while reading or writing the files.");
		}
	}



	private static int countCoins(String fileName) throws IOException, InvalidCoinFileException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		int coinCount = 0;
		String line;

		while ((line = reader.readLine()) != null) {
			if (!line.matches("^[O]+$")) {
				reader.close();
				throw new InvalidCoinFileException("Invalid coin file format.");
			}
			coinCount += line.length();
		}

		reader.close();
		return coinCount;
	}

	private static void saveCoinCount(String fileName, int coinCount) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write("You have " + coinCount + " coins!");
		writer.close();
	}

}

