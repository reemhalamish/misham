package halamish.reem.referandum;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Re'em on 5/19/2016.
 *
 * used to check various things about the user
 */
public class UserClientManager {
    private static final String IS_NORMAL_UESR = "ref.is_normal_user";
    private static final String USER_EMAIL = "ref.user_email";
    private static final String USER_REGISTERED = "ref.user_registered";
    private static final String USER_STREET = "ref.user_street";
    private static UserClientManager instance;


    private SharedPreferences mPref;
    private boolean mUserRegistered;
    private boolean mNormalUserTrueCityWorkerFalse;
    private String mUserStreetName;
    private String mUserEmail;


    private UserClientManager(AppClass app) {
        mPref = app.getSharedPreferences(AppClass.PREF_FIELE_NAME, Context.MODE_PRIVATE);
        mUserRegistered = mPref.getBoolean(USER_REGISTERED, false);
        if (! mUserRegistered) {
            mUserEmail = null;
            mUserStreetName = null;
        } else {
            mUserStreetName = mPref.getString(USER_STREET, null);
            mUserEmail = mPref.getString(USER_EMAIL, null);
            mNormalUserTrueCityWorkerFalse = mPref.getBoolean(IS_NORMAL_UESR, true);
            if (mUserEmail != null && mNormalUserTrueCityWorkerFalse) // maybe got promoted. check
                app.checkForUpdateUser(mUserEmail);
        }




    }

    public static void init(AppClass app) {
        instance = new UserClientManager(app);
    }

    public static synchronized UserClientManager getManager() {
        return instance;
    }

    public boolean isUserRegistered() {
        return mUserRegistered;
    }

    public boolean isNormalUserTrueCityWorkerFalse() {
        return mNormalUserTrueCityWorkerFalse;
    }

    public String getUserStreetName() {
        return mUserStreetName;
    }

    public String getUserEmail() {
        return mUserEmail;
    }

    public void setStreet(String street) {
        this.mUserStreetName = street;
        SharedPreferences.Editor editor = mPref.edit();
        editor.putString(USER_STREET, street);
        editor.apply();
    }
}
