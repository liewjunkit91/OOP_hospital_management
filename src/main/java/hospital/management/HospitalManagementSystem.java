package hospital.management;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Central management class for the Hospital Management System.
 * Manages all entities: patients, doctors, staff, appointments, and medical records.
 */
public class HospitalManagementSystem {
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Staff> staffMembers;
    private List<Appointment> appointments;
    private List<MedicalRecord> medicalRecords;
    
    /**
     * Constructor to initialize the Hospital Management System.
     */
    public HospitalManagementSystem() {
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.staffMembers = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.medicalRecords = new ArrayList<>();
    }
    
    // ========== Registration Methods ==========
    
    /**
     * Registers a new patient. Can only be called by staff.
     * 
     * @param staff The staff member registering the patient
     * @param patient The patient to register
     * @return true if registration successful, false otherwise
     */
    public boolean registerPatient(Staff staff, Patient patient) {
        return registerPatient(staff, patient, true);
    }
    
    /**
     * Registers a new patient with optional message display. Can only be called by staff.
     * 
     * @param staff The staff member registering the patient
     * @param patient The patient to register
     * @param showMessage Whether to display success/error messages
     * @return true if registration successful, false otherwise
     */
    public boolean registerPatient(Staff staff, Patient patient, boolean showMessage) {
        if (staff == null || patient == null) {
            return false;
        }
        
        if (!staffMembers.contains(staff)) {
            if (showMessage) {
                DisplayUtility.printMessage("Error: Staff member not found in system", false);
            }
            return false;
        }
        
        if (patients.contains(patient)) {
            if (showMessage) {
                DisplayUtility.printMessage("Patient already registered", false);
            }
            return false;
        }
        
        patients.add(patient);
        if (showMessage) {
            DisplayUtility.printMessage("Patient registered successfully by " + staff.getName(), true);
        }
        return true;
    }
    
    /**
     * Registers a new doctor. Can only be called by staff.
     * 
     * @param staff The staff member registering the doctor
     * @param doctor The doctor to register
     * @return true if registration successful, false otherwise
     */
    public boolean registerDoctor(Staff staff, Doctor doctor) {
        return registerDoctor(staff, doctor, true);
    }
    
    /**
     * Registers a new doctor with optional message display. Can only be called by staff.
     * 
     * @param staff The staff member registering the doctor
     * @param doctor The doctor to register
     * @param showMessage Whether to display success/error messages
     * @return true if registration successful, false otherwise
     */
    public boolean registerDoctor(Staff staff, Doctor doctor, boolean showMessage) {
        if (staff == null || doctor == null) {
            return false;
        }
        
        if (!staffMembers.contains(staff)) {
            if (showMessage) {
                DisplayUtility.printMessage("Error: Staff member not found in system", false);
            }
            return false;
        }
        
        if (doctors.contains(doctor)) {
            if (showMessage) {
                DisplayUtility.printMessage("Doctor already registered", false);
            }
            return false;
        }
        
        doctors.add(doctor);
        if (showMessage) {
            DisplayUtility.printMessage("Doctor registered successfully by " + staff.getName(), true);
        }
        return true;
    }
    
    /**
     * Registers a new staff member.
     * 
     * @param staff The staff member to register
     * @return true if registration successful, false otherwise
     */
    public boolean registerStaff(Staff staff) {
        return registerStaff(staff, true);
    }
    
    /**
     * Registers a new staff member with optional message display.
     * 
     * @param staff The staff member to register
     * @param showMessage Whether to display success/error messages
     * @return true if registration successful, false otherwise
     */
    public boolean registerStaff(Staff staff, boolean showMessage) {
        if (staff == null) {
            return false;
        }
        
        if (staffMembers.contains(staff)) {
            if (showMessage) {
                DisplayUtility.printMessage("Staff member already registered", false);
            }
            return false;
        }
        
        staffMembers.add(staff);
        if (showMessage) {
            DisplayUtility.printMessage("Staff member registered successfully", true);
        }
        return true;
    }
    
    // ========== Appointment Methods ==========
    
