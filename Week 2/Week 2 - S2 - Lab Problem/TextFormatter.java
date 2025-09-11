import java.util.*;

public class TextFormatter {

    // Split text into words manually without using split()
    public static List<String> getWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
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

    // Left-justify text using StringBuilder
    public static List<String> justifyText(List<String> words, int width) {
        List<String> lines = new ArrayList<>();
        int index = 0;

        while (index < words.size()) {
            int lineLength = words.get(index).length();
            int last = index + 1;

            while (last < words.size()) {
                if (lineLength + words.get(last).length() + 1 > width) break;
                lineLength += words.get(last).length() + 1;
                last++;
            }

            StringBuilder sb = new StringBuilder();
            int gaps = last - index - 1;

            if (last == words.size() || gaps == 0) {
                for (int i = index; i < last; i++) {
                    sb.append(words.get(i)).append(" ");
                }
                sb.deleteCharAt(sb.length() - 1);
                while (sb.length() < width) sb.append(" ");
            } else {
                int totalSpaces = width - lineLength + gaps;
                int spaceBetween = totalSpaces / gaps;
                int extraSpaces = totalSpaces % gaps;

                for (int i = index; i < last - 1; i++) {
                    sb.append(words.get(i));
                    for (int s = 0; s < spaceBetween + 1; s++) sb.append(" ");
                    if (extraSpaces-- > 0) sb.append(" ");
                }
                sb.append(words.get(last - 1));
            }

            lines.add(sb.toString());
            index = last;
        }
        return lines;
    }

    // Center-align text using StringBuilder
    public static List<String> centerAlign(List<String> words, int width) {
        List<String> lines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (sb.length() + word.length() + 1 > width) {
                String line = sb.toString().trim();
                int padding = (width - line.length()) / 2;
                StringBuilder centered = new StringBuilder();
                for (int i = 0; i < padding; i++) centered.append(" ");
                centered.append(line);
                while (centered.length() < width) centered.append(" ");
                lines.add(centered.toString());
                sb.setLength(0);
            }
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) {
            String line = sb.toString().trim();
            int padding = (width - line.length()) / 2;
            StringBuilder centered = new StringBuilder();
            for (int i = 0; i < padding; i++) centered.append(" ");
            centered.append(line);
            while (centered.length() < width) centered.append(" ");
            lines.add(centered.toString());
        }
        return lines;
    }

    // Performance comparison: StringBuilder vs String concatenation
    public static void comparePerformance(List<String> words, int width) {
        long start1 = System.nanoTime();
        justifyText(words, width);
        long end1 = System.nanoTime();
        long sbTime = end1 - start1;

        long start2 = System.nanoTime();
        String result = "";
        int lineLen = 0;
        for (String word : words) {
            if (lineLen + word.length() + 1 > width) {
                result += "\n";
                lineLen = 0;
            }
            result += word + " ";
            lineLen += word.length() + 1;
        }
        long end2 = System.nanoTime();
        long concatTime = end2 - start2;

        System.out.println("\n============= Performance Comparison =============");
        System.out.println("StringBuilder Time: " + sbTime + " ns");
        System.out.println("String Concatenation Time: " + concatTime + " ns");
        if (sbTime < concatTime)
            System.out.println("Winner: StringBuilder (Faster)");
        else
            System.out.println("Winner: String Concatenation (Faster)");
        System.out.println("==================================================");
    }

    // Display formatted text with line numbers & character count
    public static void displayFormattedText(List<String> lines, String title) {
        System.out.println("\n========= " + title + " =========");
        int lineNo = 1;
        for (String line : lines) {
            System.out.printf("Line %2d (%2d chars): %s\n", lineNo++, line.length(), line);
        }
        System.out.println("===================================");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text to format: ");
        String text = sc.nextLine();

        System.out.print("Enter desired line width: ");
        int width = sc.nextInt();

        List<String> words = getWords(text);

        List<String> justified = justifyText(words, width);
        List<String> centered = centerAlign(words, width);

        System.out.println("\n========= Original Text =========");
        System.out.println(text);

        displayFormattedText(justified, "Justified Text");
        displayFormattedText(centered, "Center Aligned Text");

        comparePerformance(words, width);

        sc.close();
    }
}
