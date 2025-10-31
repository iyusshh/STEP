import java.util.*;

public class UndoFeature {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Command (TYPE <word>/UNDO/PRINT/EXIT): ");
            String cmd = sc.next();

            if (cmd.equalsIgnoreCase("TYPE")) {
                String word = sc.next();
                stack.push(word);
            } else if (cmd.equalsIgnoreCase("UNDO")) {
                if (!stack.isEmpty()) stack.pop();
                else System.out.println("Nothing to undo!");
            } else if (cmd.equalsIgnoreCase("PRINT")) {
                System.out.println(String.join(" ", stack));
            } else if (cmd.equalsIgnoreCase("EXIT")) {
                break;
            } else {
                System.out.println("Invalid command!");
            }
        }
    }
}
