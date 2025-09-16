import java.util.Scanner;

public class ASCIIProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user to enter a string
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        System.out.println("\n--- ASCII Character Analysis ---");
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int ascii = (int) ch;

            System.out.print("Character: '" + ch + "' | ASCII: " + ascii);

            // Check character type
            if (Character.isUpperCase(ch)) {
                System.out.print(" | Type: Uppercase");
            } else if (Character.isLowerCase(ch)) {
                System.out.print(" | Type: Lowercase");
            } else if (Character.isDigit(ch)) {
                System.out.print(" | Type: Digit");
            } else {
                System.out.print(" | Type: Special Character");
            }

            // Show upper and lower case versions with ASCII codes (if alphabet)
            if (Character.isLetter(ch)) {
                char upper = Character.toUpperCase(ch);
                char lower = Character.toLowerCase(ch);
                System.out.print(" | Upper: '" + upper + "'(" + (int) upper + ")");
                System.out.print(" | Lower: '" + lower + "'(" + (int) lower + ")");
                System.out.print(" | Diff: " + Math.abs((int) upper - (int) lower));
            }
            System.out.println();
        }

        // ASCII art example
        System.out.println("\n--- ASCII Art ---");
        for (int i = 65; i <= 69; i++) {  // Prints A to E in a pattern
            for (int j = 65; j <= i; j++) {
                System.out.print((char) j + " ");
            }
            System.out.println();
        }

        // Simple Caesar Cipher
        System.out.print("\nEnter shift value for Caesar Cipher: ");
        int shift = scanner.nextInt();
        String encrypted = caesarCipher(input, shift);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + caesarCipher(encrypted, -shift));

        scanner.close();
    }

    // Caesar Cipher Method
    public static String caesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                ch = (char) ((ch - base + shift + 26) % 26 + base);
            }
            result.append(ch);
        }
        return result.toString();
    }
}


