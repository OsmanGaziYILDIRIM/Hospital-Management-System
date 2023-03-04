import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ReadFromFile readFromFile=new ReadFromFile();
        String[] inputFile=readFromFile.readFile(args[0]);
        HospitalManagement hospitalManagement=new HospitalManagement();
        hospitalManagement.Manage(inputFile);
    }
}