    /**
     * Books an appointment between a patient and doctor.
     * 
     * @param patient The patient booking the appointment
     * @param doctor The doctor for the appointment
     * @param date The date of the appointment
     * @param time The time of the appointment
     * @return The created appointment, or null if booking failed
     */
    public Appointment bookAppointment(Patient patient, Doctor doctor, String date, String time) {
        if (patient == null || doctor == null) {
            DisplayUtility.printMessage("Error: Patient or doctor cannot be null", false);
            return null;
        }
        
        if (!patients.contains(patient)) {
            DisplayUtility.printMessage("Error: Patient not registered", false);
            return null;
        }
        
        if (!doctors.contains(doctor)) {
            DisplayUtility.printMessage("Error: Doctor not registered", false);
            return null;
        }
        
        if (!doctor.isAvailable()) {
            DisplayUtility.printMessage("Error: Doctor is not available", false);
            return null;
        }
        
        // Check for conflicting appointments
        for (Appointment apt : appointments) {
            if (apt.getDoctor().equals(doctor) && 
                apt.getDate().equals(date) && 
                apt.getTime().equals(time)) {
                DisplayUtility.printMessage("Error: Doctor already has an appointment at this time", false);
                return null;
            }
        }
        
        Appointment appointment = new Appointment(date, time, patient, doctor);
        appointments.add(appointment);
        patient.scheduleAppointment(appointment);
        doctor.scheduleAppointment(appointment);
        
        DisplayUtility.printMessage("Appointment booked successfully", true);
        return appointment;
    }
    
    /**
     * Books an appointment silently (without messages).
     * 
     * @param patient The patient booking the appointment
     * @param doctor The doctor for the appointment
     * @param date The date of the appointment
     * @param time The time of the appointment
     * @return The created appointment, or null if booking failed
     */
    public Appointment bookAppointmentSilent(Patient patient, Doctor doctor, String date, String time) {
        if (patient == null || doctor == null) {
            return null;
        }
        
        if (!patients.contains(patient) || !doctors.contains(doctor)) {
            return null;
        }
        
        if (!doctor.isAvailable()) {
            return null;
        }
        
        // Check for conflicting appointments
        for (Appointment apt : appointments) {
            if (apt.getDoctor().equals(doctor) && 
                apt.getDate().equals(date) && 
                apt.getTime().equals(time)) {
                return null;
            }
        }
        
        Appointment appointment = new Appointment(date, time, patient, doctor);
        appointments.add(appointment);
        // Add directly to lists without printing
        patient.getAppointments().add(appointment);
        doctor.getAppointments().add(appointment);
        
        return appointment;
    }
    
    /**
     * Cancels an appointment.
     * 
     * @param patient The patient canceling the appointment
     * @param appointmentIndex The index of the appointment in patient's list
     * @return true if cancellation successful, false otherwise
     */
    public boolean cancelAppointment(Patient patient, int appointmentIndex) {
        if (patient == null) {
            DisplayUtility.printMessage("Error: Patient cannot be null", false);
            return false;
        }
        
        if (!patients.contains(patient)) {
            DisplayUtility.printMessage("Error: Patient not registered", false);
            return false;
        }
        
        List<Appointment> patientAppointments = getPatientAppointments(patient);
        if (appointmentIndex < 0 || appointmentIndex >= patientAppointments.size()) {
            DisplayUtility.printMessage("Error: Invalid appointment index", false);
            return false;
        }
        
        Appointment appointment = patientAppointments.get(appointmentIndex);
        appointments.remove(appointment);
        patient.cancelAppointment(appointment);
        appointment.getDoctor().cancelAppointment(appointment);
        
        DisplayUtility.printMessage("Appointment cancelled successfully", true);
        return true;
    }
    
    // ========== Diagnosis Update Methods ==========
    
    /**
     * Updates a patient's diagnosis. Can only be called by doctors.
     * 
     * @param doctor The doctor updating the diagnosis
     * @param patient The patient whose diagnosis is being updated
     * @param newDiagnosis The new diagnosis
     * @return true if update successful, false otherwise
     */
    public boolean updatePatientDiagnosis(Doctor doctor, Patient patient, String newDiagnosis) {
        if (doctor == null || patient == null) {
            DisplayUtility.printMessage("Error: Doctor or patient cannot be null", false);
            return false;
        }
        
        if (!doctors.contains(doctor)) {
            DisplayUtility.printMessage("Error: Doctor not registered", false);
            return false;
        }
        
        if (!patients.contains(patient)) {
            DisplayUtility.printMessage("Error: Patient not registered", false);
            return false;
        }
        
        if (!doctor.isAvailable()) {
            DisplayUtility.printMessage("Error: Doctor is not available", false);
            return false;
        }
        
        doctor.updatePatientDiagnosis(patient, newDiagnosis);
        DisplayUtility.printMessage("Diagnosis updated successfully by " + doctor.getName(), true);
        return true;
    }
    
