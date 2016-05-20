package halamish.reem.referandum;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Re'em on 5/19/2016.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            setStatusBarColor();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createList();

    }

    private void createList() {
        ListView lvMain = (ListView) findViewById(R.id.lv_main_polls);
        PollAdapter aa = new PollAdapter(
                this,
                R.layout.row_poll,
                createDemo(),
                createDemo(),
                getLayoutInflater()
        );
        lvMain.setAdapter(aa);
    }

    private List<Poll> createDemo() {
        List<Poll> retval = new ArrayList<>();
        Poll newbie = new Poll("כותרת ראשונה", "description", new String[]{"opt1", "opt2"}, System.currentTimeMillis() + 3*1000*60);
        retval.add(newbie);
        newbie = new Poll("כותרת כל כך ארוכה שהעולם מתחיל להתפוצץ", "another description", new String[]{"opt3", "opt4"},System.currentTimeMillis() - 5*1000*60*60*24);
        retval.add(newbie);
        return retval;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setStatusBarColor() {
        Window window = getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        int color = ContextCompat.getColor(this, R.color.statusbar_main_activity);
        window.setStatusBarColor(color);
    }
}
