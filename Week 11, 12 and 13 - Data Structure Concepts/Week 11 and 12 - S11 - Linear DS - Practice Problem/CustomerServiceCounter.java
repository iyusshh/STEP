import java.util.*;

public class CustomerServiceCounter {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Command (ARRIVE <name>/SERVE/STATUS/EXIT): ");
            String cmd = sc.next();

            if (cmd.equalsIgnoreCase("ARRIVE")) {
                String name = sc.next();
                queue.add(name);
            } 
            else if (cmd.equalsIgnoreCase("SERVE")) {
                if (!queue.isEmpty()) {
                    System.out.println("Serving " + queue.poll());
                } else {
                    System.out.println("No customers waiting!");
                }
            } 
            else if (cmd.equalsIgnoreCase("STATUS")) {
                if (queue.isEmpty()) {
                    System.out.println("No one is waiting!");
                } else {
                    System.out.println("Waiting: " + queue);
                }
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

