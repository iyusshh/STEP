import java.util.*;

class Patient {
    String patientId, patientName, gender, contactInfo;
    int age;
    String[] medicalHistory;
    String[] currentTreatments;
    static int totalPatients = 0;

    Patient(String patientId, String patientName, int age, String gender, String contactInfo) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.age = age;
        this.gender = gender;
        this.contactInfo = contactInfo;
        this.medicalHistory = new String[10];
        this.currentTreatments = new String[5];
        totalPatients++;
    }
}

class Doctor {
    String doctorId, doctorName, specialization;
    String[] availableSlots;
    int patientsHandled;
    double consultationFee;

    Doctor(String doctorId, String doctorName, String specialization, double consultationFee) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.consultationFee = consultationFee;
        this.availableSlots = new String[] { "10:00 AM", "11:00 AM", "12:00 PM" };
        this.patientsHandled = 0;
    }
}

class Appointment {
    String appointmentId;
    Patient patient;
    Doctor doctor;
    String appointmentDate, appointmentTime, status;
    String appointmentType;
    double billAmount;

    static int totalAppointments = 0;
    static double totalRevenue = 0;

    Appointment(String appointmentId, Patient patient, Doctor doctor, String date, String time, String type) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = date;
        this.appointmentTime = time;
        this.appointmentType = type;
        this.status = "Scheduled";
        this.billAmount = calculateBill();
        doctor.patientsHandled++;
        totalAppointments++;
        totalRevenue += billAmount;
    }

    private double calculateBill() {
        switch (appointmentType.toLowerCase()) {
            case "consultation": return doctor.consultationFee;
            case "follow-up": return doctor.consultationFee * 0.5;
            case "emergency": return doctor.consultationFee * 2;
            default: return doctor.consultationFee;
        }
    }

    void cancelAppointment() {
        if (!status.equals("Cancelled")) {
            status = "Cancelled";
            totalRevenue -= billAmount;
            System.out.println("Appointment " + appointmentId + " cancelled successfully!");
        } else {
            System.out.println("Appointment is already cancelled!");
        }
    }

    void generateBill() {
        System.out.println("\n--- BILL DETAILS ---");
        System.out.println("Appointment ID: " + appointmentId);
        System.out.println("Patient: " + patient.patientName);
        System.out.println("Doctor: " + doctor.doctorName);
        System.out.println("Type: " + appointmentType);
        System.out.println("Bill Amount: ₹" + billAmount);
    }
}

public class HospitalManagementSystem {
    static String hospitalName = "CityCare Hospital";
    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<Appointment> appointments = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Pre-adding doctors
        doctors.add(new Doctor("D101", "Dr. Sharma", "Cardiology", 1000));
        doctors.add(new Doctor("D102", "Dr. Verma", "Neurology", 1200));
        doctors.add(new Doctor("D103", "Dr. Mehta", "Orthopedics", 900));

        while (true) {
            System.out.println("\n=== " + hospitalName + " Management System ===");
            System.out.println("1. Add Patient");
            System.out.println("2. Schedule Appointment");
            System.out.println("3. Cancel Appointment");
            System.out.println("4. Generate Bill");
            System.out.println("5. Generate Hospital Report");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addPatient(sc);
                case 2 -> scheduleAppointment(sc);
                case 3 -> cancelAppointment(sc);
                case 4 -> generateBill(sc);
                case 5 -> generateHospitalReport();
                case 6 -> {
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    static void addPatient(Scanner sc) {
        System.out.print("Enter Patient ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Patient Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Gender: ");
        String gender = sc.nextLine();
        System.out.print("Enter Contact Info: ");
        String contact = sc.nextLine();

        patients.add(new Patient(id, name, age, gender, contact));
        System.out.println("Patient added successfully!");
    }

    static void scheduleAppointment(Scanner sc) {
        System.out.print("Enter Appointment ID: ");
        String appId = sc.nextLine();

        System.out.print("Enter Patient ID: ");
        String pid = sc.nextLine();
        Patient patient = findPatient(pid);
        if (patient == null) {
            System.out.println("Patient not found!");
            return;
        }

        System.out.println("Available Doctors:");
        for (int i = 0; i < doctors.size(); i++)
            System.out.println((i + 1) + ". " + doctors.get(i).doctorName + " (" + doctors.get(i).specialization + ")");
        System.out.print("Choose Doctor (1-" + doctors.size() + "): ");
        int docChoice = sc.nextInt();
        sc.nextLine();
        Doctor doctor = doctors.get(docChoice - 1);

        System.out.print("Enter Appointment Date (DD-MM-YYYY): ");
        String date = sc.nextLine();
        System.out.print("Enter Appointment Time: ");
        String time = sc.nextLine();
        System.out.print("Enter Appointment Type (Consultation/Follow-up/Emergency): ");
        String type = sc.nextLine();

        Appointment appointment = new Appointment(appId, patient, doctor, date, time, type);
        appointments.add(appointment);
        System.out.println("Appointment scheduled successfully!");
    }

    static void cancelAppointment(Scanner sc) {
        System.out.print("Enter Appointment ID: ");
        String id = sc.nextLine();
        Appointment app = findAppointment(id);
        if (app != null) app.cancelAppointment();
        else System.out.println("Appointment not found!");
    }

    static void generateBill(Scanner sc) {
        System.out.print("Enter Appointment ID: ");
        String id = sc.nextLine();
        Appointment app = findAppointment(id);
        if (app != null) app.generateBill();
        else System.out.println("Appointment not found!");
    }

    static void generateHospitalReport() {
        System.out.println("\n=== HOSPITAL REPORT ===");
        System.out.println("Hospital Name: " + hospitalName);
        System.out.println("Total Patients: " + Patient.totalPatients);
        System.out.println("Total Appointments: " + Appointment.totalAppointments);
        System.out.println("Total Revenue: ₹" + Appointment.totalRevenue);

        Doctor topDoctor = doctors.stream().max(Comparator.comparingInt(d -> d.patientsHandled)).orElse(null);
        if (topDoctor != null)
            System.out.println("Top Doctor: " + topDoctor.doctorName + " (" + topDoctor.patientsHandled + " patients)");
    }

    static Patient findPatient(String id) {
        return patients.stream().filter(p -> p.patientId.equals(id)).findFirst().orElse(null);
    }

    static Appointment findAppointment(String id) {
        return appointments.stream().filter(a -> a.appointmentId.equals(id)).findFirst().orElse(null);
    }
}
