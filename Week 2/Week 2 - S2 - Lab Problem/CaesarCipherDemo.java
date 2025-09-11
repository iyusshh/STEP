import java.util.*;

public class CaesarCipherDemo {

    public static String encrypt(String text, int shift) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                encrypted.append((char) ((c - 'A' + shift) % 26 + 'A'));
            } else if (c >= 'a' && c <= 'z') {
                encrypted.append((char) ((c - 'a' + shift) % 26 + 'a'));
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26));
    }

    public static void displayAsciiValues(String label, String text) {
        System.out.print(label + " ASCII: ");
        for (int i = 0; i < text.length(); i++) {
            System.out.print((int) text.charAt(i));
            if (i < text.length() - 1) System.out.print(" ");
        }
        System.out.println();
    }

    public static boolean validate(String original, String decrypted) {
        return original.equals(decrypted);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text to encrypt: ");
        String text = sc.nextLine();

        System.out.print("Enter shift value: ");
        int shift = sc.nextInt();

        String encrypted = encrypt(text, shift);
        String decrypted = decrypt(encrypted, shift);

        System.out.println("\n----------------------------");
        System.out.println("Original Text:   " + text);
        displayAsciiValues("Original", text);
        System.out.println("Encrypted Text:  " + encrypted);
        displayAsciiValues("Encrypted", encrypted);
        System.out.println("Decrypted Text:  " + decrypted);
        displayAsciiValues("Decrypted", decrypted);
        System.out.println("Decryption Valid: " + (validate(text, decrypted) ? "Yes" : "No"));
        System.out.println("----------------------------");

        sc.close();
    }
}
