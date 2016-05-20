package halamish.reem.referandum;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Re'em on 5/19/2016.
 */
public class PollAdapter extends ArrayAdapter<Poll> {
    private static final int TYPE_CONTENT = 0;
    private static final int TYPE_HEADING_LAST = 1;
    private static final int MAX_TYPE_COUNT = TYPE_HEADING_LAST + 1;
    private static final String TAG = "PollAdapter";
    private static final int MS_TO_SEC = 1000;
    private static final int SEC_TO_MIN = 60;
    private static final int MIN_TO_HOUR = 60;
    private static final int HOUR_TO_DAY = 24;
    private static final int DAY_TO_MONTH = 30;
    List<Poll> activePolls, answeredPolls;
    LayoutInflater inflater;


    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     */
    public PollAdapter(Context context, int resource, List<Poll> currnet, List<Poll> answered, LayoutInflater inflater) {
        super(context, resource);
        this.activePolls = currnet;
        this.answeredPolls = answered;
        this.inflater = inflater;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "position: " + position);
        View view = convertView;
        int neededType = getItemViewType(position);
        if (convertView == null || (int) convertView.getTag(R.id.POLL_ADAPTER_TAG_TYPE) != neededType) {
            view = null;
        }
        if (neededType == TYPE_CONTENT) {
            view = createPollView(parent, position, view);
        } else { // TYPE_HEADING_LAST
            view = createHeadingView(parent, position, view);
        }

        view.setTag(R.id.POLL_ADAPTER_TAG_TYPE, neededType);
        return view;
    }

    private View createHeadingView(ViewGroup parent, int position, View convertView) {
        View view;
        if (convertView == null)
            view = inflater.inflate(R.layout.row_heading, parent, false);
        else
            view = convertView;
        TextView title = (TextView) view.findViewById(R.id.tv_rowheading_title);
        if (position == 0)
            title.setText("הצבעות פתוחות");
        else
            title.setText("הצבעות בהן השתתפתי");

        view.setOnClickListener(null);
        return view;
    }

    private View createPollView(ViewGroup parent, int position, View convertedView) {
        final Poll currentPoll;
        final boolean activeTrueAnsweredFalse = position <= activePolls.size();
        if (activeTrueAnsweredFalse)
            currentPoll = activePolls.get(position - 1); // because first poll-view position is 1 and first poll-item is 0
        else
            currentPoll = answeredPolls.get(position - 2 - activePolls.size());

        View view = convertedView;
        if (view == null)
            view = inflater.inflate(R.layout.row_poll, parent, false);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_row_title);
        tvTitle.setText(currentPoll.getTitle());
        ImageView ivPollButton = (ImageView) view.findViewById(R.id.iv_row_choose);
        if (activeTrueAnsweredFalse) { // active
            ivPollButton.setImageResource(R.drawable.choose);
        } else { // answered
            ivPollButton.setImageResource(R.drawable.answered);
        }
        ivPollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PollManager.getManager().setCurrentPoll(currentPoll);
                PollManager.getManager().setIsActiveCurrentPoll(activeTrueAnsweredFalse);
                getContext().startActivity(new Intent(getContext(), KalpiChooseActivity.class));
            }
        });

        TextView tvCountdown = (TextView) view.findViewById(R.id.tv_row_time);
        String prefix, time;

        long timeNow = System.currentTimeMillis();
        long finishTime = currentPoll.getEndTimeMs();
        long diff = finishTime - timeNow;
        if (diff > 0) {
            prefix = "ההצבעה תיסגר בעוד ";
        } else {
            prefix = "ההצבעה נסגרה לפני ";
            diff = -diff; // now positive
        }
        long minutes, hours, days, weeks, months;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(diff);
        months = c.get(Calendar.MONTH);
        days = c.get(Calendar.DAY_OF_MONTH)-1;
        hours = c.get(Calendar.HOUR) % 24;
        minutes = c.get(Calendar.MINUTE) % 60;
        Log.d(TAG, "diff, months, days, hours, minutes: "+ diff+ ", "+months+", "+ days+", "+ hours+", "+ minutes);

        if (months > 0)
            time = months + " חודשים";
        else if (days > 0)
            time = days + " ימים";
        else if (hours > 0)
            time = hours + " שעות";
        else if (minutes > 0)
            time = minutes + " דקות";
        else
            time = "ברגעים אלו ממש";

        tvCountdown.setText(prefix + time);


        ImageView ivInfo = (ImageView) view.findViewById(R.id.iv_row_info);
        ivInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoDetails = new Intent(getContext(), ItemDesDialog.class);
                gotoDetails.putExtra(ItemDesDialog.ITEM_DESCRIPTION_TAG, currentPoll.getDescription());
                gotoDetails.putExtra(ItemDesDialog.ITEM_TITLE_TAG, currentPoll.getTitle());
                getContext().startActivity(gotoDetails);
            }
        });

        return view;
    }

    @Override
    public int getViewTypeCount() {
        return MAX_TYPE_COUNT; // one for the heading, one for the actual list
    }

    /**
     * {@inheritDoc}
     *
     * @param position
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == activePolls.size()+1)
            return TYPE_HEADING_LAST;
        return TYPE_CONTENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCount() {
        return 2 * TYPE_HEADING_LAST + activePolls.size() + answeredPolls.size();
    }
}
