package hospital.management;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a doctor in the hospital management system.
 * Inherits from Person and includes doctor-specific attributes
 * such as specialization and availability.
 * Implements Schedulable interface to manage appointments.
 */
public class Doctor extends Person implements Schedulable {
    private String specialization;
    private boolean availability;
    private List<Appointment> appointments;

    /**
     * Constructor to create a Doctor object.
     *
     * @param name           The name of the doctor
     * @param age            The age of the doctor
     * @param contactDetails The contact details of the doctor
     * @param specialization The specialization of the doctor
     * @param availability   The availability status of the doctor
     */
    public Doctor(String name, int age, String contactDetails, String specialization, boolean availability) {
        super(name, age, contactDetails);
        this.specialization = specialization;
        this.availability = availability;
        this.appointments = new ArrayList<>();
    }

    /**
     * Gets the specialization of the doctor.
     *
     * @return The specialization
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Sets the specialization of the doctor.
     *
     * @param specialization The specialization to set
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    /**
     * Checks if the doctor is available.
     *
     * @return true if available, false otherwise
     */
    public boolean isAvailable() {
        return availability;
    }

    /**
     * Sets the availability status of the doctor.
     *
     * @param availability The availability status to set
     */
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    /**
     * Displays the doctor information including inherited Person details.
     */
    @Override
    public void displayInfo() {
        System.out.println("===== Doctor Information =====");
        super.displayInfo();
        System.out.println("Specialization: " + specialization);
        System.out.println("Available: " + (availability ? "Yes" : "No"));
    }

    /**
     * Schedules an appointment for the doctor.
     * Checks availability before scheduling.
     *
     * @param appointment The appointment to schedule
     */
    @Override
    public void scheduleAppointment(Appointment appointment) {
        if (!availability) {
            System.out.println("Cannot schedule: Doctor " + getName() + " is not available");
            return;
        }
        if (appointment != null) {
            appointments.add(appointment);
            System.out.println("Appointment scheduled for doctor: " + getName());
            System.out.println("Date: " + appointment.getDate() + ", Time: " + appointment.getTime());
        } else {
            System.out.println("Cannot schedule: Invalid appointment");
        }
    }

    /**
     * Cancels an appointment for the doctor.
     *
     * @param appointment The appointment to cancel
     */
    @Override
    public void cancelAppointment(Appointment appointment) {
        if (appointments.remove(appointment)) {
            System.out.println("Appointment cancelled for doctor: " + getName());
        } else {
            System.out.println("Appointment not found for doctor: " + getName());
        }
    }

    /**
     * Views the schedule of appointments for the doctor.
     */
    @Override
    public void viewSchedule() {
        System.out.println("=== Schedule for Doctor: " + getName() + " ===");
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            for (int i = 0; i < appointments.size(); i++) {
                Appointment apt = appointments.get(i);
                System.out.println((i + 1) + ". Date: " + apt.getDate() + 
                                 ", Time: " + apt.getTime() + 
                                 ", Patient: " + apt.getPatient().getName());
            }
        }
    }
}
