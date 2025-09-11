import java.util.*;

public class EmailAnalyzer {

    public static boolean isValidEmail(String email) {
        int atPos = email.indexOf('@');
        int lastAt = email.lastIndexOf('@');
        if (atPos == -1 || atPos != lastAt) return false;
        int dotPos = email.indexOf('.', atPos);
        if (dotPos == -1) return false;
        String username = email.substring(0, atPos);
        String domain = email.substring(atPos + 1);
        if (username.isEmpty() || domain.isEmpty()) return false;
        return true;
    }

    public static String[] extractComponents(String email) {
        int atPos = email.indexOf('@');
        String username = email.substring(0, atPos);
        String domain = email.substring(atPos + 1);
        int dotPos = domain.lastIndexOf('.');
        String domainName = dotPos != -1 ? domain.substring(0, dotPos) : domain;
        String extension = dotPos != -1 ? domain.substring(dotPos + 1) : "";
        return new String[]{username, domain, domainName, extension};
    }

    public static void displayTable(List<String> emails) {
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.printf("%-30s | %-15s | %-20s | %-15s | %-10s | %-8s\n", 
                "Email", "Username", "Domain", "Domain Name", "Extension", "Valid?");
        System.out.println("--------------------------------------------------------------------------------");
        for (String email : emails) {
            if (isValidEmail(email)) {
                String[] parts = extractComponents(email);
                System.out.printf("%-30s | %-15s | %-20s | %-15s | %-10s | %-8s\n",
                        email, parts[0], parts[1], parts[2], parts[3], "Yes");
            } else {
                System.out.printf("%-30s | %-15s | %-20s | %-15s | %-10s | %-8s\n",
                        email, "-", "-", "-", "-", "No");
            }
        }
        System.out.println("--------------------------------------------------------------------------------");
    }

    public static void analyzeStatistics(List<String> emails) {
        int validCount = 0, invalidCount = 0, totalUsernameLength = 0;
        Map<String, Integer> domainCount = new HashMap<>();

        for (String email : emails) {
            if (isValidEmail(email)) {
                validCount++;
                String[] parts = extractComponents(email);
                totalUsernameLength += parts[0].length();
                domainCount.put(parts[1], domainCount.getOrDefault(parts[1], 0) + 1);
            } else invalidCount++;
        }

        String mostCommonDomain = "-";
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : domainCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostCommonDomain = entry.getKey();
            }
        }

        double avgUsernameLength = validCount > 0 ? (double) totalUsernameLength / validCount : 0;

        System.out.println("\n================= Email Statistics =================");
        System.out.println("Total Emails:          " + emails.size());
        System.out.println("Valid Emails:          " + validCount);
        System.out.println("Invalid Emails:        " + invalidCount);
        System.out.println("Most Common Domain:    " + mostCommonDomain);
        System.out.printf("Average Username Len:  %.2f\n", avgUsernameLength);
        System.out.println("====================================================");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> emails = new ArrayList<>();

        System.out.print("Enter number of email addresses: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter email " + i + ": ");
            emails.add(sc.nextLine().trim());
        }

        displayTable(emails);
        analyzeStatistics(emails);

        sc.close();
    }
}
