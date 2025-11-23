package hospital.management;

/**
 * Represents an appointment in the hospital management system.
 * Contains information about the appointment date, time, patient, and doctor.
 */
public class Appointment {
    private String date;
    private String time;
    private Patient patient;
    private Doctor doctor;

    /**
     * Constructor to create an Appointment object.
     *
     * @param date   The date of the appointment
     * @param time   The time of the appointment
     * @param patient The patient for the appointment
     * @param doctor  The doctor for the appointment
     */
    public Appointment(String date, String time, Patient patient, Doctor doctor) {
        this.date = date;
        this.time = time;
        this.patient = patient;
        this.doctor = doctor;
    }

    /**
     * Gets the date of the appointment.
     *
     * @return The appointment date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the appointment.
     *
     * @param date The date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the time of the appointment.
     *
     * @return The appointment time
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the time of the appointment.
     *
     * @param time The time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Gets the patient for the appointment.
     *
     * @return The patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Sets the patient for the appointment.
     *
     * @param patient The patient to set
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Gets the doctor for the appointment.
     *
     * @return The doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Sets the doctor for the appointment.
     *
     * @param doctor The doctor to set
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}

