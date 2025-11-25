package hospital.management;

/**
 * Abstract base class representing a medical record in the hospital management system.
 * Contains common medical information such as diagnosis and treatment plan.
 * Subclasses must implement the displayRecord() method to provide specific display behavior.
 */
public abstract class MedicalRecord {
    private String diagnosis;
    private String treatmentPlan;

    /**
     * Constructor to create a MedicalRecord object.
     *
     * @param d  The diagnosis
     * @param tp The treatment plan
     */
    public MedicalRecord(String d, String tp){
        diagnosis = d;
        treatmentPlan = tp;
    }

    /**
     * Gets the diagnosis.
     *
     * @return The diagnosis
     */
    public String getDiagnosis(){
        return diagnosis;
    }

    /**
     * Gets the treatment plan.
     *
     * @return The treatment plan
     */
    public String getTreatmentPlan(){
        return treatmentPlan;
    }

    /**
     * Sets the diagnosis.
     *
     * @param d The diagnosis to set
     */
    public void setDiagnosis(String d){
        diagnosis = d;
    }

    /**
     * Sets the treatment plan.
     *
     * @param tp The treatment plan to set
     */
    public void setTreatmentPlan(String tp){
        treatmentPlan = tp;
    }

    /**
     * Displays the medical record information.
     * Must be implemented by subclasses to provide specific display formatting.
     */
    public abstract void displayRecord();
}