    // ========== Medical Record Methods ==========
    
    /**
     * Adds a medical record to the system and links it to a patient.
     * 
     * @param patient The patient for the medical record
     * @param record The medical record to add
     * @return true if addition successful, false otherwise
     */
    public boolean addMedicalRecord(Patient patient, MedicalRecord record) {
        return addMedicalRecord(patient, record, true);
    }
    
    /**
     * Adds a medical record to the system and links it to a patient with optional message display.
     * 
     * @param patient The patient for the medical record
     * @param record The medical record to add
     * @param showMessage Whether to display success/error messages
     * @return true if addition successful, false otherwise
     */
    public boolean addMedicalRecord(Patient patient, MedicalRecord record, boolean showMessage) {
        if (patient == null || record == null) {
            if (showMessage) {
                DisplayUtility.printMessage("Error: Patient or record cannot be null", false);
            }
            return false;
        }
        
        if (!patients.contains(patient)) {
            if (showMessage) {
                DisplayUtility.printMessage("Error: Patient not registered", false);
            }
            return false;
        }
        
        medicalRecords.add(record);
        patient.addMedicalRecord(record);
        if (showMessage) {
            DisplayUtility.printMessage("Medical record added successfully", true);
        }
        return true;
    }
    
    // ========== Getter Methods ==========
    
    /**
     * Gets all patients.
     * 
     * @return List of all patients
     */
    public List<Patient> getPatients() {
        return new ArrayList<>(patients);
    }
    
    /**
     * Gets all doctors.
     * 
     * @return List of all doctors
     */
    public List<Doctor> getDoctors() {
        return new ArrayList<>(doctors);
    }
    
    /**
     * Gets all staff members.
     * 
     * @return List of all staff members
     */
    public List<Staff> getStaffMembers() {
        return new ArrayList<>(staffMembers);
    }
    
    /**
     * Gets all appointments.
     * 
     * @return List of all appointments
     */
    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointments);
    }
    
    /**
     * Gets all medical records.
     * 
     * @return List of all medical records
     */
    public List<MedicalRecord> getMedicalRecords() {
        return new ArrayList<>(medicalRecords);
    }
    
    /**
     * Gets appointments for a specific patient.
     * 
     * @param patient The patient
     * @return List of appointments for the patient
     */
    public List<Appointment> getPatientAppointments(Patient patient) {
        return appointments.stream()
            .filter(apt -> apt.getPatient().equals(patient))
            .collect(Collectors.toList());
    }
    
    /**
     * Gets appointments for a specific doctor.
     * 
     * @param doctor The doctor
     * @return List of appointments for the doctor
     */
    public List<Appointment> getDoctorAppointments(Doctor doctor) {
        return appointments.stream()
            .filter(apt -> apt.getDoctor().equals(doctor))
            .collect(Collectors.toList());
    }
    
    /**
     * Gets medical records for a specific patient.
     * 
     * @param patient The patient
     * @return List of medical records for the patient
     */
    public List<MedicalRecord> getPatientMedicalRecords(Patient patient) {
        return medicalRecords.stream()
            .filter(record -> patient.getMedicalRecords().contains(record))
            .collect(Collectors.toList());
    }
    
    /**
     * Finds a patient by name.
     * 
     * @param name The name to search for
     * @return The patient if found, null otherwise
     */
    public Patient findPatientByName(String name) {
        return patients.stream()
            .filter(p -> p.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }
    
    /**
     * Finds a doctor by name.
     * 
     * @param name The name to search for
     * @return The doctor if found, null otherwise
     */
    public Doctor findDoctorByName(String name) {
        return doctors.stream()
            .filter(d -> d.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }
    
    /**
     * Finds a staff member by name.
     * 
     * @param name The name to search for
     * @return The staff member if found, null otherwise
     */
    public Staff findStaffByName(String name) {
        return staffMembers.stream()
            .filter(s -> s.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }
    
    /**
     * Gets available doctors.
     * 
     * @return List of available doctors
     */
    public List<Doctor> getAvailableDoctors() {
        return doctors.stream()
            .filter(Doctor::isAvailable)
            .collect(Collectors.toList());
    }
}

