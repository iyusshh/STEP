import java.util.Scanner;

public class TextCompression {

    static char[] uniqueChars;
    static int[] freq;
    static String[] codes;

    static void countCharFrequency(String text) {
        uniqueChars = new char[text.length()];
        freq = new int[text.length()];
        int uniqueCount = 0;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            int index = -1;

            for (int j = 0; j < uniqueCount; j++) {
                if (uniqueChars[j] == ch) {
                    index = j;
                    break;
                }
            }

            if (index == -1) {
                uniqueChars[uniqueCount] = ch;
                freq[uniqueCount] = 1;
                uniqueCount++;
            } else {
                freq[index]++;
            }
        }

        char[] tempChars = new char[uniqueCount];
        int[] tempFreq = new int[uniqueCount];
        System.arraycopy(uniqueChars, 0, tempChars, 0, uniqueCount);
        System.arraycopy(freq, 0, tempFreq, 0, uniqueCount);
        uniqueChars = tempChars;
        freq = tempFreq;
    }

    static void generateCompressionCodes() {
        codes = new String[uniqueChars.length];

        int[] sortedIndices = new int[uniqueChars.length];
        for (int i = 0; i < uniqueChars.length; i++) {
            sortedIndices[i] = i;
        }

        for (int i = 0; i < sortedIndices.length - 1; i++) {
            for (int j = i + 1; j < sortedIndices.length; j++) {
                if (freq[sortedIndices[i]] < freq[sortedIndices[j]]) {
                    int temp = sortedIndices[i];
                    sortedIndices[i] = sortedIndices[j];
                    sortedIndices[j] = temp;
                }
            }
        }

        for (int i = 0; i < sortedIndices.length; i++) {
            codes[sortedIndices[i]] = Integer.toBinaryString(i + 1);
        }
    }

    static String compressText(String text) {
        StringBuilder compressed = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            for (int j = 0; j < uniqueChars.length; j++) {
                if (uniqueChars[j] == ch) {
                    compressed.append(codes[j]).append(" ");
                    break;
                }
            }
        }
        return compressed.toString().trim();
    }

    static String decompressText(String compressed) {
        StringBuilder decompressed = new StringBuilder();
        String[] tokens = compressed.split(" ");
        for (String token : tokens) {
            for (int j = 0; j < codes.length; j++) {
                if (codes[j].equals(token)) {
                    decompressed.append(uniqueChars[j]);
                    break;
                }
            }
        }
        return decompressed.toString();
    }

    static void displayCompressionAnalysis(String text, String compressed, String decompressed) {
        System.out.println("\n--- CHARACTER FREQUENCY TABLE ---");
        System.out.printf("%-10s%-10s%-10s\n", "Char", "Freq", "Code");
        System.out.println("----------------------------------");
        for (int i = 0; i < uniqueChars.length; i++) {
            System.out.printf("%-10s%-10d%-10s\n", uniqueChars[i], freq[i], codes[i]);
        }

        System.out.println("\n--- COMPRESSION RESULTS ---");
        System.out.println("Original Text      : " + text);
        System.out.println("Compressed Text    : " + compressed);
        System.out.println("Decompressed Text  : " + decompressed);

        double originalSize = text.length() * 8;
        double compressedSize = compressed.replace(" ", "").length();
        double efficiency = ((originalSize - compressedSize) / originalSize) * 100;

        System.out.println("Original Size (bits): " + (int) originalSize);
        System.out.println("Compressed Size(bits): " + (int) compressedSize);
        System.out.printf("Compression Efficiency: %.2f%%\n", efficiency);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text to compress: ");
        String text = sc.nextLine();

        countCharFrequency(text);
        generateCompressionCodes();
        String compressed = compressText(text);
        String decompressed = decompressText(compressed);

        displayCompressionAnalysis(text, compressed, decompressed);

        sc.close();
    }
}