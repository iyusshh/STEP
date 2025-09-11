public class StringBuiltInMethods {
    public static void main(String[] args) {
        String sampleText = " Java Programming is Fun and Challenging! ";

        // 1. Display original string length including spaces
        System.out.println("1. Original String: \"" + sampleText + "\"");
        System.out.println("   Original Length (with spaces): " + sampleText.length());

        // 2. Remove leading and trailing spaces, show new length
        String trimmedText = sampleText.trim();
        System.out.println("\n2. Trimmed String: \"" + trimmedText + "\"");
        System.out.println("   New Length (without leading/trailing spaces): " + trimmedText.length());

        // 3. Find and display the character at index 5
        System.out.println("\n3. Character at Index 5: " + sampleText.charAt(5));

        // 4. Extract substring "Programming" from the text
        String subStr = trimmedText.substring(5, 16);
        System.out.println("\n4. Extracted Substring: " + subStr);

        // 5. Find the index of the word "Fun"
        int funIndex = trimmedText.indexOf("Fun");
        System.out.println("\n5. Index of \"Fun\": " + funIndex);

        // 6. Check if the string contains "Java" (case-sensitive)
        System.out.println("\n6. Contains \"Java\"? " + trimmedText.contains("Java"));

        // 7. Check if the string starts with "Java" (after trimming)
        System.out.println("\n7. Starts with \"Java\"? " + trimmedText.startsWith("Java"));

        // 8. Check if the string ends with an exclamation mark
        System.out.println("\n8. Ends with '!'? " + trimmedText.endsWith("!"));

        // 9. Convert the entire string to uppercase
        System.out.println("\n9. Uppercase: " + trimmedText.toUpperCase());

        // 10. Convert the entire string to lowercase
        System.out.println("\n10. Lowercase: " + trimmedText.toLowerCase());

        // Count vowels in the string
        int vowelCount = countVowels(trimmedText);
        System.out.println("\n11. Number of vowels: " + vowelCount);

        // Find all occurrences of a character
        System.out.println("\n12. Positions of character 'a':");
        findAllOccurrences(trimmedText, 'a');
    }

    // Method to count vowels using charAt()
    public static int countVowels(String text) {
        int count = 0;
        text = text.toLowerCase();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }

    // Method to find all positions of a character
    public static void findAllOccurrences(String text, char target) {
        boolean found = false;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == target) {
                System.out.println("   Found '" + target + "' at index: " + i);
                found = true;
            }
        }
        if (!found) {
            System.out.println("   Character '" + target + "' not found in the string.");
        }
    }
}