
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by IntelliJ IDEA.
 * User: Alaa Alkheder
 * Email:alaa-alkheder@outlook.com
 * Github:alaa-alkheder
 */
public class StartServer {
    //to save user profile
    static final String userFile = "user.txt";

    static HashMap<String, User> userProfile = new HashMap<String, User>();
    static HashMap<String, String> user = new HashMap<String, String>();
    /**
     * Print User info Name&password
     */
    static private void printUserPassword() {

        System.out.println(user.size());
        user.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });

    }



    /**
     * save User info (Name&password) and save in the HashMap with Encryption
     */
    static private void saveUserPasswordWithEncryption() {
//        File f1 = new File("D:\\recUser.xls");
//        try {
//            WritableWorkbook workbook = Workbook.createWorkbook(f1);
//            WritableSheet writableSheet = workbook.createSheet("temp", 0);
//            AtomicInteger i = new AtomicInteger();
//            user.entrySet().forEach(entry -> {
//                Label name = new Label(0, i.incrementAndGet() - 1, entry.getKey());
//                Label pass = new Label(1, i.get() - 1, entry.getValue());
//                try {
//                    writableSheet.addCell(name);
//                    writableSheet.addCell(pass);
//                } catch (WriteException e) {
//                    e.printStackTrace();
//                }
//            });
//            workbook.write();
//            workbook.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (RowsExceededException e) {
//            e.printStackTrace();
//        } catch (WriteException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Check User info Name&password
     */
    static public Boolean CheckUserPassword(String name, String pass) {
        for (HashMap.Entry<String, String> entry : user.entrySet()) {
            if (entry.getKey().equals(name))
                if (entry.getValue().equals(pass))
                    return true;
        }
        return false;
    }

    /**
     * Search for UserName
     */
    static public Boolean SearchUserName(String name) {
        for (HashMap.Entry<String, String> entry : user.entrySet()) {
            if (entry.getKey().equals(name))
                return true;
        }
        return false;
    }



    static void saveUserObject() {
        try {

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(userFile));
            objectOutputStream.writeObject(userProfile);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] arg) throws RemoteException, MalformedURLException,IOException {

        System.out.println("welcome in Server * __ *");
        System.out.println("chose 1 is : For first strategy ");
        System.out.println("chose 2 is : For second strategy ");
        System.out.println("chose 3 is : For thierd strategy ");

        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        switch (x){
            case 1:{
                try {
                    java.rmi.registry.LocateRegistry.createRegistry(1099);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Naming.rebind("RMIServer", new Server());
                break;
            }
            case 2:{

                break;
            }
        }


    }


}
