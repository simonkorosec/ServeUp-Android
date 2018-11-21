package serve.serveup.dataholder;

import com.google.firebase.auth.FirebaseUser;
import java.io.Serializable;

public class UserInfo implements Serializable {

    private String email;
    private String displayName;
    private String uID;

    public UserInfo(FirebaseUser user) {
        this.email = user.getEmail();
        this.displayName = user.getDisplayName();
        this.uID = user.getUid();
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getuID() {
        return uID;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }
}
