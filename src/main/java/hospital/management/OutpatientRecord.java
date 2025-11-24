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
        String content = "Record Type: Outpatient\n" +
                        "Diagnosis: " + getDiagnosis() + "\n" +
                        "Treatment Plan: " + getTreatmentPlan() + "\n" +
                        "Visit Date: " + visitDate;
        DisplayUtility.printBox("Outpatient Medical Record", content);
    }
}