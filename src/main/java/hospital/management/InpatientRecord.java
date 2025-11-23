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
        System.out.println("Diagnosis: " + getDiagnosis());
        System.out.println("Treatment Plan: " + getTreatmentPlan());
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Duration of Stay: " + durationOfStay);
    }
}