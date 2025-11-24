package hospital.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the Hospital Management System.
 * Provides an interactive menu-driven interface for managing the hospital.
 */
public class Main {
    private static HospitalManagementSystem hospitalSystem;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        hospitalSystem = new HospitalManagementSystem();
        
        // Initialize with some sample data
        initializeSampleData();
        
        // Display welcome message
        DisplayUtility.printHeader("Hospital Management System");
        DisplayUtility.printMessage("Welcome to the Hospital Management System!", true);
        
        // Main menu loop
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    registerDoctor();
                    break;
                case 3:
                    bookAppointment();
                    break;
                case 4:
                    cancelAppointment();
                    break;
                case 5:
                    viewDoctorSchedule();
                    break;
                case 6:
                    viewPatientSchedule();
                    break;
                case 7:
                    updatePatientDiagnosis();
                    break;
                case 8:
                    viewMedicalRecords();
                    break;
                case 9:
                    displayAllPatients();
                    break;
                case 10:
                    displayAllDoctors();
                    break;
                case 11:
                    displayAllStaff();
                    break;
                case 12:
                    running = false;
                    DisplayUtility.printMessage("Thank you for using the Hospital Management System!", true);
                    break;
                default:
                    DisplayUtility.printMessage("Invalid choice. Please try again.", false);
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    /**
     * Displays the main menu.
     */
    private static void displayMainMenu() {
        String[] options = {
            "Register Patient (Staff only)",
            "Register Doctor (Staff only)",
            "Book Appointment",
            "Cancel Appointment",
            "View Doctor Schedule",
            "View Patient Schedule",
            "Update Patient Diagnosis (Doctor only)",
            "View Medical Records",
            "Display All Patients",
            "Display All Doctors",
            "Display All Staff",
            "Exit"
        };
        DisplayUtility.printMenu("Main Menu", options);
    }
    
    /**
     * Registers a new patient (staff only).
     */
    private static void registerPatient() {
        DisplayUtility.printHeader("Register Patient");
        
        Staff staff = getStaffInput();
        if (staff == null) return;
        
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine().trim();
        
        int age = getIntInput("Enter patient age: ");
        
        System.out.print("Enter contact details: ");
        String contact = scanner.nextLine().trim();
        
        System.out.print("Enter medical history: ");
        String history = scanner.nextLine().trim();
        
        System.out.print("Enter initial diagnosis: ");
        String diagnosis = scanner.nextLine().trim();
        
        Patient patient = new Patient(name, age, contact, history, diagnosis);
        hospitalSystem.registerPatient(staff, patient);
    }
    
    /**
     * Registers a new doctor (staff only).
     */
    private static void registerDoctor() {
        DisplayUtility.printHeader("Register Doctor");
        
        Staff staff = getStaffInput();
        if (staff == null) return;
        
        System.out.print("Enter doctor name: ");
        String name = scanner.nextLine().trim();
        
        int age = getIntInput("Enter doctor age: ");
        
        System.out.print("Enter contact details: ");
        String contact = scanner.nextLine().trim();
        
        System.out.print("Enter specialization: ");
        String specialization = scanner.nextLine().trim();
        
        System.out.print("Is doctor available? (yes/no): ");
        String availableStr = scanner.nextLine().trim().toLowerCase();
        boolean available = availableStr.equals("yes") || availableStr.equals("y");
        
        Doctor doctor = new Doctor(name, age, contact, specialization, available);
        hospitalSystem.registerDoctor(staff, doctor);
    }
    
    /**
     * Books an appointment.
     */
    private static void bookAppointment() {
        DisplayUtility.printHeader("Book Appointment");
        
        Patient patient = getPatientInput();
        if (patient == null) return;
        
        Doctor doctor = getDoctorInput();
        if (doctor == null) return;
        
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String date = scanner.nextLine().trim();
        
        System.out.print("Enter appointment time (HH:MM AM/PM): ");
        String time = scanner.nextLine().trim();
        
        hospitalSystem.bookAppointment(patient, doctor, date, time);
    }
    
