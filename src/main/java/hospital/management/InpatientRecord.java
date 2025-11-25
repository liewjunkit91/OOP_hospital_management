package hospital.management;

/**
 * Represents an inpatient medical record in the hospital management system.
 * Extends MedicalRecord and includes information specific to inpatient stays
 * such as room number and duration of stay.
 */
public class InpatientRecord extends MedicalRecord{
    private int roomNumber;
    private int durationOfStay;

    /**
     * Constructor to create an InpatientRecord object.
     *
     * @param d  The diagnosis
     * @param tp The treatment plan
     * @param r  The room number
     * @param ds The duration of stay in days
     */
    public InpatientRecord(String d, String tp, int r, int ds){
        super(d, tp);
        roomNumber = r;
        durationOfStay = ds;
    }

    /**
     * Gets the room number.
     *
     * @return The room number
     */
    public int getRoomNumber(){
        return roomNumber;
    }

    /**
     * Gets the duration of stay.
     *
     * @return The duration of stay in days
     */
    public int getDurationOfStay(){
        return durationOfStay;
    }

    /**
     * Sets the room number.
     *
     * @param r The room number to set
     */
    public void setRoomNumber(int r){
        roomNumber = r;
    }

    /**
     * Sets the duration of stay.
     *
     * @param ds The duration of stay in days to set
     */
    public void setDurationOfStay(int ds){
        durationOfStay = ds;
    }

    /**
     * Displays the inpatient medical record information in a formatted box.
     */
    @Override
    public void displayRecord(){
        String content = "Record Type: Inpatient\n" +
                        "Diagnosis: " + getDiagnosis() + "\n" +
                        "Treatment Plan: " + getTreatmentPlan() + "\n" +
                        "Room Number: " + roomNumber + "\n" +
                        "Duration of Stay: " + durationOfStay + " days";
        DisplayUtility.printBox("Inpatient Medical Record", content);
    }
}