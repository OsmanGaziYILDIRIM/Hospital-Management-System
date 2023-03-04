import java.io.IOException;

public class HospitalManagement {
    void Manage(String[] inputFile) throws IOException {
        PatientManagement patientManagement = new PatientManagement();
        patientManagement.addList();
        patientManagement.addAdmission();
        for (String line : inputFile) {
            String[] row = line.split(" ");

            switch (row[0]) {
                case "AddPatient":
                    patientManagement.addPatient(row);
                    break;
                case "RemovePatient":
                    patientManagement.removePatient(row[1]);
                    break;
                case "CreateAdmission":
                    patientManagement.createAdmission(row);
                    break;
                case "AddExamination":
                    patientManagement.addExemination(row);
                    break;
                case "TotalCost":
                    patientManagement.totalCost(row);
                    break;
                case "ListPatients":
                    patientManagement.listPatients();
                    break;
            }
        }
    }
}