package hospital.management;

/**
 * Represents an outpatient medical record in the hospital management system.
 * Extends MedicalRecord and includes information specific to outpatient visits
 * such as the visit date.
 */
public class OutpatientRecord extends MedicalRecord{
    private String visitDate;

    /**
     * Constructor to create an OutpatientRecord object.
     *
     * @param d  The diagnosis
     * @param tp The treatment plan
     * @param vd The visit date
     */
    public OutpatientRecord(String d, String tp, String vd){
        super(d, tp);
        visitDate = vd;
    }

    /**
     * Gets the visit date.
     *
     * @return The visit date
     */
    public String getVisitDate(){
        return visitDate;
    }

    /**
     * Sets the visit date.
     *
     * @param vd The visit date to set
     */
    public void setVisitDate(String vd){
        visitDate = vd;
    }

    /**
     * Displays the outpatient medical record information in a formatted box.
     */
    @Override
    public void displayRecord(){
        String content = "Record Type: Outpatient\n" +
                        "Diagnosis: " + getDiagnosis() + "\n" +
                        "Treatment Plan: " + getTreatmentPlan() + "\n" +
                        "Visit Date: " + visitDate;
        DisplayUtility.printBox("Outpatient Medical Record", content);
    }
}