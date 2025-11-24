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
        String content = "Name: " + getName() + "\n" +
                        "Age: " + getAge() + "\n" +
                        "Contact: " + getContactDetails() + "\n" +
                        "Specialization: " + specialization + "\n" +
                        "Available: " + (availability ? "Yes" : "No");
        DisplayUtility.printBox("Doctor Information", content);
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
        DisplayUtility.printHeader("Schedule for Doctor: " + getName());
        if (appointments.isEmpty()) {
            DisplayUtility.printMessage("No appointments scheduled", false);
        } else {
            String[] headers = {"#", "Date", "Time", "Patient", "Diagnosis"};
            List<String[]> rows = new ArrayList<>();
            for (int i = 0; i < appointments.size(); i++) {
                Appointment apt = appointments.get(i);
                rows.add(new String[]{
                    String.valueOf(i + 1),
                    apt.getDate(),
                    apt.getTime(),
                    apt.getPatient().getName(),
                    apt.getPatient().getDiagnosis()
                });
            }
            DisplayUtility.printTable(headers, rows);
        }
    }
    
    /**
     * Gets the list of appointments for the doctor.
     *
     * @return List of appointments
     */
    public List<Appointment> getAppointments() {
        return appointments;
    }
    
    /**
     * Updates the diagnosis of a patient.
     * Validates that the doctor is available before updating.
     *
     * @param patient The patient whose diagnosis is being updated
     * @param newDiagnosis The new diagnosis
     * @return true if update successful, false otherwise
     */
    public boolean updatePatientDiagnosis(Patient patient, String newDiagnosis) {
        if (patient == null) {
            System.out.println("Error: Patient cannot be null");
            return false;
        }
        
        if (newDiagnosis == null || newDiagnosis.trim().isEmpty()) {
            System.out.println("Error: Diagnosis cannot be empty");
            return false;
        }
        
        if (!availability) {
            System.out.println("Error: Doctor " + getName() + " is not available");
            return false;
        }
        
        patient.setDiagnosis(newDiagnosis);
        System.out.println("Diagnosis updated for patient " + patient.getName() + 
                          " by Dr. " + getName());
        System.out.println("New diagnosis: " + newDiagnosis);
        return true;
    }
}
