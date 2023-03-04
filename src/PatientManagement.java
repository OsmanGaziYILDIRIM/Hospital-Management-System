import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PatientManagement {
    ArrayList<Patient> patientList = new ArrayList<Patient>();
    ArrayList<Admission> admissionList = new ArrayList<Admission>();
    FileWriter fileWriter;

    {
        try {
            fileWriter = new FileWriter("output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void addList() {
        ReadFromFile readFromFile = new ReadFromFile();
        String[] patients = readFromFile.readFile("patient.txt");
        for (String line : patients) {
            Patient patient = new Patient();
            String[] mervi = line.split("\t");
            patient.setId(mervi[0]);
            String[] names = mervi[1].split(" ");
            patient.setName(names[0]);
            patient.setSurname(names[1]);
            patient.setPhoneNumber(mervi[2]);
            patient.setAdress(mervi[3]);
            patientList.add(patient);
        }
    }

    void addPatient(String[] newPatient) throws IOException {
        for (Patient member : patientList) {
            if (member.getId().equals(newPatient[1])) {
                System.out.println("Patient already added");
                break;
            }
        }
        Patient patient = new Patient();
        patient.setId(newPatient[1]);
        patient.setName(newPatient[2]);
        patient.setSurname(newPatient[3]);
        patient.setPhoneNumber(newPatient[4]);
        String count = "Address: ";
        int count2 = 0;
        for (String element : newPatient) {
            if (count2 > 4) {
                count += element + " ";
            }
            count2++;
        }
        patient.setAdress(count);
        patientList.add(patient);
        fileWriter.write("Patient " + patient.getId() + " " + patient.getName() + " added\n");
    }

    void removePatient(String id) throws IOException {
        int count = 0;
        int count2 = 0;
        for (Patient patient : patientList) {
            if (id.equals(patient.getId())) {
                break;
            }
            count++;
        }
        for (Patient patient : patientList) {
            if (count == count2) {
                fileWriter.write("Patient " + patient.getId() + " " + patient.getName() + " removed\n");
            }
            count2++;
        }
        patientList.remove(count);
    }

    void addAdmission() {
        ReadFromFile readFromFile = new ReadFromFile();
        String[] admissionFile = readFromFile.readFile("admission.txt");
        for (String line : admissionFile) {
            String[] row = line.split("\t");
            if (row[0].equals("Outpatient") || row[0].equals("Inpatient")) {

            } else {
                Admission admission = new Admission();
                admission.setAdmissionId(row[0]);
                admissionList.add(admission);
            }
        }
        for (Admission admission : admissionList) {
            for (String line : admissionFile) {
                String[] row = line.split("\t");
                if (row[0].equals(admission.getAdmissionId())) {
                    String count3 = "";
                    String count2 = "";
                    int count4 = 0;
                    int id = Integer.parseInt(admission.getAdmissionId().trim());
                    int cost1 = 0;
                    int cost2 = 0;
                    for (String line1 : admissionFile) {
                        String[] row1 = line1.split("\t");
                        if (row1[0].equals("Outpatient") || row1[0].equals("Inpatient")) {
                        } else {
                            count4++;
                        }

                        if (id == count4) {
                            if (row1[0].equals("Outpatient")) {
                                cost2 = +15;
                                String[] row2 = row1[1].split(" ");
                                for (int count = 0; count < row2.length; count++) {
                                    if (row2[count].equals("doctorvisit")) {
                                        cost2 += 15;
                                        count3 += "doctorvisit ";
                                    } else if (row2[count].equals("imaging")) {
                                        cost2 += 10;
                                        count3 += "imaging ";
                                    } else if (row2[count].equals("tests")) {
                                        cost2 += 7;
                                        count3 += "tests ";
                                    } else if (row2[count].equals("measurements")) {
                                        cost2 += 5;
                                        count3 += "measurements ";
                                    }
                                }

                            } else if (row1[0].equals("Inpatient")) {
                                cost1 += 10;
                                String[] row2 = row1[1].split(" ");
                                for (int count = 0; count < row2.length; count++) {
                                    if (row2[count].equals("doctorvisit")) {
                                        cost1 += 15;
                                        count2 += "doctorvisit ";
                                    } else if (row2[count].equals("imaging")) {
                                        cost1 += 10;
                                        count2 += "imaging ";
                                    } else if (row2[count].equals("tests")) {
                                        cost1 += 7;
                                        count2 += "tests ";
                                    } else if (row2[count].equals("measurements")) {
                                        cost1 += 5;
                                        count2 += "measurements ";
                                    }
                                }

                            }
                        }
                    }
                    admission.setInpatientType(count2);
                    admission.setInpatientCost(cost1);
                    admission.setOutpatientType(count3);
                    admission.setOutpatientCost(cost2);
                }
            }
        }
    }

    void createAdmission(String[] row) throws IOException {
        Admission admission = new Admission();
        admission.setAdmissionId(row[1]);
        fileWriter.write("Admission " + admission.getAdmissionId() + " created\n");
        admissionList.add(admission);
    }

    void addExemination(String[] row) throws IOException {
        String count2 = "";
        String count3 = "";
        for (Admission admission : admissionList) {
            if (admission.getAdmissionId().equals(row[1])) {
                if (row[2].equals("Inpatient")) {
                    int cost1 = 10;
                    for (int count = 3; count < row.length; count++) {
                        if (row[count].equals("doctorvisit")) {
                            cost1 += 15;
                            count2 += "doctorvisit ";
                        } else if (row[count].equals("imaging")) {
                            cost1 += 10;
                            count2 += "imaging ";
                        } else if (row[count].equals("tests")) {
                            cost1 += 7;
                            count2 += "tests ";
                        } else if (row[count].equals("measurements")) {
                            cost1 += 5;
                            count2 += "measurements ";
                        }
                    }
                    fileWriter.write("Inpatient exemination added to admission" + admission.getAdmissionId() + "\n");
                    admission.setInpatientType(count2);
                    admission.setInpatientCost(cost1);
                } else if (row[2].equals("Outpatient")) {
                    int cost2 = 15;
                    for (int count = 3; count < row.length; count++) {
                        if (row[count].equals("doctorvisit")) {
                            cost2 += 15;
                            count3 += "doctorvisit ";
                        } else if (row[count].equals("imaging")) {
                            cost2 += 10;
                            count3 += "imaging ";
                        } else if (row[count].equals("tests")) {
                            cost2 += 7;
                            count3 += "tests ";
                        } else if (row[count].equals("measurements")) {
                            cost2 += 5;
                            count3 += "measurements ";
                        }
                    }
                    fileWriter.write("Outpatient exemination added to admission" + admission.getAdmissionId() + "\n");
                    admission.setOutpatientType(count3);
                    admission.setOutpatientCost(cost2);
                }
            }
        }
    }

    void totalCost(String[] row) throws IOException {

        for (Admission admission : admissionList) {
            if (admission.getAdmissionId().equals(row[1])) {
                fileWriter.write("TotalCost for admission " + admission.getAdmissionId() + "\n");

                if (admission.getInpatientType().length() > 5) {
                    fileWriter.write("\tInpatient " + admission.getInpatientType() + admission.getInpatientCost() + "$\n");
                }
                if (admission.getOutpatientType().length() > 5) {
                    fileWriter.write("\tOutpatient " + admission.getOutpatientType() + admission.getOutpatientCost() + "$\n");
                }
                int cost1 = admission.getInpatientCost();
                int cost2 = admission.getOutpatientCost();
                int cost = cost1 + cost2;
                fileWriter.write("Total: " + cost + "$\n");
            }
        }
    }

    void listPatients() throws IOException {
        fileWriter.write("Patient List:\n");
        int sayac = 0;
        patientList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        for (Patient patient : patientList) {

            sayac++;
            if (sayac < patientList.size()) {
                fileWriter.write(patient.getId() + "\t" + patient.getName() + " " + patient.getSurname() + "\t" +
                        patient.getPhoneNumber() + "\t" + patient.getAdress() + "\n");
            } else {
                fileWriter.write(patient.getId() + "\t" + patient.getName() + " " + patient.getSurname() + "\t" +
                        patient.getPhoneNumber() + "\t" + patient.getAdress());
            }
        }
        fileWriter.close();
    }
}