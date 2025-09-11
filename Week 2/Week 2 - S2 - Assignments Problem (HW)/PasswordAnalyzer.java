import java.util.*;

public class PasswordAnalyzer {

    // Analyze password using ASCII values
    public static int[] analyzePassword(String password) {
        int upper = 0, lower = 0, digits = 0, special = 0;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            int ascii = (int) c;

            if (ascii >= 65 && ascii <= 90) upper++;          // Uppercase
            else if (ascii >= 97 && ascii <= 122) lower++;    // Lowercase
            else if (ascii >= 48 && ascii <= 57) digits++;    // Digits
            else if (ascii >= 33 && ascii <= 126) special++;  // Special chars
        }
        return new int[]{upper, lower, digits, special};
    }

    // Check for common weak patterns
    public static boolean hasCommonPattern(String password) {
        String lowerPwd = password.toLowerCase();
        String[] weakPatterns = {"123", "abc", "qwerty", "password", "admin"};
        for (String pattern : weakPatterns) {
            if (lowerPwd.contains(pattern)) return true;
        }
        return false;
    }

    // Calculate password strength score
    public static int calculateScore(String password, int upper, int lower, int digits, int special) {
        int score = 0;

        // Length-based points
        if (password.length() > 8) score += (password.length() - 8) * 2;

        // Variety-based points
        if (upper > 0) score += 10;
        if (lower > 0) score += 10;
        if (digits > 0) score += 10;
        if (special > 0) score += 10;

        // Deduct points for common patterns
        if (hasCommonPattern(password)) score -= 15;

        return Math.max(score, 0);
    }

    // Return password strength level
    public static String getStrengthLevel(int score) {
        if (score <= 20) return "Weak";
        else if (score <= 50) return "Medium";
        else return "Strong";
    }

    // Generate strong random password using StringBuilder
    public static String generatePassword(int length) {
        if (length < 8) length = 8;

        String upperChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerChars = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specials = "!@#$%^&*()-_=+[]{}|;:,.<>?";

        String allChars = upperChars + lowerChars + digits + specials;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        sb.append(upperChars.charAt(random.nextInt(upperChars.length())));
        sb.append(lowerChars.charAt(random.nextInt(lowerChars.length())));
        sb.append(digits.charAt(random.nextInt(digits.length())));
        sb.append(specials.charAt(random.nextInt(specials.length())));

        for (int i = 4; i < length; i++) {
            sb.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        List<Character> chars = new ArrayList<>();
        for (char c : sb.toString().toCharArray()) chars.add(c);
        Collections.shuffle(chars);
        StringBuilder shuffled = new StringBuilder();
        for (char c : chars) shuffled.append(c);

        return shuffled.toString();
    }

    public static void displayResults(List<String> passwords) {
        System.out.println("\n================ PASSWORD STRENGTH ANALYSIS =================");
        System.out.printf("%-20s %-8s %-8s %-8s %-8s %-10s %-8s %-10s\n",
                "Password", "Length", "Upper", "Lower", "Digits", "Special", "Score", "Strength");
        System.out.println("--------------------------------------------------------------------------");

        for (String pwd : passwords) {
            int[] counts = analyzePassword(pwd);
            int upper = counts[0], lower = counts[1], digits = counts[2], special = counts[3];
            int score = calculateScore(pwd, upper, lower, digits, special);
            String strength = getStrengthLevel(score);

            System.out.printf("%-20s %-8d %-8d %-8d %-8d %-10d %-8d %-10s\n",
                    pwd, pwd.length(), upper, lower, digits, special, score, strength);
        }
        System.out.println("==========================================================================");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of passwords to analyze: ");
        int n = sc.nextInt();
        sc.nextLine();

        List<String> passwords = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            System.out.print("Enter password " + i + ": ");
            passwords.add(sc.nextLine());
        }

        displayResults(passwords);

        System.out.print("\nEnter desired length for a strong password: ");
        int length = sc.nextInt();
        String strongPassword = generatePassword(length);

        System.out.println("\nGenerated Strong Password: " + strongPassword);

        sc.close();
    }
}