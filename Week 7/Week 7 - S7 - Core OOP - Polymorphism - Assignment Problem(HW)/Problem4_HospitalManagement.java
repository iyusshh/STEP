class MedicalStaff {
    void shiftSchedule() { System.out.println("Shift scheduled."); }
}
class Doctor extends MedicalStaff {
    void diagnose() { System.out.println("Doctor diagnoses patients."); }
}
class Nurse extends MedicalStaff {
    void assist() { System.out.println("Nurse assists in procedures."); }
}
class Technician extends MedicalStaff {
    void operateEquip() { System.out.println("Technician runs equipment."); }
}
class Administrator extends MedicalStaff {
    void manageRecords() { System.out.println("Administrator manages hospital records."); }
}

public class Problem4_HospitalManagement {
    public static void main(String[] args) {
        MedicalStaff staff = new Doctor(); // upcasting
        staff.shiftSchedule();             // only common method accessible
    }
}
