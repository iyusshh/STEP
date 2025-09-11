import java.util.*;

public class TextCaseConversion {

    public static char toUpper(char c) {
        if (c >= 97 && c <= 122) return (char)(c - 32);
        return c;
    }

    public static char toLower(char c) {
        if (c >= 65 && c <= 90) return (char)(c + 32);
        return c;
    }

    public static String toUpperCase(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) sb.append(toUpper(text.charAt(i)));
        return sb.toString();
    }

    public static String toLowerCase(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) sb.append(toLower(text.charAt(i)));
        return sb.toString();
    }

    public static String toTitleCase(String text) {
        StringBuilder sb = new StringBuilder();
        boolean newWord = true;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == ' ') {
                sb.append(c);
                newWord = true;
            } else {
                if (newWord) {
                    sb.append(toUpper(c));
                    newWord = false;
                } else sb.append(toLower(c));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();

        String manualUpper = toUpperCase(text);
        String manualLower = toLowerCase(text);
        String manualTitle = toTitleCase(text);

        String builtInUpper = text.toUpperCase();
        String builtInLower = text.toLowerCase();

        System.out.println("\n------------------------------");
        System.out.printf("%-15s | %s\n", "Conversion", "Result");
        System.out.println("------------------------------");
        System.out.printf("%-15s | %s\n", "Manual Upper", manualUpper);
        System.out.printf("%-15s | %s\n", "ABuilt-in Upper", builtInUpper);
        System.out.printf("%-15s | %s\n", "Manual Lower", manualLower);
        System.out.printf("%-15s | %s\n", "Built-in Lower", builtInLower);
        System.out.printf("%-15s | %s\n", "Manual Title", manualTitle);
        System.out.println("------------------------------");

        sc.close();
    }
}

