import java.util.*;

class StackUsingList {
    private List<Integer> stackList = new ArrayList<>();

    public void push(int item) {
        stackList.add(item);
        System.out.println(item + " pushed to stack");
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow!");
            return -1;
        }
        return stackList.remove(stackList.size() - 1);
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        return stackList.get(stackList.size() - 1);
    }

    public boolean isEmpty() {
        return stackList.isEmpty();
    }

    public void display() {
        System.out.println("Stack: " + stackList);
    }

    public static void main(String[] args) {
        StackUsingList stack = new StackUsingList();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter command (PUSH <num>/POP/PEEK/DISPLAY/EXIT): ");
            String cmd = sc.next();

            if (cmd.equalsIgnoreCase("PUSH")) {
                int num = sc.nextInt();
                stack.push(num);
            } 
            else if (cmd.equalsIgnoreCase("POP")) {
                int popped = stack.pop();
                if (popped != -1)
                    System.out.println("Popped: " + popped);
            } 
            else if (cmd.equalsIgnoreCase("PEEK")) {
                int top = stack.peek();
                if (top != -1)
                    System.out.println("Top element: " + top);
            } 
            else if (cmd.equalsIgnoreCase("DISPLAY")) {
                stack.display();
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

