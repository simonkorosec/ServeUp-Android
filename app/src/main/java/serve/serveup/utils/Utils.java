package serve.serveup.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

import serve.serveup.R;


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

    public static void logInfo(String text) {
        Log.d("serveup_test", text);
    }


    public static Bitmap parseBitmapFromBase64(@NonNull Context myContext, String base64String) {
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        Bitmap rawBitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return Bitmap.createScaledBitmap(rawBitmap,
                (int) myContext.getResources().getDimension(R.dimen.cardview_restaurant_image_width),
                (int) myContext.getResources().getDimension(R.dimen.cardview_restaurant_image_height),
                false);
    }



    public static ArrayList<String> readFromFile(String fileName, Context context) {
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        ArrayList<String> myStrings = new ArrayList<>();
        try {
            fIn = context.getResources().getAssets().open(fileName, Context.MODE_WORLD_READABLE);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line = "";
            while ((line = input.readLine()) != null) {
                if(line.length() > 0 && !line.contains("NEXT_STRING"))
                    myStrings.add(line);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null) isr.close();
                if (fIn != null) fIn.close();
                if (input != null) input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        return myStrings;
    }


}
