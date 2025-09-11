import java.util.*;

public class SpellChecker {

    // Split sentence into words without using split()
    public static List<String> getWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == ' ' || c == '.' || c == ',' || c == '!' || c == '?' || c == ';') {
                if (start != i) {
                    words.add(text.substring(start, i));
                }
                start = i + 1;
            }
        }
        if (start < text.length()) {
            words.add(text.substring(start));
        }
        return words;
    }

    // Calculate string distance (Levenshtein-like simplified version)
    public static int stringDistance(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();
        int[][] dp = new int[lenA + 1][lenB + 1];

        for (int i = 0; i <= lenA; i++) dp[i][0] = i;
        for (int j = 0; j <= lenB; j++) dp[0][j] = j;

        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                                    Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[lenA][lenB];
    }

    // Find closest word from dictionary
    public static String getClosestMatch(String word, String[] dictionary) {
        String closest = word;
        int minDistance = Integer.MAX_VALUE;

        for (String dictWord : dictionary) {
            int distance = stringDistance(word.toLowerCase(), dictWord.toLowerCase());
            if (distance < minDistance) {
                minDistance = distance;
                closest = dictWord;
            }
        }
        return (minDistance <= 2) ? closest : "No Suggestion";
    }

    // Display results in tabular format
    public static void displayResults(List<String> words, String[] dictionary) {
        System.out.println("\n================ SPELL CHECK REPORT ================");
        System.out.printf("%-15s %-20s %-10s %-12s\n", "Original", "Suggestion", "Distance", "Status");
        System.out.println("-----------------------------------------------------");

        for (String word : words) {
            String suggestion = getClosestMatch(word, dictionary);
            int distance = suggestion.equals("No Suggestion") ? -1 :
                           stringDistance(word.toLowerCase(), suggestion.toLowerCase());
            String status = suggestion.equals(word) ? "Correct" :
                            suggestion.equals("No Suggestion") ? "Unknown" : "Misspelled";

            System.out.printf("%-15s %-20s %-10s %-12s\n",
                    word, suggestion, (distance == -1 ? "--" : distance), status);
        }
        System.out.println("=====================================================");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] dictionary = {
                "java", "programming", "language", "is", "very", "powerful",
                "simple", "used", "worldwide", "computer", "science", "project"
        };

        System.out.print("Enter a sentence: ");
        String sentence = sc.nextLine();

        List<String> words = getWords(sentence);

        displayResults(words, dictionary);

        sc.close();
    }
}
