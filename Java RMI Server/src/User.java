import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Alaa Alkheder
 * Email:alaa-alkheder@outlook.com
 * Github:alaa-alkheder
 */
public class User implements Serializable {


    private static final long serialVersionUID = 1L;

    private String FullName;
    private String uniqueName;
    private String password;

    public User(String fullName, String uniqueName, String password) {
        FullName = fullName;
        this.uniqueName = uniqueName;
        this.password = password;
    }

    public User() {

    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
