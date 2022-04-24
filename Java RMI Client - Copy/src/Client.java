
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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
    static DriveInterface server;
    static Client client;
    static String ClientName;

    protected Client(DriveInterface chatinterface) throws RemoteException {

        server = chatinterface;
        client = this;
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
    public void sendMessageToClient(int id, String message) throws RemoteException {
        switch (id) {
            //test Connection
            case 0: {
                System.out.println(message);
                break;
            }
            //Send new File to User
            case 1: {
                System.out.println(message);
                break;
            }
        }
    }

    public boolean checkClientCredintials(DriveInterface chatinterface, String clientname, String password) throws RemoteException {
        return true;
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
                        server.registerUser(this, new User("b", "b", "b"));
                    }
                    case 2: {
                        System.out.println(server.checkClientCredintials(this, "b", "b"));
                        ;
                        break;
                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

}


