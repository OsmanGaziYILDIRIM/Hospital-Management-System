public class Admission {
    private String admissionId;
    private int inpatientCost;
    private int outpatientCost;
    private String inpatientType;
    private String outpatientType;

    public String getAdmissionId() {
        return admissionId;
    }

    public int getInpatientCost() {
        return inpatientCost;
    }

    public int getOutpatientCost() {
        return outpatientCost;
    }

    public String getInpatientType() {
        return inpatientType;
    }

    public String getOutpatientType() {
        return outpatientType;
    }


    public void setAdmissionId(String admissionId) {
        this.admissionId = admissionId;
    }

    public void setInpatientCost(int inpatientCost) {
        this.inpatientCost = inpatientCost;
    }

    public void setOutpatientCost(int outpatientCost) {
        this.outpatientCost = outpatientCost;
    }

    public void setInpatientType(String inpatientType) {
        this.inpatientType = inpatientType;
    }

    public void setOutpatientType(String outpatientType) {
        this.outpatientType = outpatientType;
    }

}