    /**
     * Cancels an appointment.
     */
    private static void cancelAppointment() {
        DisplayUtility.printHeader("Cancel Appointment");
        
        Patient patient = getPatientInput();
        if (patient == null) return;
        
        List<Appointment> appointments = hospitalSystem.getPatientAppointments(patient);
        if (appointments.isEmpty()) {
            DisplayUtility.printMessage("No appointments found for this patient", false);
            return;
        }
        
        DisplayUtility.printHeader("Patient Appointments");
        String[] headers = {"#", "Date", "Time", "Doctor", "Specialization"};
        List<String[]> rows = new ArrayList<>();
        for (int i = 0; i < appointments.size(); i++) {
            Appointment apt = appointments.get(i);
            rows.add(new String[]{
                String.valueOf(i + 1),
                apt.getDate(),
                apt.getTime(),
                apt.getDoctor().getName(),
                apt.getDoctor().getSpecialization()
            });
        }
        DisplayUtility.printTable(headers, rows);
        
        int index = getIntInput("Enter appointment number to cancel: ") - 1;
        hospitalSystem.cancelAppointment(patient, index);
    }
    
    /**
     * Views a doctor's schedule.
     */
    private static void viewDoctorSchedule() {
        DisplayUtility.printHeader("View Doctor Schedule");
        
        Doctor doctor = getDoctorInput();
        if (doctor == null) return;
        
        doctor.viewSchedule();
    }
    
    /**
     * Views a patient's schedule.
     */
    private static void viewPatientSchedule() {
        DisplayUtility.printHeader("View Patient Schedule");
        
        Patient patient = getPatientInput();
        if (patient == null) return;
        
        patient.viewSchedule();
    }
    
    /**
     * Updates a patient's diagnosis (doctor only).
     */
    private static void updatePatientDiagnosis() {
        DisplayUtility.printHeader("Update Patient Diagnosis");
        
        Doctor doctor = getDoctorInput();
        if (doctor == null) return;
        
        Patient patient = getPatientInput();
        if (patient == null) return;
        
        System.out.print("Enter new diagnosis: ");
        String newDiagnosis = scanner.nextLine().trim();
        
        hospitalSystem.updatePatientDiagnosis(doctor, patient, newDiagnosis);
    }
    
    /**
     * Views medical records for a patient.
     */
    private static void viewMedicalRecords() {
        DisplayUtility.printHeader("View Medical Records");
        
        Patient patient = getPatientInput();
        if (patient == null) return;
        
        List<MedicalRecord> records = patient.getMedicalRecords();
        if (records.isEmpty()) {
            DisplayUtility.printMessage("No medical records found for this patient", false);
            return;
        }
        
        DisplayUtility.printHeader("Medical Records for " + patient.getName());
        for (int i = 0; i < records.size(); i++) {
            System.out.println("\n--- Record " + (i + 1) + " ---");
            records.get(i).displayRecord();
        }
    }
    
    /**
     * Displays all patients.
     */
    private static void displayAllPatients() {
        DisplayUtility.printHeader("All Patients");
        
        List<Patient> patients = hospitalSystem.getPatients();
        if (patients.isEmpty()) {
            DisplayUtility.printMessage("No patients registered", false);
            return;
        }
        
        for (Patient patient : patients) {
            patient.displayInfo();
        }
    }
    
    /**
     * Displays all doctors.
     */
    private static void displayAllDoctors() {
        DisplayUtility.printHeader("All Doctors");
        
        List<Doctor> doctors = hospitalSystem.getDoctors();
        if (doctors.isEmpty()) {
            DisplayUtility.printMessage("No doctors registered", false);
            return;
        }
        
        for (Doctor doctor : doctors) {
            doctor.displayInfo();
        }
    }
    
    /**
     * Displays all staff members.
     */
    private static void displayAllStaff() {
        DisplayUtility.printHeader("All Staff Members");
        
        List<Staff> staff = hospitalSystem.getStaffMembers();
        if (staff.isEmpty()) {
            DisplayUtility.printMessage("No staff members registered", false);
            return;
        }
        
        for (Staff s : staff) {
            s.displayInfo();
        }
    }
    
