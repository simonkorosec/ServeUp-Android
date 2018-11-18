package serve.serveup.utils;

import android.content.Context;
import android.widget.Toast;
import java.util.regex.Pattern;


/**
 * author: urban_jagodic
 * Class for validating input.
 * Tool class with static methods for
 * easier validation checks.
 */
public class Utils {

    public static boolean isNullOrEmpty(String input) {
        return input == null || input.trim().length() == 0;
    }


    // check validity of email address
    public static boolean isEmailValid(String email){
        if(!isNullOrEmpty(email)) {
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";
            Pattern pat = Pattern.compile(emailRegex);
            return pat.matcher(email).matches();
        }
        return false;
    }

    //check password validity
    // must contain at least 6 chars, one upper-case letter and a number
    // (?=.*\W) for non-word char
    public static boolean isPasswordValid(String password) {
        if(!isNullOrEmpty(password)) {
            String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$";
            return isInRegex(password, passwordRegex);
        }
        return false;
    }

    public static boolean isInRegex(String input, String regex) {
        Pattern pat = Pattern.compile(regex);
        return pat.matcher(input).matches();
    }


    public static void showToast(Context myContext, String message) {
        Toast.makeText(myContext, message, Toast.LENGTH_SHORT).show();
    }
}
