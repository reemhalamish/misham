package halamish.reem.referandum;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Re'em on 5/20/2016.
 */
public class KalpiChooseActivity extends Activity {
    private Poll currentPoll;
    boolean currentPollIsActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentPoll = PollManager.getManager().getCurrentPoll();
        currentPollIsActive = PollManager.getManager().isActiveCurrentPoll();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            setStatusBarColorBasedOnPollActiveAnswered();

        // title
        setContentView(R.layout.activity_kalpi);
        LinearLayout titleBg = (LinearLayout) findViewById(R.id.ll_kalpi_title);
        int color;
        if (currentPollIsActive)
            color = R.color.title_kalpi_activity_choose;
        else
            color = R.color.title_kalpi_activity_answered;
        titleBg.setBackgroundResource(color);

        TextView tvTitle, tvDes;
        tvTitle = (TextView) findViewById(R.id.tv_kalpi_title);
        tvTitle.setText(currentPoll.getTitle());
        tvDes = (TextView) findViewById(R.id.tv_kalpi_description);
        tvDes.setText(currentPoll.getDescription());


        

    }




    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setStatusBarColorBasedOnPollActiveAnswered() {
        Window window = getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        int color;
        if (currentPollIsActive)
            color = ContextCompat.getColor(this, R.color.statusbar_kalpi_activity_choose);
        else
            color = ContextCompat.getColor(this, R.color.statusbar_kalpi_activity_answered);
        window.setStatusBarColor(color);
    }
}
