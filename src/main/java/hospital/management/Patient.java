package hospital.management;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a patient in the hospital management system.
 * Inherits from Person and includes patient-specific attributes
 * such as medical history and diagnosis.
 * Implements Schedulable interface to manage appointments.
 */
public class Patient extends Person implements Schedulable {
    private String medicalHistory;
    private String diagnosis;
    private List<Appointment> appointments;

    /**
     * Constructor to create a Patient object.
     *
     * @param name           The name of the patient
     * @param age            The age of the patient
     * @param contactDetails The contact details of the patient
     * @param medicalHistory The medical history of the patient
     * @param diagnosis      The current diagnosis of the patient
     */
    public Patient(String name, int age, String contactDetails, String medicalHistory, String diagnosis) {
        super(name, age, contactDetails);
        this.medicalHistory = medicalHistory;
        this.diagnosis = diagnosis;
        this.appointments = new ArrayList<>();
    }

    /**
     * Gets the medical history of the patient.
     *
     * @return The medical history
     */
    public String getMedicalHistory() {
        return medicalHistory;
    }

    /**
     * Sets the medical history of the patient.
     *
     * @param medicalHistory The medical history to set
     */
    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    /**
     * Gets the diagnosis of the patient.
     *
     * @return The diagnosis
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * Sets the diagnosis of the patient.
     *
     * @param diagnosis The diagnosis to set
     */
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    /**
     * Displays the patient information including inherited Person details.
     */
    @Override
    public void displayInfo() {
        System.out.println("---Patient Information---");
        super.displayInfo();
        System.out.println("Medical History: " + medicalHistory);
        System.out.println("Diagnosis: " + diagnosis);
    }

    /**
     * Schedules an appointment for the patient.
     *
     * @param appointment The appointment to schedule
     */
    @Override
    public void scheduleAppointment(Appointment appointment) {
        if (appointment != null) {
            appointments.add(appointment);
            System.out.println("Appointment scheduled for patient: " + getName());
            System.out.println("Date: " + appointment.getDate() + ", Time: " + appointment.getTime());
        } else {
            System.out.println("Cannot schedule: Invalid appointment");
        }
    }

    /**
     * Cancels an appointment for the patient.
     *
     * @param appointment The appointment to cancel
     */
    @Override
    public void cancelAppointment(Appointment appointment) {
        if (appointments.remove(appointment)) {
            System.out.println("Appointment cancelled for patient: " + getName());
        } else {
            System.out.println("Appointment not found for patient: " + getName());
        }
    }

    /**
     * Views the schedule of appointments for the patient.
     */
    @Override
    public void viewSchedule() {
        System.out.println("=== Schedule for Patient: " + getName() + " ===");
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            for (int i = 0; i < appointments.size(); i++) {
                Appointment apt = appointments.get(i);
                System.out.println((i + 1) + ". Date: " + apt.getDate() + 
                                 ", Time: " + apt.getTime() + 
                                 ", Doctor: " + apt.getDoctor().getName());
            }
        }
    }
}
