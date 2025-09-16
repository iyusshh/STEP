import java.util.Scanner;
public class Ascii {

    public static char findFirstNonRepeatingChar(String text) {
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (text.indexOf(currentChar) == text.lastIndexOf(currentChar)) {
                return currentChar;
            }
        }
        return '\0';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String inputText = scanner.nextLine();

        char result = findFirstNonRepeatingChar(inputText);

        if (result != '\0') {
            System.out.println("The first non-repeating character is: " + result);
        } else {
            System.out.println("There are no non-repeating characters in the string.");
        }

        scanner.close();
    }
}