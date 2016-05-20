package halamish.reem.referandum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

/**
 * Created by Re'em on 5/19/2016.
 */
public class SplashActivity extends Activity {
    private static final long SPLASH_DELAY_MS = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        // TODO remove later and un-comment the later
        startActivity(new Intent(this, MainActivity.class));
        finish();
//        createIntentForNextActivity();
    }

    private void createIntentForNextActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goNext;
                if (UserClientManager.getManager().isUserRegistered())
                    goNext = new Intent(SplashActivity.this, MainActivity.class);
                else
                    goNext = new Intent(SplashActivity.this, ChooseStreetActivity.class);
                SplashActivity.this.startActivity(goNext);
                SplashActivity.this.finish();
            }
        }, SPLASH_DELAY_MS);
    }
}
