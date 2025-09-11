import java.util.*;

public class FindAndReplaceDemo {

    public static List<Integer> findOccurrences(String text, String findStr) {
        List<Integer> positions = new ArrayList<>();
        int index = text.indexOf(findStr);
        while (index != -1) {
            positions.add(index);
            index = text.indexOf(findStr, index + 1);
        }
        return positions;
    }

    public static String manualReplace(String text, String findStr, String replaceStr) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
            if (i <= text.length() - findStr.length() &&
                text.substring(i, i + findStr.length()).equals(findStr)) {
                result.append(replaceStr);
                i += findStr.length();
            } else {
                result.append(text.charAt(i));
                i++;
            }
        }
        return result.toString();
    }

    public static boolean compareResults(String text, String findStr, String replaceStr, String manualResult) {
        String builtInResult = text.replace(findStr, replaceStr);
        return manualResult.equals(builtInResult);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the main text: ");
        String text = sc.nextLine();

        System.out.print("Enter the substring to find: ");
        String findStr = sc.nextLine();

        System.out.print("Enter the replacement substring: ");
        String replaceStr = sc.nextLine();

        List<Integer> positions = findOccurrences(text, findStr);

        if (positions.isEmpty()) {
            System.out.println("\nSubstring not found in the text!");
        } else {
            System.out.println("\nOccurrences of \"" + findStr + "\" found at positions: " + positions);
        }

        String manualResult = manualReplace(text, findStr, replaceStr);
        System.out.println("\nManual Replace Result: " + manualResult);

        String builtInResult = text.replace(findStr, replaceStr);
        System.out.println("Built-in Replace Result: " + builtInResult);

        boolean isSame = compareResults(text, findStr, replaceStr, manualResult);
        System.out.println("\nDo both results match? " + (isSame ? "Yes" : "No"));

        sc.close();
    }
}
