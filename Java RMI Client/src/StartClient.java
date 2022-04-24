import java.io.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alaa Alkheder
 * Email:alaa-alkheder@outlook.com
 * Github:alaa-alkheder
 */
public class StartClient {
     public static List<String> fileName=new ArrayList<>();

     static void readFileFromDirectory(){
         File file=new File("C:\\Users\\ASUS\\Documents\\GitHub\\DistruputedSystemRMI\\Java RMI Client\\Files");
         fileName= Arrays.asList(file.list());
     }

    static void printFileName(){
         for (String x: fileName
              ) {
             System.out.println(x);
         }
     }


    public static void main(String[] args) throws IOException, NotBoundException {
        readFileFromDirectory();
        printFileName();
        System.out.println(fileName.contains("Karoliny Jaty (2).jpg"));
//        System.exit(2);
        DriveInterface chatinterface = (DriveInterface) Naming.lookup("rmi://localhost/RMIServer");
        new Thread(new Client(chatinterface)).start();
    }

}
