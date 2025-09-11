import java.util.*;

public class ExpressionCalculator {

    public static boolean validateExpression(String expr) {
        try {
            List<String> tokens = tokenize(expr);
            int balance = 0;
            for (int i = 0; i < tokens.size(); i++) {
                String t = tokens.get(i);
                if (t.equals("(")) balance++;
                if (t.equals(")")) balance--;
                if (balance < 0) return false;
            }
            if (balance != 0) return false;
            if (tokens.size() == 0) return false;
            String last = tokens.get(tokens.size() - 1);
            if (isOperator(last) || last.equals("(")) return false;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static List<String> tokenize(String expr) {
        List<String> tokens = new ArrayList<>();
        int i = 0;
        int n = expr.length();
        while (i < n) {
            char c = expr.charAt(i);
            int ascii = (int) c;
            if (ascii == 32) { i++; continue; }
            if ((ascii >= 48 && ascii <= 57) || c == '.') {
                int j = i;
                while (j < n) {
                    char d = expr.charAt(j);
                    int ad = (int) d;
                    if ((ad >= 48 && ad <= 57) || d == '.') j++;
                    else break;
                }
                tokens.add(expr.substring(i, j));
                i = j;
                continue;
            }
            if (c == '+' || c == '*' || c == '/' || c == '(' || c == ')') {
                tokens.add(String.valueOf(c));
                i++;
                continue;
            }
            if (c == '-') {
                boolean unary = tokens.isEmpty() || tokens.get(tokens.size() - 1).equals("(") || isOperator(tokens.get(tokens.size() - 1));
                if (unary) {
                    if (i + 1 < n) {
                        char next = expr.charAt(i + 1);
                        int an = (int) next;
                        if ((an >= 48 && an <= 57) || next == '.') {
                            int j = i + 1;
                            while (j < n) {
                                char d = expr.charAt(j);
                                int ad = (int) d;
                                if ((ad >= 48 && ad <= 57) || d == '.') j++;
                                else break;
                            }
                            tokens.add(expr.substring(i, j));
                            i = j;
                            continue;
                        } else if (next == '(') {
                            tokens.add("0");
                            tokens.add("-");
                            i++;
                            continue;
                        } else {
                            tokens.add("-");
                            i++;
                            continue;
                        }
                    } else {
                        tokens.add("-");
                        i++;
                        continue;
                    }
                } else {
                    tokens.add("-");
                    i++;
                    continue;
                }
            }
            throw new IllegalArgumentException("Invalid character: " + c);
        }
        return tokens;
    }

    static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    static String joinTokens(List<String> tokens) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tokens.size(); i++) {
            if (i > 0) sb.append(" ");
            sb.append(tokens.get(i));
        }
        return sb.toString();
    }

    static String formatNumber(double v) {
        double rounded = Math.rint(v);
        if (Math.abs(v - rounded) < 1e-9) return String.valueOf((long) rounded);
        return String.valueOf(v);
    }

    public static String evaluateExpression(String expr, StringBuilder steps) throws Exception {
        List<String> tokens = tokenize(expr);
        steps.append("Original: ").append(expr).append("\n");
        while (tokens.contains("(")) {
            int open = -1;
            for (int i = 0; i < tokens.size(); i++) if (tokens.get(i).equals("(")) open = i;
            if (open == -1) break;
            int close = -1;
            for (int j = open + 1; j < tokens.size(); j++) if (tokens.get(j).equals(")")) { close = j; break; }
            if (close == -1) throw new IllegalArgumentException("Mismatched parentheses");
            List<String> sub = new ArrayList<>();
            for (int k = open + 1; k < close; k++) sub.add(tokens.get(k));
            String subResult = evaluateFlat(sub, steps);
            tokens.set(open, subResult);
            for (int k = 0; k < close - open; k++) tokens.remove(open + 1);
            steps.append("After evaluating parentheses -> ").append(joinTokens(tokens)).append("\n");
        }
        String finalRes = evaluateFlat(tokens, steps);
        steps.append("Final Result: ").append(finalRes).append("\n");
        return finalRes;
    }

    static String evaluateFlat(List<String> tokens, StringBuilder steps) throws Exception {
        List<String> t = new ArrayList<>(tokens);
        int i = 0;
        while (i < t.size()) {
            String op = t.get(i);
            if (op.equals("*") || op.equals("/")) {
                double a = Double.parseDouble(t.get(i - 1));
                double b = Double.parseDouble(t.get(i + 1));
                double r;
                if (op.equals("*")) r = a * b;
                else {
                    if (Math.abs(b) < 1e-12) throw new ArithmeticException("Division by zero");
                    r = a / b;
                }
                String rStr = formatNumber(r);
                t.set(i - 1, rStr);
                t.remove(i);
                t.remove(i);
                steps.append("Compute ").append(a).append(" ").append(op).append(" ").append(b).append(" = ").append(rStr).append("  => ").append(joinTokens(t)).append("\n");
                i = Math.max(i - 1, 0);
            } else i++;
        }
        i = 0;
        while (i < t.size()) {
            String op = t.get(i);
            if (op.equals("+") || op.equals("-")) {
                double a = Double.parseDouble(t.get(i - 1));
                double b = Double.parseDouble(t.get(i + 1));
                double r = op.equals("+") ? a + b : a - b;
                String rStr = formatNumber(r);
                t.set(i - 1, rStr);
                t.remove(i);
                t.remove(i);
                steps.append("Compute ").append(a).append(" ").append(op).append(" ").append(b).append(" = ").append(rStr).append("  => ").append(joinTokens(t)).append("\n");
                i = Math.max(i - 1, 0);
            } else i++;
        }
        if (t.size() == 0) throw new IllegalStateException("Empty expression");
        return t.get(0);
    }

    public static void displayCalculation(String expr) {
        StringBuilder steps = new StringBuilder();
        try {
            if (!validateExpression(expr)) {
                System.out.println("Expression invalid: " + expr);
                return;
            }
            String result = evaluateExpression(expr, steps);
            System.out.println("\nExpression: " + expr);
            System.out.println("Steps:");
            System.out.println(steps.toString());
            System.out.println("Validated Result: " + result);
        } catch (ArithmeticException ae) {
            System.out.println("Error while evaluating '" + expr + "': " + ae.getMessage());
        } catch (Exception e) {
            System.out.println("Error while evaluating '" + expr + "': " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of expressions to evaluate: ");
        int n = 1;
        try { n = Integer.parseInt(sc.nextLine().trim()); } catch (Exception e) { n = 1; }
        for (int i = 1; i <= n; i++) {
            System.out.print("Enter expression " + i + ": ");
            String expr = sc.nextLine();
            displayCalculation(expr);
        }
        sc.close();
    }
}