    /**
     * Gets patient input from user.
     */
    private static Patient getPatientInput() {
        List<Patient> patients = hospitalSystem.getPatients();
        if (patients.isEmpty()) {
            DisplayUtility.printMessage("No patients registered", false);
            return null;
        }
        
        System.out.println("\nAvailable Patients:");
        for (int i = 0; i < patients.size(); i++) {
            System.out.println((i + 1) + ". " + patients.get(i).getName());
        }
        
        int index = getIntInput("Select patient number: ") - 1;
        if (index < 0 || index >= patients.size()) {
            DisplayUtility.printMessage("Invalid patient selection", false);
            return null;
        }
        
        return patients.get(index);
    }
    
    /**
     * Gets doctor input from user.
     */
    private static Doctor getDoctorInput() {
        List<Doctor> doctors = hospitalSystem.getDoctors();
        if (doctors.isEmpty()) {
            DisplayUtility.printMessage("No doctors registered", false);
            return null;
        }
        
        System.out.println("\nAvailable Doctors:");
        for (int i = 0; i < doctors.size(); i++) {
            Doctor doc = doctors.get(i);
            System.out.println((i + 1) + ". " + doc.getName() + 
                             " (" + doc.getSpecialization() + 
                             ") - " + (doc.isAvailable() ? "Available" : "Not Available"));
        }
        
        int index = getIntInput("Select doctor number: ") - 1;
        if (index < 0 || index >= doctors.size()) {
            DisplayUtility.printMessage("Invalid doctor selection", false);
            return null;
        }
        
        return doctors.get(index);
    }
    
    /**
     * Gets staff input from user.
     */
    private static Staff getStaffInput() {
        List<Staff> staff = hospitalSystem.getStaffMembers();
        if (staff.isEmpty()) {
            DisplayUtility.printMessage("No staff members registered", false);
            return null;
        }
        
        System.out.println("\nAvailable Staff:");
        for (int i = 0; i < staff.size(); i++) {
            System.out.println((i + 1) + ". " + staff.get(i).getName() + 
                             " (" + staff.get(i).getRole() + ")");
        }
        
        int index = getIntInput("Select staff number: ") - 1;
        if (index < 0 || index >= staff.size()) {
            DisplayUtility.printMessage("Invalid staff selection", false);
            return null;
        }
        
        return staff.get(index);
    }
    
    /**
     * Gets integer input from user with error handling.
     */
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
    
    /**
     * Initializes the system with sample data (silently, without messages).
     */
    private static void initializeSampleData() {
        // Create sample staff (silent registration)
        Staff staff1 = new Staff("Michael", 30, "011-39232999", "Nurse", "Emergency Department");
        Staff staff2 = new Staff("Sarah", 28, "012-3456789", "Receptionist", "Front Desk");
        hospitalSystem.registerStaff(staff1, false);
        hospitalSystem.registerStaff(staff2, false);
        
        // Create sample doctors (silent registration)
        Doctor doctor1 = new Doctor("Dr. Lin", 45, "018-9988776", "Cardiologist", true);
        Doctor doctor2 = new Doctor("Dr. Smith", 40, "019-8877665", "Pediatrician", true);
        hospitalSystem.registerDoctor(staff1, doctor1, false);
        hospitalSystem.registerDoctor(staff1, doctor2, false);
        
        // Create sample patients (silent registration)
        Patient patient1 = new Patient("Alice", 25, "012-3456789", "Asthma", "Breathing issue");
        Patient patient2 = new Patient("Bob", 35, "013-4567890", "Diabetes", "Type 2 Diabetes");
        hospitalSystem.registerPatient(staff1, patient1, false);
        hospitalSystem.registerPatient(staff2, patient2, false);
        
        // Create sample appointments (silent booking)
        hospitalSystem.bookAppointmentSilent(patient1, doctor1, "2024-01-15", "10:00 AM");
        hospitalSystem.bookAppointmentSilent(patient2, doctor2, "2024-01-16", "2:00 PM");
        
        // Create sample medical records (silent addition)
        InpatientRecord record1 = new InpatientRecord("Heart condition", "Medication and rest", 101, 5);
        OutpatientRecord record2 = new OutpatientRecord("Routine checkup", "Follow-up in 3 months", "2024-01-10");
        hospitalSystem.addMedicalRecord(patient1, record1, false);
        hospitalSystem.addMedicalRecord(patient2, record2, false);
    }
}
