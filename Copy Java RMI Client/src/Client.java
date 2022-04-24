
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Base64;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 * User: Alaa Alkheder
 * Email:alaa-alkheder@outlook.com
 * Github:alaa-alkheder
 */
public class Client extends UnicastRemoteObject implements DriveInterface, Runnable {
    private static final long serialVersionUID = 1L;
    private static final String FILE_DIR = "Files\\";
    private static final String UNIQUE_NAME = "soso";
    private static final String PASSWORD = "1234";

    static DriveInterface server;
    static Client client;

    /**
     * Constrictor
     *
     * @param chatinterface
     * @throws RemoteException
     */
    protected Client(DriveInterface chatinterface) throws RemoteException {
        server = chatinterface;
        client = this;
    }


    @Override
    public boolean checkFileIfExist(String fileName) throws RemoteException {
        return StartClient.fileName.contains(fileName);
    }

    @Override
    public String getDriveInterfaceName() throws RemoteException {
        return UNIQUE_NAME;
    }


    @Override
    public void sendMessageToClient(int id, String message) throws RemoteException {
        switch (id) {
            //test Connection
            case 0: {
                System.out.println(message);
                break;
            }
        }
    }


    @Override
    public String sendFileToME(String fileName) throws RemoteException {
        File file = new File(FILE_DIR + fileName);

        return encodeFileToBase64(file);
    }


    public void run() {
        while (true) {
            System.out.println("welcome in client A");
            System.out.println("Case 1 is : register new user");

            Scanner in = new Scanner(System.in);
            int x = in.nextInt();
            try {
                switch (x) {
                    case 1: {
                        System.out.println("//////");
                        server.registerUser(client, new User(UNIQUE_NAME, UNIQUE_NAME, PASSWORD));
                        break;
                    }
                    case 2: {
                        System.out.println(server.checkClientCredintials(client, UNIQUE_NAME, PASSWORD));
                        break;
                    }
                    case 3: {
                        server.getClintRemote("b").sendMessageToClient(0, "sssssssssss");
                        break;
                    }
                    case 4: {
                        String fileName = "Karoliny Jaty.jpg";
                        DriveInterface di = server.getClientDIViaFileName(fileName);
                        System.out.println("We should download " + fileName + " file from client : " + di.getDriveInterfaceName());
                        String string=di.sendFileToME(fileName);
                        decodeFile(string,"f.jpg");

                        break;
                    }

                }
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    private static String encodeFileToBase64(File file) {
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            throw new IllegalStateException("could not read file " + file, e);
        }
    }


    public void decode2file(String path,String fileName,String fileType) throws IOException {
        File file = new File(path);
        BufferedReader br = null;
        try {
            String strline,l = "";
            FileInputStream fstream = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(fstream));
            while((strline=br.readLine())!=null)
                l+=strline;
            byte b[]= new byte[l.length()/2];
            for (int i = 0; i < l.length(); i+=2) {
                b[i/2]=(Integer.decode("0x"+l.charAt(i)+l.charAt(i+1))).byteValue();
            }
            FileOutputStream fOut = new FileOutputStream(fileName+fileType);
            fOut.write(b);
            fOut.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void decodeFile(String encodedfilecontent, String decodedfile)
            throws IOException {
        File f=new File(encodedfilecontent);
        f.createNewFile();
        Path inPath = Paths.get(encodedfilecontent);
        Path outPath = Paths.get(decodedfile);
        try (InputStream in = Base64.getDecoder().wrap(Files.newInputStream(inPath))) {
            Files.copy(in, outPath);
        }
    }


    /**
     * Server function
     */


    public boolean checkClientCredintials(DriveInterface chatinterface, String clientname, String password) throws RemoteException {
        return true;
    }

    @Override
    public DriveInterface getClientDIViaFileName(String FileName) throws RemoteException {
        return null;
    }


    @Override
    public Boolean testUserName(String user) throws RemoteException {
        return null;
    }

    @Override
    public Boolean forgetPassword(User user) throws RemoteException {
        return null;
    }

    @Override
    public Boolean ChangePassword(User user) throws RemoteException {
        return null;
    }

    @Override
    public String downloadFileInfo(String fileName, String me) throws RemoteException {
        return "";
    }


    public void broadcastMessage(String clientname, String message) throws RemoteException {
    }


    @Override
    public boolean shareFile(String fileName, LinkedList<String> name, String me) throws RemoteException {
        return false;
    }


    @Override
    public Boolean registerUser(DriveInterface ci, User user) throws RemoteException {

        return false;
    }

    @Override
    public DriveInterface getClintRemote(String ClientName) throws RemoteException {
        return null;
    }


}


