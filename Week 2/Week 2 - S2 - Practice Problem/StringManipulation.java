import java.util.*;

public class StringManipulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String input = sc.nextLine().trim();

        // 1. Trim
        System.out.println("\nTrimmed: " + input);

        // 2. Replace spaces with underscores
        System.out.println("Spaces replaced: " + input.replace(" ", "_"));

        // 3. Remove digits
        String noDigits = input.replaceAll("\\d", "");
        System.out.println("No digits: " + noDigits);

        // 4. Split words
        String[] words = noDigits.split("\\s+");
        System.out.println("Words: " + Arrays.toString(words));

        // 5. Join words
        System.out.println("Joined: " + String.join(" | ", words));

        // 6. Remove punctuation
        String noPunct = noDigits.replaceAll("\\p{Punct}", "");
        System.out.println("No punctuation: " + noPunct);

        // 7. Capitalize words
        System.out.println("Capitalized: " + capitalizeWords(noPunct));

        // 8. Reverse words
        System.out.println("Reversed: " + reverseWords(noPunct));

        // 9. Word frequency
        System.out.println("\nWord Frequency:");
        countWordFrequency(noPunct);

        sc.close();
    }

    // Capitalize first letter of each word
    public static String capitalizeWords(String text) {
        StringBuilder sb = new StringBuilder();
        for (String w : text.split("\\s+")) {
            sb.append(Character.toUpperCase(w.charAt(0)))
              .append(w.substring(1).toLowerCase())
              .append(" ");
        }
        return sb.toString().trim();
    }

    // Reverse order of words
    public static String reverseWords(String text) {
        String[] words = text.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) sb.append(words[i]).append(" ");
        return sb.toString().trim();
    }

    // Count word frequency 
    public static void countWordFrequency(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        boolean[] visited = new boolean[words.length];
        for (int i = 0; i < words.length; i++) {
            if (visited[i]) continue;
            int count = 1;
            for (int j = i + 1; j < words.length; j++) {
                if (words[i].equals(words[j])) {
                    count++;
                    visited[j] = true;
                }
            }
            System.out.println("   " + words[i] + " -> " + count);
        }
    }
}
