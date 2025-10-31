import java.util.*;

public class BrowserNavigation {
    public static void main(String[] args) {
        Stack<String> backStack = new Stack<>();
        Stack<String> forwardStack = new Stack<>();
        Scanner sc = new Scanner(System.in);

        String current = "Home";

        while (true) {
            System.out.print("Command (VISIT/BACK/FORWARD/PRINT/EXIT): ");
            String cmd = sc.next();

            if (cmd.equalsIgnoreCase("VISIT")) {
                String page = sc.next();
                backStack.push(current);
                current = page;
                forwardStack.clear();
            } 
            else if (cmd.equalsIgnoreCase("BACK")) {
                if (!backStack.isEmpty()) {
                    forwardStack.push(current);
                    current = backStack.pop();
                } else {
                    System.out.println("No previous pages!");
                }
            } 
            else if (cmd.equalsIgnoreCase("FORWARD")) {
                if (!forwardStack.isEmpty()) {
                    backStack.push(current);
                    current = forwardStack.pop();
                } else {
                    System.out.println("No forward pages!");
                }
            } 
            else if (cmd.equalsIgnoreCase("PRINT")) {
                System.out.println("Current Page: " + current);
            } 
            else if (cmd.equalsIgnoreCase("EXIT")) {
                break;
            } 
            else {
                System.out.println("Invalid command!");
            }
        }
        sc.close();
    }
}
