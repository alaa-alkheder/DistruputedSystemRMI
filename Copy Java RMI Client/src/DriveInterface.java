

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Alaa Alkheder
 * Email:alaa-alkheder@outlook.com
 * Github:alaa-alkheder
 */

public interface DriveInterface extends java.rmi.Remote {

    Boolean registerUser(DriveInterface ci, User user) throws RemoteException;

    DriveInterface getClintRemote(String ClientName) throws RemoteException;

    boolean checkFileIfExist (String fileName) throws RemoteException;

    DriveInterface getClientDIViaFileName(String FileName) throws RemoteException;

    String sendFileToME(String fileName) throws RemoteException;

    String getDriveInterfaceName() throws RemoteException;






    boolean checkClientCredintials(DriveInterface ci, String name, String pass) throws RemoteException;


    void broadcastMessage(String name, String message) throws RemoteException;

    void sendMessageToClient(int id, String message) throws RemoteException;


    boolean shareFile(String fileName, LinkedList<String> name, String me) throws RemoteException;



    Boolean testUserName(String user) throws RemoteException;

    Boolean forgetPassword(User user) throws RemoteException;

    Boolean ChangePassword(User user) throws RemoteException;

    String downloadFileInfo(String fileName, String me) throws RemoteException;

}