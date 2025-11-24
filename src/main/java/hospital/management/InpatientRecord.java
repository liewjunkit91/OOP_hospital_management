package hospital.management;

public class InpatientRecord extends MedicalRecord{
    private int roomNumber;
    private int durationOfStay;

    //Constructor
    public InpatientRecord(String d, String tp, int r, int ds){
        super(d, tp);
        roomNumber = r;
        durationOfStay = ds;
    }

    //Getters
    public int getRoomNumber(){
        return roomNumber;
    }

    public int getDurationOfStay(){
        return durationOfStay;
    }

    //Setters
    public void setRoomNumber(int r){
        roomNumber = r;
    }

    public void setDurationOfStay(int ds){
        durationOfStay = ds;
    }

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