package hospital.management;

public abstract class MedicalRecord {
    private String diagnosis;
    private String treatmentPlan;

    //Constructor
    public MedicalRecord(String d, String tp){
        diagnosis = d;
        treatmentPlan = tp;
    }

    //Getters
    public String getDiagnosis(){
        return diagnosis;
    }

    public String getTreatmentPlan(){
        return treatmentPlan;
    }

    //Setters
    public void setDiagnosis(String d){
        diagnosis = d;
    }

    public void setTreatmentPlan(String tp){
        treatmentPlan = tp;
    }

    public abstract void displayRecord();
}
