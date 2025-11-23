package hospital.management;

/**
 * Main class to demonstrate the Hospital Management System.
 * Shows inheritance (Person hierarchy) and polymorphism (Schedulable interface).
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hospital Management System ===\n");

        // Demonstrate Inheritance: Person hierarchy
        System.out.println("--- Demonstrating Inheritance ---");
        Person[] persons = new Person[3];

        persons[0] = new Patient("Alice", 25, "012-3456789", "Asthma", "Breathing issue");
        persons[1] = new Doctor("Dr.Lin", 45, "018-9988776", "Cardiologist", true);
        persons[2] = new Staff("Micheal", 30, "011-39232999", "Nurse", "Emergency Department");

        for (int i = 0; i < persons.length; i++) {
            persons[i].displayInfo();
            System.out.println();
        }

        // Demonstrate Polymorphism: Schedulable interface
        System.out.println("\n--- Demonstrating Polymorphism with Schedulable Interface ---\n");

        // Create specific instances
        Patient patient = new Patient("Alice", 25, "012-3456789", "Asthma", "Breathing issue");
        Doctor doctor = new Doctor("Dr.Lin", 45, "018-9988776", "Cardiologist", true);

        // Treat both as Schedulable (polymorphism)
        Schedulable[] schedulables = new Schedulable[2];
        schedulables[0] = patient;
        schedulables[1] = doctor;

        // Create appointments
        Appointment appointment1 = new Appointment("2024-01-15", "10:00 AM", patient, doctor);
        Appointment appointment2 = new Appointment("2024-01-16", "2:00 PM", patient, doctor);

        // Demonstrate polymorphic behavior: same interface, different implementations
        System.out.println("Scheduling appointments using Schedulable interface...\n");
        for (Schedulable schedulable : schedulables) {
            if (schedulable instanceof Patient) {
                schedulable.scheduleAppointment(appointment1);
            } else if (schedulable instanceof Doctor) {
                schedulable.scheduleAppointment(appointment1);
                schedulable.scheduleAppointment(appointment2);
            }
            System.out.println();
        }

        // View schedules polymorphically
        System.out.println("Viewing schedules using Schedulable interface...\n");
        for (Schedulable schedulable : schedulables) {
            schedulable.viewSchedule();
            System.out.println();
        }

        // Demonstrate canceling appointments
        System.out.println("Canceling appointments using Schedulable interface...\n");
        schedulables[0].cancelAppointment(appointment1);
        System.out.println();
    }
}