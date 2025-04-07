/*
 * FileStatsReader.java
 * Author: Alexander La Barge
 * Date: 7 Apr 25
 * Company: Brilliant Solutions, Inc.
 *
 * SAMPLE SOLUTION for the File Stats Reader Challenge
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileStatsReader {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java FileStatsReader <filePath>");
            return;
        }

        String filePath = args[0];
        int lineCount = 0;
        int wordCount = 0;
        int totalWordLength = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineCount++;
                // Split on whitespace
                String[] words = line.trim().split("\\s+");
                for (String w : words) {
                    if (!w.isEmpty()) {
                        wordCount++;
                        totalWordLength += w.length();
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("File not found or cannot be read.");
            return;
        }

        System.out.println("Line Count: " + lineCount);
        System.out.println("Word Count: " + wordCount);

        if (wordCount > 0) {
            double averageWordLength = (double) totalWordLength / wordCount;
            // Round to 2 decimal places
            System.out.printf("Average Word Length: %.2f%n", averageWordLength);
        } else {
            System.out.println("Average Word Length: 0");
        }
    }
}
