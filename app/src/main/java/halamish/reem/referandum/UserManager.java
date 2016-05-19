package halamish.reem.referandum;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Re'em on 5/19/2016.
 *
 * used to check various things about the user
 */
public class UserManager {
    private static final String IS_NORMAL_UESR = "ref.is_normal_user";
    private static final String USER_EMAIL = "ref.user_email";
    private static final String USER_REGISTERED = "ref.user_registered";
    private static UserManager instance;


    private AppClass mApp;
    private boolean mUserRegistered;
    private boolean mNormalUserTrueCityWorkerFalse;
    private String mUserStreetName;
    private String mUserEmail;


    private UserManager(AppClass app) {
        mApp = app;
        SharedPreferences pref = app.getSharedPreferences(AppClass.PREF_FIELE_NAME, Context.MODE_PRIVATE);
        mUserRegistered = pref.getBoolean(USER_REGISTERED, false);
        if (! mUserRegistered) {
            mUserEmail = null;
            mUserStreetName = null;
        } else {
            mUserEmail = pref.getString(USER_EMAIL, null);
            mNormalUserTrueCityWorkerFalse = pref.getBoolean(IS_NORMAL_UESR, true);
            if (mUserEmail != null && mNormalUserTrueCityWorkerFalse) // maybe got promoted. check
                app.checkForUpdateUser(mUserEmail);
        }




    }

    public static void init(AppClass app) {
        instance = new UserManager(app);
    }

    public static synchronized UserManager getManager() {
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
}
