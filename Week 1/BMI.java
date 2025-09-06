import java.util.Scanner;

public class BMI{

    public static String[][] calculateBMIStatus(double[][] healthData) {
        String[][] results = new String[10][4];

        for (int i = 0; i < healthData.length; i++) {
            double weight = healthData[i][0];
            double heightCm = healthData[i][1];
            double heightM = heightCm / 100.0;
            double bmi = weight / (heightM * heightM);
            
            String status;
            if (bmi <= 18.4) {
                status = "Underweight";
            } else if (bmi >= 18.5 && bmi <= 24.9) {
                status = "Normal";
            } else if (bmi >= 25.0 && bmi <= 39.9) {
                status = "Overweight";
            } else {
                status = "Obese";
            }
            
            results[i][0] = String.valueOf(heightCm);
            results[i][1] = String.valueOf(weight);
            results[i][2] = String.format("%.2f", bmi);
            results[i][3] = status;
        }
        return results;
    }

    public static void displayResults(String[][] results) {
        System.out.println("\n----------------------------------------------------------------");
        System.out.printf("%-15s %-15s %-15s %-15s%n", "Height (cm)", "Weight (kg)", "BMI", "Status");
        System.out.println("----------------------------------------------------------------");
        
        for (String[] personData : results) {
            System.out.printf("%-15s %-15s %-15s %-15s%n", personData[0], personData[1], personData[2], personData[3]);
        }
        System.out.println("----------------------------------------------------------------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[][] healthData = new double[10][2];

        System.out.println("Enter the Weight (kg) and Height (cm) for 10 team members.");
        
        for (int i = 0; i < 10; i++) {
            System.out.println("\n--- Person " + (i + 1) + " ---");
            System.out.print("Enter weight (kg): ");
            healthData[i][0] = scanner.nextDouble();
            
            System.out.print("Enter height (cm): ");
            healthData[i][1] = scanner.nextDouble();
        }

        String[][] finalResults = calculateBMIStatus(healthData);
        
        displayResults(finalResults);
        
        scanner.close();
    }
}