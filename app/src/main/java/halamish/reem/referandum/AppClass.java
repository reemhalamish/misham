package halamish.reem.referandum;

import android.app.Application;

/**
 * Created by Re'em on 5/19/2016.
 * app context
 */
public class AppClass extends Application {
    public static final String PREF_FIELE_NAME = "ReferendumApp";

    @Override
    public void onCreate() {
        super.onCreate();
        UserManager.init(this);
        StreetNeighbourhoodManager.init(this);
    }

    /**
     * will initate user to be city-worker if is using the gmail address in the db
     * @param userEmail
     */
    public void checkForUpdateUser(String userEmail) {
        // TODO
    }
}
