package hospital.management;

public class OutpatientRecord extends MedicalRecord{
    private String visitDate;

    public OutpatientRecord(String d, String tp, String vd){
        super(d, tp);
        visitDate = vd;
    }

    //Getter
    public String getVisitDate(){
        return visitDate;
    }

    //Setter
    public void setVisitDate(String vd){
        visitDate = vd;
    }

    @Override
    public void displayRecord(){
        System.out.println("Diagnosis: " + getDiagnosis());
        System.out.println("Treatment Plan: " + getTreatmentPlan());
        System.out.println("Visit Date: " + visitDate);
    }
}