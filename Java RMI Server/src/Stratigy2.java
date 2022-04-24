//import com.sun.scenario.animation.shared.ClipInterpolator;
//import com.sun.xml.internal.fastinfoset.tools.FI_DOM_Or_XML_DOM_SAX_SAXEvent;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Alaa Alkheder
 * Email:alaa-alkheder@outlook.com
 * Github:alaa-alkheder
 */

public class Stratigy2 extends UnicastRemoteObject implements DriveInterface, Runnable {

    public String jsonExtension = ".json";

    private static final long serialVersionUID = 1L;
    static Map<String, DriveInterface> userClient = new HashMap<String, DriveInterface>();
    private User user;
    private String myName;

    /**
     * default constrictor
     *
     * @throws RemoteException
     */
    protected Stratigy2() throws RemoteException {

    }

    /**
     * check Client Credentials method
     *
     * @param chatinterface
     * @param clientname
     * @param password
     * @return true | false
     * @throws RemoteException this function responsible for check User name and password
     *                         1-if the user name and password are correct define the interface , save client data and return true.
     *                         2-return false otherwise
     */
    public boolean checkClientCredintials(DriveInterface chatinterface, String clientname, String password) throws RemoteException {
        if (StartServer.CheckUserPassword(clientname, password)) {
            userClient.put(clientname, chatinterface);
            //send massage to test the connection =>for programmer delete in product version
//            chatinterface.sendMessageToClient(0, "Test Connection ");
            return true;//if you login done
        }
        return false;//if happen any problem
    }

    public void broadcastMessage(String clientname, String message) throws RemoteException {
    }

    @Override
    public void sendMessageToClient(int id, String message) throws RemoteException {
    }

    /**
     * this function for create new account
     *
     * @param user
     * @return
     * @throws RemoteException
     */
    @Override
    public Boolean registerUser(DriveInterface chatinterface, User user) throws RemoteException {
        //check the user if exits
        if (StartServer.SearchUserName(user.getUniqueName()))
            return false;

        userClient.put(user.getUniqueName(), chatinterface);
        //send massage to test the connection =>for programmer delete in product version
        chatinterface.sendMessageToClient(0, "Test Connection ");

        //Save user object in the DB
        StartServer.userProfile.put(user.getUniqueName(), user);
        StartServer.user.put(user.getUniqueName(), user.getPassword());
        StartServer.saveUserObject();
        return true;
    }


    DriveInterface temp = null;

    @Override
    public DriveInterface getClintRemote(String ClientName) throws RemoteException {
        System.out.println("getClintRemote " + userClient.size());
        userClient.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());

//                if (entry.getKey().equals(ClientName) ){
//                    System.out.println(entry.getValue());
//                 temp=(DriveInterface)   entry.getValue();
//                }

        });
        System.out.println(temp);
        return temp;
    }

    @Override
    public boolean checkFileIfExist(String fileName) throws RemoteException {
        return false;
    }

    @Override
    public DriveInterface getClientDIViaFileName(String FileName) throws RemoteException {
        temp = null;
        userClient.entrySet().forEach(entry -> {
            try {
                if (entry.getValue().checkFileIfExist(FileName)) {
                    temp = entry.getValue();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        return temp;
    }

    @Override
    public String sendFileToME(String fileName) throws RemoteException {
        return null;
    }

    @Override
    public String getDriveInterfaceName() throws RemoteException {
        return null;
    }

    @Override
    public void run() {

    }
}
